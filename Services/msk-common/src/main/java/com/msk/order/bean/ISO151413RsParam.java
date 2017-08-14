package com.msk.order.bean;

import com.msk.common.bean.RsPageParam;

/**
 * zhang_qiang1
 */

public class ISO151413RsParam extends RsPageParam {
   private String orderCode;// 订单编码

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
