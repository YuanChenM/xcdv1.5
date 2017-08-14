package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD1411212RsResult;
import com.msk.product.logic.IPD1411212Logic;
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
@Api(description = "举报类型查询RestController", tags = {"IPD1411212RsController"})
public class IPD1411212RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD1411212RsController.class);

    @Autowired
    private IPD1411212Logic ipd1411212Logic;

    /**
     * 举报类型查询接口
     *
     * @return RsResponse 结果
     * @author yang_chunyan
     */
    @ApiOperation(value = "举报类型", notes = "查询举报类型接口")
    @RequestMapping(value = "/pd/reportType/_search", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD1411212Validator")
    public RsResponse<IPD1411212RsResult> searchReportType(@RequestBody RsRequest<String> param) {
        RsResponse<IPD1411212RsResult> rsResponse = new RsResponse<IPD1411212RsResult>();

        IPD1411212RsResult rsResult = ipd1411212Logic.queryReportInfo();
        if (rsResult != null) {
            logger.info("查询举报类型接口成功！DB连接");
            rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
            rsResponse.setMessage("查询举报类型接口成功！");
            rsResponse.setResult(rsResult);
            return rsResponse;
        }
        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
        rsResponse.setMessage("查询举报类型接口失败！");

        return rsResponse;

    }


}
