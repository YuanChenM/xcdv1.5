package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141140RsParam;
import com.msk.product.bean.IPD141140RsResult;
import com.msk.product.logic.IPD141140Logic;
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
 * 原料种源信息同步接口
 * IPD141140RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "原料种源信息同步接口RestController", tags = {"IPD141140RsController"})
public class IPD141140RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141140RsController.class);

    @Autowired
    private IPD141140Logic ipd141140Logic;

    /**
     * 产品包装一览查询接口
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "原料种源信息同步", notes = "提供美侍客电商平台产品同步接口")
    @RequestMapping(value = "/pd/pd_mat_list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141140RsResult> findBreedList(@RequestBody RsRequest<IPD141140RsParam> param) {
        RsResponse<IPD141140RsResult> rs = new RsResponse<IPD141140RsResult>();
        //查询后台,获取List数据
        IPD141140RsResult result = this.ipd141140Logic.findListMat(param.getParam());
        if (result != null && result.getSearchList().size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询原料种源信息同步接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询原料种源信息同步接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询原料种源信息同步接口,数据错误！");
        return rs;

    }
}
