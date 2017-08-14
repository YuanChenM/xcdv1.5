package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141107RsParam;
import com.msk.product.bean.IPD141107RsResult;
import com.msk.product.logic.IPD141107Logic;
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
 * 查询指定产品品种的标准质量档案卡信息
 * IPD141107RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品标准技术档案卡查询接口（加工质量）RestController", tags = {"IPD141107RsController"})
public class IPD141107RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141107RsController.class);

    @Autowired
    private IPD141107Logic ipd141107Logic;

    /**
     * 产品标准质量档案卡查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "档案卡信息", notes = "查询指定产品品种的标准技术档案卡信息")
    @RequestMapping(value = "/pd/pd_tnc_std",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141107Validator")
    public RsResponse<List<IPD141107RsResult>> findPdQltStd(@RequestBody RsRequest<IPD141107RsParam> param) {
        logger.info("查询指定产品品种的标准质量档案卡信息");
        RsResponse<List<IPD141107RsResult>> rs = new RsResponse<List<IPD141107RsResult>>();

        List<IPD141107RsResult> res = this.ipd141107Logic.findListPdQltTncStd(param.getParam());
        if (res != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage(" 产品标准质量档案卡查询异常!");
        return rs;
    }
}
