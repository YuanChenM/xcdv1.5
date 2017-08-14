package com.msk.so.bean;

import com.msk.common.bean.RsPageParam;

import java.util.Date;

/**
 * ISO153201Param
 * yang_yang
 */
public class ISO153201Param extends RsPageParam{

    private static final long serialVersionUID = 1L;

    private Date startTime;

    private Date endTime;

    private String supplyPlatform;

    private String[] supplyPlatforms;

    private String transFlg;

    private String[] transFlgs;

    private String paymentType;

    private String[] paymentTypes;

    private String businessAssistantCode;

    private String businessAssistantName;

    private String transCode;

    private String transType;

    private String[] transTypes;

    private String settlementStatus;

    private String[] settlementStatuss;

    private String remark;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSupplyPlatform() {
        return supplyPlatform;
    }

    public void setSupplyPlatform(String supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    public String[] getSupplyPlatforms() {
        return supplyPlatforms;
    }

    public void setSupplyPlatforms(String[] supplyPlatforms) {
        this.supplyPlatforms = supplyPlatforms;
    }

    public String getTransFlg() {
        return transFlg;
    }

    public void setTransFlg(String transFlg) {
        this.transFlg = transFlg;
    }

    public String[] getTransFlgs() {
        return transFlgs;
    }

    public void setTransFlgs(String[] transFlgs) {
        this.transFlgs = transFlgs;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String[] getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(String[] paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public String getBusinessAssistantCode() {
        return businessAssistantCode;
    }

    public void setBusinessAssistantCode(String businessAssistantCode) {
        this.businessAssistantCode = businessAssistantCode;
    }

    public String getBusinessAssistantName() {
        return businessAssistantName;
    }

    public void setBusinessAssistantName(String businessAssistantName) {
        this.businessAssistantName = businessAssistantName;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String[] getTransTypes() {
        return transTypes;
    }

    public void setTransTypes(String[] transTypes) {
        this.transTypes = transTypes;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String[] getSettlementStatuss() {
        return settlementStatuss;
    }

    public void setSettlementStatuss(String[] settlementStatuss) {
        this.settlementStatuss = settlementStatuss;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
