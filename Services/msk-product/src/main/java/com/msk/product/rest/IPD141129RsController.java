package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdFeature;
import com.msk.product.bean.IPD141129RsParam;
import com.msk.product.bean.IPD141129RsResult;
import com.msk.product.logic.IPD141129Logic;
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
 * 产品品种一览查询接口
 * IPD141129RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品特征一览查询接口RestController", tags = {"IPD141129RsController"})
public class IPD141129RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141129RsController.class);

    @Autowired
    private IPD141129Logic ipd141129Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品特征一览", notes = "查询产品特征名称和编码")
    @RequestMapping(value = "/pd/pd_feature",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141129Validator")
    public RsResponse<List<IPD141129RsResult>> findBreedList(@RequestBody RsRequest<IPD141129RsParam> param) {
        RsResponse<List<IPD141129RsResult>> rs = new RsResponse<List<IPD141129RsResult>>();
        //查询后台,获取List数据
        List<IPD141129RsResult> result = this.ipd141129Logic.findListFeature(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品特征一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品特征一览查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品特征一览查询接口,数据错误！");
        return rs;

    }

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdFeature",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IPD141129Validator")
    public RsResponse<List<PdFeature>> findFeatureList(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<List<PdFeature>> rs = new RsResponse<List<PdFeature>>();
        //查询后台,获取List数据
        BaseParam rsParam = param.getParam();
        List<PdFeature> result = this.productLogic.findPdFeature(rsParam);
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品特征一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品特征一览查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品特征一览查询接口,数据错误！");
        return rs;

    }
}
