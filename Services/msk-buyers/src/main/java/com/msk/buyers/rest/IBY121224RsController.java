package com.msk.buyers.rest;

import com.hoperun.core.consts.SystemConst;
import com.msk.buyers.bean.IBY121224Param;
import com.msk.buyers.bean.IBY121224Result;
import com.msk.buyers.logic.IBY121224Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
@RestController
public class IBY121224RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBY121224RsController.class);
    @Autowired
    private IBY121224Logic iby121224Logic;


    /**
     * 查询买手店管家会员信息
     * @param request
     * @return
     */
    @RequestMapping(value = "by/buyerInfo/searchBuyerShop",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
        public RsResponse<IBY121224Result> searSlInfoBuyer(@RequestBody RsRequest<IBY121224Param> request) throws Exception{
        logger.debug("开始调用买手店管家会员信息接口");
        RsResponse<IBY121224Result> response = new RsResponse<IBY121224Result>();
        IBY121224Result  buyerHandResult =  iby121224Logic.searchBuyerShop(request.getParam());
        if(!CollectionUtils.isEmpty(buyerHandResult.getBuyerShopList())){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(buyerHandResult);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("没有查询到相关数据");
        }
        logger.debug("调用买手店管家会员信息接口结束");
        return response;
    }
}
