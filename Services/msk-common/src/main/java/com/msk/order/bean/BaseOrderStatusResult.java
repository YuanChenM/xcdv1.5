package com.msk.order.bean;



import com.msk.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * *BaseOrderStatusResult
 * *@author jiang_nan
 * *@version 1.0
 **/
public class BaseOrderStatusResult extends BaseBean {
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 需求订单编码
     */
    private String orderCode;
    /**
     * 1:新建,2:待付款,3:已付款,4:待审核,5:已审核,6:待分销,7:分销中,8:已确认,9:待发货,10:部分发货,11:部分收货,12:全部发货,13:全部收货,14:分销失败
     */
    private Integer orderStatus;
    /**
     * 退款单ID
     */
    private Integer returnId;
    /**
     * 退款单编码
     */
    private String returnCode;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 付款方式
     */
    private  int paymentType;
    /**
     * 订单来源
     */
    private  int orderSource;

    /** 发货单号 */
    private Long shipId;

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
