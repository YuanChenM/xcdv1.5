/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_inventory_by_prod对应的IvmInventoryByProd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmInventoryByProd extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** IV_ID */
    private Long ivId;
    /** <CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C> */
    private String productXml;
    /** <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>平台卖家ID */
    private String beloneXml;
    /** OWNER_ID */
    private Long ownerId;
    /** OWNER_CODE */
    private String ownerCode;
    /** IV_STATUS */
    private String ivStatus;
    /** IV_TYPE */
    private String ivType;
    /** QTY */
    private java.math.BigDecimal qty;
    /** ALLOCATED_QTY */
    private java.math.BigDecimal allocatedQty;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmInventoryByProd() {

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
     * <p><CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C>。</p>
     *
     * @return the <CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C>
     */
    public String getProductXml() {
        return productXml;
    }

    /**
     * <p><CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C>。</p>
     *
     * @param productXml <CLS_C><MACH_C><BRE_C><FEAT_C><WEI_C><GRAD_C>。
     */
    public void setProductXml(String productXml) {
        this.productXml = productXml;
    }

    /**
     * <p><AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>平台卖家ID。</p>
     *
     * @return the <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>平台卖家ID
     */
    public String getBeloneXml() {
        return beloneXml;
    }

    /**
     * <p><AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>平台卖家ID。</p>
     *
     * @param beloneXml <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>平台卖家ID。
     */
    public void setBeloneXml(String beloneXml) {
        this.beloneXml = beloneXml;
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
