package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.hoperun.core.utils.StringUtil;
import com.msk.bs.bean.*;
import com.msk.bs.logic.IBS2101112RsLogic;
import com.msk.bs.logic.IBS2101113RsLogic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 解除买手和冻品管家之间的关系
 * Created by ni_shaotang on 2016/7/8.
 */
@RestController
@Api(description = "解除买手和冻品管家之间的关系RestController", tags = {"IBS2101113RsController"})
public class IBS2101113RsController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBS2101113RsController.class);

    @Autowired
    private IBS2101113RsLogic iBS2101113RsLogic;
    /**
     * 解除买手和冻品管家之间的关系
     *
     * @param param
     * @return rs
     */
    @ApiOperation(value = "解除关系", notes = "解除买手和冻品管家之间的关系,并自动解除冻品管家和买家之间的绑定关系")
    @RequestMapping(value = "/bs/slInfo/releaseHouseKeeper", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<T> releaseHouseKeeper(@RequestBody RsRequest<IBS2101113RsParam> param) {
        logger.debug("解除买手和冻品管家之间的关系");
        IBS2101113RsParam params = param.getParam();
        RsResponse<T> rs = new RsResponse<T>();
        //获取当前时间
        Date nowDate = DateTimeUtil.getCustomerDate();
        params.setCrtId(param.getAuth());
        params.setUpdId(param.getAuth());
        params.setActId(param.getAuth());
        params.setCrtTime(nowDate);
        params.setUpdTime(nowDate);
        params.setActTime(nowDate);
        //添加买手code条件
        if(StringUtil.isNullOrEmpty(params.getSlCode())){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("买手Code不能为空！");
        }else if(StringUtil.isNullOrEmpty(params.getHouseCode())){
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("管家Code不能为空！");
        }else{
            rs = iBS2101113RsLogic.releaseRelationship(params);
        }
        return rs;
    }

}
