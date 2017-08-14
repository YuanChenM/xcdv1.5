package com.msk.order.bean;

import java.math.BigDecimal;

/**
 * OrderQty
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class OrderQtyParam extends BaseOrderParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private Long orderDetailId;
    private Long orderDetailAvailabilityId;
    private String supplierCode;
    private String pdCode;
    private BigDecimal qty;

    public OrderQtyParam() {
    }

    public OrderQtyParam(Long orderId, String orderCode, Long orderDetailId, Long orderDetailAvailabilityId, String supplierCode, String pdCode, BigDecimal qty) {
        super(orderId, orderCode);
        this.orderDetailId = orderDetailId;
        this.orderDetailAvailabilityId = orderDetailAvailabilityId;
        this.supplierCode = supplierCode;
        this.pdCode = pdCode;
        this.qty = qty;
    }

    /**
     * Getter method for property <tt>orderDetailId</tt>.
     *
     * @return property value of orderDetailId
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * Setter method for property <tt>orderDetailId</tt>.
     *
     * @param orderDetailId value to be assigned to property orderDetailId
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * Getter method for property <tt>orderDetailAvailabilityId</tt>.
     *
     * @return property value of orderDetailAvailabilityId
     */
    public Long getOrderDetailAvailabilityId() {
        return orderDetailAvailabilityId;
    }

    /**
     * Setter method for property <tt>orderDetailAvailabilityId</tt>.
     *
     * @param orderDetailAvailabilityId value to be assigned to property orderDetailAvailabilityId
     */
    public void setOrderDetailAvailabilityId(Long orderDetailAvailabilityId) {
        this.orderDetailAvailabilityId = orderDetailAvailabilityId;
    }

    /**
     * Getter method for property <tt>supplierCode</tt>.
     *
     * @return property value of supplierCode
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Setter method for property <tt>supplierCode</tt>.
     *
     * @param supplierCode value to be assigned to property supplierCode
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Getter method for property <tt>qty</tt>.
     *
     * @return property value of qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * Setter method for property <tt>qty</tt>.
     *
     * @param qty value to be assigned to property qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

}
