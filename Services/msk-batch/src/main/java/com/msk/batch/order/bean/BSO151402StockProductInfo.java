package com.msk.batch.order.bean;

import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * Created by liu_tao2 on 2016/8/22.
 */
public class BSO151402StockProductInfo extends BaseParam{

    private String pdCode;

    private String pdName;

    private String classesCode;

    private String breedCode;

    private String supplierCode;

    private String skuCode;

    private String inboundBatch;

    private String inventoryStatus;

    private BigDecimal occupyQty;

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

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public BigDecimal getOccupyQty() {
        return occupyQty;
    }

    public void setOccupyQty(BigDecimal occupyQty) {
        this.occupyQty = occupyQty;
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

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
