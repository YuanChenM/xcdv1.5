package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141122RsParam;
import com.msk.product.bean.IPD141122RsResult;
import com.msk.product.logic.IPD141122Logic;
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
 * Created by fjm on 2016/3/9.
 */

@RestController
@Api(description = "原料种源信息查询接口RestController", tags = {"IPD141122RsController"})
public class IPD141122RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IPD141122RsController.class);

    @Autowired
    private IPD141122Logic ipd141122RsLogic;

    @ApiOperation(value = "原料种源信息", notes = "查询产品的原料种源信息")
    @RequestMapping(value = "/pd/pd_mat",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141122Validator")
    public RsResponse<IPD141122RsResult> selectSource(@RequestBody RsRequest<IPD141122RsParam> param){

        logger.info("原料种源信息查询接口");
        RsResponse<IPD141122RsResult> rsResponse = new RsResponse<IPD141122RsResult>();

        IPD141122RsResult ipdResult = new IPD141122RsResult();

        ipdResult = this.ipd141122RsLogic.selectSource(param);

        if(ipdResult != null){
            rsResponse.setResult(ipdResult);
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("原料种源信息查询成功");
            return rsResponse;
        }

        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
        logger.info("没有查询到数据！");
        rsResponse.setMessage("没有查询到数据！");
        return rsResponse;




    }


}
