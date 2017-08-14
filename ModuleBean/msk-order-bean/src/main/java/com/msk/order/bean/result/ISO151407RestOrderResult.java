package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * ISO151407_买家平台下单数量统计
 * Created by sun_jiaju on 2016/8/24.
 */
public class ISO151407RestOrderResult extends BaseResult {
    // 下单平台
    private String orderPlatform;

    // 下单数量
    private Integer orderCount;

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
