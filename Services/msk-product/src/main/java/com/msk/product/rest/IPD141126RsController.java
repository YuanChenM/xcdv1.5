package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141126RsParam;
import com.msk.product.bean.IPD141126RsResult;
import com.msk.product.logic.IPD141126Logic;
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
@Api(description = "储存运输指标档案卡查询接口RestController", tags = {"IPD141126RsController"})
public class IPD141126RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IPD141126RsController.class);

    @Autowired
    private IPD141126Logic ipd141126Logic;

    @ApiOperation(value = "储存运输指标档案卡", notes = "查询产品的储存运输指标档案卡信息")
    @RequestMapping(value = "/pd/pd_tsp_std",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    @Validator(validatorClass = "com.msk.product.validator.IPD141126Validator")
    public RsResponse<List<IPD141126RsResult>> selectSource(@RequestBody RsRequest<IPD141126RsParam> param) {
        logger.debug("存储运输指标档案卡查询接口");
        // 新建响应对象
        RsResponse<List<IPD141126RsResult>> rsResponse = new RsResponse<List<IPD141126RsResult>>();
        List<IPD141126RsResult> s = this.ipd141126Logic.selectSource(param);

        if (s.size() >= 1) {
            rsResponse.setResult(s);
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("存储运输指标档案卡查询成功");
            return rsResponse;
        }

        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
        logger.debug("没有查询到数据！");
        rsResponse.setMessage("没有查询到数据！");
        return rsResponse;

    }
}
