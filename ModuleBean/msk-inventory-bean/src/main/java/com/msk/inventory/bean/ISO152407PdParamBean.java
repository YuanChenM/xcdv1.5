package com.msk.inventory.bean;

/**
 * Created by wang_fan2 on 2016/8/25.
 */
public class ISO152407PdParamBean {
    private String pdCode;
    private String supplierCode;
    private String skuCode;
    private String inboundBatch;
    private String inventoryStatus;
    private java.math.BigDecimal occupyQty;

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }

    public java.math.BigDecimal getOccupyQty() {
        return occupyQty;
    }

    public void setOccupyQty(java.math.BigDecimal occupyQty) {
        this.occupyQty = occupyQty;
    }
}
