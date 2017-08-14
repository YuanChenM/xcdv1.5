package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdWeight;
import com.msk.product.bean.IPD141138RsParam;
import com.msk.product.bean.IPD141138RsResult;
import com.msk.product.logic.IPD141138Logic;
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
 * 产品净重一览查询接口
 * IPD141138RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品净重一览查询接口RestController", tags = {"IPD141138RsController"})
public class IPD141138RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141138RsController.class);

    @Autowired
    private IPD141138Logic ipd141138Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 产品净重一览查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品净重一览", notes = "查询产品净重名称和编码")
    @RequestMapping(value = "/pd/pd_weight",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141138Validator")
    public RsResponse<List<IPD141138RsResult>> findBreedList(@RequestBody RsRequest<IPD141138RsParam> param) {
        RsResponse<List<IPD141138RsResult>> rs = new RsResponse<List<IPD141138RsResult>>();
        //查询后台,获取List数据
        List<IPD141138RsResult> result = this.ipd141138Logic.findListWeight(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品净重一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品净重一览查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品净重一览查询接口,数据错误！");
        return rs;

    }


    /**
     * 产品净重一览查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdWeight",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IPD141138Validator")
    public RsResponse<List<PdWeight>> findWeightList(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<List<PdWeight>> rs = new RsResponse<List<PdWeight>>();
        //查询后台,获取List数据
        BaseParam rsParam = param.getParam();
        List<PdWeight> result = this.productLogic.findPdWeight(rsParam);
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品净重一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品净重一览查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品净重一览查询接口,数据错误！");
        return rs;

    }
}
