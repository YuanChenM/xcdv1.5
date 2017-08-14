package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/1.
 */
public class ISO151414OrderResult extends BaseResult {

    /**订单ID*/
    private Long orderId;
    /**订单编码*/
    private String orderCode;
    /**订单创建时间*/
    private Date orderTime;
    /**订单状态*/
    private Integer orderStatus;
    /**数据版本号*/
    private Integer ver;

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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
