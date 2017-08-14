package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151423RestParam;
import com.msk.order.bean.result.ISO151423RestResult;
import com.msk.order.service.ISO151423Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 卖家，买手，管家快捷信息查询接口
 * Created by wang_jianzhou on 2016/8/22.
 */

@RestController
public class ISO151423RestController extends BaseRestController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151423RestController.class);

    @Autowired
    private ISO151423Service iso151423Service;

    /**
     * 卖家快捷信息查询
     *
     * @param request 传入参数
     */
    @RequestMapping(value = "/so/sdo/seller/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151423RestResult> searchSellerOrders(@RequestBody RestRequest<ISO151423RestParam> request) {
        logger.info("查询卖家快捷信息");
        RestResponse<ISO151423RestResult> response = new RestResponse<ISO151423RestResult>();
        ISO151423RestParam param = request.getParam();
        ISO151423RestResult rsResult = this.iso151423Service.getSellerResultInfo(param, "seller");
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(rsResult);
        return response;
    }


    /**
     * 买手快捷信息查询
     *
     * @param request 传入参数
     */
    @RequestMapping(value = "/so/sdo/buyer/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151423RestResult> searchBuyerOrders(@RequestBody RestRequest<ISO151423RestParam> request) {
        logger.info("查询买手快捷信息");
        RestResponse<ISO151423RestResult> response = new RestResponse<ISO151423RestResult>();
        ISO151423RestParam param = request.getParam();
        ISO151423RestResult rsResult = this.iso151423Service.getSellerResultInfo(param, "buyer");
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(rsResult);
        return response;
    }

    /**
     * 管家快捷信息查询
     *
     * @param request 传入参数
     */
    @RequestMapping(value = "/so/sdo/housekeeping/search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151423RestResult> searchHousekeepingOrders(@RequestBody RestRequest<ISO151423RestParam> request) {
        logger.info("查询管家快捷信息");
        RestResponse<ISO151423RestResult> response = new RestResponse<ISO151423RestResult>();
        ISO151423RestParam param = request.getParam();
        ISO151423RestResult rsResult = this.iso151423Service.getSellerResultInfo(param, "housekeeping");
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(rsResult);
        return response;
    }
}
