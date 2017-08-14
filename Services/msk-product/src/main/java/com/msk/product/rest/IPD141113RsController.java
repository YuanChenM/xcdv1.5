package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141113RsParam;
import com.msk.product.bean.IPD141113RsResult;
import com.msk.product.logic.IPD141113Logic;
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
 * IPD141113RsController.
 *
 * @author yuan_chen
 */
@RestController
@Api(description = "物流区下所有产品信息查询接口RestController", tags = {"IPD141113RsController"})
public class IPD141113RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141113RsController.class);

    /**
     * IPD141113Logic
     */
    @Autowired
    private IPD141113Logic ipd141113Logic;

    /**
     * 查询物流区产品信息
     *
     * @param param param
     * @return 结果
     */
    @ApiOperation(value = "物流区下所有产品信息", notes = "根据制定的物流区编码查询此物流区下的所有产品信息（待定义,供分销管理系统使用）")
    @RequestMapping(value = "/pd/pdBidLogiArea",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141113RsResult> query(@RequestBody RsRequest<IPD141113RsParam> param) {
        logger.info("查询物流区产品信息");
        RsResponse<IPD141113RsResult> rs = new RsResponse<IPD141113RsResult>();
        IPD141113RsResult result = ipd141113Logic.findRsResult(param.getParam());
        if (result.getTotalCount() == NumberConst.IntDef.INT_ZERO) {
            rs.setMessage("未查询到物流区产品信息！");
        } else {
            rs.setMessage("查询物流区产品信息成功！");
        }
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setResult(result);
        return rs;
    }
}
