package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.SystemConst;
import com.hoperun.core.exception.BusinessException;
import com.msk.buyers.bean.BY121315Bean;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.core.entity.BrBuyerMarketingStatusHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhou_yajun on 2016/7/20.
 */
@Controller
@RequestMapping("BY121315")
public class BY121315Controller extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(BY121315Controller.class);

    /**
     * 买家上线状态信息
     * @param buyerId
     * @return
     */
    @RequestMapping(value = "init/{buyerId}")
    public String init(@PathVariable("buyerId") String buyerId,Model model){
        logger.info("买家上线状态信息管控初始化");
        model.addAttribute("buyerId",buyerId);
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        RsRequest<BaseParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = SystemServerManager.BuyersServerManage.getMarketingStatusControl();
        RsResponse<BY121315Bean> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY121315Bean>>(){});
        if(rsResponse.getStatus() == SystemConst.RsStatus.FAIL){
            throw new BusinessException("更新失败");
        }
        BY121315Bean by121315Bean = rsResponse.getResult();
        model.addAttribute("marketStatus",by121315Bean);
        return "buyers/BY121315";
    }

    /**
     * 买家上线状态信息保存
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public @ResponseBody RsResponse<String> save(BY121315Bean by121315Bean){
        //买家ID
        String buyerId = by121315Bean.getBuyerId();
        //买家正常上线状态
        String marketStatus = by121315Bean.getMarketingsStatus();
        //买家异常上线状态
        String marketExceptionStatus = by121315Bean.getMarketExceptionStatus();
        //买家详细错误信息
        String marketExceptionRemark = by121315Bean.getMarketExceptionRemark();
        BaseParam param = new BaseParam();
        param.setFilter("buyerId",buyerId);
        param.setFilter("marketingsStatus",marketStatus);
        param.setFilter("marketExceptionStatus",marketExceptionStatus);
        param.setFilter("marketExceptionRemark",marketExceptionRemark);
        RsRequest<BaseParam> request = new RsRequest<>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);

        String url = SystemServerManager.BuyersServerManage.getModifyMarketingStatus();
        RsResponse<String> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<String>>(){});
        if(rsResponse.getStatus() == SystemConst.RsStatus.FAIL){
            throw new BusinessException("更新失败");
        }

        BrBuyerMarketingStatusHistory brBuyerMarketingStatusHistory = new BrBuyerMarketingStatusHistory();
        Map<String, String> marketStatu = CodeMasterManager.findCodeMasterMap(BuyersConstant.MarketingsStatus.Type);
        brBuyerMarketingStatusHistory.setBuyerId(by121315Bean.getBuyerId());
        if(marketStatus != null){
            if(marketStatus.equals("01") || marketStatus.equals("02")){
                brBuyerMarketingStatusHistory.setNewStatusClass("1");
                brBuyerMarketingStatusHistory.setNewStatusClassName("营销期");
            }else{
                if(marketStatus.equals("31")){
                    brBuyerMarketingStatusHistory.setNewStatusClass("3");
                    brBuyerMarketingStatusHistory.setNewStatusClassName("异常");
                }else {
                    brBuyerMarketingStatusHistory.setNewStatusClass("2");
                    brBuyerMarketingStatusHistory.setNewStatusClassName("销售期");
                }

            }
            brBuyerMarketingStatusHistory.setNewStatusBreed(by121315Bean.getMarketingsStatus());
            brBuyerMarketingStatusHistory.setNewStatusBreedName(marketStatu.get(by121315Bean.getMarketingsStatus()));
        }
        if(by121315Bean.getMarketExceptionStatus() != null){
            brBuyerMarketingStatusHistory.setNewExceptionStatus(by121315Bean.getMarketExceptionStatus());
            brBuyerMarketingStatusHistory.setNewExceptionStatusName(marketStatu.get(by121315Bean.getMarketExceptionStatus()));
        }

        RsRequest<BrBuyerMarketingStatusHistory> requests = new RsRequest<>();
        requests.setSiteCode("1");
        requests.setAuth("MSK00001");
        requests.setLoginId("msk01");
        requests.setParam(brBuyerMarketingStatusHistory);
//        String urls = "http://localhost:8380/msk-br-api/api/br/brBuyerMarketingStatusHistory/update";
        String urls = SystemServerManager.BuyersReportServerManager.getUpdateBuyerMarketingStatusHistory();
        RsResponse<Integer> response = RestClientUtil.post(urls, requests, new TypeReference<RsResponse<Integer>>() {
        });


        return rsResponse;
    }
}
