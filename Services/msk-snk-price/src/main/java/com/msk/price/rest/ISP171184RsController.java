package com.msk.price.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.ISP171184Param;
import com.msk.price.bean.ISP171184Result;
import com.msk.price.bean.PricePlateInfoParam;
import com.msk.price.bean.PricePlateInfoResult;
import com.msk.price.logic.ISP171184Logic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询价盘通道接口Controller
 *
 * @author peng_hao
 * @version 1.0
 */
@RestController
@Api(description = "查询神农客价盘通道RestController", tags = {"ISP171184RsController"})
public class ISP171184RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171184RsController.class);

    @Autowired
    private ISP171184Logic iSP171184Logic;

    /**
     * 查询价盘通道接口
     *
     * @param param
     * @return result
     */
    @ApiOperation(value = "价盘通道", notes = "查询神农客价盘通道接口")
    @RequestMapping(value = "/pd/pd_priceway",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISP171184Result> search(@RequestBody RsRequest<ISP171184Param> param) {
        logger.debug("查询神农客价盘通道");
        RsResponse<ISP171184Result> response = new RsResponse<ISP171184Result>();
        ISP171184Result result = this.iSP171184Logic.findAllList(param);
        if (!CollectionUtils.isEmpty(result.getSearchList())) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询神农客价盘通道同步接口成功!");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("查询神农客价盘通道同步接口失败!");
        }
        response.setResult(result);
        return response;
    }

    /**
     * 查询价盘通道接口(带参数条件)
     *
     * @param param
     * @return result
     */
    @ApiOperation(value = "价盘通道", notes = "查询神农客价盘通道接口(带参数条件)")
    @RequestMapping(value = "/pd/getPriceWay",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISP171184Result> getPriceWay(@RequestBody RsRequest<ISP171184Param> param) {
        logger.debug("查询神农客价盘通道");
        ISP171184Param params = param.getParam();
        params.getFilterMap().put("pdCode", params.getPdCode());
        RsResponse<ISP171184Result> response = new RsResponse<ISP171184Result>();
        ISP171184Result result = this.iSP171184Logic.findListByPdCode(param);
        if (!CollectionUtils.isEmpty(result.getSearchList())) {
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询神农客价盘通道同步接口成功!");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("查询神农客价盘通道同步接口失败!");
        }
        response.setResult(result);
        return response;
    }

}
