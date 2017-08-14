package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151410RestPram;
import com.msk.order.bean.result.ISO151410RestResult;
import com.msk.order.service.ISO151410Service;
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
 * 整单取消接口
 * zhangqiang
 */
@RestController
@Api(value = "order_cancel", description = "订单整单取消Rest接口", tags = {"ISO151410RestController"})
public class ISO151410RestController extends BaseRestController {


    @Autowired
    private ISO151410Service iso151410Service;

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISO151410RestController.class);

    /**
     * @param param
     * @return
     */
    @RequestMapping(value = "so/sdo/cancel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151410RestResult> cancel(@RequestBody RestRequest<ISO151410RestPram> param) {
        RestResponse<ISO151410RestResult> response = new RestResponse<>();
        try {
            ISO151410RestResult baseOrder = this.iso151410Service.modifyOrderCancel(param.getParam(), param.getLoginId());
            logger.info("整单取消");
            response.setStatus(SystemConstant.RsStatus.SUCCESS);
            response.setMessage("处理成功");
            response.setResult(baseOrder);
        } catch (Exception e) {
            response.setStatus(SystemConstant.RsStatus.FAIL);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}