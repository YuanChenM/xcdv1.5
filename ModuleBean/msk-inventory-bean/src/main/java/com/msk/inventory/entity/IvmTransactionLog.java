/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.inventory.entity;

import com.msk.comm.entity.BaseEntity;

/**
 * <p>表ivm_transaction_log对应的IvmTransactionLog。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class IvmTransactionLog extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** LOG_ID */
    private Long logId;
    /** PRODUCT_XML */
    private String productXml;
    /** WH_ID */
    private Long whId;
    /** WH_CODE */
    private String whCode;
    /** OWNER_ID */
    private Long ownerId;
    /** OWNER_CODE */
    private String ownerCode;
    /** <AREA>物流区域<PLT>平台<SLT>卖家类型<SLID>卖家ID */
    private String beloneXml;
    /** SUPPLIER_ID */
    private Long supplierId;
    /** SUPPLIER_CODE */
    private String supplierCode;
    /** TRANSACTION_ID */
    private Long transactionId;
    /** TRANSACTION_NO */
    private String transactionNo;
    /** 采购入库，调整入库，退货入库，样品入库，采购出库，调整出库，退货出库，样品出库，囤货，占用，状态变更 */
    private String transactionType;
    /** 库存标识变更：1 -> 2      卖家变更：2 -> 3 等 */
    private String transactionDesc;
    /** PUCHARSE_BATCH */
    private String pucharseBatch;
    /** INNER_BATCH */
    private String innerBatch;
    /** PRICE */
    private java.math.BigDecimal price;
    /** QTY */
    private java.math.BigDecimal qty;
    /** UOM */
    private String uom;
    /** TRANSACTION_DATE */
    private java.util.Date transactionDate;
    /**
     * <p>默认构造函数。</p>
     */
    public IvmTransactionLog() {

    }

    /**
     * <p>LOG_ID。</p>
     *
     * @return the LOG_ID
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * <p>LOG_ID。</p>
     *
     * @param logId LOG_ID。
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * <p>PRODUCT_XML。</p>
     *
     * @return the PRODUCT_XML
     */
    public String getProductXml() {
        return productXml;
    }

    /**
     * <p>PRODUCT_XML。</p>
     *
     * @param productXml PRODUCT_XML。
     */
    public void setProductXml(String productXml) {
        this.productXml = productXml;
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
     * <p>SUPPLIER_ID。</p>
     *
     * @return the SUPPLIER_ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>SUPPLIER_ID。</p>
     *
     * @param supplierId SUPPLIER_ID。
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>SUPPLIER_CODE。</p>
     *
     * @return the SUPPLIER_CODE
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>SUPPLIER_CODE。</p>
     *
     * @param supplierCode SUPPLIER_CODE。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>TRANSACTION_ID。</p>
     *
     * @return the TRANSACTION_ID
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * <p>TRANSACTION_ID。</p>
     *
     * @param transactionId TRANSACTION_ID。
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * <p>TRANSACTION_NO。</p>
     *
     * @return the TRANSACTION_NO
     */
    public String getTransactionNo() {
        return transactionNo;
    }

    /**
     * <p>TRANSACTION_NO。</p>
     *
     * @param transactionNo TRANSACTION_NO。
     */
    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    /**
     * <p>采购入库，调整入库，退货入库，样品入库，采购出库，调整出库，退货出库，样品出库，囤货，占用，状态变更。</p>
     *
     * @return the 采购入库，调整入库，退货入库，样品入库，采购出库，调整出库，退货出库，样品出库，囤货，占用，状态变更
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * <p>采购入库，调整入库，退货入库，样品入库，采购出库，调整出库，退货出库，样品出库，囤货，占用，状态变更。</p>
     *
     * @param transactionType 采购入库，调整入库，退货入库，样品入库，采购出库，调整出库，退货出库，样品出库，囤货，占用，状态变更。
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * <p>库存标识变更：1 -> 2      卖家变更：2 -> 3 等。</p>
     *
     * @return the 库存标识变更：1 -> 2      卖家变更：2 -> 3 等
     */
    public String getTransactionDesc() {
        return transactionDesc;
    }

    /**
     * <p>库存标识变更：1 -> 2      卖家变更：2 -> 3 等。</p>
     *
     * @param transactionDesc 库存标识变更：1 -> 2      卖家变更：2 -> 3 等。
     */
    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    /**
     * <p>PUCHARSE_BATCH。</p>
     *
     * @return the PUCHARSE_BATCH
     */
    public String getPucharseBatch() {
        return pucharseBatch;
    }

    /**
     * <p>PUCHARSE_BATCH。</p>
     *
     * @param pucharseBatch PUCHARSE_BATCH。
     */
    public void setPucharseBatch(String pucharseBatch) {
        this.pucharseBatch = pucharseBatch;
    }

    /**
     * <p>INNER_BATCH。</p>
     *
     * @return the INNER_BATCH
     */
    public String getInnerBatch() {
        return innerBatch;
    }

    /**
     * <p>INNER_BATCH。</p>
     *
     * @param innerBatch INNER_BATCH。
     */
    public void setInnerBatch(String innerBatch) {
        this.innerBatch = innerBatch;
    }

    /**
     * <p>PRICE。</p>
     *
     * @return the PRICE
     */
    public java.math.BigDecimal getPrice() {
        return price;
    }

    /**
     * <p>PRICE。</p>
     *
     * @param price PRICE。
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
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
     * <p>UOM。</p>
     *
     * @return the UOM
     */
    public String getUom() {
        return uom;
    }

    /**
     * <p>UOM。</p>
     *
     * @param uom UOM。
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * <p>TRANSACTION_DATE。</p>
     *
     * @return the TRANSACTION_DATE
     */
    public java.util.Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * <p>TRANSACTION_DATE。</p>
     *
     * @param transactionDate TRANSACTION_DATE。
     */
    public void setTransactionDate(java.util.Date transactionDate) {
        this.transactionDate = transactionDate;
    }

}
