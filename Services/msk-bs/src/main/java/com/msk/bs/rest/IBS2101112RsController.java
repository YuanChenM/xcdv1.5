package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.IBS2101112Bean;
import com.msk.bs.bean.IBS2101112RsParam;
import com.msk.bs.bean.IBS2101112RsResult;
import com.msk.bs.logic.IBS2101112RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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

import java.util.List;

/**
 * Created by cx on 2016/4/14.
 */
@RestController
@Api(description = "查询买手冻品管家的买家履历信息RestController", tags = {"IBS2101112RsController"})
public class IBS2101112RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101112RsController.class);

    @Autowired
    private IBS2101112RsLogic ibS2101112RsLogic;

    /**
     * 查询买手冻品管家的买家履历信息
     * @param request request
     * @return rs
     */
    @ApiOperation(value = "买家履历信息", notes = "查询买手冻品管家的买家履历信息接口")
    @RequestMapping(value = "/bs/slInfo/slExclusive/searchHis",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101112RsResult> querySlExclusive(@RequestBody RsRequest<IBS2101112RsParam> request) {
        logger.debug("查询买手冻品管家的买家履历信息接口");
        RsResponse<IBS2101112RsResult> rs = new RsResponse<IBS2101112RsResult>();
        IBS2101112RsResult result = new IBS2101112RsResult();
        List<IBS2101112Bean> slBuyerList = ibS2101112RsLogic.findPageList(request.getParam(),result);
        if (!CollectionUtils.isEmpty(slBuyerList) && slBuyerList.size() > 0) {
            result.setSlBuyerList(slBuyerList);
            rs.setResult(result);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("查询买手冻品管家的买家履历信息成功");
        } else {
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("暂不存在数据");
        }
        return rs;
    }

}
