package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestPageParam;

import java.util.Date;

/**
 * Created by liutao on 2016/9/19.
 */
public class ISO151416SoldProductParam extends BaseRestPageParam {
    private String sellerCode;

    private Integer orderStatus;

    private String inputParam;

    private Date orderStartTime;

    private Date orderEndTime;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }
}
