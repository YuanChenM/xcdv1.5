package com.msk.bms.order.controller.common;


import com.alibaba.fastjson.TypeReference;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderReceiveDemand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 订单买家基本信息
 * 
 * @author zhangqiang1
 * @version 1.0
 **/
@Controller
@RequestMapping("so/buyers")
public class SOBuyersController extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SOBuyersController.class);

    /**
     * 加载订单买家信息数据
     * 
     * @param orderId 订单ID
     * @param model Model
     * @return 订单买家数据页面
     */
    @RequestMapping(value = "init/{orderId}",
        method = RequestMethod.POST)
    public String init(@PathVariable("orderId") Long orderId, Model model) {
        logger.debug("加载订单买家信息数据");
        SoOrder soOrder= this.getSoOrder(orderId);
        SoOrderReceiveDemand orderBuyers= soOrder.getSoOrderReceiveDemand();
        model.addAttribute("orderBuyers", orderBuyers);
        model.addAttribute("buyerCode", soOrder.getBuyerCode());
        model.addAttribute("buyerName", soOrder.getBuyerName());
        model.addAttribute("buyerType", soOrder.getBuyerType());
        return "/order/common/OM_BUYERS_INFO";
    }


    /**
     * 查询主订单
     *
     * @param
     * @return
     */
    public  SoOrder getSoOrder(Long orderId) {
        logger.debug("获取主订单信息数据");
        RsRequest<Long> request = new RsRequest<Long>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(orderId);
        String url = SystemServerManager.SoOrderApiServerManager.getFindPageOrderDetail();
        RsResponse<SoOrder> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SoOrder>>() {
        });
        return rsResponse.getResult();
    }

}
