package com.msk.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhang_qiang1 on 2016/8/9.
 */

public class ISO151501SubOrderToTalBean implements Serializable {

    private Long totalQty;// 订单数量


    private Double totalAmount;// 订单金额


    public Long getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Long totalQty) {
        this.totalQty = totalQty;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
