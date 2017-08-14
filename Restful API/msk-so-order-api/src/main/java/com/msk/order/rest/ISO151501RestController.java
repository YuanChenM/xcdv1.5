package com.msk.order.rest;

import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.bean.param.BasePageParam;
import com.msk.common.bean.result.PageResult;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.ISO151501Bean;
import com.msk.order.service.ISO151501Service;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订单列表页面 接口
 * zhangqiang1
 */
@Api(value = "Menu-Api", description = "订单查询相关Rest接口", tags = {"ISO151501RestController"})
@RestController
public class ISO151501RestController extends BaseRestController {
    /**
     * logger
     */
    @Autowired
    private ISO151501Service iso151501Service;


    private static Logger logger = LoggerFactory.getLogger(ISO151501RestController.class);


    /**
     * 订单页面查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/order/page/_find", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<PageResult<ISO151501Bean>> findAllOrders(@RequestBody RestRequest<BasePageParam> param) throws Exception {
        RestResponse<PageResult<ISO151501Bean>> response = new RestResponse<>();
        PageResult<ISO151501Bean> allOrders = this.iso151501Service.findAllOrders(param.getParam());
        logger.info("查询主订单");
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(allOrders);
        return response;
    }


    /**
     * 订单及明细导出
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/order/details/_export",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<List<Map<String, Object>>> exportOrderAndDetail(@RequestBody RestRequest<BasePageParam> param) {
        RestResponse<List<Map<String, Object>>> response = new RestResponse<>();
        logger.info("查询要导出的数据");
        List<Map<String, Object>> orderAndDetails = this.iso151501Service.exportOrderAndDetail(param.getParam());
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(orderAndDetails);
        return response;
    }







}


