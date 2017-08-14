package com.msk.order.bean.result;

import java.math.BigDecimal;

/**
 * Created by liu_tao2 on 2016/8/15.
 */
public class ISO151414ProductStockInfo extends ISO151414ProductBaseInfo {
    /** 仓库编码 */
    private String warehouseCode;

    /** 仓库名称 */
    private String wareHouseName;

    /** 产品编码 */
    private String pdCode;

    /** 产品名称 */
    private String pdName;

    /** 单位 */
    private String unit;

    /** 单箱体积 */
    private BigDecimal packingVolume;

    /** 良品在库库存 */
    private BigDecimal stockQty;

    /** 良品可用库存 */
    private BigDecimal availableQty;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    private String supplierName;

    /** 货号 */
    private String skuCode;

    /** 采购批次 */
    private String purchaseBatch;

    /** 最近一次入库批次 */
    private String inboundBatch;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPurchaseBatch() {
        return purchaseBatch;
    }

    public void setPurchaseBatch(String purchaseBatch) {
        this.purchaseBatch = purchaseBatch;
    }

    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(BigDecimal availableQty) {
        this.availableQty = availableQty;
    }
}
