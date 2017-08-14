package com.msk.order.bean.param;

import java.math.BigDecimal;

/**
 * Created by wang_shuai on 2016/9/26.
 */
public class ISO151433PdParam {
    //原发货明细ID，对应发货XML中ORDERLINE
    private String pdCode;
    //库存类型
    private String inventoryStatus;
    //订单占用减少数量
    private java.math.BigDecimal decreaseQty;
    private String supplierCode;
    private String skuCode;
    private String inboundBatch;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public BigDecimal getDecreaseQty() {
        return decreaseQty;
    }

    public void setDecreaseQty(BigDecimal decreaseQty) {
        this.decreaseQty = decreaseQty;
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
}
