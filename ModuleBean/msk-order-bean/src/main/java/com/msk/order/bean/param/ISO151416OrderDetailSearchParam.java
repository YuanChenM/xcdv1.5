package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * Created by liu_tao2 on 2016/8/8.
 */
public class ISO151416OrderDetailSearchParam extends BaseParam {
    /**订单编码*/
    private String orderId;

    /**订单Code*/
    private String orderCode;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
