/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_plan_basic对应的SscDeliveryPlanBasic。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryPlanBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 批次ID */
    private java.lang.Long lotId;
    /** 批次编号 */
    private java.lang.String batchCode;
    /** 合同编号 */
    private java.lang.Long contractId;
    /** 产品ID */
    private java.lang.String pdCode;
    /** 产品属性码 */
    private java.lang.String pdDesc;
    /** 预计到达时间 */
    private java.util.Date eta;
    /** 到货数量 */
    private java.math.BigDecimal arriveQut;
    /** 到货箱数 */
    private java.lang.Integer arriveBoxes;
    /** 净重数值 */
    private java.math.BigDecimal weightVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryPlanBasic() {

    }

    /**
     * <p>批次ID。</p>
     *
     * @return the 批次ID
     */
    public java.lang.Long getLotId() {
        return lotId;
    }

    /**
     * <p>批次ID。</p>
     *
     * @param lotId 批次ID。
     */
    public void setLotId(java.lang.Long lotId) {
        this.lotId = lotId;
    }

    /**
     * <p>批次编号。</p>
     *
     * @return the 批次编号
     */
    public java.lang.String getBatchCode() {
        return batchCode;
    }

    /**
     * <p>批次编号。</p>
     *
     * @param batchCode 批次编号。
     */
    public void setBatchCode(java.lang.String batchCode) {
        this.batchCode = batchCode;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public java.lang.Long getContractId() {
        return contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractId 合同编号。
     */
    public void setContractId(java.lang.Long contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>产品ID。</p>
     *
     * @return the 产品ID
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品ID。</p>
     *
     * @param pdCode 产品ID。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @return the 产品属性码
     */
    public java.lang.String getPdDesc() {
        return pdDesc;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @param pdDesc 产品属性码。
     */
    public void setPdDesc(java.lang.String pdDesc) {
        this.pdDesc = pdDesc;
    }

    /**
     * <p>预计到达时间。</p>
     *
     * @return the 预计到达时间
     */
    public java.util.Date getEta() {
        return eta;
    }

    /**
     * <p>预计到达时间。</p>
     *
     * @param eta 预计到达时间。
     */
    public void setEta(java.util.Date eta) {
        this.eta = eta;
    }

    /**
     * <p>到货数量。</p>
     *
     * @return the 到货数量
     */
    public java.math.BigDecimal getArriveQut() {
        return arriveQut;
    }

    /**
     * <p>到货数量。</p>
     *
     * @param arriveQut 到货数量。
     */
    public void setArriveQut(java.math.BigDecimal arriveQut) {
        this.arriveQut = arriveQut;
    }

    /**
     * <p>到货箱数。</p>
     *
     * @return the 到货箱数
     */
    public java.lang.Integer getArriveBoxes() {
        return arriveBoxes;
    }

    /**
     * <p>到货箱数。</p>
     *
     * @param arriveBoxes 到货箱数。
     */
    public void setArriveBoxes(java.lang.Integer arriveBoxes) {
        this.arriveBoxes = arriveBoxes;
    }

    /**
     * <p>净重数值。</p>
     *
     * @return the 净重数值
     */
    public java.math.BigDecimal getWeightVal() {
        return weightVal;
    }

    /**
     * <p>净重数值。</p>
     *
     * @param weightVal 净重数值。
     */
    public void setWeightVal(java.math.BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

}
