/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ds_delivery_stock_detail对应的DsDeliveryStockDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class DsDeliveryStockDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** DELIVERY_STOCK_ID */
    private Long deliveryStockId;
    /** PD_CODE */
    private String pdCode;
    /** NORMS_CODE */
    private String normsCode;
    /** APPLY_DELIVERY_NUM */
    private java.math.BigDecimal applyDeliveryNum;
    /** CONFIRM_DELIVERY_NUM */
    private java.math.BigDecimal confirmDeliveryNum;
    /** PLAN_DELIVERY_NUM */
    private java.math.BigDecimal planDeliveryNum;
    /** ACTUAL_DELIVERY_NUM */
    private java.math.BigDecimal actualDeliveryNum;
    /** ACTUAL_RECEIVE_NUM */
    private java.math.BigDecimal actualReceiveNum;
    /** MEMO */
    private String memo;
    /** 1代表平台供应链,2代表卖家采供链
            当值为2时,平台供应链不做处理,只保存数据 */
    private String sourceFlg;
    /** 用来处理采购链扫码入库接口数据传输 */
    private Long sscDeliveryStockId;
    /** 用来处理采购链扫码入库接口数据传输 */
    private String sku;
    /** 产品外包装净重 */
    private java.math.BigDecimal pdOutNw;
    /**
     * <p>默认构造函数。</p>
     */
    public DsDeliveryStockDetail() {

    }

    /**
     * <p>DELIVERY_STOCK_ID。</p>
     *
     * @return the DELIVERY_STOCK_ID
     */
    public Long getDeliveryStockId() {
        return deliveryStockId;
    }

    /**
     * <p>DELIVERY_STOCK_ID。</p>
     *
     * @param deliveryStockId DELIVERY_STOCK_ID。
     */
    public void setDeliveryStockId(Long deliveryStockId) {
        this.deliveryStockId = deliveryStockId;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @return the PD_CODE
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @param pdCode PD_CODE。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>NORMS_CODE。</p>
     *
     * @return the NORMS_CODE
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>NORMS_CODE。</p>
     *
     * @param normsCode NORMS_CODE。
     */
    public void setNormsCode(String normsCode) {
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
     * <p>ACTUAL_RECEIVE_NUM。</p>
     *
     * @return the ACTUAL_RECEIVE_NUM
     */
    public java.math.BigDecimal getActualReceiveNum() {
        return actualReceiveNum;
    }

    /**
     * <p>ACTUAL_RECEIVE_NUM。</p>
     *
     * @param actualReceiveNum ACTUAL_RECEIVE_NUM。
     */
    public void setActualReceiveNum(java.math.BigDecimal actualReceiveNum) {
        this.actualReceiveNum = actualReceiveNum;
    }

    /**
     * <p>MEMO。</p>
     *
     * @return the MEMO
     */
    public String getMemo() {
        return memo;
    }

    /**
     * <p>MEMO。</p>
     *
     * @param memo MEMO。
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * <p>1代表平台供应链,2代表卖家采供链
            当值为2时,平台供应链不做处理,只保存数据。</p>
     *
     * @return the 1代表平台供应链,2代表卖家采供链
            当值为2时,平台供应链不做处理,只保存数据
     */
    public String getSourceFlg() {
        return sourceFlg;
    }

    /**
     * <p>1代表平台供应链,2代表卖家采供链
            当值为2时,平台供应链不做处理,只保存数据。</p>
     *
     * @param sourceFlg 1代表平台供应链,2代表卖家采供链
            当值为2时,平台供应链不做处理,只保存数据。
     */
    public void setSourceFlg(String sourceFlg) {
        this.sourceFlg = sourceFlg;
    }

    /**
     * <p>用来处理采购链扫码入库接口数据传输。</p>
     *
     * @return the 用来处理采购链扫码入库接口数据传输
     */
    public Long getSscDeliveryStockId() {
        return sscDeliveryStockId;
    }

    /**
     * <p>用来处理采购链扫码入库接口数据传输。</p>
     *
     * @param sscDeliveryStockId 用来处理采购链扫码入库接口数据传输。
     */
    public void setSscDeliveryStockId(Long sscDeliveryStockId) {
        this.sscDeliveryStockId = sscDeliveryStockId;
    }

    /**
     * <p>用来处理采购链扫码入库接口数据传输。</p>
     *
     * @return the 用来处理采购链扫码入库接口数据传输
     */
    public String getSku() {
        return sku;
    }

    /**
     * <p>用来处理采购链扫码入库接口数据传输。</p>
     *
     * @param sku 用来处理采购链扫码入库接口数据传输。
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * <p>产品外包装净重。</p>
     *
     * @return the 产品外包装净重
     */
    public java.math.BigDecimal getPdOutNw() {
        return pdOutNw;
    }

    /**
     * <p>产品外包装净重。</p>
     *
     * @param pdOutNw 产品外包装净重。
     */
    public void setPdOutNw(java.math.BigDecimal pdOutNw) {
        this.pdOutNw = pdOutNw;
    }

}
