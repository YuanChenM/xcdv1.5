package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.IBA2141204RsParam;
import com.msk.bs.bean.IBA2141204RsResult;
import com.msk.bs.logic.IBA2141204RsLogic;
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
 * Created by zhu_kai1 on 2016/9/18.
 * 根据买家ID查询买家与管家之间的关系
 */
@RestController
@Api(description = "根据买家ID查询买家与管家之间的关系RestController", tags = {"IBA2141204RsController"})
public class IBA2141204RsController extends BaseRsController {

    private static Logger logger = LoggerFactory.getLogger(IBA2141204RsController.class);

    @Autowired
    IBA2141204RsLogic iba2141204RsLogic;

    @ApiOperation(value = "查询买家与管家之间的关系", notes = "根据买家ID查询买家与管家之间的关系接口")
    @RequestMapping(value = "/ba/findHouseCodeByBuyerId", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<List<IBA2141204RsResult>> findHouseCodeByBuyerId(@RequestBody RsRequest<IBA2141204RsParam> param) {
        logger.info("开始调用根据买家ID查询买家与管家之间的关系接口");
        RsResponse<List<IBA2141204RsResult>> response = new RsResponse<List<IBA2141204RsResult>>();
        List<IBA2141204RsResult> rsResultList = iba2141204RsLogic.findHouseCodeByBuyerId(param.getParam());
        if (!CollectionUtils.isEmpty(rsResultList)) {
            response.setResult(rsResultList);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
        } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("处理失败");
        }
        logger.info("调用根据买家ID查询买家与管家之间的关系接口结束");
        return response;
    }
}
