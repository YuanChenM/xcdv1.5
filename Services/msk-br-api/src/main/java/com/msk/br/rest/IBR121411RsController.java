package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.core.utils.StringUtil;

import com.msk.br.bean.IBR12141101RsBean;
import com.msk.br.bean.IBR12141101RsResult;
import com.msk.br.bean.IBR121411RsParam;
import com.msk.br.bean.IBR121411RsResult;
import com.msk.br.logic.IBR121411Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.core.entity.BrBuyerPool;
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
 * 根据买家ID获取所属买家池
 */
@RestController
public class IBR121411RsController extends BaseRsController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(IBR121411RsController.class);

    @Autowired
    private IBR121411Logic ibr121411Logic;

    @RequestMapping(value = "/br/query/brBuyerPoolListByBuyerId",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR121411RsResult> queryBrBuyerPoolByBuyerId(@RequestBody RsRequest<IBR121411RsParam> param) {
        logger.info("根据买家ID获取所属买家池");
        RsResponse<IBR121411RsResult> response = new RsResponse<>();
        if(StringUtil.isNullOrEmpty(param.getParam().getBuyerId())){
            throw new BusinessException("买家ID必须传入");
        }
        if(param.getParam().getPageCount() != NumberConst.IntDef.INT_ZERO){
            param.getParam().setPaging(true);
        }
        IBR121411RsResult buyerPoolResult = new IBR121411RsResult();
        List<BrBuyerPool> brBuyerPoolList = ibr121411Logic.findPageList(param.getParam(), buyerPoolResult);
        buyerPoolResult.setBrBuyerPoolList(brBuyerPoolList);
        response.setResult(buyerPoolResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);

        return response;
    }

    //批量查询所属买家池信息
    @RequestMapping(value = "/br/PoolsByBuyerIds/query",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBR12141101RsResult> queryPoolsByBuyerIds(@RequestBody RsRequest<IBR121411RsParam> param) {
        logger.info("批量获取所属买家池");
        RsResponse<IBR12141101RsResult> response = new RsResponse<>();
        List<IBR12141101RsBean> brBuyerPoolList = new ArrayList<>();
        if (param.getParam()!=null){
             brBuyerPoolList = ibr121411Logic.getBrBuyerPoolList(param.getParam());
        }
        IBR12141101RsResult buyerPoolResult = new IBR12141101RsResult();
        buyerPoolResult.setPoolList(brBuyerPoolList);
        response.setResult(buyerPoolResult);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        return response;
    }



}


