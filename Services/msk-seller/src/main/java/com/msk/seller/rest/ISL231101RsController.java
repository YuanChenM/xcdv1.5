package com.msk.seller.rest;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.plug.spring.annotation.Validator;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.seller.bean.ISL231101RsParam;
import com.msk.seller.bean.ISL231101RsResult;
import com.msk.seller.logic.ISL231101RsLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gyh on 2016/1/14.
 */
@RestController
public class ISL231101RsController extends BaseRsController {
    /**logger*/
    private static Logger logger = LoggerFactory.getLogger(ISL231101RsController.class);
    @Autowired
    private ISL231101RsLogic isl231101Logic;

    /**
     * 卖家信息接口
     * @param param param
     * @return 卖家信息
     */
    @RequestMapping(value = "/sl/slInfo",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Validator(validatorClass = "com.msk.seller.validator.ISL231101Validator")
    public RsResponse<List<ISL231101RsResult>> querySlInfo(@RequestBody RsRequest<ISL231101RsParam> param){
        RsResponse<List<ISL231101RsResult>> rs=new RsResponse<List<ISL231101RsResult>>();
        ISL231101RsParam isl231101RsParam=param.getParam();
        List<ISL231101RsResult> SlInfoList  = isl231101Logic.findSlInfo(isl231101RsParam);
        if (null != SlInfoList && SlInfoList.size() > 0) {
            rs.setResult(SlInfoList);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("取得卖家信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("卖家信息不存在");
        }
        return rs;
    }

    /**
     * 生产商信息接口
     * @param param param
     * @return 卖家信息
     */
    @RequestMapping(value = "/sl/findEp",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<ISL231101RsResult> queryEPName(@RequestBody RsRequest<BasePageParam> param){


        RsResponse<ISL231101RsResult> rs=new RsResponse<ISL231101RsResult>();
        BasePageParam basePageParam=new BasePageParam();
        basePageParam = param.getParam();
        Map<String,String> epNameMap = new HashMap<String,String>();
        ISL231101RsResult iSL231101RsResult =new ISL231101RsResult();
        List<ISL231101RsResult> SlInfoList  = isl231101Logic.findEPName(basePageParam);
        if (null != SlInfoList && SlInfoList.size() > 0) {
            for(ISL231101RsResult result : SlInfoList){
                epNameMap.put(result.getSlCode(),result.getEpName());
            }

            iSL231101RsResult.setEpNameMap(epNameMap);
            rs.setResult(iSL231101RsResult);
            rs.setStatus(SystemConst.RsStatus.SUCCESS);
            rs.setMessage("取得生产商信息成功");
        }else{
            rs.setStatus(SystemConst.RsStatus.FAIL);
            rs.setMessage("生产商信息不存在");
        }
        return rs;
    }
}
