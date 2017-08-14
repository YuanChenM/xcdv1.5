package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153117Bean
 * li_huiqian
 */
public class SOCp153117Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 资金池 退货、退款、拒收费用明细ID/卖家计费单历史ID
     */
    private Long id;

    /**
     * 卖家计费单历史ID
     */
    private Long sellerBillHisId;
    /**
     * 资金池 退货、退款、拒收费用明细ID
     */
    private Long refundId;

    /**
     * 表名：退货、退款、拒收费用明细 REFUND ; 卖家计费单历史 SELLER_BILL_HIS
     */
    private String tb;

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
     * 主键
     */
    private String sellerBillId;

    /**
     * 操作人
     */
    private String emplId;

    /**
     * 费用调整类型
     */
    private Integer refundType;

    /**
     * 费用调整类型
     */
    private Integer oldRefundType;

    /**
     * 参考码
     */
    private String referenceCode;

    /**
     * 供应商编码
     */
    private String suppCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerBillHisId() {
        return sellerBillHisId;
    }

    public void setSellerBillHisId(Long sellerBillHisId) {
        this.sellerBillHisId = sellerBillHisId;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
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

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
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

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }
}
