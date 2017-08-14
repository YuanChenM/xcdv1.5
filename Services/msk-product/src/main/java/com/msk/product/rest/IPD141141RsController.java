package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141141RsParam;
import com.msk.product.bean.IPD141141RsResult;
import com.msk.product.logic.IPD141141Logic;
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
 * IPD141141RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "物流区下所有等级产品信息查询接口RestController", tags = {"IPD141141RsController"})
public class IPD141141RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141141RsController.class);

    /**
     * IPD141141Logic
     */
    @Autowired
    private IPD141141Logic ipd141141Logic;

    /**
     * 查询物流区等级产品信息
     *
     * @param param param
     * @return 结果
     */
    @ApiOperation(value = "原料种源信息同步", notes = "根据制定的物流区编码查询此物流区下的所有包含等级的产品信息")
    @RequestMapping(value = "/pd/pdGradeBidLogiArea",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141141RsResult> query(@RequestBody RsRequest<IPD141141RsParam> param) {
        logger.info("查询物流区等级产品信息");
        RsResponse<IPD141141RsResult> rs = new RsResponse<IPD141141RsResult>();
        IPD141141RsResult result = ipd141141Logic.findRsResult(param.getParam());
        if (result.getTotalCount() == NumberConst.IntDef.INT_ZERO) {
            rs.setMessage("未查询到物流区产品信息！");
        } else {
            rs.setMessage("查询物流区等级产品信息成功！");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(result);
        return rs;
    }
}
