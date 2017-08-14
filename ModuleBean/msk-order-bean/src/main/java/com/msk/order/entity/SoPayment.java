/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>表so_payment对应的SoPayment。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoPayment extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 支付明细 */
    @Id
    private Long paymentId;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 支付类型 */
    private Integer payMode;
    /** 支付金额 */
    private java.math.BigDecimal payAmount;
    /** 支付交易流水号 */
    private String payOrderCode;
    /** 支付时间 */
    private java.util.Date payTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SoPayment() {

    }

    /**
     * <p>支付明细。</p>
     *
     * @return the 支付明细
     */
    public Long getPaymentId() {
        return paymentId;
    }

    /**
     * <p>支付明细。</p>
     *
     * @param paymentId 支付明细。
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>支付类型。</p>
     *
     * @return the 支付类型
     */
    public Integer getPayMode() {
        return payMode;
    }

    /**
     * <p>支付类型。</p>
     *
     * @param payMode 支付类型。
     */
    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    /**
     * <p>支付金额。</p>
     *
     * @return the 支付金额
     */
    public java.math.BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * <p>支付金额。</p>
     *
     * @param payAmount 支付金额。
     */
    public void setPayAmount(java.math.BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * <p>支付交易流水号。</p>
     *
     * @return the 支付交易流水号
     */
    public String getPayOrderCode() {
        return payOrderCode;
    }

    /**
     * <p>支付交易流水号。</p>
     *
     * @param payOrderCode 支付交易流水号。
     */
    public void setPayOrderCode(String payOrderCode) {
        this.payOrderCode = payOrderCode;
    }

    /**
     * <p>支付时间。</p>
     *
     * @return the 支付时间
     */
    public java.util.Date getPayTime() {
        return payTime;
    }

    /**
     * <p>支付时间。</p>
     *
     * @param payTime 支付时间。
     */
    public void setPayTime(java.util.Date payTime) {
        this.payTime = payTime;
    }

}
