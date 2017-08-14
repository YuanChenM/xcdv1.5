package com.msk.br.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.br.bean.IBR121310Bean;
import com.msk.br.bean.IBR121408RsBean;
import com.msk.br.bean.IBR121408RsParam;
import com.msk.br.logic.IBR121406Logic;
import com.msk.br.logic.IBR121408Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrFileBuyerPool;
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

/**
 * 营销期公众买家报表
 * Created by tao_zhifa on 2016/8/8.
 */
@RestController
public class IBR121408RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121408RsController.class);

    @Autowired
    private IBR121408Logic ibr121408Logic;
    @Autowired
    private IBR121406Logic ibr121406Logic;


    /*
        * 分销分类买家池生成excel文件
        *
        * @param param
        * @return
        */
    @RequestMapping(value = "/br/createMarketingPeriodExcel/generateAndUpload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121408RsBean> createMarketingPeriodExcel(@RequestBody RsRequest<IBR121408RsParam> ibr121408rsParam) {
        String message = null;
        String fileId = null;
        Date currentDate = DateTimeUtil.getCustomerDate();
        RsResponse<IBR121408RsBean> rsResponse = new RsResponse<>();
        int count = NumberConst.IntDef.INT_ZERO;

        if(ibr121408rsParam != null){
            IBR121408RsParam param = ibr121408rsParam.getParam();
            param.setCrtId(ibr121408rsParam.getLoginId());
            param.setUpdId(ibr121408rsParam.getLoginId());
            param.setActId(ibr121408rsParam.getLoginId());
            param.setUpdTime(currentDate);
            param.setCrtTime(currentDate);
            param.setActTime(currentDate);

            if(param.getFileStatusFlag().equals("0")){
                logger.info("营销期公众买家报表数据新增");
                List<BrFileBuyerPool> res = ibr121408Logic.findList(param);
                String fileName;
                if(!CollectionUtils.isEmpty(res)){
                    fileName = res.get(NumberConst.IntDef.INT_ZERO).getFileName() + param.getFileStartTime() + "~" + param.getFileEndTime();
                    BaseParam baseParam = new BaseParam();
                    baseParam.setFilter("fileName", fileName);
                    baseParam.setFilter("poolType", StringUtil.toString(NumberConst.IntDef.INT_FOUR));
                    baseParam.setFilter("currentDate", StringUtil.toString(currentDate));
                    int ret = this.ibr121408Logic.isExist(baseParam);
                    if (ret > NumberConst.IntDef.INT_ZERO) {
                        count = NumberConst.IntDef.INT_THREE;
                        message = "报表已存在！";
                        if (param.isFlag()) {
                            //覆盖生成新文件
                            this.ibr121408Logic.updateFileBuyerPoolByFileName(baseParam);
                            count = NumberConst.IntDef.INT_TWO;
                            message = "新文件生成成功！";
                            param.setFileName(fileName);
                            fileId = this.ibr121408Logic.dataResolve(param);
                        }


                    } else {
                        param.setFileName(fileName);
                        fileId= this.ibr121408Logic.dataResolve(param);
                        count = NumberConst.IntDef.INT_TWO;
                        message = "报表生成中";
                    }
                }else {
                    message = "文件不存在";
                }
            }
            if(param.getFileStatusFlag().equals("2")){
                this.ibr121408Logic.createFileInformatica(param);
                message = "文件生成成功";
            }
            if(param.getFileStatusFlag().equals("3")){
                count = this.ibr121408Logic.deleteFlag(param);
                if(count == NumberConst.IntDef.INT_ZERO){
                    message = "文件删除失败";
                }else {
                    message = "文件删除成功";
                }

            }
        }
        
        IBR121408RsBean ibr121408RsBean = new IBR121408RsBean();
        ibr121408RsBean.setDataCount(String.valueOf(count));
        ibr121408RsBean.setFileId(fileId);
        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
        rsResponse.setMessage(message);
        rsResponse.setResult(ibr121408RsBean);
        return rsResponse;
    }
}


