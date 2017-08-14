package com.msk.buyers.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.buyers.bean.IBY121319RsBean;
import com.msk.buyers.bean.IBY121319RsParam;
import com.msk.buyers.logic.IBY121320Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.consts.BuyersConst;
import com.msk.core.entity.ByBuyerBasicInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IBY121320RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBY121320RsController.class);

    @Autowired
    private IBY121320Logic iby121320Logic;

    /**
     * 通过买家编码查询买家ID
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/by/buyerId/queryByBuyerCode", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121319RsBean> queryBuyerIdByBuyerCode(@RequestBody RsRequest<IBY121319RsParam> param) {
        RsResponse<IBY121319RsBean> response = new RsResponse<>();
        IBY121319RsBean iby121319RsBean = new IBY121319RsBean();
        String buyerCode = param.getParam().getBuyerCode();
        String subBuyerCode = "";
        String message = "";
        if (!StringUtil.isNullOrEmpty(buyerCode)) {
            String superiorType = buyerCode.substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWO);
            param.getParam().setSuperiorType(superiorType);
            subBuyerCode = changeBuyerCode(param.getParam());
        }
        if (!StringUtil.isNullOrEmpty(subBuyerCode)) {
            List<ByBuyerBasicInfo> basicInfoList = this.iby121320Logic.findBuyerId(param.getParam());
            if (!CollectionUtils.isEmpty(basicInfoList)) {
                if (basicInfoList.size() == NumberConst.IntDef.INT_ONE) {
                    message = "处理成功！";
                    ByBuyerBasicInfo basicInfo = basicInfoList.get(NumberConst.IntDef.INT_ZERO);
                    iby121319RsBean.setBuyerId(basicInfo.getBuyerId());
                } else {
                    message = "传入编码有误！";
                }
            } else {
                message = "传入编码有误！";
            }
        } else {
            message = "传入编码有误！";
        }


        response.setResult(iby121319RsBean);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage(message);
        return response;
    }

    private String changeBuyerCode(IBY121319RsParam param) {
        String superiorType = param.getSuperiorType();
        String subBuyerCode;
        if (superiorType.equals(BuyersConst.BuyerType.Distribution)) {//分销买家
            subBuyerCode = param.getBuyerCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_THIRTEEN);
            param.setSubLength(StringUtil.toString(NumberConst.IntDef.INT_THIRTEEN));
            param.setBuyerCode(subBuyerCode);
        } else if (superiorType.equals(BuyersConst.BuyerType.Market)) {//菜场买家
            subBuyerCode = param.getBuyerCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOURTEEN);
            param.setSubLength(StringUtil.toString(NumberConst.IntDef.INT_FOURTEEN));
            param.setBuyerCode(subBuyerCode);
        } else if (superiorType.equals(BuyersConst.BuyerType.GroupMeals)) {//团膳买家
            subBuyerCode = param.getBuyerCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWELVE);
            param.setSubLength(StringUtil.toString(NumberConst.IntDef.INT_TWELVE));
            param.setBuyerCode(subBuyerCode);
        } else if (superiorType.equals(BuyersConst.BuyerType.HotPot)) {//火锅买家
            subBuyerCode = param.getBuyerCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_TWELVE);
            param.setSubLength(StringUtil.toString(NumberConst.IntDef.INT_TWELVE));
            param.setBuyerCode(subBuyerCode);
        } else if (superiorType.equals(BuyersConst.BuyerType.Processing)) {//加工厂买家
            subBuyerCode = param.getBuyerCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_SIXTEEN);
            param.setSubLength(StringUtil.toString(NumberConst.IntDef.INT_SIXTEEN));
            param.setBuyerCode(subBuyerCode);
        } else if (superiorType.equals(BuyersConst.BuyerType.ChineseFood)) {//中餐买家
            subBuyerCode = param.getBuyerCode().substring(NumberConst.IntDef.INT_ZERO, NumberConst.IntDef.INT_FOURTEEN);
            param.setSubLength(StringUtil.toString(NumberConst.IntDef.INT_FOURTEEN));
            param.setBuyerCode(subBuyerCode);
        } else {
            subBuyerCode = "";
        }
        return subBuyerCode;
    }
}
