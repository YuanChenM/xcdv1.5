package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdNormsStd;
import com.msk.product.bean.IPD141108RsParam;
import com.msk.product.bean.IPD141108RsResult;
import com.msk.product.logic.IPD141108Logic;
import com.msk.product.logic.ProductLogic;
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
 * 产品标准包装档案卡查询参数接口
 * IPD141108RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品标准包装一览查询接口RestController", tags = {"IPD141108RsController"})
public class IPD141108RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141108RsController.class);

    @Autowired
    private IPD141108Logic ipd141108Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 产品标准包装档案卡查询参数接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品标准包装一览", notes = "提供美迪福系统数据同步用的接口，返回产品包装信息")
    @RequestMapping(value = "/pd/pd_norms_list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141108Validator")
    public RsResponse<List<IPD141108RsResult>> findPdNormsStd(@RequestBody RsRequest<IPD141108RsParam> param) {
        logger.info(" 产品标准包装档案卡查询参数接口");
        RsResponse<List<IPD141108RsResult>> rs = new RsResponse<List<IPD141108RsResult>>();
        List<IPD141108RsResult> res = ipd141108Logic.findListPdQltTncStd(param.getParam());
        if (res.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info(" 产品标准包装档案卡查询参数接口,DB,查询OK");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品标准包装档案卡查询接口,数据错误！");
        return rs;
    }

    /**
     * 产品标准包装档案卡查询参数接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdNormsStd",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IPD141108Validator")
    public RsResponse<List<PdNormsStd>> findPdNormsStds(@RequestBody RsRequest<BaseParam> param) {
        logger.info(" 产品标准包装档案卡查询参数接口");
        RsResponse<List<PdNormsStd>> rs = new RsResponse<List<PdNormsStd>>();
        List<PdNormsStd> res = productLogic.findPdNormsStd(param.getParam());
        if (res.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info(" 产品标准包装档案卡查询参数接口,DB,查询OK");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品标准包装档案卡查询接口,数据错误！");
        return rs;
    }
}
