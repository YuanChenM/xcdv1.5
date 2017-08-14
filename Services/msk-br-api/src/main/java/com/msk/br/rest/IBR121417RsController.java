package com.msk.br.rest;

import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.br.bean.IBR121417RsBean;
import com.msk.br.logic.IBR121417Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.logic.CommonLogic;
import com.msk.core.entity.BrOBuyerInfo;
import com.msk.core.entity.BrOBuyerPdCla;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yuan_zhifei on 2016/11/07.
 */

@RestController
public class IBR121417RsController extends BaseRsController {
    @Autowired
    private IBR121417Logic ibr121417Logic;
    @Autowired
    private CommonLogic commonLogic;

    @RequestMapping(value = "br/buyerReportInfo/_update",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    RsResponse<Integer> updateBuyerReportInfo(@RequestBody RsRequest<IBR121417RsBean> param) {
        RsResponse<Integer> response = new RsResponse<>();
        //获取买家上线状态对应的值
        Map<String, String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        Date currentDate = DateTimeUtil.getCustomerDate();
        BrOBuyerInfo oBuyerInfo = new BrOBuyerInfo();
        String message = "";
        oBuyerInfo = param.getParam().getBrOBuyerInfo();
        int saveOBuyerInfoCount = NumberConst.IntDef.INT_ZERO;
        int saveBuyerPdClaCount= NumberConst.IntDef.INT_ZERO;
        int modifyOBuyerInfoCount = NumberConst.IntDef.INT_ZERO;
        int modifyBuyerPdClaCount = NumberConst.IntDef.INT_ZERO;
        if(oBuyerInfo != null){
            oBuyerInfo.setActTime(currentDate);
            oBuyerInfo.setCrtTime(currentDate);
            oBuyerInfo.setUpdTime(currentDate);
            oBuyerInfo.setActId(param.getLoginId());
            oBuyerInfo.setCrtId(param.getLoginId());
            oBuyerInfo.setUpdId(param.getLoginId());
            if (BuyersConstant.MarketingsStatus.PreRegister.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.NoMarket.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.ActivePeriod.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.StablePeriodCentral.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.StablePeriodStandard.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.EarlyWarnPeriod.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.SalePublicBuyers.equals(oBuyerInfo.getMarketingsStatus()) ||
                    BuyersConstant.MarketingsStatus.OutBusiness.equals(oBuyerInfo.getMarketingsStatus())) {
                oBuyerInfo.setMarketingsStatusName(marketStatus.get(oBuyerInfo.getMarketingsStatus()));
            } else {
                oBuyerInfo.setMarketingsStatusName("");
            }
            int result = this.ibr121417Logic.countOBuyerInfo(oBuyerInfo);
            if(result == NumberConst.IntDef.INT_ZERO){
                saveOBuyerInfoCount = this.ibr121417Logic.save(oBuyerInfo);
            }else{
                modifyOBuyerInfoCount = this.ibr121417Logic.modifyOBuyerInfo(oBuyerInfo);
            }
        }
        List<BrOBuyerPdCla> oBuyerPdClaList = new ArrayList<>();
        oBuyerPdClaList = param.getParam().getBrOBuyerPdClaList();
        if(!CollectionUtils.isEmpty(oBuyerPdClaList)){
            for(BrOBuyerPdCla oBuyerPdCla:oBuyerPdClaList){
                int res = this.ibr121417Logic.countOBuyerPdCla(oBuyerPdCla);
                if(res == NumberConst.IntDef.INT_ZERO){
                    Long maxId = commonLogic.maxId("br_o_buyer_pd_cla", "ID");
                    oBuyerPdCla.setId(maxId);
                    oBuyerPdCla.setActTime(currentDate);
                    oBuyerPdCla.setCrtTime(currentDate);
                    oBuyerPdCla.setUpdTime(currentDate);
                    oBuyerPdCla.setActId(param.getLoginId());
                    oBuyerPdCla.setCrtId(param.getLoginId());
                    oBuyerPdCla.setUpdId(param.getLoginId());
                    saveBuyerPdClaCount = this.ibr121417Logic.saveBuyerPdCla(oBuyerPdCla);
                }else{
                    if(!"true".equals(param.getParam().getIsModify())){
                        modifyBuyerPdClaCount = this.ibr121417Logic.modifyOBuyerPdCla(oBuyerPdCla);
                    }else{
                        modifyBuyerPdClaCount = NumberConst.IntDef.INT_ZERO;
                    }
                }
            }
        }
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setMessage("处理成功");
        response.setResult(saveOBuyerInfoCount);
        return response;
    }
}