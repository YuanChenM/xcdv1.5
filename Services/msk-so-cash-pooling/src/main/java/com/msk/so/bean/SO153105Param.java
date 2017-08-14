package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153105Bean
 * zhang_chi
 */
public class SO153105Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 退货、退款、拒收费用明细ID
     */
    private Long refundId;

    /**
     * 减免金额
     */
    private String reliefAmount;
    /**
     * 修改前减免金额
     */
    private String oldReliefAmount;
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
    private String referenceCode;
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
     * 修改前费用调整类型
     */
    private Integer oldRefundType;

    /**
     * 供应商编码
     */
    private String suppCode;

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

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

    public String getOldReliefAmount() {
        return oldReliefAmount;
    }

    public void setOldReliefAmount(String oldReliefAmount) {
        this.oldReliefAmount = oldReliefAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
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

    public Integer getOldRefundType() {
        return oldRefundType;
    }

    public void setOldRefundType(Integer oldRefundType) {
        this.oldRefundType = oldRefundType;
    }
}
