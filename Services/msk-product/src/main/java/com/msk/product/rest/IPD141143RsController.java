package com.msk.product.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141143RsParam;
import com.msk.product.bean.IPD141143RsResult;
import com.msk.product.logic.IPD141143Logic;
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
 * 神农客价盘通道同步接口
 * IPD141143RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "神农客价盘通道同步接口RestController", tags = {"IPD141143RsController"})
public class IPD141143RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141143RsController.class);

    @Autowired
    private IPD141143Logic ipd141143Logic;

    /**
     * 产品信息接口查询
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "神农客价盘通道同步", notes = "提供神农客电商平台价盘等级通道同步接口")
    @RequestMapping(value = "/pd/pd_priceway",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141143RsResult> findPdInformation(@RequestBody RsRequest<IPD141143RsParam> param) {
        RsResponse<IPD141143RsResult> rs = new RsResponse<IPD141143RsResult>();
        //查询后台,获取List数据
        IPD141143RsResult result = this.ipd141143Logic.findListOrderLevel(param.getParam());
        if (result != null) {
            logger.info("查询神农客价盘通道同步接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询神农客价盘通道同步接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("没有查询到数据！");
        return rs;

    }
}
