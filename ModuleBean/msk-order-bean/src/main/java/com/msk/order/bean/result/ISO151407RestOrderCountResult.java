package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;


/**
 * ISO151407_买家平台下单数量统计
 * Created by sun_jiaju on 2016/8/24.
 */
public class ISO151407RestOrderCountResult extends BaseResult {
    // 下单平台
    private int orderPlatform;

    // 下单数量
    private long orderCount;

    public ISO151407RestOrderCountResult(int orderPlatform, long orderCount) {
        this.orderPlatform = orderPlatform;
        this.orderCount = orderCount;
    }

    public int getOrderPlatform() {
        return orderPlatform;
    }

    public void setOrderPlatform(int orderPlatform) {
        this.orderPlatform = orderPlatform;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }
}
