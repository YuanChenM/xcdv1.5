package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141103RsParam;
import com.msk.product.logic.IPD141103Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 销售状态一览查询接口
 * IPD141103RsController.
 * 
 * @author xhy
 */
@RestController
@Api(description = "销售状态一览查询接口RestController", tags = {"IPD141103RsController"})
public class IPD141103RsController extends BaseRsController {

    @Autowired
    private IPD141103Logic ipd141103Logic;

    /**
     * 查询销售订单状态
     * 
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "销售订单状态", notes = "查询产品的销售状态定义一览，例如规划，研发，试销")
    @RequestMapping(value = "pd/pd_sale_status",
        method = RequestMethod.POST,
        produces = { MediaType.APPLICATION_JSON_VALUE })
    public RsResponse<List<IPD141103RsParam>> findListSaleStatus() {
        RsResponse<List<IPD141103RsParam>> rs = new RsResponse<List<IPD141103RsParam>>();
        // 查询所有销售状态信息

        List<IPD141103RsParam> result = ipd141103Logic.findListSaleStatus();
        if (result.size() > NumberConst.IntDef.INT_ZERO && result != null) {
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询正常！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("销售状态没有查询到数据！");
        return rs;
    }
}
