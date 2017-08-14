package com.msk.bs.rest;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.bs.bean.IBS2101105RsResult;
import com.msk.bs.logic.BS2101102Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.SlHouseAccount;
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

import java.util.List;

/**
 * 获取冻品管家列表
 * Created by ni_shaotang on 2016/8/18.
 */
@RestController
@Api(description = "获取冻品管家列表RestController", tags = {"IBA2141202RsController"})
public class IBA2141202RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141202RsController.class);
    @Autowired
    private BS2101102Logic bS2101102Logic;

    /**
     * 根据买手id查询冻品管家列表
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "冻品管家列表", notes = "获取冻品管家列表接口")
    @RequestMapping(value = "/ba/searchHouseList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBS2101105RsResult>> searchHouseList(@RequestBody RsRequest<SlHouseAccount> request) {
        logger.info("开始查询冻品管家列表");
        RsResponse<List<IBS2101105RsResult>> response = new RsResponse<List<IBS2101105RsResult>>();
        BasePageParam basePageParam = new BasePageParam();
        SlHouseAccount houseAccount = request.getParam();
        basePageParam.getFilterMap().put("slCode", houseAccount.getSlCode());
        basePageParam.getFilterMap().put("houseAccount", houseAccount.getHouseAccount());
        basePageParam.getFilterMap().put("houseCode", houseAccount.getHouseCode());
        PageResult<IBS2101105RsResult> pageResult = bS2101102Logic.findBS2101102List(basePageParam);
        List<IBS2101105RsResult> result = pageResult.getData();
        response.setResult(result);
        return response;
    }
}
