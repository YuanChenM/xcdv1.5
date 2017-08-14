package com.msk.buyers.controller;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.jdbc.bean.PageResult;
import com.msk.buyers.bean.BY12130302Bean;
import com.msk.buyers.bean.BY12130302Result;
import com.msk.buyers.bean.BY12130302RsParam;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.business.constant.BuyersConstant;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查看冻品管家列表
 */
@Controller
@RequestMapping("BY12130302")
public class BY12130302Controller extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(BY12130302Controller.class);

    /**
     * 初始化页面
     *
     * @param model Model
     * @return 页面
     */
    @RequestMapping(value = "init/{buyerId}/{marketingStatus}",method = RequestMethod.POST)
    public String init(@PathVariable("buyerId") String buyerId,@PathVariable("marketingStatus") String marketingStatus,Model model) {
        logger.debug("查看冻品管家列表");
        model.addAttribute("buyerId", buyerId);
        String applyStatus;
        if(BuyersConstant.MarketingsStatus.PreRegister.equals(marketingStatus) || BuyersConstant.MarketingsStatus.NoMarket.equals(marketingStatus)){
            applyStatus = "1";
        }else{
            applyStatus = "2";
        }
        RsRequest<BY12130302RsParam> request = new RsRequest<>();
        BY12130302RsParam searchParam = new BY12130302RsParam();
        searchParam.setBuyerId(buyerId);
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(searchParam);
        String url = SystemServerManager.BsServerManage.getGetSaleByBuyerId();
        RsResponse<BY12130302Result> pageResultList = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY12130302Result>>() {});
        if(null != pageResultList.getResult()){
            if(!CollectionUtils.isEmpty(pageResultList.getResult().getSlHouseSaleList())){
                applyStatus = pageResultList.getResult().getSlHouseSaleList().get(0).getApplyStatus();
            }
        }
        model.addAttribute("applyStatus",applyStatus);
        return "buyers/BY12130302";
    }

    /**
     * 分页查询数据
     *
     * @return 分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody
    PageResult<BY12130302RsParam> search(BY12130302Bean param) {

        RsRequest<BY12130302RsParam> request = new RsRequest<>();

        BY12130302RsParam searchParam = new BY12130302RsParam();
        searchParam.setPageCount(param.getPageSize());
        searchParam.setPageNo(param.getStartPos() / param.getPageSize() + 1);
        searchParam.setBuyerId(param.getBuyerId());
        /*searchParam.setSearchType(param.getSearchType());*/
        request.setSiteCode("1");
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setParam(searchParam);
        String url = SystemServerManager.BsServerManage.getGetSaleByBuyerId();
        //String url = "http://10.20.16.49:8181/api/bs/getSaleByBuyerId";
        RsResponse<BY12130302Result> pageResultList = RestClientUtil.post(url, request, new TypeReference<RsResponse<BY12130302Result>>() {});
        PageResult<BY12130302RsParam> result = new PageResult<>();
        if(null != pageResultList){
            result.setRecordsTotal(pageResultList.getResult().getTotalCount());
            result.setData(pageResultList.getResult().getSlHouseSaleList());
        }else{
            result.setRecordsTotal(NumberConst.IntDef.INT_ZERO);
            result.setData(null);
        }
        return result;
    }
}
