package com.msk.cashPooling.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**买家资金池管理
 * SO153101Bean
 * yang_yang
 */
public class SO153101Param extends BasePageParam{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String businessAssistantCode;
    private String businessAssistantName;
    private String orderTimeStart;
    private String orderTimeEnd;
    private String transCode;
    private String paymentType;
    private String paymentTypeStr;
    /** 支付方式 */
    private String paidType;
    private String paidTypeStr;

    private String settlementStatus;
    private String settlementStatusStr;
    private String remark;
    private Date tranTime;
    /** so_cp_transaction 主键 */
    private long transId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(String orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public String getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(String orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeStr() {
        return paymentTypeStr;
    }

    public void setPaymentTypeStr(String paymentTypeStr) {
        this.paymentTypeStr = paymentTypeStr;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }

    public String getPaidTypeStr() {
        return paidTypeStr;
    }

    public void setPaidTypeStr(String paidTypeStr) {
        this.paidTypeStr = paidTypeStr;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementStatusStr() {
        return settlementStatusStr;
    }

    public void setSettlementStatusStr(String settlementStatusStr) {
        this.settlementStatusStr = settlementStatusStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTranTime() {
        return tranTime;
    }

    public void setTranTime(Date tranTime) {
        this.tranTime = tranTime;
    }

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }
}
