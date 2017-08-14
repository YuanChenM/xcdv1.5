package com.msk.so.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 买手
 * Created by wu_honglei on 2016/5/9.
 */
public class SO153121Bean extends BaseEntity{

    private Long buyerBillId;

    private Long sellerBillId;

    /**账期*/
    private String accountDate;
    /**收款方编码*/
    private String businessMainCode;
    /**收款方名称*/
    private String businessMainName;
    /**收款方角色*/
    private String businessMainRole;
    /**付款方编码*/
    private String businessAssistantCode;
    /**付款方名称*/
    private String businessAssistantName;
    /**付款方角色*/
    private String businessAssistantRole;
    /**交易编码*/
    private String transCode;
    /**交易类型*/
    private String transType;
    /**订单金额*/
    private BigDecimal orderAmount;
    /**应付金额*/
    private  BigDecimal due;
    /**应收金额*/
    private BigDecimal receiveAble;
    /**结算状态*/
    private Integer  settlementStatus;
    /**结算状态*/
    private String  settlementStatusStr;
    /**资金池角色 0:买家  1:卖家*/
    private Integer roleFlag;
    /**订单状态 0 正常 1 交易关闭*/
    private String transFlg;


    /** 结余 */
    private BigDecimal balance;
    /** 减免金额 */
    private BigDecimal reliefAmount;
    /** 实际应付 */
    private BigDecimal actualDue;
    /** 实际已付 */
    private BigDecimal actualPaid;

    /** 实际应收 */
    private BigDecimal actualReceiveable;
    /** 实际已收 */
    private BigDecimal actualReceived;

    /** 支付方式 */
    private String paidType;

    /** 备注 */
    private String remark;

    /** xlsx用 序列 */
    private Integer  xlsxNo;

    public Integer getXlsxNo() {
        return xlsxNo;
    }

    public void setXlsxNo(Integer xlsxNo) {
        this.xlsxNo = xlsxNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private BigDecimal currentOrderAmount;

    private BigDecimal totalOrderAmount;

    public BigDecimal getCurrentOrderAmount() {
        return currentOrderAmount;
    }

    public void setCurrentOrderAmount(BigDecimal currentOrderAmount) {
        this.currentOrderAmount = currentOrderAmount;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public BigDecimal getCurrentActualReceiveable() {
        return currentActualReceiveable;
    }

    public void setCurrentActualReceiveable(BigDecimal currentActualReceiveable) {
        this.currentActualReceiveable = currentActualReceiveable;
    }

    public BigDecimal getTotalActualReceiveable() {
        return totalActualReceiveable;
    }

    public void setTotalActualReceiveable(BigDecimal totalActualReceiveable) {
        this.totalActualReceiveable = totalActualReceiveable;
    }

    public BigDecimal getCurrentActualReceived() {
        return currentActualReceived;
    }

    public void setCurrentActualReceived(BigDecimal currentActualReceived) {
        this.currentActualReceived = currentActualReceived;
    }

    public BigDecimal getTotalActualReceived() {
        return totalActualReceived;
    }

    public void setTotalActualReceived(BigDecimal totalActualReceived) {
        this.totalActualReceived = totalActualReceived;
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

    public BigDecimal getCurrentReliefAmount() {
        return currentReliefAmount;
    }

    public void setCurrentReliefAmount(BigDecimal currentReliefAmount) {
        this.currentReliefAmount = currentReliefAmount;
    }

    public BigDecimal getTotalReliefAmount() {
        return totalReliefAmount;
    }

    public void setTotalReliefAmount(BigDecimal totalReliefAmount) {
        this.totalReliefAmount = totalReliefAmount;
    }

    public BigDecimal getCurrentActualDue() {
        return currentActualDue;
    }

    public void setCurrentActualDue(BigDecimal currentActualDue) {
        this.currentActualDue = currentActualDue;
    }

    public BigDecimal getTotalActualDue() {
        return totalActualDue;
    }

    public void setTotalActualDue(BigDecimal totalActualDue) {
        this.totalActualDue = totalActualDue;
    }

    public BigDecimal getCurrentActualPaid() {
        return currentActualPaid;
    }

    public void setCurrentActualPaid(BigDecimal currentActualPaid) {
        this.currentActualPaid = currentActualPaid;
    }

    public BigDecimal getTotalActualPaid() {
        return totalActualPaid;
    }

    public void setTotalActualPaid(BigDecimal totalActualPaid) {
        this.totalActualPaid = totalActualPaid;
    }

    private BigDecimal currentActualReceiveable;

    private BigDecimal totalActualReceiveable;

    private BigDecimal currentActualReceived;

    private BigDecimal totalActualReceived;

    private BigDecimal currentBalance;

    private BigDecimal totalBalance;

    private BigDecimal currentReliefAmount;

    private BigDecimal totalReliefAmount;

    private BigDecimal currentActualDue;

    private BigDecimal totalActualDue;

    private BigDecimal currentActualPaid;

    private BigDecimal totalActualPaid;


    public BigDecimal getActualReceiveable() {
        return actualReceiveable;
    }

    public void setActualReceiveable(BigDecimal actualReceiveable) {
        this.actualReceiveable = actualReceiveable;
    }

    public BigDecimal getActualReceived() {
        return actualReceived;
    }

    public void setActualReceived(BigDecimal actualReceived) {
        this.actualReceived = actualReceived;
    }

    public BigDecimal getActualPaid() {
        return actualPaid;
    }

    public void setActualPaid(BigDecimal actualPaid) {
        this.actualPaid = actualPaid;
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

    public BigDecimal getActualDue() {
        return actualDue;
    }

    public void setActualDue(BigDecimal actualDue) {
        this.actualDue = actualDue;
    }

    public String getTransFlg() {
        return transFlg;
    }

    public void setTransFlg(String transFlg) {
        this.transFlg = transFlg;
    }

    public Long getBuyerBillId() {
        return buyerBillId;
    }

    public void setBuyerBillId(Long buyerBillId) {
        this.buyerBillId = buyerBillId;
    }

    public Long getSellerBillId() {
        return sellerBillId;
    }

    public void setSellerBillId(Long sellerBillId) {
        this.sellerBillId = sellerBillId;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
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

    public BigDecimal getReceiveAble() {
        return receiveAble;
    }

    public void setReceiveAble(BigDecimal receiveAble) {
        this.receiveAble = receiveAble;
    }

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementStatusStr() {
        return settlementStatusStr;
    }

    public void setSettlementStatusStr(String settlementStatusStr) {
        this.settlementStatusStr = settlementStatusStr;
    }

    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getPaidType() {
        return paidType;
    }

    public void setPaidType(String paidType) {
        this.paidType = paidType;
    }
}
