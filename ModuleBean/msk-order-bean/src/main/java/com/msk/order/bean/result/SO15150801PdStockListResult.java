package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * Created by wang_jianzhou on 2016/9/13.
 */
public class SO15150801PdStockListResult extends BaseResult {
    private String pdCode;
    private String pdName;
    private String unit;
    private BigDecimal activeQty;
    private BigDecimal price;
    private String orderLevelName;
    private String priceCyclePeriod;
    private String supplierCode;
    private String supplierName;
    private String lgcsName;
    private BigDecimal orderQty;
    private BigDecimal pdKgPrice;
    private String sellWayCode;
    private String priceCycle;
    private String sellWayName;
    private String logiAreaCode;
    private BigDecimal pdBoxPrice;
    private String proDate;

    public String getProDate() {
        return proDate;
    }

    public void setProDate(String proDate) {
        this.proDate = proDate;
    }

    public BigDecimal getPdBoxPrice() {
        return pdBoxPrice;
    }

    public void setPdBoxPrice(BigDecimal pdBoxPrice) {
        this.pdBoxPrice = pdBoxPrice;
    }

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    public String getSellWayName() {
        return sellWayName;
    }

    public void setSellWayName(String sellWayName) {
        this.sellWayName = sellWayName;
    }

    public String getSellWayCode() {
        return sellWayCode;
    }

    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public BigDecimal getPdKgPrice() {
        return pdKgPrice;
    }

    public void setPdKgPrice(BigDecimal pdKgPrice) {
        this.pdKgPrice = pdKgPrice;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public BigDecimal getActiveQty() {
        return activeQty;
    }

    public void setActiveQty(BigDecimal activeQty) {
        this.activeQty = activeQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderLevelName() {
        return orderLevelName;
    }

    public void setOrderLevelName(String orderLevelName) {
        this.orderLevelName = orderLevelName;
    }

    public String getPriceCyclePeriod() {
        return priceCyclePeriod;
    }

    public void setPriceCyclePeriod(String priceCyclePeriod) {
        this.priceCyclePeriod = priceCyclePeriod;
    }

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
}
