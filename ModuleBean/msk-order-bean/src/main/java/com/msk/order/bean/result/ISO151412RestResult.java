package com.msk.order.bean.result;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单删除/恢复 返回
 * Created by zhangqiang1
 */
public class ISO151412RestResult implements Serializable {
    private Long orderId;
    private String orderCode;
    private Integer orderStatus;
    private Integer delFlg;
    private Date updTime;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }


    public Date getUpdTime() {
        return updTime;
    }


    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }


    public Integer getVer() {
        return ver;
    }


    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
