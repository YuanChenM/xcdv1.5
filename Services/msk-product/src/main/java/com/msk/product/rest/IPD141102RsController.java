package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdGrade;
import com.msk.product.bean.IPD141102RsParam;
import com.msk.product.logic.IPD141102Logic;
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
 * 查询产品等级
 * IPD141102RsController.
 * 
 * @author xhy
 */
@RestController
@Api(description = "产品等级编码查询接口RestController", tags = {"IPD141102RsController"})
public class IPD141102RsController extends BaseRsController {

    @Autowired
    private IPD141102Logic ipd141102Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 产品等级编码查询接口
     * 
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "产品等级编码查询接口", notes = "查询产品的定义等级和编码，例如A1，A2，A3")
    @RequestMapping(value = "/pd/pd_grade",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IPD141102RsParam>> findListGrade() {

        RsResponse<List<IPD141102RsParam>> rs = new RsResponse<List<IPD141102RsParam>>();
        // 查询所有产品等级信息
        List<IPD141102RsParam> res = this.ipd141102Logic.findListGrade();
        if (res.size() > NumberConst.IntDef.INT_ZERO && res != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品等级没有查询到数据！");
        return rs;
    }

    /**
     * 产品等级编码查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdGrade",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<PdGrade>> findListGrades(@RequestBody RsRequest<BaseParam> param) {

        RsResponse<List<PdGrade>> rs = new RsResponse<List<PdGrade>>();
        // 查询所有产品等级信息
        List<PdGrade> res = this.productLogic.findPdGrade(param.getParam());
        if (res.size() > NumberConst.IntDef.INT_ZERO && res != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询正常！");
            rs.setResult(res);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品等级没有查询到数据！");
        return rs;
    }
}
