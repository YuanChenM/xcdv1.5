package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.bs.bean.IBS2101107Bean;
import com.msk.buyers.bean.IBY121225Param;
import com.msk.buyers.bean.IBY121225Result;
import com.msk.buyers.logic.IBY121225Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.core.entity.ByBuyerPdCla;
import com.msk.core.entity.ByBuyerSalestarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
@RestController
public class IBY121225RsController extends BaseRsController {

    private  static Logger logger = LoggerFactory.getLogger(IBY121225RsController.class);
    @Autowired
    private IBY121225Logic iby121225Logic;


    /**
     * 查询买手冻品管家的买家信息
     * @param request
     * @return
     */
    @RequestMapping(value = "by/buyerInfo/searchExclusive",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<IBY121225Result> searSlInfoBuyer(@RequestBody RsRequest<IBY121225Param> request) throws Exception{
        logger.debug("开始调用查询买手冻品管家的买家信息接口");
        RsResponse<IBY121225Result> response = new RsResponse<IBY121225Result>();
        int count = 0;
        List<IBS2101107Bean> list = new ArrayList<>();

        request.getParam().setBuyerAddr(DbUtils.buildLikeCondition(request.getParam().getBuyerAddr(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setEmployeeName(DbUtils.buildLikeCondition(request.getParam().getEmployeeName(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBuyerName(DbUtils.buildLikeCondition(request.getParam().getBuyerName(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBuyerShop(DbUtils.buildLikeCondition(request.getParam().getBuyerShop(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setMarketId(DbUtils.buildLikeCondition(request.getParam().getMarketId(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBuyerCode(DbUtils.buildLikeCondition(request.getParam().getBuyerCode(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBusiTel(DbUtils.buildLikeCondition(request.getParam().getBusiTel(), DbUtils.LikeMode.PARTIAL));
        request.getParam().setBuyerTypeName(DbUtils.buildLikeCondition(request.getParam().getBuyerTypeName(), DbUtils.LikeMode.PARTIAL));
        Map<String,String> marketingStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        list =  iby121225Logic.findList(request.getParam());
        for(int i=0;i<list.size();i++){
            BaseParam baseParam =new BaseParam();
            baseParam.setFilter("buyerId",list.get(i).getBuyerId());
            if(request.getParam().getSalesTargetType() != null){
                baseParam.setFilter("salesTargetType",request.getParam().getSalesTargetType());
            }
            if(request.getParam().getClassCode() != null){
                baseParam.setFilter("classCode",request.getParam().getClassCode());
            }
            List<ByBuyerSalestarget> byBuyerSalestargetList = new ArrayList<>();
            byBuyerSalestargetList = iby121225Logic.getBuyerSalesTargetList(baseParam);
            list.get(i).setByBuyerSalestargetList(byBuyerSalestargetList);
            List<ByBuyerPdCla> byBuyerPdClaList = new ArrayList<>();
            byBuyerPdClaList = iby121225Logic.getBuyerPdClaList(baseParam);
            list.get(i).setByBuyerPdClaList(byBuyerPdClaList);

            if(marketingStatus.containsKey(list.get(i).getMarketingsStatusCode())){
                String marketingStatusName = marketingStatus.get(list.get(i).getMarketingsStatusCode());
                list.get(i).setMarketingsStatusName(marketingStatusName);
            }else{
                list.get(i).setMarketingsStatusName("");
            }

        }


        IBY121225Result buyerHandResult = new IBY121225Result();
        buyerHandResult.setSlBuyerList(list);
        count = iby121225Logic.getPageCount(request.getParam());
        buyerHandResult.setTotalCount(count);
        buyerHandResult.setPageNo(request.getParam().getPageNo());
        buyerHandResult.setTotalPage(count,request.getParam().getPageCount());

        if(!CollectionUtils.isEmpty(buyerHandResult.getSlBuyerList())){
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            response.setMessage("查询成功");
            response.setResult(buyerHandResult);
        }else{
            response.setStatus(SystemConst.RsStatus.FAIL);
            response.setMessage("没有查询到相关数据");
        }
        logger.debug("调用查询买手冻品管家的买家信息结束");
        return response;
    }
}
