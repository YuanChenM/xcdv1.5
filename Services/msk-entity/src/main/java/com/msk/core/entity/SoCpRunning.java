/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_running对应的SoCpRunning。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpRunning extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
    /** 0 主订单 1管理费用清单 */
    private Integer transType;
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
    /** 买家业务主体角色 */
    private Integer payerRole;
    /** 卖家家业务主体角色 */
    private Integer payeeRole;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpRunning() {

    }

    /**
     * <p>RUNNING_ID。</p>
     *
     * @return the RUNNING_ID
     */
    public Long getRunningId() {
        return runningId;
    }

    /**
     * <p>RUNNING_ID。</p>
     *
     * @param runningId RUNNING_ID。
     */
    public void setRunningId(Long runningId) {
        this.runningId = runningId;
    }

    /**
     * <p>0：付款 1：退款 3：期初。</p>
     *
     * @return the 0：付款 1：退款 3：期初
     */
    public Integer getAmountType() {
        return amountType;
    }

    /**
     * <p>0：付款 1：退款 3：期初。</p>
     *
     * @param amountType 0：付款 1：退款 3：期初。
     */
    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    /**
     * <p>1：买家表 2：卖家表 。</p>
     *
     * @return the 1：买家表 2：卖家表 
     */
    public Integer getBackType() {
        return backType;
    }

    /**
     * <p>1：买家表 2：卖家表 。</p>
     *
     * @param backType 1：买家表 2：卖家表 。
     */
    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    /**
     * <p>买家计费单ID、卖家计费单ID。</p>
     *
     * @return the 买家计费单ID、卖家计费单ID
     */
    public Long getBillId() {
        return billId;
    }

    /**
     * <p>买家计费单ID、卖家计费单ID。</p>
     *
     * @param billId 买家计费单ID、卖家计费单ID。
     */
    public void setBillId(Long billId) {
        this.billId = billId;
    }

    /**
     * <p>订单号、管理费用单号。</p>
     *
     * @return the 订单号、管理费用单号
     */
    public String getTransCode() {
        return transCode;
    }

    /**
     * <p>订单号、管理费用单号。</p>
     *
     * @param transCode 订单号、管理费用单号。
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
     * <p>退回费用明细ID。</p>
     *
     * @return the 退回费用明细ID
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * <p>退回费用明细ID。</p>
     *
     * @param refundId 退回费用明细ID。
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    /**
     * <p>退货退款单编码、拒收单编码。</p>
     *
     * @return the 退货退款单编码、拒收单编码
     */
    public String getRefundCode() {
        return refundCode;
    }

    /**
     * <p>退货退款单编码、拒收单编码。</p>
     *
     * @param refundCode 退货退款单编码、拒收单编码。
     */
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    /**
     * <p>支付金额。</p>
     *
     * @return the 支付金额
     */
    public java.math.BigDecimal getPaidAmount() {
        return paidAmount;
    }

    /**
     * <p>支付金额。</p>
     *
     * @param paidAmount 支付金额。
     */
    public void setPaidAmount(java.math.BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * <p>1：现金 2：转账 3：支票 4：冲抵核销。</p>
     *
     * @return the 1：现金 2：转账 3：支票 4：冲抵核销
     */
    public Integer getPaidType() {
        return paidType;
    }

    /**
     * <p>1：现金 2：转账 3：支票 4：冲抵核销。</p>
     *
     * @param paidType 1：现金 2：转账 3：支票 4：冲抵核销。
     */
    public void setPaidType(Integer paidType) {
        this.paidType = paidType;
    }

    /**
     * <p>付款流水号。</p>
     *
     * @return the 付款流水号
     */
    public String getPaidSeq() {
        return paidSeq;
    }

    /**
     * <p>付款流水号。</p>
     *
     * @param paidSeq 付款流水号。
     */
    public void setPaidSeq(String paidSeq) {
        this.paidSeq = paidSeq;
    }

    /**
     * <p>冲抵核销序列号。</p>
     *
     * @return the 冲抵核销序列号
     */
    public Long getVerSeq() {
        return verSeq;
    }

    /**
     * <p>冲抵核销序列号。</p>
     *
     * @param verSeq 冲抵核销序列号。
     */
    public void setVerSeq(Long verSeq) {
        this.verSeq = verSeq;
    }

    /**
     * <p>支付日期。</p>
     *
     * @return the 支付日期
     */
    public java.util.Date getPaidTime() {
        return paidTime;
    }

    /**
     * <p>支付日期。</p>
     *
     * @param paidTime 支付日期。
     */
    public void setPaidTime(java.util.Date paidTime) {
        this.paidTime = paidTime;
    }

    /**
     * <p>PAYER_ID。</p>
     *
     * @return the PAYER_ID
     */
    public String getPayerId() {
        return payerId;
    }

    /**
     * <p>PAYER_ID。</p>
     *
     * @param payerId PAYER_ID。
     */
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    /**
     * <p>PAYEE_ID。</p>
     *
     * @return the PAYEE_ID
     */
    public String getPayeeId() {
        return payeeId;
    }

    /**
     * <p>PAYEE_ID。</p>
     *
     * @param payeeId PAYEE_ID。
     */
    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
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
     * <p>PAYEE_CODE。</p>
     *
     * @return the PAYEE_CODE
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * <p>PAYEE_CODE。</p>
     *
     * @param payeeCode PAYEE_CODE。
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode;
    }

    /**
     * <p>1:神农客 2:美侍客 3:大促会。</p>
     *
     * @return the 1:神农客 2:美侍客 3:大促会
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * <p>1:神农客 2:美侍客 3:大促会。</p>
     *
     * @param platform 1:神农客 2:美侍客 3:大促会。
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * <p>经手人。</p>
     *
     * @return the 经手人
     */
    public String getHandler() {
        return handler;
    }

    /**
     * <p>经手人。</p>
     *
     * @param handler 经手人。
     */
    public void setHandler(String handler) {
        this.handler = handler;
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

    /**
     * <p>买家业务主体角色。</p>
     *
     * @return the 买家业务主体角色
     */
    public Integer getPayerRole() {
        return payerRole;
    }

    /**
     * <p>买家业务主体角色。</p>
     *
     * @param payerRole 买家业务主体角色。
     */
    public void setPayerRole(Integer payerRole) {
        this.payerRole = payerRole;
    }

    /**
     * <p>卖家家业务主体角色。</p>
     *
     * @return the 卖家家业务主体角色
     */
    public Integer getPayeeRole() {
        return payeeRole;
    }

    /**
     * <p>卖家家业务主体角色。</p>
     *
     * @param payeeRole 卖家家业务主体角色。
     */
    public void setPayeeRole(Integer payeeRole) {
        this.payeeRole = payeeRole;
    }

}
