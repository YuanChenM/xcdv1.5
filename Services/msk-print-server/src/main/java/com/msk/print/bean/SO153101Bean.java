package com.msk.print.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**买家资金池管理
 * SO153101Bean
 * yang_yang
 */
public class SO153101Bean extends BaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long buyerBillId;

    private String supplyPlatform;

    private String transFlg;

    private String paymentType;
    private String paymentTypeStr;

    private Date tranTime;

    /** 卖家ID  */
    private String businessMainId;

    private String businessMainCode;

    private String businessMainName;

    private String businessMainRole;

    private String businessAssistantId;

    private String businessAssistantCode;

    private String businessAssistantName;

    private String businessAssistantRole;

    private String transCode;

    private String transType;

    private BigDecimal orderAmount;

    private BigDecimal due;

    private BigDecimal receiveable;

    private BigDecimal paid;

    private BigDecimal refundable;

    private String refundFlg;

    private String settlementStatus;
    private String settlementStatusStr;

    private BigDecimal realRefund;

    private String matchVerFlg;
    private String matchVerFlgStr;

    private String statementFlg;
    private String statementFlgStr;

    private String remark;

    /** 结余 */
    private BigDecimal balance;

    /** 减免金额 */
    private BigDecimal reliefAmount;

    /** 实际应付 */
    private BigDecimal actualDue;

    /** 实际已付 */
    private BigDecimal actualPaid;

    /** so_cp_transaction 主键 */
    private long transId;

    /** 支付方式 */
    private String paidType;

    /** 买家对应的管家的买手姓名 */
    private String buyerName;

    private BigDecimal totalOrder;

    private BigDecimal totalActual;

    private BigDecimal totalPaid;

    private BigDecimal totalRelief;

    private BigDecimal totalBalance;

    private BigDecimal currentOrder;

    private BigDecimal currentActual;

    private BigDecimal currentPaid;

    private BigDecimal currentRelief;

    private BigDecimal currentBalance;


    public BigDecimal getCurrentRelief() {
        return currentRelief;
    }

    public void setCurrentRelief(BigDecimal currentRelief) {
        this.currentRelief = currentRelief;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(BigDecimal currentOrder) {
        this.currentOrder = currentOrder;
    }

    public BigDecimal getCurrentActual() {
        return currentActual;
    }

    public void setCurrentActual(BigDecimal currentActual) {
        this.currentActual = currentActual;
    }

    public BigDecimal getCurrentPaid() {
        return currentPaid;
    }

    public void setCurrentPaid(BigDecimal currentPaid) {
        this.currentPaid = currentPaid;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }

    public BigDecimal getTotalActual() {
        return totalActual;
    }

    public void setTotalActual(BigDecimal totalActual) {
        this.totalActual = totalActual;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getTotalRelief() {
        return totalRelief;
    }

    public void setTotalRelief(BigDecimal totalRelief) {
        this.totalRelief = totalRelief;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBusinessMainId() {
        return businessMainId;
    }

    public void setBusinessMainId(String businessMainId) {
        this.businessMainId = businessMainId;
    }

    public BigDecimal getActualDue() {
        return actualDue;
    }

    public void setActualDue(BigDecimal actualDue) {
        this.actualDue = actualDue;
    }

    public BigDecimal getActualPaid() {
        return actualPaid;
    }

    public void setActualPaid(BigDecimal actualPaid) {
        this.actualPaid = actualPaid;
    }

    public String getBusinessAssistantId() {
        return businessAssistantId;
    }

    public void setBusinessAssistantId(String businessAssistantId) {
        this.businessAssistantId = businessAssistantId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getReliefAmount() {
        return reliefAmount;
    }

    public void setReliefAmount(BigDecimal reliefAmount) {
        this.reliefAmount = reliefAmount;
    }

    public Long getBuyerBillId() {
        return buyerBillId;
    }

    public void setBuyerBillId(Long buyerBillId) {
        this.buyerBillId = buyerBillId;
    }

    public String getSupplyPlatform() {
        return supplyPlatform;
    }

    public void setSupplyPlatform(String supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    public String getTransFlg() {
        return transFlg;
    }

    public void setTransFlg(String transFlg) {
        this.transFlg = transFlg;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getTranTime() {
        return tranTime;
    }

    public void setTranTime(Date tranTime) {
        this.tranTime = tranTime;
    }

    public String getBusinessMainCode() {
        return businessMainCode;
    }

    public void setBusinessMainCode(String businessMainCode) {
        this.businessMainCode = businessMainCode;
    }

    public String getBusinessMainName() {
        return businessMainName;
    }

    public void setBusinessMainName(String businessMainName) {
        this.businessMainName = businessMainName;
    }

    public String getBusinessMainRole() {
        return businessMainRole;
    }

    public void setBusinessMainRole(String businessMainRole) {
        this.businessMainRole = businessMainRole;
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

    public String getBusinessAssistantRole() {
        return businessAssistantRole;
    }

    public void setBusinessAssistantRole(String businessAssistantRole) {
        this.businessAssistantRole = businessAssistantRole;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getDue() {
        return due;
    }

    public void setDue(BigDecimal due) {
        this.due = due;
    }

    public BigDecimal getReceiveable() {
        return receiveable;
    }

    public void setReceiveable(BigDecimal receiveable) {
        this.receiveable = receiveable;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public BigDecimal getRefundable() {
        return refundable;
    }

    public void setRefundable(BigDecimal refundable) {
        this.refundable = refundable;
    }

    public String getRefundFlg() {
        return refundFlg;
    }

    public void setRefundFlg(String refundFlg) {
        this.refundFlg = refundFlg;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public BigDecimal getRealRefund() {
        return realRefund;
    }

    public void setRealRefund(BigDecimal realRefund) {
        this.realRefund = realRefund;
    }

    public String getMatchVerFlg() {
        return matchVerFlg;
    }

    public void setMatchVerFlg(String matchVerFlg) {
        this.matchVerFlg = matchVerFlg;
    }

    public String getStatementFlg() {
        return statementFlg;
    }

    public void setStatementFlg(String statementFlg) {
        this.statementFlg = statementFlg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTransId() {
        return transId;
    }

    public void setTransId(long transId) {
        this.transId = transId;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }


    public String getPaymentTypeStr() {
        return paymentTypeStr;
    }

    public void setPaymentTypeStr(String paymentTypeStr) {
        this.paymentTypeStr = paymentTypeStr;
    }

    public String getSettlementStatusStr() {
        return settlementStatusStr;
    }

    public void setSettlementStatusStr(String settlementStatusStr) {
        this.settlementStatusStr = settlementStatusStr;
    }

    public String getMatchVerFlgStr() {
        return matchVerFlgStr;
    }

    public void setMatchVerFlgStr(String matchVerFlgStr) {
        this.matchVerFlgStr = matchVerFlgStr;
    }

    public String getStatementFlgStr() {
        return statementFlgStr;
    }

    public void setStatementFlgStr(String statementFlgStr) {
        this.statementFlgStr = statementFlgStr;
    }
}
