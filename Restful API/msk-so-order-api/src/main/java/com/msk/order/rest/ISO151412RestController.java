package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151412RestParam;
import com.msk.order.bean.result.ISO151412RestResult;
import com.msk.order.service.ISO151412Service;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zhang_qiang1 on 2016/8/24.
 * 订单删除或恢复 接口
 */

@RestController
@Api(value = "Order_Api", description = "订单删除或恢复Rest接口", tags = {"ISO151412RestController"})

public class ISO151412RestController extends BaseRestController {


    private static Logger logger = LoggerFactory.getLogger(ISO151412RestController.class);


    @Autowired
    private ISO151412Service iso151412Service;


    /**
     * 订单删除或恢复接口
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/sdo/delete", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151412RestResult> setOrderDelOrRecover(@RequestBody RestRequest<ISO151412RestParam> param) {
        logger.info("订单删除或恢复接口调用开始");
        RestResponse<ISO151412RestResult> response = new RestResponse<ISO151412RestResult>();
        try {
            response = this.iso151412Service.setOrderDelOrRecover(param);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(SystemConstant.RsStatus.FAIL);
            e.printStackTrace();
        }
        logger.info("订单删除或恢复接口调用结束");
        return response;
    }
}
