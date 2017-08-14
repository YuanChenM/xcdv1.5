package com.msk.order.rest;

import com.msk.common.annotation.valid.CustomValidation;
import com.msk.common.bean.RestRequest;
import com.msk.common.bean.RestResponse;
import com.msk.common.base.BaseRestController;
import com.msk.common.constant.SystemConstant;
import com.msk.order.bean.param.ISO151408RestParam;
import com.msk.order.bean.result.ISO151408RestResult;
import com.msk.order.service.ISO151408Service;
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
 * Created by zhang_qiang1 on 2016/8/22.
 * 退货单创建
 */

@RestController
@Api(value = "CreateReturnOrder_Api", description = "退货单创建Rest接口", tags = {"ISO151408RestController"})

public class ISO151408RestController extends BaseRestController {
    @Autowired
    private ISO151408Service iso151408Service;

    private static Logger logger = LoggerFactory.getLogger(ISO151501RestController.class);


    /**
     * 创建标准分销退货单
     *
     * @param param param
     * @return 结果
     * @author zhangqiang1
     */
    @RequestMapping(value = "/so/sro/new", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @CustomValidation(className = "com.msk.order.validator.ISO151408Validator")
    public RestResponse<ISO151408RestResult> modifyDbOrder(@RequestBody RestRequest<ISO151408RestParam> param) {
        logger.info("创建标准分销退货单");
        RestResponse<ISO151408RestResult> rs = new RestResponse<ISO151408RestResult>();
        try {
            ISO151408RestParam rsParam = param.getParam();
            rsParam.setUpdId(param.getLoginId());
            ISO151408RestResult result = iso151408Service.createReturnOrder(rsParam);
            rs.setStatus(SystemConstant.RsStatus.SUCCESS);
            rs.setMessage("创建标准分销退货单成功！");
            rs.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setStatus(SystemConstant.RsStatus.FAIL);
            rs.setMessage(e.getMessage());
        }
        return rs;
    }
}
