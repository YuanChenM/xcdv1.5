package com.msk.batch.order.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * Created by wang_shuai on 2016/8/30.
 */
public class BSO151404Param extends BaseParam {
    /** ORDER_ID */
    private Long orderId;
    //订单编码
    private String orderCode;
    //分批订单id
    private Long subOrderId;
    /**
     * <p>默认构造函数。</p>
     */
    public BSO151404Param() {

    }

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

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }
}
