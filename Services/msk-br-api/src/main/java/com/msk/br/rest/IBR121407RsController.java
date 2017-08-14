package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.br.bean.IBR121407RsBean;
import com.msk.br.bean.IBR121407RsParam;
import com.msk.br.logic.IBR121406Logic;
import com.msk.br.logic.IBR121407Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**分销买家池各上线状态管控表接口
 * Created by yuan_zhifei on 2016/7/27.
 */
@RestController
public class IBR121407RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121407RsController.class);

    @Autowired
    private IBR121407Logic ibr121407Logic;

    @Autowired
    private IBR121406Logic ibr121406Logic;

    /*
        * 分销分类买家池生成excel文件
        *
        * @param param
        * @return
        */
    @RequestMapping(value = "/br/onlineExcelFiles/generateAndUpload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121407RsBean> generateAndUploadOnlineExcelFiles(@RequestBody RsRequest<IBR121407RsParam> ibr121407RsParam) {
        logger.info("分销分类买家池生成上线状态excel文件");
        RsResponse<IBR121407RsBean> response = new RsResponse<>();
        Date currentDate = DateTimeUtil.getCustomerDate();
        String message = null;
        String fileId = null;
        int result = 0;
        if (ibr121407RsParam != null) {
            IBR121407RsParam param = ibr121407RsParam.getParam();
            param.setCrtId(ibr121407RsParam.getLoginId());
            param.setUpdId(ibr121407RsParam.getLoginId());
            param.setActId(ibr121407RsParam.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtTime(currentDate);
            param.setActTime(currentDate);
            param.setMarketId("");
//            super.setCommonParam(param);
            if (param.getFileStatusFlag().equals("0")) {
                BaseParam baseParam = new BaseParam();
                super.setCommonParam(baseParam);
                List<IBR121407RsBean> buyerInfoList = null;
                if (param.getMarketingsStatusCla().equals("1")) {
                    //营销期
                    buyerInfoList = this.ibr121407Logic.findMarketingDistributionBuyers(param);
                } else {
                    //销售期
                    //营销期异常
                    //销售期异常
                    buyerInfoList = this.ibr121407Logic.findSalesPeriodDistributionBuyers(param);
                }
                result = NumberConst.IntDef.INT_ZERO;
                String fileName;
                if (!CollectionUtils.isEmpty(buyerInfoList)) {
                    fileName = buyerInfoList.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime();
                    baseParam.setFilter("fileName", fileName);
                    baseParam.setFilter("marketingsPeriodName", param.getMarketingsPeriodName());
                    baseParam.setFilter("marketingsStatus", param.getMarketingsStatus());
                    baseParam.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_THREE));
                    baseParam.setFilter("currentDate", StringUtil.toString(currentDate));
                    int res = this.ibr121406Logic.isExist(baseParam);
                    if (res > NumberConst.IntDef.INT_ZERO) {
                        message = "报表已存在!";
                        result = NumberConst.IntDef.INT_THREE;
                        if (param.isFlag()) {
                            //覆盖生成新文件
                            baseParam.setUpdId(param.getUpdId());
                            this.ibr121406Logic.updateFileBuyerPoolByFileName(baseParam);
                            result = NumberConst.IntDef.INT_TWO;
                            message = "新文件生成成功！";
                            param.setFileName(fileName);
                            fileId = this.ibr121407Logic.dataResolve(param);
                        }
                    } else {
                        result = NumberConst.IntDef.INT_TWO;
                        message = "文件生成成功！";
                        param.setFileName(fileName);
                        fileId = this.ibr121407Logic.dataResolve(param);
                    }
                } else {
                    message = "文件不存在";
                }
            }
            if (param.getFileStatusFlag().equals("2")) {
                this.ibr121407Logic.createFileInformatica(param);
                message = "文件生成成功";
            }
            if(param.getFileStatusFlag().equals("3")){
                result = this.ibr121407Logic.deleteFlag(param);
                if(result == NumberConst.IntDef.INT_ZERO){
                    message = "文件删除失败";
                }else {
                    message = "文件删除成功";
                }
            }
        }

        IBR121407RsBean ibr121407RsBean = new IBR121407RsBean();
        ibr121407RsBean.setDataCount(String.valueOf(result));
        ibr121407RsBean.setFileId(fileId);
        response.setResult(ibr121407RsBean);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage(message);
        return response;
    }
}


