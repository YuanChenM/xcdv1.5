package com.msk.bs.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.bs.bean.*;
import com.msk.bs.logic.IBS2101126RsLogic;
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
 * 根据买手账号查询买手id和买手编码
 * Created by ren_qiang on 2016/8/29.
 */
@RestController
@Api(description = "根据用户帐号获得专属买手ID和CODE RestController", tags = {"IBS2101126RsController"})
public class IBS2101126RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBS2101126RsController.class);

    @Autowired
    private IBS2101126RsLogic ibs2101126RsLogic;
    @ApiOperation(value = "获得专属买手ID和CODE", notes = "根据用户帐号获得专属买手ID和CODE接口")
    @RequestMapping(value = "/bs/findSlcodeAndDisList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBS2101126RsResult> findSlcodeAndDisList(@RequestBody RsRequest<IBS2101126RsParam> request){
        logger.info("开始根据买手账号查询买手id和买手编码");
        RsResponse<IBS2101126RsResult> rsResponse = new RsResponse<IBS2101126RsResult>();
        /**添加request非空判断 2016/10/13 任强 Start*/
        if(null != request){
            IBS2101126RsParam param = request.getParam();
            if(null != param){
                if(!CollectionUtils.isEmpty(param.getUserAccountList())){
                    List<IBS2101126RsBean> result = ibs2101126RsLogic.findSlcodeAndDisList(param);
                    if(!CollectionUtils.isEmpty(result)){
                        IBS2101126RsResult ibs2101126RsResult = new IBS2101126RsResult();
                        ibs2101126RsResult.setSlList(result);
                        rsResponse.setResult(ibs2101126RsResult);
                        rsResponse.setStatus(SystemConst.RsStatus.SUCCESS);
                        rsResponse.setMessage("处理成功");
                    }
                    else{
                        rsResponse.setResult(null);
                        rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                        rsResponse.setMessage("未能根据条件查询到数据");
                    }
                }
                else{
                    rsResponse.setResult(null);
                    rsResponse.setStatus(SystemConst.RsStatus.FAIL);
                    rsResponse.setMessage("传入的list不能为空");
                }


            }
        }
        /**添加request非空判断 2016/10/13 任强 End*/
        logger.info("结束根据买手账号查询买手id和买手编码");
        return  rsResponse;

    }

}
