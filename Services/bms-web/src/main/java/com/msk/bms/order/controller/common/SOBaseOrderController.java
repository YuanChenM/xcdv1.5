package com.msk.bms.order.controller.common;

import com.alibaba.fastjson.TypeReference;
import com.hoperun.core.consts.NumberConst;
import com.hoperun.core.consts.SystemConst;
import com.msk.common.base.BaseController;
import com.msk.common.bean.RsRequest;
import com.msk.common.bean.RsResponse;
import com.msk.common.config.server.SystemServerManager;
import com.msk.common.utils.DecimalUtil;
import com.msk.common.utils.RestClientUtil;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoSubOrder;
import com.msk.order.entity.SoSubOrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * 订单基本信息Controller
 *
 * @author zhangqiang1
 * @version 1.0
 */
@Controller
@RequestMapping("so/baseorder")
public class SOBaseOrderController extends BaseController {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SOBaseOrderController.class);


    /**
     * @param orderId
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "init/{orderId}/{subOrderId}",
            method = RequestMethod.POST)
    public String init(@PathVariable("orderId") Long orderId, @PathVariable("subOrderId") Long subOrderId, Model model) {
        logger.debug("加载订单基础信息数据");
        SoOrder baseOrder = this.getSoOrder(orderId);
        if(baseOrder!=null){
            SoSubOrder subOrder = this.getSubOrderBySubOrderId(subOrderId, baseOrder.getSoSubOrders());
            model.addAttribute("subOrder", subOrder);
            model.addAttribute("baseOrder", baseOrder);
            model.addAttribute("subOrderAmount", this.getSubOrderAmount(subOrder));
        }
        return "/order/common/OM_BASE_ORDER_INFO";
    }


    /**
     * 查询主订单
     *
     * @param
     * @return
     */
    public SoOrder getSoOrder(Long orderId) {
        logger.debug("获取主订单信息数据");
        RsRequest<Long> request = new RsRequest<Long>();
        request.setAuth("MSK00001");
        request.setLoginId("msk01");
        request.setSiteCode("1");
        request.setParam(orderId);
        String url = SystemServerManager.SoOrderApiServerManager.getFindPageOrderDetail();
      /*  String localUrl = "http://localhost:8889/msk-order-api/api/so/order/order/_get/by/orderid";*/
        RsResponse<SoOrder> rsResponse = RestClientUtil.post(url, request, new TypeReference<RsResponse<SoOrder>>() {
        });
        if (rsResponse.getStatus().equals(SystemConst.RsStatus.SUCCESS)) {// 成功
            return rsResponse.getResult();
        } else {// 失败
            return null;
        }

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


    /**
     * 获取分批订单 金额
     *
     * @param subOrder
     * @return
     */
    private BigDecimal getSubOrderAmount(SoSubOrder subOrder) {
        BigDecimal subOrderAmount = new BigDecimal(NumberConst.IntDef.INT_ZERO);
        List<SoSubOrderDetail> soSubOrderDetailList = subOrder.getSoSubOrderDetailList();
        for (SoSubOrderDetail subOrderDetail : soSubOrderDetailList) {
            BigDecimal subOrderDetailAmount = DecimalUtil.multiply(subOrderDetail.getOrderQty(), subOrderDetail.getPdPrice());
            subOrderAmount = DecimalUtil.add(subOrderAmount, subOrderDetailAmount);
        }
        return subOrderAmount;
    }


}
