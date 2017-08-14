package com.msk.order.bean.param;

import com.msk.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wang_shuai on 2016/9/13.
 */
public class ISO151415RestCpRunningParam extends BaseEntity {
    /** RUNNING_ID */
    private Long runningId;
    /** 0：付款 1：退款 3：期初 */
    private Integer amountType;
    /** 1：买家表 2：卖家表  */
    private Integer backType;
    /** 买家计费单ID、卖家计费单ID */
    private Long billId;
    /** 订单号、管理费用单号 */
    private String transCode;
    /** 退回费用明细ID */
    private Long refundId;
    /** 退货退款单编码、拒收单编码 */
    private String refundCode;
    /** 支付金额 */
    private java.math.BigDecimal paidAmount;
    /** 1：现金 2：转账 3：支票 4：冲抵核销 */
    private Integer paidType;
    /** 付款流水号 */
    private String paidSeq;
    /** 冲抵核销序列号 */
    private Long verSeq;
    /** 支付日期 */
    private java.util.Date paidTime;
    /** PAYER_ID */
    private String payerId;
    /** PAYEE_ID */
    private String payeeId;
    /** 备注 */
    private String remark;
    /** PAYEE_CODE */
    private String payeeCode;
    /** 1:神农客 2:美侍客 3:大促会 */
    private Integer platform;
    /** 经手人 */
    private String handler;
    /** 订单ID */
    private Long orderId;
    /** 退款金额 */
    private java.math.BigDecimal refundAmount;
    /** 交易类型：0：主订单 1：管理费用清单 */
    private Integer transType;
    /** 退款发生日期 */
    private java.util.Date refundTime;
    /** 0：退货退款 1：拒收退款 */
    private Integer refundType;
    /** 0：不重新发货 1：重新发货（针对拒收退款） */
    private String reShipFlg;
    /** 账期开始时间 */
    private java.util.Date startDate;
    /** 账期结束时间 */
    private java.util.Date endDate;
    /** 付款方式 */
    private Integer paymentType;

    public Long getRunningId() {
        return runningId;
    }

    public void setRunningId(Long runningId) {
        this.runningId = runningId;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public Integer getBackType() {
        return backType;
    }

    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getPaidType() {
        return paidType;
    }

    public void setPaidType(Integer paidType) {
        this.paidType = paidType;
    }

    public String getPaidSeq() {
        return paidSeq;
    }

    public void setPaidSeq(String paidSeq) {
        this.paidSeq = paidSeq;
    }

    public Long getVerSeq() {
        return verSeq;
    }

    public void setVerSeq(Long verSeq) {
        this.verSeq = verSeq;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayeeCode() {
        return payeeCode;
    }

    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public String getReShipFlg() {
        return reShipFlg;
    }

    public void setReShipFlg(String reShipFlg) {
        this.reShipFlg = reShipFlg;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
}
