package com.msk.so.bean;

import com.msk.common.base.BaseBean;

import java.util.Date;

/**
 * OrderBean
 *
 * @author yang_yang
 * @version 1.0
 **/
public class OrderBean extends BaseBean {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String orderCode;

    private String orderStatus;

    private Date orderTime;

    private Date orderSendTime;

    private Date orderReceiveTime;

    private String orderReceiveAddr;

    private String remark;

    private Date finishTime;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
