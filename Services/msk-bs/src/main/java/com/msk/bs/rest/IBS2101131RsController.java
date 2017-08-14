package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.bs.bean.IBS2101131RsParam;
import com.msk.bs.bean.IBS2101131RsResult;
import com.msk.bs.logic.IBS2101131RsLogic;
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
 * Created by gao_min on 2016/10/12.
 */
@RestController
@Api(description = "管家服务经历查询RestController", tags = {"IBS2101131RsController"})
public class IBS2101131RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBS2101131RsController.class);

    @Autowired
    private IBS2101131RsLogic ibs2101131RsLogic;

    @ApiOperation(value = "管家服务经历", notes = "找管家-我的服务经历以及心得查询")
    @RequestMapping(value = "/bs/houseKeeperExperience/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.bs.validator.IBS2101131Validator")
    public RsResponse<IBS2101131RsResult> searchHkExperience (@RequestBody RsRequest<IBS2101131RsParam> request) {
        RsResponse<IBS2101131RsResult> rsResponse = new RsResponse<IBS2101131RsResult>();
        if(null==request.getParam()){
            IBS2101131RsParam req= new IBS2101131RsParam();
            request.setParam(req);
        }
        IBS2101131RsParam rsParam = request.getParam();
        logger.info("查询管家服务经历");
        IBS2101131RsResult result = ibs2101131RsLogic.findHouseKeeperList(rsParam);
        if (null != result) {
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("查询管家服务经历成功!");
            rsResponse.setResult(result);
            return rsResponse;
        }
        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
        rsResponse.setMessage("查询管家服务经历失败!");
        return rsResponse;

    }
}
