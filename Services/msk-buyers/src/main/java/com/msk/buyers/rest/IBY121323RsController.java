package com.msk.buyers.rest;

import com.hoperun.core.consts.SystemConst;
import com.hoperun.jdbc.utils.DbUtils;
import com.msk.buyers.bean.IBY121323Bean;
import com.msk.buyers.bean.IBY121323RsParam;
import com.msk.buyers.bean.IBY121323RsResult;
import com.msk.buyers.logic.IBY121323Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.consts.CodeMasterConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tao_zhifa on 2016/9/13.
 */
@RestController
public class IBY121323RsController extends BaseRsController{

    @Autowired
    private IBY121323Logic iby121323Logic;

    @RequestMapping(value = "/by/buyers/query",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<IBY121323RsResult> queryBuyers(@RequestBody RsRequest<IBY121323RsParam> param){
        RsResponse<IBY121323RsResult> rs = new RsResponse<>();
        List<IBY121323Bean> iby121323BeanList = new ArrayList<>();
        Map<String,String> map = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);

        param.getParam().setAccountName(DbUtils.buildLikeCondition(param.getParam().getAccountName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setBusiTel(DbUtils.buildLikeCondition(param.getParam().getBusiTel(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setTelNo(DbUtils.buildLikeCondition(param.getParam().getTelNo(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setStoreNo(DbUtils.buildLikeCondition(param.getParam().getStoreNo(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setBuyerCode(DbUtils.buildLikeCondition(param.getParam().getBuyerCode(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setBossName(DbUtils.buildLikeCondition(param.getParam().getBossName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setSuperiorName(DbUtils.buildLikeCondition(param.getParam().getSuperiorName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setBuyerName(DbUtils.buildLikeCondition(param.getParam().getBuyerName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setBuyerAddr(DbUtils.buildLikeCondition(param.getParam().getBuyerAddr(), DbUtils.LikeMode.PARTIAL));
//        param.getParam().setMarketingsStatusName(DbUtils.buildLikeCondition(param.getParam().getMarketingsStatusName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setLgcsAreaName(DbUtils.buildLikeCondition(param.getParam().getLgcsAreaName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setMarketName(DbUtils.buildLikeCondition(param.getParam().getMarketName(), DbUtils.LikeMode.PARTIAL));
        param.getParam().setPaging(true);
        if(param.getParam().getMarketingsStatus() != null && param.getParam().getMarketingsStatus() != ""){
            String [] marketStatusArray = param.getParam().getMarketingsStatus().split(",");
            param.getParam().setMarketingsStatusArray(marketStatusArray);
        }


        Map<String,String> marketingStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        iby121323BeanList = iby121323Logic.findList(param.getParam());

        for(int i=0;i<iby121323BeanList.size();i++){
            String marketingStatusName = marketingStatus.get(iby121323BeanList.get(i).getMarketingsStatusCode());
            iby121323BeanList.get(i).setMarketingsStatusName(marketingStatusName);
        }
        int count = iby121323Logic.getPageCount(param.getParam());
        IBY121323RsResult iby121323RsResult = new IBY121323RsResult();
        iby121323RsResult.setBuyerList(iby121323BeanList);
        iby121323RsResult.setTotalCount(count);
        iby121323RsResult.setPageNo(param.getParam().getPageNo());
        iby121323RsResult.setTotalPage(count,param.getParam().getPageCount());
        rs.setResult(iby121323RsResult);
        rs.setStatus(SystemConst.RsStatus.SUCCESS);
        rs.setMessage("处理成功");
        return rs;

    }

}
