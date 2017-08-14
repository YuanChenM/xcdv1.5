package com.msk.order.bean;


import com.msk.common.base.BaseBean;

/**
 * OrderCountBean
 *
 * @author sunjiaju
 * @version 1.0
 **/
public class OrderCountBean extends BaseBean {

    private String orderPlatform; // 下单平台

    private Integer orderCount; // 下单数量

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
}
