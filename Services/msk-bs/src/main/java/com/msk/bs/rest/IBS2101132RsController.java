package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101132RsParam;
import com.msk.bs.logic.IBS2101132RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by gao_min on 2016/10/13.
 */
@RestController
@Api(description = "买家管家关系解除RestController", tags = {"IBS2101132RsController"})
public class IBS2101132RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101132RsController.class);

    @Autowired
    private IBS2101132RsLogic ibs2101132RsLogic;

    @ApiOperation(value = "关系解除", notes = "买家管家关系解除接口")
    @RequestMapping(value = "/bs/hkBuyerReleationship/_unbind", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101132Validator")
    public RsResponse<T> unbindRelation(@RequestBody RsRequest<IBS2101132RsParam> request) {
        logger.debug("解除买家管家关系！");
        RsResponse<T> rsResponse = new RsResponse<T>();
        IBS2101132RsParam param = request.getParam();
        String loginId = request.getLoginId();
        param.setUpdId(loginId);
        Date date = DateTimeUtil.getCustomerDate();
        param.setUpdTime(date);
        // 请求中管家code或者买家id有一个必填.
        if (StringUtil.isNullOrEmpty(param.getHouseCode()) && null == param.getBuyerIds()) {
            rsResponse.setStatus(SystemConst.RsStatus.FAIL);
            rsResponse.setMessage("买家编码和管家编码不能全为空！");
        } else if (null != param.getBuyerIds()) {
            if (StringUtil.isNullOrEmpty(param.getHouseCode()) && 0 == param.getBuyerIds().size()) {
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                rsResponse.setMessage("买家编码和管家编码不能全为空！");
            } else {
                rsResponse = ibs2101132RsLogic.relieveRelationship(param);
            }
        } else {
            rsResponse = ibs2101132RsLogic.relieveRelationship(param);
        }
        return rsResponse;

    }


}
