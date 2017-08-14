package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153103Bean
 * zhang_chi
 */
public class SO153103Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 减免金额
     */
    private String reliefAmount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 发生日期
     */
    private String operateTime;
    /**
     * 参考号
     */
    private String backNo;
    /**
     * 主键
     */
    private String buyerBillId;

    /**
     * 操作人
     */
    private String emplId;
    /**
     * 费用调整类型
     */
    private Integer refundType;

    /**
     * 供应商编码
     */
    private String suppCode;

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getReliefAmount() {
        return reliefAmount;
    }

    public void setReliefAmount(String reliefAmount) {
        this.reliefAmount = reliefAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBackNo() {
        return backNo;
    }

    public void setBackNo(String backNo) {
        this.backNo = backNo;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getBuyerBillId() {
        return buyerBillId;
    }

    public void setBuyerBillId(String buyerBillId) {
        this.buyerBillId = buyerBillId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }
}
