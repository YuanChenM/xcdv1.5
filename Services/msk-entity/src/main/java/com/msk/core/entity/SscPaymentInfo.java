/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_payment_info对应的SscPaymentInfo。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscPaymentInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 唯一ID */
    private java.lang.Long paymentId;
    /** 付款申请单ID */
    private java.lang.Long paymentRequestId;
    /** 付款申请单号 */
    private java.lang.String paymentRequestCode;
    /** 金额 */
    private java.math.BigDecimal amount;
    /** 付款方 */
    private java.lang.String payer;
    /** 付款方银行 */
    private java.lang.String payerBank;
    /** 付款帐号 */
    private java.lang.String paterAccount;
    /** 收款方 */
    private java.lang.String receiving;
    /** 收款方银行 */
    private java.lang.String receivingBank;
    /** 收款帐号 */
    private java.lang.String receivingAccount;
    /** 转账时间 */
    private java.util.Date remitTime;
    /** 科目编号 */
    private java.lang.String subjectCode;
    /** 科目 */
    private java.lang.String subject;
    /** 付款状态 */
    private java.lang.Integer status;
    /** 付款方式 */
    private java.lang.String paidType;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscPaymentInfo() {

    }

    /**
     * <p>唯一ID。</p>
     *
     * @return the 唯一ID
     */
    public java.lang.Long getPaymentId() {
        return paymentId;
    }

    /**
     * <p>唯一ID。</p>
     *
     * @param paymentId 唯一ID。
     */
    public void setPaymentId(java.lang.Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * <p>付款申请单ID。</p>
     *
     * @return the 付款申请单ID
     */
    public java.lang.Long getPaymentRequestId() {
        return paymentRequestId;
    }

    /**
     * <p>付款申请单ID。</p>
     *
     * @param paymentRequestId 付款申请单ID。
     */
    public void setPaymentRequestId(java.lang.Long paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    /**
     * <p>付款申请单号。</p>
     *
     * @return the 付款申请单号
     */
    public java.lang.String getPaymentRequestCode() {
        return paymentRequestCode;
    }

    /**
     * <p>付款申请单号。</p>
     *
     * @param paymentRequestCode 付款申请单号。
     */
    public void setPaymentRequestCode(java.lang.String paymentRequestCode) {
        this.paymentRequestCode = paymentRequestCode;
    }

    /**
     * <p>金额。</p>
     *
     * @return the 金额
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    /**
     * <p>金额。</p>
     *
     * @param amount 金额。
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * <p>付款方。</p>
     *
     * @return the 付款方
     */
    public java.lang.String getPayer() {
        return payer;
    }

    /**
     * <p>付款方。</p>
     *
     * @param payer 付款方。
     */
    public void setPayer(java.lang.String payer) {
        this.payer = payer;
    }

    /**
     * <p>付款方银行。</p>
     *
     * @return the 付款方银行
     */
    public java.lang.String getPayerBank() {
        return payerBank;
    }

    /**
     * <p>付款方银行。</p>
     *
     * @param payerBank 付款方银行。
     */
    public void setPayerBank(java.lang.String payerBank) {
        this.payerBank = payerBank;
    }

    /**
     * <p>付款帐号。</p>
     *
     * @return the 付款帐号
     */
    public java.lang.String getPaterAccount() {
        return paterAccount;
    }

    /**
     * <p>付款帐号。</p>
     *
     * @param paterAccount 付款帐号。
     */
    public void setPaterAccount(java.lang.String paterAccount) {
        this.paterAccount = paterAccount;
    }

    /**
     * <p>收款方。</p>
     *
     * @return the 收款方
     */
    public java.lang.String getReceiving() {
        return receiving;
    }

    /**
     * <p>收款方。</p>
     *
     * @param receiving 收款方。
     */
    public void setReceiving(java.lang.String receiving) {
        this.receiving = receiving;
    }

    /**
     * <p>收款方银行。</p>
     *
     * @return the 收款方银行
     */
    public java.lang.String getReceivingBank() {
        return receivingBank;
    }

    /**
     * <p>收款方银行。</p>
     *
     * @param receivingBank 收款方银行。
     */
    public void setReceivingBank(java.lang.String receivingBank) {
        this.receivingBank = receivingBank;
    }

    /**
     * <p>收款帐号。</p>
     *
     * @return the 收款帐号
     */
    public java.lang.String getReceivingAccount() {
        return receivingAccount;
    }

    /**
     * <p>收款帐号。</p>
     *
     * @param receivingAccount 收款帐号。
     */
    public void setReceivingAccount(java.lang.String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    /**
     * <p>转账时间。</p>
     *
     * @return the 转账时间
     */
    public java.util.Date getRemitTime() {
        return remitTime;
    }

    /**
     * <p>转账时间。</p>
     *
     * @param remitTime 转账时间。
     */
    public void setRemitTime(java.util.Date remitTime) {
        this.remitTime = remitTime;
    }

    /**
     * <p>科目编号。</p>
     *
     * @return the 科目编号
     */
    public java.lang.String getSubjectCode() {
        return subjectCode;
    }

    /**
     * <p>科目编号。</p>
     *
     * @param subjectCode 科目编号。
     */
    public void setSubjectCode(java.lang.String subjectCode) {
        this.subjectCode = subjectCode;
    }

    /**
     * <p>科目。</p>
     *
     * @return the 科目
     */
    public java.lang.String getSubject() {
        return subject;
    }

    /**
     * <p>科目。</p>
     *
     * @param subject 科目。
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }

    /**
     * <p>付款状态。</p>
     *
     * @return the 付款状态
     */
    public java.lang.Integer getStatus() {
        return status;
    }

    /**
     * <p>付款状态。</p>
     *
     * @param status 付款状态。
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * <p>付款方式。</p>
     *
     * @return the 付款方式
     */
    public java.lang.String getPaidType() {
        return paidType;
    }

    /**
     * <p>付款方式。</p>
     *
     * @param paidType 付款方式。
     */
    public void setPaidType(java.lang.String paidType) {
        this.paidType = paidType;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

}
