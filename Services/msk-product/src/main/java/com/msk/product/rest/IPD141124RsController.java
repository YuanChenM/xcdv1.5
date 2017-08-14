package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141124RsParam;
import com.msk.product.bean.PD141147Bean;
import com.msk.product.logic.IPD141124Logic;
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
 * Created by FjM on 2016/3/10.
 */
@RestController
@Api(description = "饲养指标档案卡查询接口RestController", tags = {"IPD141124RsController"})
public class IPD141124RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IPD141122RsController.class);

    @Autowired
    private IPD141124Logic ipd141124RsLogic;
    @ApiOperation(value = "饲养指标档案卡信息", notes = "查询产品的饲养指标档案卡信息")
    @RequestMapping(value = "/pd/pd_fed_std",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141124Validator")
    public RsResponse<List<PD141147Bean>> selectSource(@RequestBody RsRequest<IPD141124RsParam> param){
        logger.info("饲养指标档案卡查询接口");
        //新建响应对象
        /*RsResponse<IPD141124RsResult> rsResponse = new RsResponse<IPD141124RsResult>();*/
        RsResponse<List<PD141147Bean>> rsResponse = new RsResponse<List<PD141147Bean>>();
        /*IPD141124RsResult ipdResult = new IPD141124RsResult();*/
        List<PD141147Bean> s= this.ipd141124RsLogic.selectSource(param);

        if(s.size() >= 1){
            rsResponse.setResult(s);
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("饲养指标档案卡查询成功");
            return rsResponse;
        }

        rsResponse.setStatus(SystemConst.RsStatus.FAIL);


        logger.info("没有查询到数据！");
        rsResponse.setMessage("没有查询到数据！");
        return rsResponse;

    }
}
