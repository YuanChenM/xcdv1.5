package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101137Result;
import com.msk.bs.bean.IBS2101137RsParam;
import com.msk.bs.logic.IBS2101137RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ni_shaotang on 2016/10/28.
 */
@RestController
@Api(description = "查询买手账户和基本信息", tags = {"IBS2101134RsController"})
public class IBS2101137RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101137RsController.class);

    @Autowired
    private IBS2101137RsLogic ibs2101137RsLogic;

    @ApiOperation(value = "查询买手账户和基本信息", notes = "查询买手账户和基本信息")
    @RequestMapping(value = "bs/bsInfo/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101137Validator")
    public RsResponse<IBS2101137Result> searchBsInfo(@RequestBody RsRequest<IBS2101137RsParam> request) {
        logger.debug("查询买手账户和基本信息");
        RsResponse<IBS2101137Result> response = new RsResponse<IBS2101137Result>();
        IBS2101137RsParam param = request.getParam();
        //判断是否存在分页要求
        if (param.getPageCount() > 0) {
            param.setPaging(true);
        }

        IBS2101137Result result = ibs2101137RsLogic.queryBuyerList(param);
        response.setResult(result);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("查询成功");
        return response;
    }
}
