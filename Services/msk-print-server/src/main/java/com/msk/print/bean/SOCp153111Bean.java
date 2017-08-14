package com.msk.print.bean;

import com.msk.core.entity.SoCpSellerBill;

import java.math.BigDecimal;

/**
 * Created by wu_honglei on 2016/4/29.
 */
public class SOCp153111Bean extends SoCpSellerBill {

    /**账期*/
    private String accountDate;

    /**开始时间*/
    private String startDateStr;

    /**结束时间*/
    private String endDateStr;

    /**实际应收:应收-应退*/
    private  BigDecimal realReceiveable;

    /**实际结算金额：实收-实退*/
    private  BigDecimal realSettlementAmount;

    /**未结算金额:实际应收-实际应退(应退-实退)*/
    private BigDecimal unSettlementAmount;

    /**配送单编码*/
    private String deliveryCode;

    /**退货单编码*/
    private String refundCode;

    /** 支付方式 */
    private String paidType;

    /** 当前页计费金额 */
    private BigDecimal  currentBillAmount;
    /** 当前页应付金额 */
    private BigDecimal  currentRealReceiveable;
    /** 当前页实付金额 */
    private BigDecimal  currentRealSettlementAmount;
    /** 当前页减/退金额 */
    private BigDecimal  currentAjustAmount;
    /** 当前页结余金额 */
    private BigDecimal  currentUnSettlementAmount;

    /** 当前页计费金额 */
    private BigDecimal  totalBillAmount;
    /** 当前页应付金额 */
    private BigDecimal  totalRealReceiveable;
    /** 当前页实付金额 */
    private BigDecimal  totalRealSettlementAmount;
    /** 当前页减/退金额 */
    private BigDecimal  totalAjustAmount;
    /** 当前页结余金额 */
    private BigDecimal  totalUnSettlementAmount;

    /** xlsx用 序列 */
    private Integer  xlsxNo;

    public Integer getXlsxNo() {
        return xlsxNo;
    }

    public void setXlsxNo(Integer xlsxNo) {
        this.xlsxNo = xlsxNo;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public BigDecimal getRealReceiveable() {
        return realReceiveable;
    }

    public void setRealReceiveable(BigDecimal realReceiveable) {
        this.realReceiveable = realReceiveable;
    }

    public BigDecimal getRealSettlementAmount() {
        return realSettlementAmount;
    }

    public void setRealSettlementAmount(BigDecimal realSettlementAmount) {
        this.realSettlementAmount = realSettlementAmount;
    }

    public BigDecimal getUnSettlementAmount() {
        return unSettlementAmount;
    }

    public void setUnSettlementAmount(BigDecimal unSettlementAmount) {
        this.unSettlementAmount = unSettlementAmount;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }


    public BigDecimal getCurrentBillAmount() {
        return currentBillAmount;
    }

    public void setCurrentBillAmount(BigDecimal currentBillAmount) {
        this.currentBillAmount = currentBillAmount;
    }

    public BigDecimal getCurrentRealReceiveable() {
        return currentRealReceiveable;
    }

    public void setCurrentRealReceiveable(BigDecimal currentRealReceiveable) {
        this.currentRealReceiveable = currentRealReceiveable;
    }

    public BigDecimal getCurrentRealSettlementAmount() {
        return currentRealSettlementAmount;
    }

    public void setCurrentRealSettlementAmount(BigDecimal currentRealSettlementAmount) {
        this.currentRealSettlementAmount = currentRealSettlementAmount;
    }

    public BigDecimal getCurrentAjustAmount() {
        return currentAjustAmount;
    }

    public void setCurrentAjustAmount(BigDecimal currentAjustAmount) {
        this.currentAjustAmount = currentAjustAmount;
    }

    public BigDecimal getCurrentUnSettlementAmount() {
        return currentUnSettlementAmount;
    }

    public void setCurrentUnSettlementAmount(BigDecimal currentUnSettlementAmount) {
        this.currentUnSettlementAmount = currentUnSettlementAmount;
    }

    public BigDecimal getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(BigDecimal totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public BigDecimal getTotalRealReceiveable() {
        return totalRealReceiveable;
    }

    public void setTotalRealReceiveable(BigDecimal totalRealReceiveable) {
        this.totalRealReceiveable = totalRealReceiveable;
    }

    public BigDecimal getTotalRealSettlementAmount() {
        return totalRealSettlementAmount;
    }

    public void setTotalRealSettlementAmount(BigDecimal totalRealSettlementAmount) {
        this.totalRealSettlementAmount = totalRealSettlementAmount;
    }

    public BigDecimal getTotalAjustAmount() {
        return totalAjustAmount;
    }

    public void setTotalAjustAmount(BigDecimal totalAjustAmount) {
        this.totalAjustAmount = totalAjustAmount;
    }

    public BigDecimal getTotalUnSettlementAmount() {
        return totalUnSettlementAmount;
    }

    public void setTotalUnSettlementAmount(BigDecimal totalUnSettlementAmount) {
        this.totalUnSettlementAmount = totalUnSettlementAmount;
    }
}
