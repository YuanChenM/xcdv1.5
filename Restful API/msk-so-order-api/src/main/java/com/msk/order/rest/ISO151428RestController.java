package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.annotation.valid.Validation;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151428RestParam;
import com.msk.order.bean.result.ISO151428RestResult;
import com.msk.order.service.ISO151428Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ISO151428_购买记录查询接口
 * Created by wang_shuai on 2016/8/24.
 */
@RestController
public class ISO151428RestController extends BaseRestController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151428RestController.class);
    @Autowired
    private ISO151428Service iso151428Service;

    @RequestMapping(value = "/so/sdo/buyRecord",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validation
    @CustomValidation(className = "com.msk.order.validator.ISO151428RestValidator")
    public RestResponse<ISO151428RestResult> getBuyRecordInfo(@RequestBody RestRequest<ISO151428RestParam> param) {
        logger.info("查询购买记录");
        RestResponse<ISO151428RestResult> response = new RestResponse<ISO151428RestResult>();
        ISO151428RestParam iso151428RestParam =param.getParam();
        try{
            ISO151428RestResult result= this.iso151428Service.findBuyRecord(iso151428RestParam);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setResult(result);
        }catch (Exception e){
            String message="查询失败,异常原因："+e.getMessage()+e.toString();
            logger.info(message);
        }
        return response;
    }
}
