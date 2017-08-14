/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_buyer_bill对应的SoCpBuyerBill。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpBuyerBill extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long buyerBillId;
    /** 交易明细的唯一检索标识（订单号、管理费用单号） */
    private String transCode;
    /** 0 主订单 1管理费用清单 */
    private Integer transType;
    /** 1：付 2：退 3：平（应付-应退=实付-实退） */
    private Integer settlementStatus;
    /** 买家应付金额 */
    private java.math.BigDecimal due;
    /** 0：无退款 1：有退款 */
    private String refundFlg;
    /** 实际付款金额 */
    private java.math.BigDecimal paid;
    /** 退款金额的集计 */
    private java.math.BigDecimal refundable;
    /** 实际退款金额 */
    private java.math.BigDecimal realRefund;
    /** 0：正常收支 1：冲抵核销 */
    private String matchVerFlg;
    /** 0：可对账 1：已对账 2：挂起 */
    private Integer statementFlg;
    /** 备注 */
    private String remark;
    /** TRANS_ID */
    private Long transId;
    /** 减免金额 */
    private java.math.BigDecimal reliefAmount;
    /** 订单ID */
    private Long orderId;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpBuyerBill() {

    }

    /**
     * <p>主键。</p>
     *
     * @return the 主键
     */
    public Long getBuyerBillId() {
        return buyerBillId;
    }

    /**
     * <p>主键。</p>
     *
     * @param buyerBillId 主键。
     */
    public void setBuyerBillId(Long buyerBillId) {
        this.buyerBillId = buyerBillId;
    }

    /**
     * <p>交易明细的唯一检索标识（订单号、管理费用单号）。</p>
     *
     * @return the 交易明细的唯一检索标识（订单号、管理费用单号）
     */
    public String getTransCode() {
        return transCode;
    }

    /**
     * <p>交易明细的唯一检索标识（订单号、管理费用单号）。</p>
     *
     * @param transCode 交易明细的唯一检索标识（订单号、管理费用单号）。
     */
    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    /**
     * <p>0 主订单 1管理费用清单。</p>
     *
     * @return the 0 主订单 1管理费用清单
     */
    public Integer getTransType() {
        return transType;
    }

    /**
     * <p>0 主订单 1管理费用清单。</p>
     *
     * @param transType 0 主订单 1管理费用清单。
     */
    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    /**
     * <p>1：付 2：退 3：平（应付-应退=实付-实退）。</p>
     *
     * @return the 1：付 2：退 3：平（应付-应退=实付-实退）
     */
    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    /**
     * <p>1：付 2：退 3：平（应付-应退=实付-实退）。</p>
     *
     * @param settlementStatus 1：付 2：退 3：平（应付-应退=实付-实退）。
     */
    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    /**
     * <p>买家应付金额。</p>
     *
     * @return the 买家应付金额
     */
    public java.math.BigDecimal getDue() {
        return due;
    }

    /**
     * <p>买家应付金额。</p>
     *
     * @param due 买家应付金额。
     */
    public void setDue(java.math.BigDecimal due) {
        this.due = due;
    }

    /**
     * <p>0：无退款 1：有退款。</p>
     *
     * @return the 0：无退款 1：有退款
     */
    public String getRefundFlg() {
        return refundFlg;
    }

    /**
     * <p>0：无退款 1：有退款。</p>
     *
     * @param refundFlg 0：无退款 1：有退款。
     */
    public void setRefundFlg(String refundFlg) {
        this.refundFlg = refundFlg;
    }

    /**
     * <p>实际付款金额。</p>
     *
     * @return the 实际付款金额
     */
    public java.math.BigDecimal getPaid() {
        return paid;
    }

    /**
     * <p>实际付款金额。</p>
     *
     * @param paid 实际付款金额。
     */
    public void setPaid(java.math.BigDecimal paid) {
        this.paid = paid;
    }

    /**
     * <p>退款金额的集计。</p>
     *
     * @return the 退款金额的集计
     */
    public java.math.BigDecimal getRefundable() {
        return refundable;
    }

    /**
     * <p>退款金额的集计。</p>
     *
     * @param refundable 退款金额的集计。
     */
    public void setRefundable(java.math.BigDecimal refundable) {
        this.refundable = refundable;
    }

    /**
     * <p>实际退款金额。</p>
     *
     * @return the 实际退款金额
     */
    public java.math.BigDecimal getRealRefund() {
        return realRefund;
    }

    /**
     * <p>实际退款金额。</p>
     *
     * @param realRefund 实际退款金额。
     */
    public void setRealRefund(java.math.BigDecimal realRefund) {
        this.realRefund = realRefund;
    }

    /**
     * <p>0：正常收支 1：冲抵核销。</p>
     *
     * @return the 0：正常收支 1：冲抵核销
     */
    public String getMatchVerFlg() {
        return matchVerFlg;
    }

    /**
     * <p>0：正常收支 1：冲抵核销。</p>
     *
     * @param matchVerFlg 0：正常收支 1：冲抵核销。
     */
    public void setMatchVerFlg(String matchVerFlg) {
        this.matchVerFlg = matchVerFlg;
    }

    /**
     * <p>0：可对账 1：已对账 2：挂起。</p>
     *
     * @return the 0：可对账 1：已对账 2：挂起
     */
    public Integer getStatementFlg() {
        return statementFlg;
    }

    /**
     * <p>0：可对账 1：已对账 2：挂起。</p>
     *
     * @param statementFlg 0：可对账 1：已对账 2：挂起。
     */
    public void setStatementFlg(Integer statementFlg) {
        this.statementFlg = statementFlg;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>TRANS_ID。</p>
     *
     * @return the TRANS_ID
     */
    public Long getTransId() {
        return transId;
    }

    /**
     * <p>TRANS_ID。</p>
     *
     * @param transId TRANS_ID。
     */
    public void setTransId(Long transId) {
        this.transId = transId;
    }

    /**
     * <p>减免金额。</p>
     *
     * @return the 减免金额
     */
    public java.math.BigDecimal getReliefAmount() {
        return reliefAmount;
    }

    /**
     * <p>减免金额。</p>
     *
     * @param reliefAmount 减免金额。
     */
    public void setReliefAmount(java.math.BigDecimal reliefAmount) {
        this.reliefAmount = reliefAmount;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
