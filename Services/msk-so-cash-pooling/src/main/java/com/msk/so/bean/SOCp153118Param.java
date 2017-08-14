package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153118Bean
 * li_huiqian
 */
public class SOCp153118Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 资金池 支付流水ID
     */
    private Long runningId;

    /**
     * 支付类型
     */
    private Integer amountType;
    /**
     * 修改前支付类型
     */
    private Integer oldAmountType;
    /**
     * 支付方式
     */
    private Integer paidType;
    /**
     * 支付金额
     */
    private String paidAmount;
    /**
     * 修改前支付金额
     */
    private String oldPaidAmount;
    /**
     * 退款编码
     */
    private String refundCode;
    /**
     * 支付流水号
     */
    private String paidSeq;
    /**
     * 发生日期
     */
    private String operateTime;
    /**
     * 经手人
     */
    private String handler;
    /**
     * 备注
     */
    private String remark;

    /**
     * 主键
     */
    private String sellerBillId;
    /**
     * 操作人
     */
    private String emplId;

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

    public Integer getOldAmountType() {
        return oldAmountType;
    }

    public void setOldAmountType(Integer oldAmountType) {
        this.oldAmountType = oldAmountType;
    }

    public Integer getPaidType() {
        return paidType;
    }

    public void setPaidType(Integer paidType) {
        this.paidType = paidType;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getOldPaidAmount() {
        return oldPaidAmount;
    }

    public void setOldPaidAmount(String oldPaidAmount) {
        this.oldPaidAmount = oldPaidAmount;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public String getPaidSeq() {
        return paidSeq;
    }

    public void setPaidSeq(String paidSeq) {
        this.paidSeq = paidSeq;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSellerBillId() {
        return sellerBillId;
    }

    public void setSellerBillId(String sellerBillId) {
        this.sellerBillId = sellerBillId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

}
