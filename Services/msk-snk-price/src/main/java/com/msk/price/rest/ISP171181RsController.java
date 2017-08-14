package com.msk.price.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.price.bean.DemandParam;
import com.msk.price.bean.DemandResult;
import com.msk.price.logic.ISP171181Logic;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 获取供应商产品分销数量接口Controller
 *
 * @author yang_yang
 * @version 1.0
 */
@RestController
@Api(description = "获取供应商产品分销数量RestController", tags = {"ISP171181RsController"})
public class ISP171181RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISP171181RsController.class);

    @Autowired
    private ISP171181Logic logic;

    /**
     * 获取供应商产品分销数量接口调用
     *
     * @param param
     * @return result
     */
    @ApiOperation(value = "分销数量", notes = "获取供应商产品分销数量接口")
    @RequestMapping(value = "/sp/getSupDistributeDemand",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<DemandResult>> getSupDistributeDemand(@RequestBody RsRequest<DemandParam> param) {
        logger.debug("获取供应商产品分销数量接口调用 start");

        RsResponse<List<DemandResult>> rs = new RsResponse<List<DemandResult>>();

        List<DemandResult> demandResultList = new ArrayList<DemandResult>();

        DemandParam rsParam = param.getParam();

        if (rsParam == null || StringUtil.isEmpty(rsParam.getDemandMonth())) {
            rs.setResult(demandResultList);
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("查询分销数量接口分销月度不能为空！");

            return rs;
        }

        demandResultList = logic.getDemandList(rsParam);

        rs.setResult(demandResultList);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("获取供应商产品分销数量接口调用成功！");

        logger.debug("获取供应商产品分销数量接口调用 end");
        return rs;
    }

}
