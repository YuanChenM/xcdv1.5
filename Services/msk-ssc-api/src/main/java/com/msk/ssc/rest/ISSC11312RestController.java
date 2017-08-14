package com.msk.ssc.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DecimalUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.SSC11312Bean;
import com.msk.ssc.bean.SSC11312Param;
import com.msk.ssc.bean.SSC11312Result;
import com.msk.ssc.logic.SSC11312Logic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务控制器：管理生产商入库差异单详情页面的业务
 * Created by xia_xiaojie on 2016/7/5.
 */
@RestController
public class ISSC11312RestController extends BaseRsController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ISSC11312RestController.class);

    @Autowired
    private SSC11312Logic ssc11312Logic;

    /**
     * 查询生产商入库差异单详情
     * 接口URL：SystemServerManager.SellerSupplyChainManage.getQueryDifferDetails()
     */
    @RequestMapping(value = "/ssc/differ/query/details", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RsResponse<SSC11312Result> queryDifferDetails(@RequestBody RsRequest<SSC11312Param> reqParam) {
        SSC11312Param queryParam = reqParam.getParam();
        boolean paging = queryParam.isPaging();

        SSC11312Result ssc11312Result = new SSC11312Result();
        int count = NumberConst.IntDef.INT_ZERO;
        if (paging) {
            count = ssc11312Logic.getPageCount(queryParam);
            ssc11312Result.setTotalCount(count);
        }

        List<SSC11312Bean> sscDifferDetails = new ArrayList<SSC11312Bean>();
        if (!paging || count > NumberConst.IntDef.INT_ZERO) {
            sscDifferDetails = ssc11312Logic.findPageList(queryParam, SSC11312Bean.class);
        }

        this.calculate(sscDifferDetails);
        this.format(sscDifferDetails);
        ssc11312Result.setSscDifferDetails(sscDifferDetails);

        RsResponse<SSC11312Result> respResult = new RsResponse<SSC11312Result>();
        respResult.setResult(ssc11312Result);
        respResult.setStatus(SystemConst.RsStatus.SUCCESS);
        return respResult;
    }

    /**
     * 计算收发货箱数、重量和金额的合计和差额
     */
    private void calculate(List<SSC11312Bean> sscDifferDetails) {
        int totalSendBoxes = NumberConst.IntDef.INT_ZERO;       //发货总箱数
        int totalReceiveBoxes = NumberConst.IntDef.INT_ZERO;    //收货总箱数
        BigDecimal totalSendWeight = BigDecimal.ZERO;           //发货总重量
        BigDecimal totalReceiveWeight = BigDecimal.ZERO;        //收货总重量
        BigDecimal totalSendAmount = BigDecimal.ZERO;           //发货总金额
        BigDecimal totalReceiveAmount = BigDecimal.ZERO;        //收货总金额

        for (SSC11312Bean ssc11312Bean : sscDifferDetails) {
            BigDecimal sendWeight = ssc11312Bean.getSendWeight();
            BigDecimal receiveWeight = ssc11312Bean.getReceiveWeight();
            BigDecimal sendPrice = ssc11312Bean.getUnitPrice();
            BigDecimal receivePrice = ssc11312Bean.getReceivePrice();
            BigDecimal sendAmount = DecimalUtil.multiply(sendWeight, sendPrice);
            BigDecimal receiveAmount = DecimalUtil.multiply(receiveWeight, receivePrice);

            sendAmount = this.scale2(sendAmount);
            receiveAmount = this.scale2(receiveAmount);
            ssc11312Bean.setSendAmount(sendAmount);
            ssc11312Bean.setReceiveAmount(receiveAmount);

            ssc11312Bean.setWeightDiff(DecimalUtil.subtract(receiveWeight, sendWeight));
            ssc11312Bean.setPriceDiff(DecimalUtil.subtract(receivePrice, sendPrice));
            ssc11312Bean.setAmountDiff(DecimalUtil.subtract(receiveAmount, sendAmount));

            totalSendBoxes += ssc11312Bean.getSendBox();
            totalReceiveBoxes += ssc11312Bean.getReceiveBox();
            totalSendWeight = DecimalUtil.add(totalSendWeight, sendWeight);
            totalReceiveWeight = DecimalUtil.add(totalReceiveWeight, receiveWeight);
            totalSendAmount = DecimalUtil.add(totalSendAmount, sendAmount);
            totalReceiveAmount = DecimalUtil.add(totalReceiveAmount, receiveAmount);
        }

        //将合计值保存在list第一个元素中
        if (!CollectionUtils.isEmpty(sscDifferDetails)) {
            SSC11312Bean ssc11312Bean = sscDifferDetails.get(NumberConst.IntDef.INT_ZERO);
            ssc11312Bean.setTotalWeightDiff(DecimalUtil.subtract(totalReceiveWeight, totalSendWeight));
            ssc11312Bean.setTotalAmountDiff(DecimalUtil.subtract(totalReceiveAmount, totalSendAmount));

            ssc11312Bean.setTotalSendBoxes(totalSendBoxes);
            ssc11312Bean.setTotalReceiveBoxes(totalReceiveBoxes);
            ssc11312Bean.setTotalSendWeight(totalSendWeight);
            ssc11312Bean.setTotalReceiveWeight(totalReceiveWeight);
            ssc11312Bean.setTotalSendAmount(totalSendAmount);
            ssc11312Bean.setTotalReceiveAmount(totalReceiveAmount);
        }
    }

    /**
     * 保留两位小数，四舍五入
     */
    private BigDecimal scale2(BigDecimal bd) {
        if (null == bd) {
            return BigDecimal.ZERO.setScale(NumberConst.IntDef.INT_TWO);
        }
        return bd.setScale(NumberConst.IntDef.INT_TWO, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 整数位每3位加逗号，箱数保留0位小数，重量保留4位小数，金额保留2位小数
     */
    private void format(List<SSC11312Bean> sscDifferDetails) {
        DecimalFormat boxFmt = new DecimalFormat("#,##0");          //箱数格式
        DecimalFormat weightFmt = new DecimalFormat("#,##0.0000");  //重量格式
        DecimalFormat moneyFmt = new DecimalFormat("#,##0.00");     //金额格式

        for (SSC11312Bean ssc11312Bean : sscDifferDetails) {
            ssc11312Bean.setSendBoxStr(boxFmt.format(ssc11312Bean.getSendBox()));
            ssc11312Bean.setReceiveBoxStr(boxFmt.format(ssc11312Bean.getReceiveBox()));
            ssc11312Bean.setSendWeightStr(weightFmt.format(ssc11312Bean.getSendWeight()));
            ssc11312Bean.setReceiveWeightStr(weightFmt.format(ssc11312Bean.getReceiveWeight()));
            ssc11312Bean.setSendPriceStr(moneyFmt.format(ssc11312Bean.getUnitPrice()));
            ssc11312Bean.setReceivePriceStr(moneyFmt.format(ssc11312Bean.getReceivePrice()));
            ssc11312Bean.setSendAmountStr(moneyFmt.format(ssc11312Bean.getSendAmount()));
            ssc11312Bean.setReceiveAmountStr(moneyFmt.format(ssc11312Bean.getReceiveAmount()));

            ssc11312Bean.setWeightDiffStr(weightFmt.format(ssc11312Bean.getWeightDiff()));
            ssc11312Bean.setPriceDiffStr(moneyFmt.format(ssc11312Bean.getPriceDiff()));
            ssc11312Bean.setAmountDiffStr(moneyFmt.format(ssc11312Bean.getAmountDiff()));
        }

        if (!CollectionUtils.isEmpty(sscDifferDetails)) {
            SSC11312Bean ssc11312Bean = sscDifferDetails.get(NumberConst.IntDef.INT_ZERO);
            ssc11312Bean.setTotalWeightDiffStr(weightFmt.format(ssc11312Bean.getTotalWeightDiff()));
            ssc11312Bean.setTotalAmountDiffStr(moneyFmt.format(ssc11312Bean.getTotalAmountDiff()));

            ssc11312Bean.setTotalSendBoxesStr(boxFmt.format(ssc11312Bean.getTotalSendBoxes()));
            ssc11312Bean.setTotalReceiveBoxesStr(boxFmt.format(ssc11312Bean.getTotalReceiveBoxes()));
            ssc11312Bean.setTotalSendWeightStr(weightFmt.format(ssc11312Bean.getTotalSendWeight()));
            ssc11312Bean.setTotalReceiveWeightStr(weightFmt.format(ssc11312Bean.getTotalReceiveWeight()));
            ssc11312Bean.setTotalSendAmountStr(moneyFmt.format(ssc11312Bean.getTotalSendAmount()));
            ssc11312Bean.setTotalReceiveAmountStr(moneyFmt.format(ssc11312Bean.getTotalReceiveAmount()));
        }
    }

}
