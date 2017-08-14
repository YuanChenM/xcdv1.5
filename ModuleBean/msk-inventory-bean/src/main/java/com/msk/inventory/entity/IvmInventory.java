/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_inventory对应的IvmInventory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmInventory extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** IV_ID */
    private Long ivId;
    /** PM_ID */
    private Long pmId;
    /** PM_CODE */
    private String pmCode;
    /** <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID */
    private String beloneXml;
    /** IV_STATUS */
    private String ivStatus;
    /** 1良品，11良品占用，2问题，21问题占用，3不良品，31不良品占用 */
    private String ivType;
    /** QTY */
    private java.math.BigDecimal qty;
    /** ALLOCATED_QTY */
    private java.math.BigDecimal allocatedQty;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmInventory() {

    }

    /**
     * <p>IV_ID。</p>
     *
     * @return the IV_ID
     */
    public Long getIvId() {
        return ivId;
    }

    /**
     * <p>IV_ID。</p>
     *
     * @param ivId IV_ID。
     */
    public void setIvId(Long ivId) {
        this.ivId = ivId;
    }

    /**
     * <p>PM_ID。</p>
     *
     * @return the PM_ID
     */
    public Long getPmId() {
        return pmId;
    }

    /**
     * <p>PM_ID。</p>
     *
     * @param pmId PM_ID。
     */
    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    /**
     * <p>PM_CODE。</p>
     *
     * @return the PM_CODE
     */
    public String getPmCode() {
        return pmCode;
    }

    /**
     * <p>PM_CODE。</p>
     *
     * @param pmCode PM_CODE。
     */
    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    /**
     * <p><AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID。</p>
     *
     * @return the <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID
     */
    public String getBeloneXml() {
        return beloneXml;
    }

    /**
     * <p><AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID。</p>
     *
     * @param beloneXml <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID。
     */
    public void setBeloneXml(String beloneXml) {
        this.beloneXml = beloneXml;
    }

    /**
     * <p>IV_STATUS。</p>
     *
     * @return the IV_STATUS
     */
    public String getIvStatus() {
        return ivStatus;
    }

    /**
     * <p>IV_STATUS。</p>
     *
     * @param ivStatus IV_STATUS。
     */
    public void setIvStatus(String ivStatus) {
        this.ivStatus = ivStatus;
    }

    /**
     * <p>1良品，11良品占用，2问题，21问题占用，3不良品，31不良品占用。</p>
     *
     * @return the 1良品，11良品占用，2问题，21问题占用，3不良品，31不良品占用
     */
    public String getIvType() {
        return ivType;
    }

    /**
     * <p>1良品，11良品占用，2问题，21问题占用，3不良品，31不良品占用。</p>
     *
     * @param ivType 1良品，11良品占用，2问题，21问题占用，3不良品，31不良品占用。
     */
    public void setIvType(String ivType) {
        this.ivType = ivType;
    }

    /**
     * <p>QTY。</p>
     *
     * @return the QTY
     */
    public java.math.BigDecimal getQty() {
        return qty;
    }

    /**
     * <p>QTY。</p>
     *
     * @param qty QTY。
     */
    public void setQty(java.math.BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * <p>ALLOCATED_QTY。</p>
     *
     * @return the ALLOCATED_QTY
     */
    public java.math.BigDecimal getAllocatedQty() {
        return allocatedQty;
    }

    /**
     * <p>ALLOCATED_QTY。</p>
     *
     * @param allocatedQty ALLOCATED_QTY。
     */
    public void setAllocatedQty(java.math.BigDecimal allocatedQty) {
        this.allocatedQty = allocatedQty;
    }

}
