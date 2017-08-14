package com.msk.order.bean;

import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * 订单状态变更WebService接口参数
 * *@author jiang_nan
 * *@version 1.0
 **/
public class BaseOrderStatusParam  extends BaseParam {
    /**订单ID*/
    private Long orderId;
    /**1.买家取消 2.不同意拼货的取消 3.不同意分批的取消神农客网站调用时默认是1.买家取消，CallCenter调用时有不同选择。*/
    private Integer cancelType;
    /**取消原因*/
    private String cancelReason;
    /**订单类型*/
    private Integer orderType;
    /**支付金额*/
    private BigDecimal orderAmount;
    /**支付单号*/
    private String paymentOrderCode;
    /**配送单号*/
    private String deliverCode;
    /**配送单信息*/
    private OrderDeliveryBean deliver;

    /**
     * 付款类型
     */
    private Integer paymentType;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentOrderCode() {
        return paymentOrderCode;
    }

    public void setPaymentOrderCode(String paymentOrderCode) {
        this.paymentOrderCode = paymentOrderCode;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }



    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public OrderDeliveryBean getDeliver() {
        return deliver;
    }

    public void setDeliver(OrderDeliveryBean deliver) {
        this.deliver = deliver;
    }


}
