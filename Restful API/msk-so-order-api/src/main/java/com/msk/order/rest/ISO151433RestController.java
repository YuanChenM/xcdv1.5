package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151433RestParam;
import com.msk.order.bean.result.ISO151433RestResult;
import com.msk.order.service.ISO151433Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wang_shuai on 2016/8/26.
 */
@RestController
public class ISO151433RestController extends BaseRestController {
    private static Logger logger = LoggerFactory.getLogger(ISO151433RestController.class);

    @Autowired
    private ISO151433Service iso151433Service;
    @RequestMapping(value = "/so/sdo/shipment/cancel",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE },consumes = { MediaType.APPLICATION_XML_VALUE })
    @CustomValidation(className = "com.msk.order.validator.ISO151433RestValidator")
    public RestResponse<ISO151433RestResult> halfMonthOrder(@RequestBody RestRequest<ISO151433RestParam> request){
        logger.info("订单发货取消回传接口！");
        request.getParam().setUpdId(request.getLoginId());
        RestResponse<ISO151433RestResult> response = new RestResponse<ISO151433RestResult>();
        ISO151433RestResult iso151433RestResult = iso151433Service.cancelShipment(request.getParam());
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setResult(iso151433RestResult);
        response.setMessage("订单发货取消成功！");
        logger.info("订单发货取消结束！");
        return response;
    }
}
