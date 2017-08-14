package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151427RestParam;
import com.msk.order.bean.result.ISO151427RestResult;
import com.msk.order.service.ISO151427Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
@RestController
public class ISO151427RestController extends BaseRestController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151427RestController.class);
    @Autowired
    private ISO151427Service iso151427Service;


    @RequestMapping(value = "/so/sdo/settlementDetail",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_VALUE })
    @CustomValidation(className = "com.msk.order.validator.ISO151427RestValidator")
    public RestResponse<ISO151427RestResult> getSettlementDetail(@RequestBody RestRequest<ISO151427RestParam> param) {
        logger.info("查询结算详情");
        RestResponse<ISO151427RestResult> response = new RestResponse<ISO151427RestResult>();
        ISO151427RestParam iso151427RestParam =param.getParam();
        try{
            ISO151427RestResult result= this.iso151427Service.findSettlementDetail(iso151427RestParam);
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(result);
        }catch (Exception e){
            String message="查询失败,异常原因："+e.getMessage()+e.toString();
            System.out.println(message);
        }
        return response;
    }
}
