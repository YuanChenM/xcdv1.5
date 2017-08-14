package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by yang_yang
 */
public class SSC11319RsBean extends BaseEntity {

    /** 交易记录ID */
    private String paymentId;
    /** 交易时间 */
    private String remitTime;
    /** 付款申请单Id */
    private String paymentRequestId;
    /** 付款申请单编号 */
    private String paymentRequestCode;
    /** 付款方 */
    private String payer;
    /** 收款方 */
    private String receiving;
    /** 付款类型 */
    private String paymentType;
    /** 本次付款金额 */
    private String amount;
    /** 总金额 */
    private String contractAmount;
    /** 付款状态 */
    private String paymentStatus;
    /** 付款依据 */
    private String paymentStr;
    /** 发货订单ID */
    private String deliveryId;
    /** 发货订单编号 */
    private String deliveryCode;
    /** 合同核销单ID */
    private String verificationId;
    /** 合同核销单号 */
    private String verificationCode;
    /** 合同Id */
    private String contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 科目 */
    private String subject;
    /** 付款方式 */
    private String paidType;
    /** 备注 */
    private String remark;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRemitTime() {
        return remitTime;
    }

    public void setRemitTime(String remitTime) {
        this.remitTime = remitTime;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getPaymentRequestCode() {
        return paymentRequestCode;
    }

    public void setPaymentRequestCode(String paymentRequestCode) {
        this.paymentRequestCode = paymentRequestCode;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStr() {
        return paymentStr;
    }

    public void setPaymentStr(String paymentStr) {
        this.paymentStr = paymentStr;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
