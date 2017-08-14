package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.IBS2101116Bean;
import com.msk.bs.bean.IBS2101116Param;
import com.msk.bs.bean.IBS2101116Result;
import com.msk.bs.logic.IBS2101116RsLogic;
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
 * 查询买家对应的买手信息
 * Created by ni_shaotang on 2016/8/1.
 */
@RestController
@Api(description = "查询买家对应的买手信息RestController", tags = {"IBS2101116RsController"})
public class IBS2101116RsController extends BaseRsController {
    private static Logger logger = LoggerFactory.getLogger(IBS2101114RsController.class);

    @Autowired
    private IBS2101116RsLogic ibs2101116RsLogic;

    @ApiOperation(value = "买家对应的买手信息 ", notes = "根据买家ID查询买手信息")
    @RequestMapping(value = "/bs/searchBuyerId", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101116Result> searchBuyerId(@RequestBody RsRequest<IBS2101116Param> request) {
        logger.info("查询买家对应的买手信息开始");
        RsResponse<IBS2101116Result> response = new  RsResponse<IBS2101116Result>();
        IBS2101116Result result = new IBS2101116Result();
        List<String> list = request.getParam().getBuyerIdList();//获取参数列表
        //判断参数是否为空
        if (null != list && list.size() > 0) {
            //获取买手列表
            List<IBS2101116Bean> resultList = ibs2101116RsLogic.findBuyerList(list);
            result.setResultList(resultList);
            response.setMessage("查询成功");
            response.setStatus(SystemConst.RsStatus.SUCCESS);
        }else {
            response.setMessage("查询失败，参数不能为空");
            response.setStatus(SystemConst.RsStatus.FAIL);
        }
        response.setResult(result);
        logger.info("查询买家对应的买手信息结束");
        return response;
    }
}
