package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD1411214RsParam;
import com.msk.product.bean.IPD1411214RsResult;
import com.msk.product.logic.IPD1411214Logic;
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
 * Created by gao_min on 2016/10/9.
 */
@RestController
@Api(description = "举报一览查询RestController", tags = {"IPD1411214RsController"})
public class IPD1411214RsController extends BaseRsController {
    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD1411214RsController.class);

    @Autowired
    private IPD1411214Logic iPD1411214Logic;

    /**
     * 举报一览查询接口
     *
     * @return RsResponse 结果
     * @author gao_min
     */
    @ApiOperation(value = "一览查询", notes = "举报一览查询接口")
    @RequestMapping(value = "/pd/reportInfo/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD1411214Validator")
    public RsResponse<IPD1411214RsResult> searchReportList(@RequestBody RsRequest<IPD1411214RsParam> param) {
        RsResponse<IPD1411214RsResult> rsResponse = new RsResponse<IPD1411214RsResult>();
        IPD1411214RsParam rsParam = param.getParam();
        String userType = rsParam.getUserType();
        switch (userType) {
            case "1":
                rsParam.getUserType().equals(NumberConst.IntDef.INT_ONE);
                break;
            case "2":
                rsParam.getUserType().equals(NumberConst.IntDef.INT_TWO);
                break;
            case "3":
                rsParam.getUserType().equals(NumberConst.IntDef.INT_THREE);
                break;
            default:
                rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                rsResponse.setMessage("用户类型错误！");
                return rsResponse;
        }
        IPD1411214RsResult result = iPD1411214Logic.searchReportList(rsParam);


        if (null != result) {
            logger.info("举报一览查询接口成功！DB连接");
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("查询举报一览接口成功！");
            rsResponse.setResult(result);
            return rsResponse;
        }
        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
        rsResponse.setMessage("查询举报一览接口失败！");
        return rsResponse;

    }
}
