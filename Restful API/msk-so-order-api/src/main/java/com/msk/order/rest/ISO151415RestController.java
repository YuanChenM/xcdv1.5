package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151415OrderDeliverParam;
import com.msk.order.service.ISO151415Service;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.order.bean.param.ISO151415OrderReceiptParam;
import com.msk.order.bean.result.BaseOrderStatusResult;

/**
 * Created by wang_shuai on 2016/8/9.
 */
@RestController
@Api(value = "Api",description = "收发货rest接口",tags = {"ISO151415RestController"})
public class ISO151415RestController extends BaseRestController {
    @Autowired
    private ISO151415Service iso151415Service;

    /**
     * 订单收发货操作
     * @param request 请求参数
     * @return 处理结果集
     */
    @RequestMapping(value = "/so/sdo/part/receipt", method = RequestMethod.POST, produces = { MediaType.APPLICATION_XML_VALUE },consumes = { MediaType.APPLICATION_XML_VALUE })
    @CustomValidation(className = "com.msk.order.validator.ISO151415RestReceiptValidator")
    public RestResponse<BaseOrderStatusResult> orderReceivingOrDeliver(@RequestBody RestRequest<ISO151415OrderReceiptParam> request){
        RestResponse<BaseOrderStatusResult> response = new RestResponse<BaseOrderStatusResult>();
        ISO151415OrderReceiptParam param = request.getParam();
        param.setUpdId(request.getLoginId());
        response.setMessage("订单receipt成功");
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        BaseOrderStatusResult result = this.iso151415Service.modifyPartReceipt(param);
        response.setResult(result);
        return response;
    }


    /**
     * 订单发货操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/so/sdo/part/deliver", method = RequestMethod.POST, produces = { MediaType.APPLICATION_XML_VALUE },consumes = { MediaType.APPLICATION_XML_VALUE })
    @CustomValidation(className = "com.msk.order.validator.ISO151415RestDeliverValidator")
    public RestResponse<BaseOrderStatusResult> orderDeliver(@RequestBody RestRequest<ISO151415OrderDeliverParam> request){
        RestResponse<BaseOrderStatusResult> response = new RestResponse<BaseOrderStatusResult>();
        ISO151415OrderDeliverParam param = request.getParam();
        param.setUpdId(request.getLoginId());
        BaseOrderStatusResult result = this.iso151415Service.modifyPartDeliver(param);
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("订单发货成功");
        response.setResult(result);
        return response;
    }
}

