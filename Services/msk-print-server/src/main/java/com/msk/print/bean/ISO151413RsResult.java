package com.msk.print.bean;


import com.msk.core.entity.BaseEntity;

import java.util.Date;

public class ISO151413RsResult extends BaseEntity{

    private String orderCode;// 订单编号

    private  String orderStatus;// 订单状态
    private  String orderStatusStr;// 订单状态

    private Date orderTime;// 成交时间

    private String orderTimeStr;// 成交时间

    private Date orderSendTime;//发货时间

    private String orderSendTimeStr;//发货时间


    private Date orderReceiveTime;//收货时间

    private String orderReceiveTimeStr;//收货时间

    private String orderReceiveAddr;//收货地址

    private String remark;//买家留言

    private Date finishTime;//完成时间

    private String finishTimeStr;//完成时间

    /** pdf抬头 */
    private String  socpTitle;

    public String getOrderTimeStr() {
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getOrderSendTimeStr() {
        return orderSendTimeStr;
    }

    public void setOrderSendTimeStr(String orderSendTimeStr) {
        this.orderSendTimeStr = orderSendTimeStr;
    }

    public String getOrderReceiveTimeStr() {
        return orderReceiveTimeStr;
    }

    public void setOrderReceiveTimeStr(String orderReceiveTimeStr) {
        this.orderReceiveTimeStr = orderReceiveTimeStr;
    }

    public String getFinishTimeStr() {
        return finishTimeStr;
    }

    public void setFinishTimeStr(String finishTimeStr) {
        this.finishTimeStr = finishTimeStr;
    }

    public String getSocpTitle() {
        return socpTitle;
    }

    public void setSocpTitle(String socpTitle) {
        this.socpTitle = socpTitle;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderStatusStr() {
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(Date orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public Date getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(Date orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public String getOrderReceiveAddr() {
        return orderReceiveAddr;
    }

    public void setOrderReceiveAddr(String orderReceiveAddr) {
        this.orderReceiveAddr = orderReceiveAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
