package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdMachining;
import com.msk.product.bean.IPD141104RsParam;
import com.msk.product.bean.IPD141104RsResult;
import com.msk.product.logic.IPD141104Logic;
import com.msk.product.logic.ProductLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品加工程度查询接口
 * IPD141104RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "产品二级分类一览查询接口RestController", tags = {"IPD141104RsController"})
public class IPD141104RsController extends BaseRsController {

    @Autowired
    private ProductLogic productLogic;

    @Autowired
    private IPD141104Logic ipd141104Logic;

    /**
     * 产品加工程度查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品加工程度", notes = "查询产品加工程度名称和编码")
    @RequestMapping(value = "/pd/pd_machining",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.product.validator.IPD141104Validator")
    public RsResponse<List<IPD141104RsResult>> findMachining(@RequestBody RsRequest<IPD141104RsParam> param) {
        RsResponse<List<IPD141104RsResult>> rs = new RsResponse<List<IPD141104RsResult>>();
        // 查询所有产品加工程度
        List<IPD141104RsResult> result = this.ipd141104Logic.findListMaching(param.getParam());
        if (result.size() > NumberConst.IntDef.INT_ZERO && result != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品二级分类一览查询接口,查询正常！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品二级分类一览查询接口,没有查询到数据！");
        return rs;
    }

    /**
     * 产品加工程度查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdMachining",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Validator(validatorClass = "com.msk.product.validator.IPD141104Validator")
    public RsResponse<List<PdMachining>> findMachinings(@RequestBody RsRequest<BaseParam> param) {
        RsResponse<List<PdMachining>> rs = new RsResponse<List<PdMachining>>();
        // 查询所有产品加工程度
        BaseParam rsParam = param.getParam();
        List<PdMachining> result = this.productLogic.findPdMachining(rsParam);
        if (result.size() > NumberConst.IntDef.INT_ZERO && result != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("产品二级分类一览查询接口,查询正常！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品二级分类一览查询接口,没有查询到数据！");
        return rs;
    }
}
