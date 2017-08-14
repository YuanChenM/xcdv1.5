package com.msk.cashPooling.bean;

import com.msk.core.entity.SoCpTransaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by qiu_wenting on 2016/4/18.
 */
public class CpTransactionParam extends SoCpTransaction {

    /** 退款编码：退货单号、退款单号、拒收单号 */
    private java.lang.String refundCode;
    /** 退款金额 */
    private java.math.BigDecimal refundAmount;
    /** 退款发生日期 */
    private java.util.Date refundTime;
    /** 退回费用类型：0：退货退款 1：拒收退款 */
    private java.lang.Integer refundType;
    /** 重新发货标识：0：不重新发货 1：重新发货（针对拒收退款） */
    private java.lang.String reShipFlg;
    /** 退款备注 */
    private java.lang.String refundRemark;
    /** 交易费用明细类型：１：买家　２：卖家 */
    private java.lang.Integer backType;
    /** 是否新增标识： 0：否 1：是 */
    private String insertFlg;
    /** 操作时间 */
    private java.util.Date operateDate;
    /** 退回费用明细列表 */
    private List<FundDetail> refundDetailList;

    /**
     * <p>默认构造函数。</p>
     */
    public CpTransactionParam() {
    }

    /**
     * <p>refundCode。</p>
     *
     * @return the refundCode
     */
    public String getRefundCode() {
        return refundCode;
    }

    /**
     * <p>refundCode。</p>
     *
     * @param refundCode refundCode。
     */
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
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
     * <p>refundRemark。</p>
     *
     * @return the refundRemark
     */
    public String getRefundRemark() {
        return refundRemark;
    }

    /**
     * <p>refundRemark。</p>
     *
     * @param refundRemark refundRemark。
     */
    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    /**
     * <p>backType。</p>
     *
     * @return the backType
     */
    public Integer getBackType() {
        return backType;
    }

    /**
     * <p>backType。</p>
     *
     * @param backType backType。
     */
    public void setBackType(Integer backType) {
        this.backType = backType;
    }

    /**
     * <p>insertFlg。</p>
     *
     * @return the insertFlg
     */
    public String getInsertFlg() {
        return insertFlg;
    }

    /**
     * <p>insertFlg。</p>
     *
     * @param insertFlg insertFlg。
     */
    public void setInsertFlg(String insertFlg) {
        this.insertFlg = insertFlg;
    }

    /**
     * <p>operateDate。</p>
     *
     * @return the operateDate
     */
    public Date getOperateDate() {
        return operateDate;
    }
    /**
     * <p>operateDate。</p>
     *
     * @param operateDate operateDate。
     */
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
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
}
