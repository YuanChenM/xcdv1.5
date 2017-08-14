package com.msk.order.rest;

import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.service.ISO151402Service;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.order.bean.param.ISO151402RestParam;
import com.msk.order.bean.result.ISO151402RestResult;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
@RestController
@Api(value = "Api", description = "打印查询订单详细信息PDF接口", tags = {"ISO151402RestController"})
public class ISO151402RestController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(ISO151401RestController.class);
    @Autowired
    private ISO151402Service iso151402Service;

    @RequestMapping(value = "/so/detail/print", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RestResponse<ISO151402RestResult> printSoDetail(@RequestBody RestRequest<ISO151402RestParam> param) {
        RestResponse<ISO151402RestResult> response = new RestResponse<ISO151402RestResult>();
        logger.debug("-----查询订单详细信息PDF接口 开始-----");
        // 查询详情
        ISO151402RestResult result = iso151402Service.selectSoProDetail(param);
        response.setStatus(SystemConstant.RsStatus.SUCCESS);
        response.setMessage("查询订单详细信息PDF接口成功。");
        response.setResult(result);
        return response;
    }

}