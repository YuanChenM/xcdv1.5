package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.base.BaseRestController;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151803RestParam;
import com.msk.order.bean.result.ISO151803RestResult;
import com.msk.order.service.ISO151803Service;
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
 * ISO151803_订单发货明细信息查询接口
 * Created by sun_jiaju on 2016/8/29.
 */
@RestController
@Api(value = "Api", description = "订单发货明细信息查询接口", tags = {"ISO151803RestController"})
public class ISO151803RestController extends BaseRestController {
    private static Logger logger = LoggerFactory.getLogger(ISO151401RestController.class);

    @Autowired
    private ISO151803Service iso151803Service;

    @RequestMapping(value = "/so/sdo/shipDetail/query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151803RestResult> searchShipDetail(@RequestBody RestRequest<ISO151803RestParam> request) throws Exception{
        RestResponse<ISO151803RestResult> response = new RestResponse<ISO151803RestResult>();
        logger.debug("订单发货明细信息查询开始");
        try {
            ISO151803RestResult result = iso151803Service.searchShipDetail(request.getParam());
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("订单发货明细信息查询成功!");
            response.setResult(result);
        }catch (Exception e){
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}