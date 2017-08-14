package com.msk.bms.order.controller;

import com.alibaba.fastjson.TypeReference;
import com.msk.bms.order.controller.common.SoRestUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.bean.param.SO151510Param;
import com.msk.order.bean.result.SO151510Bean;
import com.msk.order.bean.result.SO151510BeanList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * SO151510_发货单取消画面
 * Created by wang_jianzhou on 2016/8/4.
 */
@Controller
@RequestMapping("SO151510")
public class SO151510Controller extends BaseController {

    /**
     * 查询询发货单信息
     *
     * @param orderId
     * @return 发货单详情页面
     */
    @RequestMapping("init/{orderId}/{subOrderId}")
    public String init(@PathVariable("orderId") String orderId, @PathVariable("subOrderId") String subOrderId,Model model){
        SO151510Param param = new SO151510Param();
        param.setOrderId(Long.valueOf(orderId));
        param.setSubOrderId(Long.valueOf(subOrderId));
        RsRequest<SO151510Param> request = new RsRequest<SO151510Param>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(param);
        //http://localhost:8888/order/api/so/deliver/_search
        String queryDeliverInfoUrl = SystemServerManager.SoOrderApiServerManager.getQueryDeliverInfo();
        RsResponse<SO151510BeanList> deliverList = RestClientUtil.post(queryDeliverInfoUrl, request, new TypeReference<RsResponse<SO151510BeanList>>() {});
        SO151510BeanList so151510BeanList = deliverList.getResult();
        model.addAttribute("so151510Bean", so151510BeanList);
        model.addAttribute("orderId", orderId);
        model.addAttribute("subOrderId", subOrderId);
        return "/order/SO151510";
    }

    /**
     * 发货单取消
     *
     * @param param
     * @return 发货单详情页面
     */
    @RequestMapping(value = "cancel",method = RequestMethod.POST)
    public String cancel(SO151510Param param, Model model){
        RsRequest<SO151510Param> request = new RsRequest<SO151510Param>();
        request.setAuth("MSK00001");
        request.setLoginId(super.getLoginUser().getEmplId());
        request.setSiteCode("1");
        param.setUpdId(super.getLoginUser().getEmplId());
        param.setCancelManName(super.getLoginUser().getEmplName());
        request.setParam(param);
        //http://localhost:8888/order/api/so/deliver/_cancel
        String cancelOrderDeliverUrl = SystemServerManager.SoOrderApiServerManager.getCancelOrderDeliver();
        RsResponse<SO151510Bean> rsResponse = RestClientUtil.post(cancelOrderDeliverUrl, request, new TypeReference<RsResponse<SO151510Bean>>() {});
        return this.init(String.valueOf(param.getOrderId()),String.valueOf(param.getSubOrderId()),model);
    }
}
