package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141132RsParam;
import com.msk.product.bean.IPD141132RsResult;
import com.msk.product.logic.IPD141132Logic;
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
 * 饲养标准指标信息同步接口
 * IPD141132RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "饲养标准指标信息同步接口RestController", tags = {"IPD141132RsController"})
public class IPD141132RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141132RsController.class);

    @Autowired
    private IPD141132Logic ipd141132Logic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "饲养标准指标信息同步", notes = "提供神农客电商平台产品档案卡同步接口")
    @RequestMapping(value = "/pd/pd_fed_list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141132RsResult> findBreedList(@RequestBody RsRequest<IPD141132RsParam> param) {
        RsResponse<IPD141132RsResult> rs = new RsResponse<IPD141132RsResult>();
        //查询后台,获取List数据
        IPD141132RsResult result = this.ipd141132Logic.findListFed(param.getParam());
        if (result != null && result.getSearchList().size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询饲养标准指标信息同步接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询饲养标准指标信息同步接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询饲养标准指标信息同步接口,数据错误！");
        return rs;

    }
}
