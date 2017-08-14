package com.msk.order.bean.param;



import com.msk.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ISO151413_已付款接口
 * Created by sun_jiaju on 2016/8/9.
 */
public class ISO151413RestCpRunningParam extends BaseEntity {

    /** 退款金额 */
    private BigDecimal refundAmount;
    /** 交易类型：0：主订单 1：管理费用清单 */
    private Integer transType;
    /** 冲抵核销序列号 */
    private Long verSeq;
    /** 退款发生日期 */
    private Date refundTime;
    /** 0：退货退款 1：拒收退款 */
    private Integer refundType;
    /** 0：不重新发货 1：重新发货（针对拒收退款） */
    private String reShipFlg;
    /** 账期开始时间 */
    private Date startDate;
    /** 账期结束时间 */
    private Date endDate;
    /** 付款方式 */
    private Integer paymentType;
    /** 1：买家表 2：卖家表  */
    private Integer backType;
    /** 0：付款 1：退款 3：期初 */
    private Integer amountType;
    /** 订单号、管理费用单号 */
    private String transCode;
    /** 订单ID */
    private Long orderId;
    /** 支付金额 */
    private BigDecimal paidAmount;
    /** 1：现金 2：转账 3：支票 4：冲抵核销 */
    private Integer paidType;
    /** 付款流水号 */
    private String paidSeq;
    /** 支付日期 */
    private Date paidTime;
    /** PAYER_ID */
    private String payerId;
    /** PAYEE_ID */
    private String payeeId;
    /** 1:神农客 2:美侍客 3:大促会 */
    private Integer platform;
    /**
     * <p>默认构造函数。</p>
     */
    public ISO151413RestCpRunningParam() {
    }

    /**
     * <p>refundAmount。</p>
     *
     * @return the refundAmount
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * <p>refundAmount。</p>
     *
     * @param refundAmount refundAmount。
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * <p>transType。</p>
     *
     * @return the transType
     */
    public Integer getTransType() {
        return transType;
    }

    /**
     * <p>transType。</p>
     *
     * @param transType transType。
     */
    public void setTransType(Integer transType) {
        this.transType = transType;
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
     * <p>refundTime。</p>
     *
     * @return the refundTime
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * <p>refundTime。</p>
     *
     * @param refundTime refundTime。
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * <p>refundType。</p>
     *
     * @return the refundType
     */
    public Integer getRefundType() {
        return refundType;
    }

    /**
     * <p>refundType。</p>
     *
     * @param refundType refundType。
     */
    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    /**
     * <p>reShipFlg。</p>
     *
     * @return the reShipFlg
     */
    public String getReShipFlg() {
        return reShipFlg;
    }

    /**
     * <p>reShipFlg。</p>
     *
     * @param reShipFlg reShipFlg。
     */
    public void setReShipFlg(String reShipFlg) {
        this.reShipFlg = reShipFlg;
    }

    /**
     * <p>startDate。</p>
     *
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * <p>startDate。</p>
     *
     * @param startDate startDate。
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * <p>endDate。</p>
     *
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * <p>endDate。</p>
     *
     * @param endDate endDate。
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * <p>paymentType。</p>
     *
     * @return the paymentType
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * <p>paymentType。</p>
     *
     * @param paymentType paymentType。
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getBackType() {
        return backType;
    }

    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
