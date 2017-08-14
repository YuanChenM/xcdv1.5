package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.Date;
import java.util.List;

/**
 * Created by liutao on 2016/9/19.
 */
public class ISO151416SellerProductResult extends BaseResult {
    private Date orderTime;

    private Long orderId;

    private String orderCode;

    private Integer orderStatus;

    private Integer paymentType;

    private List<ISO151416ProductResult> productResultList;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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

    public List<ISO151416ProductResult> getProductResultList() {
        return productResultList;
    }

    public void setProductResultList(List<ISO151416ProductResult> productResultList) {
        this.productResultList = productResultList;
    }
}
