package com.msk.bs.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.*;
import com.msk.bs.logic.IBA2141122RsLogic;
import com.msk.bs.utils.CommRestUtil;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
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
 * 买手销售订单
 * Created by ni_shaotang on 2016/10/11.
 */
@RestController
@Api(description = "买手销售订单RestController", tags = {"IBA2141122RsController"})
public class IBA2141122RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141122RsController.class);

    @Autowired
    private IBA2141122RsLogic iba2141122RsLogic;

    @ApiOperation(value = "查询所属买手销售订单", notes = "根据买手code获取买手销售订单")
    @RequestMapping(value = "/bs/buyer/bss/list/_query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141122Bean>> findBuyerBss(@RequestBody RsRequest<IBA2141122RsParam> request) {
        RsResponse<List<IBA2141122Bean>> response = new RsResponse<List<IBA2141122Bean>>();
        IBA2141122RsParam param = request.getParam();
        param.setOrderSource("1");
        param.setPageCount(NumberConst.IntDef.INT_TIMEOUTS);
        List<IBA2141122Bean> list = iba2141122RsLogic.findBuyerBss(param);
        if (null != list && list.size() > 0) {
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setResult(list);
        }else {
            response.setMessage("未查询到数据");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }
    @ApiOperation(value = "查询所属买手销售订单详情", notes = "根据买手code获取买手销售订单")
    @RequestMapping(value = "/bs/buyer/bss/detail/_query", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBA2141123Bean> findBuyerBssDetail(@RequestBody RsRequest<IBA2141123RsParam> request) {
        RsResponse<IBA2141123Bean> response = new RsResponse<IBA2141123Bean>();
        IBA2141123RsParam param = request.getParam();
        RsResponse<IBA2141123RsResult> result = CommRestUtil.findBssDetail(param);
        List<IBA2141123Bean> list = result.getResult().getOrders();
        if (null != list && list.size() > 0) {
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setResult(list.get(0));
        }else {
            response.setMessage("未查询到数据");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        return response;
    }
}
