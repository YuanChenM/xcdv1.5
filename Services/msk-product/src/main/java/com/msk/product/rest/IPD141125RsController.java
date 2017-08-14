package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141125RsParam;
import com.msk.product.bean.IPD141125RsResult;
import com.msk.product.logic.IPD141125Logic;
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

import java.util.List;

/**
 * Created by FjM on 2016/3/11.
 */
@RestController
@Api(description = "通用质量指标档案卡查询接口RestController", tags = {"IPD141125RsController"})
public class IPD141125RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IPD141125RsController.class);

    @Autowired
    private IPD141125Logic IPD141125Logic;
    @ApiOperation(value = "通用质量指标档案卡", notes = "查询产品的通用质量指标档案卡信息")
    @RequestMapping(value = "/pd/pd_gnq_std",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141125Validator")
    public RsResponse<List<IPD141125RsResult>> selectSource(@RequestBody RsRequest<IPD141125RsParam> param) {
        logger.info("通用质量指标档案卡查询接口");
        //新建响应对象
        //*RsResponse<IPD141124RsResult> rsResponse = new RsResponse<IPD141124RsResult>();*//*
        RsResponse<List<IPD141125RsResult>> rsResponse = new RsResponse<List<IPD141125RsResult>>();
        //*IPD141124RsResult ipdResult = new IPD141124RsResult();*//*
        List<IPD141125RsResult> s= this.IPD141125Logic.selectSource(param);

        if(s.size() >= 1){
            rsResponse.setResult(s);
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("通用质量指标档案卡查询成功");
            return rsResponse;
        }

        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
        logger.info("没有查询到数据！");
        rsResponse.setMessage("没有查询到数据！");
        return rsResponse;

    }
}
