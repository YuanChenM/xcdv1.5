package com.msk.ssc.bean;


import com.msk.core.entity.SscPaymentRequest;

import java.math.BigDecimal;


/**
 * Created by peng_hao on 2016/8/9.
 */
public class SSC11320RsBean extends SscPaymentRequest {

    /** 对应合同总金额 */
    private BigDecimal contractAmount;
    /** 本次支付主体付款金额 */
    private BigDecimal amount;
    /** 累计付款金额 */
    private BigDecimal accumulateAmount;
    /** 尚余付款金额 */
    private BigDecimal  remainAmount;
    /** 唯一ID */
    private Long paymentId;
    /** 本次付款申请金额 */
    private BigDecimal totalAmount;
    /** 付款方 */
    private String payer;
    /** 付款方银行 */
    private String payerBank;
    /** 付款帐号 */
    private String paterAccount;
    /** 收款方 */
    private String receiving;
    /** 收款方银行 */
    private String receivingBank;
    /** 收款帐号 */
    private String receivingAccount;
    /** 转账时间 */
    private String remitTime;
    /** 备注 */
    private String remark;
    /** 付款状态 */
    private Integer status;
    /** 付款依据 */
    private String paymentStr;
    /** 科目 */
    private String subject;

    /** 支付方式 */
    private String paidType;

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

    public String getPaymentStr() {
        return paymentStr;
    }

    public void setPaymentStr(String paymentStr) {
        this.paymentStr = paymentStr;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAccumulateAmount() {
        return accumulateAmount;
    }

    public void setAccumulateAmount(BigDecimal accumulateAmount) {
        this.accumulateAmount = accumulateAmount;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayerBank() {
        return payerBank;
    }

    public void setPayerBank(String payerBank) {
        this.payerBank = payerBank;
    }

    public String getPaterAccount() {
        return paterAccount;
    }

    public void setPaterAccount(String paterAccount) {
        this.paterAccount = paterAccount;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public String getReceivingBank() {
        return receivingBank;
    }

    public void setReceivingBank(String receivingBank) {
        this.receivingBank = receivingBank;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public String getRemitTime() {
        return remitTime;
    }

    public void setRemitTime(String remitTime) {
        this.remitTime = remitTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
