package com.msk.ssc.bean;


import com.msk.core.entity.SscPaymentRequest;

import java.math.BigDecimal;

/**
 * Created by peng_hao on 2016/8/4.
 */
public class SSC11307RsBean extends SscPaymentRequest {

    private static final long serialVersionUID = 1L;

    /** 合同id*/
    private Long contractId;

    /** 合同编号*/
    private String contractCode;

    /** 合同状态 */
    private String contractStatus;

    /** 合同名称*/
    private String contractName;

    /**合同金额*/
    private BigDecimal contractAmount;

    /**合同总金额*/
    private BigDecimal contractTotalMoney;

    /**已付金额(合计)*/
    private BigDecimal totalPaidAmount;

    /*预付款*/
    private BigDecimal paidDownPaymentPercentage;

    /*余款金额*/
    private BigDecimal verificationAmount;

    @Override
    public BigDecimal getPaidDownPaymentPercentage() {
        return paidDownPaymentPercentage;
    }

    @Override
    public void setPaidDownPaymentPercentage(BigDecimal paidDownPaymentPercentage) {
        this.paidDownPaymentPercentage = paidDownPaymentPercentage;
    }

    @Override
    public BigDecimal getVerificationAmount() {
        return verificationAmount;
    }

    @Override
    public void setVerificationAmount(BigDecimal verificationAmount) {
        this.verificationAmount = verificationAmount;
    }

    /** 最近付款日期 yyyy/MM/dd 格式 */
    private String remitTimeStr;
    /**付款状态*/
    private String status;
    /**页面跳转标记*/
    private String navigation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemitTimeStr() {
        return remitTimeStr;
    }

    public void setRemitTimeStr(String remitTimeStr) {
        this.remitTimeStr = remitTimeStr;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    @Override
    public Long getContractId() {
        return contractId;
    }

    @Override
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public String getContractCode() {
        return contractCode;
    }

    @Override
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractTotalMoney() {
        return contractTotalMoney;
    }

    public void setContractTotalMoney(BigDecimal contractTotalMoney) {
        this.contractTotalMoney = contractTotalMoney;
    }

    public BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }
}
