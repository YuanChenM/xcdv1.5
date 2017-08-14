package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141131RsParam;
import com.msk.product.bean.IPD141131RsResult;
import com.msk.product.logic.IPD141131Logic;
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
 * 原种种源信息同步接口
 * IPD141131RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "原种种源指标信息同步接口RestController", tags = {"IPD141131RsController"})
public class IPD141131RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141131RsController.class);

    @Autowired
    private IPD141131Logic ipd141131Logic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "原种种源指标信息同步", notes = "提供神农客电商平台产品档案卡同步接口")
    @RequestMapping(value = "/pd/pd_org_list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141131RsResult> findBreedList(@RequestBody RsRequest<IPD141131RsParam> param) {
        RsResponse<IPD141131RsResult> rs = new RsResponse<IPD141131RsResult>();
        //查询后台,获取List数据
       IPD141131RsResult result = this.ipd141131Logic.findListOrg(param.getParam());
        if (result != null && result.getSearchList().size() > NumberConst.IntDef.INT_ZERO) {
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
