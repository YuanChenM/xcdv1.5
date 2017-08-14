/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_warehouse_inventory对应的IvmWarehouseInventory。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmWarehouseInventory extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** SYNCHRO_ID */
    private Long synchroId;
    /** SYNCHRO_NO */
    private String synchroNo;
    /** SYNCHRO_DATE */
    private java.util.Date synchroDate;
    /** SKU */
    private String sku;
    /** WH_ID */
    private Long whId;
    /** WH_CODE */
    private String whCode;
    /** LOGIC_AREA */
    private String logicArea;
    /** OWNER_ID */
    private Long ownerId;
    /** OWNER_CODE */
    private String ownerCode;
    /** SL_ID */
    private Long slId;
    /** SL_CODE */
    private String slCode;
    /** IV_TYPE */
    private String ivType;
    /** QTY */
    private java.math.BigDecimal qty;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmWarehouseInventory() {

    }

    /**
     * <p>SYNCHRO_ID。</p>
     *
     * @return the SYNCHRO_ID
     */
    public Long getSynchroId() {
        return synchroId;
    }

    /**
     * <p>SYNCHRO_ID。</p>
     *
     * @param synchroId SYNCHRO_ID。
     */
    public void setSynchroId(Long synchroId) {
        this.synchroId = synchroId;
    }

    /**
     * <p>SYNCHRO_NO。</p>
     *
     * @return the SYNCHRO_NO
     */
    public String getSynchroNo() {
        return synchroNo;
    }

    /**
     * <p>SYNCHRO_NO。</p>
     *
     * @param synchroNo SYNCHRO_NO。
     */
    public void setSynchroNo(String synchroNo) {
        this.synchroNo = synchroNo;
    }

    /**
     * <p>SYNCHRO_DATE。</p>
     *
     * @return the SYNCHRO_DATE
     */
    public java.util.Date getSynchroDate() {
        return synchroDate;
    }

    /**
     * <p>SYNCHRO_DATE。</p>
     *
     * @param synchroDate SYNCHRO_DATE。
     */
    public void setSynchroDate(java.util.Date synchroDate) {
        this.synchroDate = synchroDate;
    }

    /**
     * <p>SKU。</p>
     *
     * @return the SKU
     */
    public String getSku() {
        return sku;
    }

    /**
     * <p>SKU。</p>
     *
     * @param sku SKU。
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * <p>WH_ID。</p>
     *
     * @return the WH_ID
     */
    public Long getWhId() {
        return whId;
    }

    /**
     * <p>WH_ID。</p>
     *
     * @param whId WH_ID。
     */
    public void setWhId(Long whId) {
        this.whId = whId;
    }

    /**
     * <p>WH_CODE。</p>
     *
     * @return the WH_CODE
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * <p>WH_CODE。</p>
     *
     * @param whCode WH_CODE。
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    /**
     * <p>LOGIC_AREA。</p>
     *
     * @return the LOGIC_AREA
     */
    public String getLogicArea() {
        return logicArea;
    }

    /**
     * <p>LOGIC_AREA。</p>
     *
     * @param logicArea LOGIC_AREA。
     */
    public void setLogicArea(String logicArea) {
        this.logicArea = logicArea;
    }

    /**
     * <p>OWNER_ID。</p>
     *
     * @return the OWNER_ID
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * <p>OWNER_ID。</p>
     *
     * @param ownerId OWNER_ID。
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * <p>OWNER_CODE。</p>
     *
     * @return the OWNER_CODE
     */
    public String getOwnerCode() {
        return ownerCode;
    }

    /**
     * <p>OWNER_CODE。</p>
     *
     * @param ownerCode OWNER_CODE。
     */
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    /**
     * <p>SL_ID。</p>
     *
     * @return the SL_ID
     */
    public Long getSlId() {
        return slId;
    }

    /**
     * <p>SL_ID。</p>
     *
     * @param slId SL_ID。
     */
    public void setSlId(Long slId) {
        this.slId = slId;
    }

    /**
     * <p>SL_CODE。</p>
     *
     * @return the SL_CODE
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>SL_CODE。</p>
     *
     * @param slCode SL_CODE。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>IV_TYPE。</p>
     *
     * @return the IV_TYPE
     */
    public String getIvType() {
        return ivType;
    }

    /**
     * <p>IV_TYPE。</p>
     *
     * @param ivType IV_TYPE。
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

}
