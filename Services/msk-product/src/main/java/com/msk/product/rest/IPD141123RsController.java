package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141123RsParam;
import com.msk.product.bean.IPD141123RsResult;
import com.msk.product.logic.IPD141123Logic;
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
 * Created by FjM on 2016/3/14.
 */
@RestController
@Api(description = "原种种源档案卡信息查询接口RestController", tags = {"IPD141123RsController"})
public class IPD141123RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IPD141125RsController.class);

    @Autowired
    private IPD141123Logic ipd141123Logic;

    @ApiOperation(value = "原种种源档案卡信息", notes = "查询产品的原种种源档案卡信息接口")
    @RequestMapping(value = "/pd/pd_org_std",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141123Validator")
    public RsResponse<List<IPD141123RsResult>> selectSource(@RequestBody RsRequest<IPD141123RsParam> param){
        logger.info("原种种源档案卡查询接口");
        //新建响应对象
        /*RsResponse<IPD141124RsResult> rsResponse = new RsResponse<IPD141124RsResult>();*/
        RsResponse<List<IPD141123RsResult>> rsResponse = new RsResponse<List<IPD141123RsResult>>();
        /*IPD141124RsResult ipdResult = new IPD141124RsResult();*/
        List<IPD141123RsResult> s= this.ipd141123Logic.selectSource(param);

        if(s.size() >= 1){
            rsResponse.setResult(s);
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("原种种源档案卡查询成功");
            return rsResponse;
        }

        rsResponse.setStatus(SystemConst.RsStatus.FAIL);


        logger.info("没有查询到数据！");
        rsResponse.setMessage("没有查询到数据！");
        return rsResponse;

    }
}
