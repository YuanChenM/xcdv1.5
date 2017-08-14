package com.msk.order.bean;

import java.math.BigDecimal;

/**
 * OrderCancelQtyBean
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class OrderCancelQtyParam extends OrderQtyParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String cancelReason;

    public OrderCancelQtyParam() {
    }

    public OrderCancelQtyParam(Long orderId, String orderCode, Long orderDetailId, Long orderDetailAvailabilityId, String supplierCode, String pdCode, BigDecimal qty, String cancelReason) {
        super(orderId, orderCode, orderDetailId, orderDetailAvailabilityId, supplierCode, pdCode, qty);
        this.cancelReason = cancelReason;
    }

    /**
     * Getter method for property <tt>cancelReason</tt>.
     *
     * @return property value of cancelReason
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * Setter method for property <tt>cancelReason</tt>.
     *
     * @param cancelReason value to be assigned to property cancelReason
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
