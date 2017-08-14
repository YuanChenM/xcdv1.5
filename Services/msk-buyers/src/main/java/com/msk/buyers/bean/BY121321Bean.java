package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;


public class BY121321Bean extends BaseEntity{

    private String orderType;

    private Integer orderTel;

    private Integer orderMicroMall;

    private Integer orderPC;
//
    private Integer orderWeChat;
//qq订单
    private Integer orderQq;
//合计
    private Integer totalOrder;

    private String startTime;

    private String endTime;
   //平台
    private String orderPlatform;
   //下单数量
    private Integer  orderCount;
    //云冻品
    private Integer  frozen;
    //云冻品b2b
    private Integer  frozenB2b;
   //买手app
    private Integer  buyerApp;




    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(Integer orderTel) {
        this.orderTel = orderTel;
    }

    public Integer getOrderMicroMall() {
        return orderMicroMall;
    }

    public void setOrderMicroMall(Integer orderMicroMall) {
        this.orderMicroMall = orderMicroMall;
    }

    public Integer getOrderPC() {
        return orderPC;
    }

    public void setOrderPC(Integer orderPC) {
        this.orderPC = orderPC;
    }

    public Integer getOrderWeChat() {
        return orderWeChat;
    }

    public void setOrderWeChat(Integer orderWeChat) {
        this.orderWeChat = orderWeChat;
    }

    public Integer getOrderQq() {
        return orderQq;
    }

    public void setOrderQq(Integer orderQq) {
        this.orderQq = orderQq;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderPlatform() {
        return orderPlatform;
    }

    public void setOrderPlatform(String orderPlatform) {
        this.orderPlatform = orderPlatform;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getFrozen() {
        return frozen;
    }

    public void setFrozen(Integer frozen) {
        this.frozen = frozen;
    }

    public Integer getFrozenB2b() {
        return frozenB2b;
    }

    public void setFrozenB2b(Integer frozenB2b) {
        this.frozenB2b = frozenB2b;
    }

    public Integer getBuyerApp() {
        return buyerApp;
    }

    public void setBuyerApp(Integer buyerApp) {
        this.buyerApp = buyerApp;
    }
}
