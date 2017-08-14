package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * Created by liu_tao2 on 2016/8/30.
 */
public class ISO151414OccupyStockResult extends BaseResult{
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
