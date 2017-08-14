package com.msk.ds.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 收货入库通知单明细bean
 */
public class SC182102Bean extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** DELIVERY_STOCK_ID */
    private java.lang.Long deliveryStockId;
    /** PD_CODE */
    private java.lang.String pdCode;
    /** NORMS_CODE */
    private java.lang.String normsCode;
    /** APPLY_DELIVERY_NUM */
    private java.math.BigDecimal applyDeliveryNum;
    /** CONFIRM_DELIVERY_NUM */
    private java.math.BigDecimal confirmDeliveryNum;
    /** PLAN_DELIVERY_NUM */
    private java.math.BigDecimal planDeliveryNum;
    /** ACTUAL_DELIVERY_NUM */
    private java.math.BigDecimal actualDeliveryNum;
    /** MEMO */
    private java.lang.String memo;
    /** 产品外包装净重 */
    private BigDecimal pdOutNw;
    /**
     * <p>默认构造函数。</p>
     */
    public SC182102Bean() {

    }

    /**
     * <p>DELIVERY_STOCK_ID。</p>
     *
     * @return the DELIVERY_STOCK_ID
     */
    public java.lang.Long getDeliveryStockId() {
        return deliveryStockId;
    }

    /**
     * <p>DELIVERY_STOCK_ID。</p>
     *
     * @param deliveryStockId DELIVERY_STOCK_ID。
     */
    public void setDeliveryStockId(java.lang.Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @return the PD_CODE
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @param pdCode PD_CODE。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>NORMS_CODE。</p>
     *
     * @return the NORMS_CODE
     */
    public java.lang.String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>NORMS_CODE。</p>
     *
     * @param normsCode NORMS_CODE。
     */
    public void setNormsCode(java.lang.String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>APPLY_DELIVERY_NUM。</p>
     *
     * @return the APPLY_DELIVERY_NUM
     */
    public java.math.BigDecimal getApplyDeliveryNum() {
        return applyDeliveryNum;
    }

    /**
     * <p>APPLY_DELIVERY_NUM。</p>
     *
     * @param applyDeliveryNum APPLY_DELIVERY_NUM。
     */
    public void setApplyDeliveryNum(java.math.BigDecimal applyDeliveryNum) {
        this.applyDeliveryNum = applyDeliveryNum;
    }

    /**
     * <p>CONFIRM_DELIVERY_NUM。</p>
     *
     * @return the CONFIRM_DELIVERY_NUM
     */
    public java.math.BigDecimal getConfirmDeliveryNum() {
        return confirmDeliveryNum;
    }

    /**
     * <p>CONFIRM_DELIVERY_NUM。</p>
     *
     * @param confirmDeliveryNum CONFIRM_DELIVERY_NUM。
     */
    public void setConfirmDeliveryNum(java.math.BigDecimal confirmDeliveryNum) {
        this.confirmDeliveryNum = confirmDeliveryNum;
    }

    /**
     * <p>PLAN_DELIVERY_NUM。</p>
     *
     * @return the PLAN_DELIVERY_NUM
     */
    public java.math.BigDecimal getPlanDeliveryNum() {
        return planDeliveryNum;
    }

    /**
     * <p>PLAN_DELIVERY_NUM。</p>
     *
     * @param planDeliveryNum PLAN_DELIVERY_NUM。
     */
    public void setPlanDeliveryNum(java.math.BigDecimal planDeliveryNum) {
        this.planDeliveryNum = planDeliveryNum;
    }

    /**
     * <p>ACTUAL_DELIVERY_NUM。</p>
     *
     * @return the ACTUAL_DELIVERY_NUM
     */
    public java.math.BigDecimal getActualDeliveryNum() {
        return actualDeliveryNum;
    }

    /**
     * <p>ACTUAL_DELIVERY_NUM。</p>
     *
     * @param actualDeliveryNum ACTUAL_DELIVERY_NUM。
     */
    public void setActualDeliveryNum(java.math.BigDecimal actualDeliveryNum) {
        this.actualDeliveryNum = actualDeliveryNum;
    }

    /**
     * <p>MEMO。</p>
     *
     * @return the MEMO
     */
    public java.lang.String getMemo() {
        return memo;
    }

    /**
     * <p>MEMO。</p>
     *
     * @param memo MEMO。
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getPdOutNw() {
        return pdOutNw;
    }

    public void setPdOutNw(BigDecimal pdOutNw) {
        this.pdOutNw = pdOutNw;
    }
}
