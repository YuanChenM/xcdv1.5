package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * ISO151413_已付款接口
 * Created by sun_jiaju on 2016/8/9.
 */
public class ISO151413RestResult extends BaseResult {

    private Long orderId;

    private Integer orderStatus;
    private Integer ver;

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
