package com.msk.buyers.rest;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.utils.DateTimeUtil;
import com.msk.buyers.bean.BY121315Bean;
import com.msk.buyers.logic.IBY121315Logic;
import com.msk.common.base.BaseRsController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class IBY121315RsController extends BaseRsController {

    @Autowired
    private IBY121315Logic iby121315Logic;
    /**
     * 买家上线状态控制
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/marketingStatus/find", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<BY121315Bean> marketingStatusControl(@RequestBody RsRequest<BaseParam> request){
        RsResponse<BY121315Bean> response = new RsResponse<>();
        BaseParam param = request.getParam();
        BY121315Bean buyerBasicInfo = iby121315Logic.findOne(param);
        if(null == buyerBasicInfo){
            response.setStatus(SystemConst.RsStatus.FAIL);
            return response;
        }
        Map<String,String> marketStatus = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        //营销期状态基础数据
        TreeMap<String,String> marketPeriod = new TreeMap<>();
        //销售期状态基础数据
        TreeMap<String,String> salesPeriod = new TreeMap<>();
        //异常状态基础数据
        TreeMap<String,String> exceptional = new TreeMap<>();

        for (String key: marketStatus.keySet()){
            if(key.equals(BuyersConstant.MarketingsStatus.PreRegister) || key.equals(BuyersConstant.MarketingsStatus.NoMarket)){
                marketPeriod.put(key,marketStatus.get(key));
            }else if(key.equals(BuyersConstant.MarketingsStatus.OutBusiness) || key.equals(BuyersConstant.MarketingsStatus.InfoError)){
                exceptional.put(key,marketStatus.get(key));
            }else{
                salesPeriod.put(key,marketStatus.get(key));
            }
        }
        buyerBasicInfo.setMarketPeriod(marketPeriod);
        buyerBasicInfo.setSalesPeriod(salesPeriod);
        buyerBasicInfo.setExceptional(exceptional);
        response.setStatus(SystemConst.RsStatus.SUCCESS);
        response.setResult(buyerBasicInfo);

        return response;
    }

    /**
     * 买家上线状态变更保存
     * @param request
     * @return
     */
    @RequestMapping(value = "/by/marketingStatus/modify", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public RsResponse<String> modifyMarketingStatus(@RequestBody RsRequest<BaseParam> request){
        RsResponse<String> response = new RsResponse<>();
        BaseParam param = request.getParam();
            Date currentDate = DateTimeUtil.getCustomerDate();
            param.setUpdId(request.getLoginId());
            param.setUpdTime(currentDate);
            //更新买家上线状态
            int result = iby121315Logic.modify(param);
            if (result == NumberConst.IntDef.INT_ZERO) {
                response.setStatus(SystemConst.RsStatus.FAIL);
                return response;
            }
            response.setStatus(SystemConst.RsStatus.SUCCESS);
            //更新买家编码
            iby121315Logic.modifyBuyerCode(param);



        return response;
    }
}
