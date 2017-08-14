package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141136RsParam;
import com.msk.product.bean.IPD141136RsResult;
import com.msk.product.logic.IPD141136Logic;
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
 * 安全指标信息同步接口
 * IPD141136RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "安全指标信息同步接口RestController", tags = {"IPD141136RsController"})
public class IPD141136RsController extends BaseRsController {

    /**
     * logger 日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141136RsController.class);

    @Autowired
    private IPD141136Logic ipd141136Logic;

    /**
     * 产品等级&卫生质量标准
     *
     * @return RsResponse 结果
     * @author xhy
     */
    @ApiOperation(value = "安全指标信息同步", notes = "提供神农客电商平台产品档案卡同步接口")
    @RequestMapping(value = "/pd/pd_sft_list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141136RsResult> findSftList(@RequestBody RsRequest<IPD141136RsParam> param) {
        RsResponse<IPD141136RsResult> rs = new RsResponse<IPD141136RsResult>();
        //查询后台,获取List数据
        IPD141136RsResult result = this.ipd141136Logic.findSftList(param.getParam());
        if (result != null && result.getSearchList().size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询安全指标信息同步接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询安全指标信息同步接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询安全指标信息同步接口,数据错误！");
        return rs;

    }
}
