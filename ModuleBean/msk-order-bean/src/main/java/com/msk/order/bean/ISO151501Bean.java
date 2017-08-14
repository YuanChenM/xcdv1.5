package com.msk.order.bean;

import com.msk.order.entity.SoOrder;

import java.math.BigDecimal;

/**
 * Created by zhang_qiang1 on 2016/8/9.
 */

public class ISO151501Bean extends SoOrder {


    public ISO151501Bean() {
    }

    private  Long subOrderId;// 分批订单id
    private String orderTimeStr;//  订单创建时间

    private BigDecimal orderQty;// 订单数量
    /**
     * 所有订单总量
     */
    private BigDecimal totalQty;
    /**
     * 所有订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 当前页订单总量
     */
    private BigDecimal currentPageQty;
    /**
     * 当前页订单总金额
     */
    private BigDecimal currentPageAmount;


    private String subOrderStatusName;

    private String orderStatusName;

    private String orderTypeName;

    private String buyerTypeName;

    private String orderSourceName;

    private String orderQtyFmt;//千分位化数量

    private String orderAmountFmt;//千分位化

    public String getOrderTimeStr() {
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(BigDecimal totalQty) {
        this.totalQty = totalQty;
    }

    public BigDecimal getCurrentPageQty() {
        return currentPageQty;
    }

    public void setCurrentPageQty(BigDecimal currentPageQty) {
        this.currentPageQty = currentPageQty;
    }

    public BigDecimal getCurrentPageAmount() {
        return currentPageAmount;
    }

    public void setCurrentPageAmount(BigDecimal currentPageAmount) {
        this.currentPageAmount = currentPageAmount;
    }


    public String getOrderSourceName() {
        return orderSourceName;
    }

    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }


    public String getSubOrderStatusName() {
        return subOrderStatusName;
    }

    public void setSubOrderStatusName(String subOrderStatusName) {
        this.subOrderStatusName = subOrderStatusName;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getOrderQtyFmt() {
        return orderQtyFmt;
    }

    public void setOrderQtyFmt(String orderQtyFmt) {
        this.orderQtyFmt = orderQtyFmt;
    }

    public String getOrderAmountFmt() {
        return orderAmountFmt;
    }

    public void setOrderAmountFmt(String orderAmountFmt) {
        this.orderAmountFmt = orderAmountFmt;
    }
}
