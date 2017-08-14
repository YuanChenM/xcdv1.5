package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151401RestParam;
import com.msk.order.bean.result.ISO151401RestResult;
import com.msk.order.service.ISO151401Service;
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
 * ISO151401_创建购物需求订单接口
 * Created by chu_jian on 2016/8/3.
 */

@RestController
@Api(value = "Menu-Api", description = "菜单相关Rest接口", tags = {"ISO151401RestController"})
public class ISO151401RestController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(ISO151401RestController.class);
    @Autowired
    private ISO151401Service service;

    /**
     * ISO151401_创建购买需求订单
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/so/pro/new", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151401RestValidator")
    public RestResponse<ISO151401RestResult> createProOrder(@RequestBody RestRequest<ISO151401RestParam> param) {
        RestResponse<ISO151401RestResult> responseBody = new RestResponse<>();
        logger.debug("-----创建购物需求订单成功 开始-----");
        // 保存购物需求
        ISO151401RestResult result = service.createSoPro(param);
        responseBody.setStatus(SystemConstant.RsStatus.SUCCESS);
        responseBody.setMessage("创建购物需求订单成功。");
        responseBody.setResult(result);
        return responseBody;
    }

}