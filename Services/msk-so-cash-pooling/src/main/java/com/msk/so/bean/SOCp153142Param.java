package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SOCp153142Param
 * zhang_chi
 */
public class SOCp153142Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 支付方式
     */
    private Integer paidType;

    /**
     * 期初金额
     */
    private String periodAmount;
    /**
     * 备注
     */
    private String remark;

    /**
     * 支付流水号
     */
    private String paidSeq;

    /**
     * 发生日期
     */
    private String operateTime;
    /**
     * 主键
     */
    private String accountBookId;

    /**
     * 操作人
     */
    private String emplId;


    public Integer getPaidType() {
        return paidType;
    }

    public void setPaidType(Integer paidType) {
        this.paidType = paidType;
    }

    public String getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(String periodAmount) {
        this.periodAmount = periodAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getAccountBookId() {
        return accountBookId;
    }

    public void setAccountBookId(String accountBookId) {
        this.accountBookId = accountBookId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getPaidSeq() {
        return paidSeq;
    }

    public void setPaidSeq(String paidSeq) {
        this.paidSeq = paidSeq;
    }
}
