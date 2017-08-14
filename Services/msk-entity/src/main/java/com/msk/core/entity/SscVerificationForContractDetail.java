/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_verification_for_contract_detail对应的SscVerificationForContractDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscVerificationForContractDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 合同核销单明细ID */
    private java.lang.Long verificationDetailId;
    /** 合同核销单ID */
    private java.lang.Long verificationId;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编码 */
    private java.lang.String deliveryCode;
    /** 差异单ID */
    private java.lang.Long differId;
    /** 差异单编号 */
    private java.lang.String differCode;
    /** 付款申请单ID */
    private java.lang.Long paymentRequestId;
    /** 付款申请单号 */
    private java.lang.String paymentRequestCode;
    /** 参考单ID */
    private java.lang.Long refId;
    /** 参考单编号 */
    private java.lang.String refCode;
    /** 科目编号 */
    private java.lang.String subjectCode;
    /** 科目 */
    private java.lang.String subject;
    /** 差异金额 */
    private java.math.BigDecimal diffAmount;
    /** 运费实际支付 */
    private java.math.BigDecimal freightActualPaid;
    /**
     * <p>默认构造函数。</p>
     */
    public SscVerificationForContractDetail() {

    }

    /**
     * <p>合同核销单明细ID。</p>
     *
     * @return the 合同核销单明细ID
     */
    public java.lang.Long getVerificationDetailId() {
        return verificationDetailId;
    }

    /**
     * <p>合同核销单明细ID。</p>
     *
     * @param verificationDetailId 合同核销单明细ID。
     */
    public void setVerificationDetailId(java.lang.Long verificationDetailId) {
        this.verificationDetailId = verificationDetailId;
    }

    /**
     * <p>合同核销单ID。</p>
     *
     * @return the 合同核销单ID
     */
    public java.lang.Long getVerificationId() {
        return verificationId;
    }

    /**
     * <p>合同核销单ID。</p>
     *
     * @param verificationId 合同核销单ID。
     */
    public void setVerificationId(java.lang.Long verificationId) {
        this.verificationId = verificationId;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @return the 发货订单ID
     */
    public java.lang.Long getDeliveryId() {
        return deliveryId;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @param deliveryId 发货订单ID。
     */
    public void setDeliveryId(java.lang.Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * <p>发货订单编码。</p>
     *
     * @return the 发货订单编码
     */
    public java.lang.String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货订单编码。</p>
     *
     * @param deliveryCode 发货订单编码。
     */
    public void setDeliveryCode(java.lang.String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>差异单ID。</p>
     *
     * @return the 差异单ID
     */
    public java.lang.Long getDifferId() {
        return differId;
    }

    /**
     * <p>差异单ID。</p>
     *
     * @param differId 差异单ID。
     */
    public void setDifferId(java.lang.Long differId) {
        this.differId = differId;
    }

    /**
     * <p>差异单编号。</p>
     *
     * @return the 差异单编号
     */
    public java.lang.String getDifferCode() {
        return differCode;
    }

    /**
     * <p>差异单编号。</p>
     *
     * @param differCode 差异单编号。
     */
    public void setDifferCode(java.lang.String differCode) {
        this.differCode = differCode;
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
     * <p>参考单ID。</p>
     *
     * @return the 参考单ID
     */
    public java.lang.Long getRefId() {
        return refId;
    }

    /**
     * <p>参考单ID。</p>
     *
     * @param refId 参考单ID。
     */
    public void setRefId(java.lang.Long refId) {
        this.refId = refId;
    }

    /**
     * <p>参考单编号。</p>
     *
     * @return the 参考单编号
     */
    public java.lang.String getRefCode() {
        return refCode;
    }

    /**
     * <p>参考单编号。</p>
     *
     * @param refCode 参考单编号。
     */
    public void setRefCode(java.lang.String refCode) {
        this.refCode = refCode;
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
     * <p>差异金额。</p>
     *
     * @return the 差异金额
     */
    public java.math.BigDecimal getDiffAmount() {
        return diffAmount;
    }

    /**
     * <p>差异金额。</p>
     *
     * @param diffAmount 差异金额。
     */
    public void setDiffAmount(java.math.BigDecimal diffAmount) {
        this.diffAmount = diffAmount;
    }

    /**
     * <p>运费实际支付。</p>
     *
     * @return the 运费实际支付
     */
    public java.math.BigDecimal getFreightActualPaid() {
        return freightActualPaid;
    }

    /**
     * <p>运费实际支付。</p>
     *
     * @param freightActualPaid 运费实际支付。
     */
    public void setFreightActualPaid(java.math.BigDecimal freightActualPaid) {
        this.freightActualPaid = freightActualPaid;
    }

}
