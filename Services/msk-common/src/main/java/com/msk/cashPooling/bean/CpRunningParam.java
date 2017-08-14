package com.msk.cashPooling.bean;


import com.msk.core.entity.SoCpRunning;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by qiu_wenting on 2016/4/18.
 */
public class CpRunningParam extends SoCpRunning {

    /** 退款金额 */
    private java.math.BigDecimal refundAmount;
    /** 交易类型：0：主订单 1：管理费用清单 */
    private Integer transType;
    /** 冲抵核销序列号 */
    private Long verSeq;
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
    /** 退回费用明细列表 */
    private List<FundDetail> refundDetailList;
    /** 付款方式 */
    private Integer paymentType;

    /**
     * <p>默认构造函数。</p>
     */
    public CpRunningParam() {
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
     * <p>refundDetailList。</p>
     *
     * @return the refundDetailList
     */
    public List<FundDetail> getRefundDetailList() {
        return refundDetailList;
    }

    /**
     * <p>refundDetailList。</p>
     *
     * @param refundDetailList refundDetailList。
     */
    public void setRefundDetailList(List<FundDetail> refundDetailList) {
        this.refundDetailList = refundDetailList;
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
}
