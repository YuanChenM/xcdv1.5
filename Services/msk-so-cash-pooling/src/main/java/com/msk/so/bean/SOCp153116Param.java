package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153116Bean
 * zhang_chi
 */
public class SOCp153116Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 支付类型
     */
    private Integer amountType;
    /**
     * 支付方式
     */
    private Integer paidType;
    /**
     * 支付金额
     */
    private String paidAmount;
    /**
     * 退款编码
     */
    private String backNo;
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


    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
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

    public String getBackNo() {
        return backNo;
    }

    public void setBackNo(String backNo) {
        this.backNo = backNo;
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
