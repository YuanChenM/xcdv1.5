package com.msk.product.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.PdClasses;
import com.msk.product.bean.IPD141101RsParam;
import com.msk.product.logic.IPD141101Logic;
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
 * 产品分类一览查询接口
 * IPD141101RsController.
 * 
 * @author xhy
 */
@RestController
@Api(description = "产品分类一览查询接口RestController", tags = {"IPD141101RsController"})
public class IPD141101RsController extends BaseRsController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(IPD141101RsController.class);

    @Autowired
    private IPD141101Logic ipd141101Logic;

    @Autowired
    private ProductLogic productLogic;

    /**
     * 产品分类一览查询接口
     * 
     * @return 结果
     * @author xhy
     */
    @ApiOperation(value = "产品分类一览查询", notes = "查询所有产品分类，例如鸡产品，鸭产品")
    @RequestMapping(value = "/pd/pd_classes",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IPD141101RsParam>> createClasses() {
        // 查询所有产品类别信息
        RsResponse<List<IPD141101RsParam>> rs = new RsResponse<List<IPD141101RsParam>>();
        List<IPD141101RsParam> result = ipd141101Logic.findListPd();
        if (result.size() > NumberConst.IntDef.INT_ZERO && result != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据查询成功!");
            rs.setResult(result);
            logger.info("数据查询成功!");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品类别没有查询到数据！");
        return rs;
    }

    /**
     * 产品分类一览查询接口
     *
     * @return 结果
     * @author xhy
     */
    @RequestMapping(value = "/product/findPdClasses",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<PdClasses>> createClasses(@RequestBody RsRequest<BaseParam> param) {
        // 查询所有产品类别信息
        RsResponse<List<PdClasses>> rs = new RsResponse<List<PdClasses>>();
        BaseParam rsParam = param.getParam();
        List<PdClasses> result = productLogic.findPdClasses(rsParam);
        if (result.size() > NumberConst.IntDef.INT_ZERO && result != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("数据查询成功!");
            rs.setResult(result);
            logger.info("数据查询成功!");
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("产品类别没有查询到数据！");
        return rs;
    }

}
