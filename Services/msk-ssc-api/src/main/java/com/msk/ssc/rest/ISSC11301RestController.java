package com.msk.ssc.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.ssc.bean.*;
import com.msk.ssc.logic.SSC11301Logic;
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
 * 产品共通接口
 * Created by yuan_zhifei on 2016/6/29.
 */
@RestController
public class ISSC11301RestController extends BaseRsController {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ISSC11301RestController.class);

    @Autowired
    private SSC11301Logic ssc11301Logic;

    /**
     * 批量查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findSscBidBasicInfoList",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<SSC11301RsPageResult> findSscBidBasicInfoList(@RequestBody RsRequest<SSC11301RsParam> param) {
        logger.info("-----中标确认书一览信息-----");
        RsResponse<SSC11301RsPageResult> response = new RsResponse<>();
        SSC11301RsPageResult ssc11301RsPageResult = new SSC11301RsPageResult();
        int count = this.ssc11301Logic.getPageCount(param.getParam());
        List<SSC11301RsBean> res = null;
     /*   if (count != NumberConst.IntDef.INT_ZERO) {*/
            res = ssc11301Logic.findPageList(param.getParam(), SSC11301RsBean.class);
            ssc11301RsPageResult.setSSC11301RsBean(res);
            ssc11301RsPageResult.setTotalCount(count);
            response.setResult(ssc11301RsPageResult);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("处理成功");
     /*   } else {
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }*/
        return response;
    }


    /**
     * 查询未关联合同的中标产品信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/findNoRelatedBidBase",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public  RsResponse<SSC11301RsPageResult> findNoRelatedBidBase(@RequestBody RsRequest<SSC11301Param> param) {
        logger.info("查询未关联合同的中标产品详细信息");

        RsResponse<SSC11301RsPageResult> response = new RsResponse<>();
        SSC11301RsPageResult ssc11301RsPageResult = new SSC11301RsPageResult();
        List<SSC11301RsBean> res =  this.ssc11301Logic.findNoRelatedBidBase(param.getParam());
        ssc11301RsPageResult.setSSC11301RsBean(res);
        response.setResult(ssc11301RsPageResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("操作成功");
        return response;
    }


    /**
     * 根据中标编号，查询中标数据是否存在
     * @param param
     * @return
     */
    @RequestMapping(value = "/ssc/checkBidBaseExist",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public  RsResponse<SSC11301RsBean> checkBidBaseExist(@RequestBody RsRequest<SSC11301Param> param) {

        logger.info("根据中标编号，查询中标数据是否存在");
        RsResponse<SSC11301RsBean> response = new RsResponse<>();
        SSC11301RsBean res =  this.ssc11301Logic.checkBidBaseExist(param.getParam());
        if(res!=null){
            response.setResult(res);
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("操作成功");
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("数据不存在");
        }

        return response;
    }



}


