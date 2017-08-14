package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153115Bean
 * zhang_chi
 */
public class SOCp153115Param extends BaseParam {
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
}
