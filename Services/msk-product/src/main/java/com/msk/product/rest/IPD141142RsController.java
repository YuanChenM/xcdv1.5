package com.msk.product.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.product.bean.IPD141142RsParam;
import com.msk.product.bean.IPD141142RsResult;
import com.msk.product.logic.IPD141142Logic;
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
 * IPD142142RsController.
 *
 * @author xhy
 */
@RestController
@Api(description = "StandardID一览同步接口RestController", tags = {"IPD141142RsController"})
public class IPD141142RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IPD141142RsController.class);

    /**
     * IPD142142Logic
     */
    @Autowired
    private IPD141142Logic ipd141142Logic;

    /**
     * 查询物流区等级产品信息
     *
     * @param param param
     * @return 结果
     */
    @ApiOperation(value = "StandardID一览同步", notes = "提供美侍客电商平台StandardID产品同步接口")
    @RequestMapping(value = "/pd/pd_standard_list",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IPD141142RsResult> query(@RequestBody RsRequest<IPD141142RsParam> param) {
        logger.info("查询物流区等级产品信息");
        RsResponse<IPD141142RsResult> rs = new RsResponse<IPD141142RsResult>();
        //查询后台,获取List数据
        IPD141142RsResult result = this.ipd141142Logic.findPdStandardIdList(param.getParam());
        if (result != null && result.getSearchList().size() > NumberConst.IntDef.INT_ZERO) {
            logger.info("查询产品标准数据接口成功！DB连接");
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询产品标准数据接口成功！");
            rs.setResult(result);
            return rs;
        }
        rs.setStatus(SystemConst.RsStatus.FAIL);
        rs.setMessage("查询产品标准数据接口,数据错误！");
        return rs;
    }
}
