package com.msk.bms.order.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.utils.StringUtil;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.CodeMasterManager;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.consts.BuyersConst;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.entity.SoDeliver;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderReceiveDemand;
import com.msk.order.entity.SoSubOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 分批订单配送信息
 *
 * @author zhangqiang1
 * @version 1.0
 */
@Controller
@RequestMapping("so/delivery")
public class SODeliveryController extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SODeliveryController.class);

    /**
     * 加载订单配送信息数据
     *
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping(value = "init/{orderId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("orderId") Long orderId, Model model) {
        logger.debug("加载订单配送信息数据");
        SoOrder soOrder = this.getSoOrder(orderId);
        SoOrderReceiveDemand soOrderReceiveDemand = soOrder.getSoOrderReceiveDemand();
        if (soOrderReceiveDemand != null) {
            this.setTimeFiled(soOrderReceiveDemand);
        }
        model.addAttribute("orderDelivery", soOrderReceiveDemand);
        model.addAttribute("sendDateTime", soOrder.getOrderSendTime());
        model.addAttribute("receiveDateTime", soOrder.getOrderReceiveTime());
        return "/order/common/OM_DELIVERY_INFO";
    }


    /**
     * 实际订单配送信息数据
     *
     * @param orderId
     * @param subOrderId
     * @param model
     * @return
     */
    @RequestMapping(value = "actual/init/{orderId}/{subOrderId}",
            method = RequestMethod.POST)
    public String actualDeliveryInit(@PathVariable("orderId") Long orderId, @PathVariable("subOrderId") Long subOrderId, Model model) {
        logger.debug("实际订单配送信息数据");
        SoOrder soOrder = this.getSoOrder(orderId);
        List<SoSubOrder> subOrders = soOrder.getSoSubOrders();
        SoSubOrder subOrder = this.getSubOrderBySubOrderId(subOrderId, subOrders);
        List<SoDeliver> deliverList = subOrder.getSoDeliverList();
        model.addAttribute("delivers", deliverList);
        return "/order/common/OM_DELIVERY_ACTUAL_INFO";
    }


    /**
     * 设置习惯收货时间段
     *
     * @param orderDelivery
     */
    public void setTimeFiled(SoOrderReceiveDemand orderDelivery) {
        String receiveTimeIds = orderDelivery.getReceiveTime();
        orderDelivery.setReceiveTime(this.getReceiverTime(receiveTimeIds));
        String receiveEarliestIds = orderDelivery.getReceiveEarliest();
        String receiveEarliestResult = this.getReceiverTime(receiveEarliestIds);
        orderDelivery.setReceiveEarliest(receiveEarliestResult);
        String receiveLastIds = orderDelivery.getReceiveLast();
        String receiveLastResult = this.getReceiverTime(receiveLastIds);
        orderDelivery.setReceiveLast(receiveLastResult);
    }


    /**
     * 通过习惯收货时间段ids  从codeMaster 获取
     *
     * @param receiverTimeIds
     * @return
     */
    private String getReceiverTime(String receiverTimeIds) {
        String receiveTimeResult = "";// 时间段
        if (!StringUtil.isEmpty(receiverTimeIds)) {
            String[] ids = receiverTimeIds.split(",");
            for (String id : ids) {
                receiveTimeResult = receiveTimeResult + this.getReceiverTimeResult(id) + ",";
            }
            if (!StringUtil.isEmpty(receiveTimeResult)) {
                receiveTimeResult = receiveTimeResult.substring(NumberConst.IntDef.INT_ZERO, receiveTimeResult.length() - NumberConst.IntDef.INT_ONE);
            }
        }
        return receiveTimeResult;
    }


    /**
     * 通过codeMaster 获取时间
     *
     * @param receiverTimeId
     * @return
     */
    private String getReceiverTimeResult(String receiverTimeId) {
        String result = "";
        Map<String, String> codeMasterMap = CodeMasterManager.findCodeMasterMap(BuyersConst.ReceivePeriodType.TYPE);
        if(codeMasterMap!=null){//  因有时 从redis 中取值为空 所以加此判断条件
            result = codeMasterMap.get(receiverTimeId);
        }
        return result;
    }

    /**
     * 获取订单信息数据
     *
     * @param orderId
     * @return
     */
    public SoOrder getSoOrder(Long orderId) {
        logger.debug("获取订单信息数据");
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


    /**
     * 根据subOrderId  获取subOrder
     *
     * @param subOrderId
     * @param subOrderList
     * @return
     */
    public SoSubOrder getSubOrderBySubOrderId(Long subOrderId, List<SoSubOrder> subOrderList) {
        SoSubOrder needSubSoOrder = null;
        for (SoSubOrder subOrder : subOrderList) {
            if (subOrder.getSubOrderId().longValue() == subOrderId.longValue()) {
                needSubSoOrder = subOrder;
                break;
            }
        }
        return needSubSoOrder;
    }
}
