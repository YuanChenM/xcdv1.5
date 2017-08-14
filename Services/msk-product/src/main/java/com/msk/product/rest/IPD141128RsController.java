package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdBreed;
import com.msk.product.bean.IPD141128RsParam;
import com.msk.product.bean.IPD141128RsResult;
import com.msk.product.logic.IPD141128Logic;
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
 * IPD141128RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品品种一览查询接口RestController", tags = {"IPD141128RsController"})
public class IPD141128RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141128RsController.class);

    @Autowired
    private IPD141128Logic ipd141128Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品品种一览", notes = "查询产品品种名称和编码")
    @RequestMapping(value = "/pd/pd_breed",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141128Validator")
    public RsResponse<List<IPD141128RsResult>> findBreedList(@RequestBody RsRequest<IPD141128RsParam> param) {
        RsResponse<List<IPD141128RsResult>> rs = new RsResponse<List<IPD141128RsResult>>();
        //查询后台,获取List数据
        List<IPD141128RsResult> result = this.ipd141128Logic.findListBreed(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品品种一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品品种一览查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("没有查询到产品品种,数据错误！");
        return rs;

    }

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdBreed",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IPD141128Validator")
    public RsResponse<List<PdBreed>> findBreedsList(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<List<PdBreed>> rs = new RsResponse<List<PdBreed>>();
        //查询后台,获取List数据
        BaseParam rsParam = param.getParam();
        List<PdBreed> result = productLogic.findPdBreed(rsParam);//this.ipd141128Logic.findListBreed(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品品种一览查询接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品品种一览查询接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("没有查询到产品品种,数据错误！");
        return rs;

    }
}
