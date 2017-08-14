package com.msk.order.bean.param;

import java.io.Serializable;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestParam implements Serializable {
    private Long orderId;
    private Long subOrderId;

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
