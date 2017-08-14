package com.msk.order.service;


import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderStatus;

/**
 * 更新主订单状态履历
 * zhangqiang1
 */
public interface OrderStatusService {
    /**
     * 根据订单跟新订单状态
     *
     * @param crtId
     * @return
     */
    SoOrderStatus saveOrderStatusBySoOrderId(Long orderId, Integer orderStatus, String crtId);

    /**
     * @param savedOrUpdatedOrder 已经更新过的订单状态的订单
     * @param crtId
     * @return
     */
    SoOrderStatus saveOrderStatusBySoOrder(SoOrder savedOrUpdatedOrder, String crtId);
}
