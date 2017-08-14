package com.msk.order.rest;

import com.msk.common.annotation.valid.Validation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.exception.BusinessException;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.common.utils.StringUtil;
import com.msk.order.bean.param.ISO151409RestParam;
import com.msk.order.bean.result.ISO151409RestResult;
import com.msk.order.service.ISO151409Service;
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
 * Created by zhang_qiang1 on 2016/8/19.
 * 退货单查询接口
 */

@RestController
@Api(value = "ReturnOrder_Api", description = "退货单查询Rest接口", tags = {"ISO151409RestController"})

public class ISO151409RestController extends BaseRestController {
    @Autowired
    private ISO151409Service iso151409Service;


    private static Logger logger = LoggerFactory.getLogger(ISO151409RestController.class);


    @RequestMapping(value = "/so/sro/list", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validation
    public RestResponse<ISO151409RestResult> findReturnOrder(@RequestBody RestRequest<ISO151409RestParam> request) {
        logger.info("查询退货单");
        RestResponse<ISO151409RestResult> rs = new RestResponse<ISO151409RestResult>();
        ISO151409RestResult result = null;
        try {
            result = iso151409Service.findReturnOrder(request.getParam());
            rs.setResult(result);
            rs.setStatus(SystemConstant.RsStatus.SUCCESS);
            rs.setMessage("退货单列表查询成功");
        } catch (Exception e) {
            rs.setStatus(SystemConstant.RsStatus.FAIL);
            rs.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }


}
