package com.msk.order.rest;


import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151435RestParam;
import com.msk.order.bean.result.ISO151435RestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msk.order.service.ISO151435Service;

/**
 * ISO151435_根据订单号(订单编码)获取订单相关信息
 * Created by chu_jian on 2016/8/3.
 */
@RestController
public class ISO151435RestController extends BaseRestController {
    private static Logger logger = LoggerFactory.getLogger(ISO151435RestController.class);
    @Autowired
    private ISO151435Service iso151435Service;

    /**
     * 根据订单号(订单编码)获取订单相关信息
     *
     * @param request 请求参数
     * @return
     */
    @RequestMapping(value = "/so/getPartOrderDetail", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151435RestResult> getPartOrderDetail(@RequestBody RestRequest<ISO151435RestParam> request) {
        logger.info("-----根据订单号(订单编码)获取订单相关信息 开始-----");
        // 查询详情
        RestResponse<ISO151435RestResult> response = new RestResponse<>();
        ISO151435RestResult result = iso151435Service.getPartOrderDetail(request.getParam());
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("查询成功。");
        response.setResult(result);
        return response;
    }
}
