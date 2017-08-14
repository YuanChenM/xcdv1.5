package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * ProductStock
 *SO15158081_选择产品画面，后台接口返回参数
 * @author wang_jianzhou
 * @version 1.0
 **/
public class SO15150801ProductStockBean extends BaseResult{

    /** 价盘周期*/
    private String priceCyclePeriod;
    /** 供应商编码*/
    private String supplierCode;

    private String supplierName;

    private String unit;

    private String pdCode;

    private String pdName;

    private BigDecimal activeQty;

    private BigDecimal price;

    private String orderLevelName;

    private String lgcsName;

    private String slCode;

    private String lgcsCode;

    private String salePlatform;

    private String pricecycleId;

    private String proDate;

    private List<SO15150801PdStockListResult> pdStockList;

    private List<SO15150801PdStockListResult> productList;

    public String getProDate() {
        return proDate;
    }

    public void setProDate(String proDate) {
        this.proDate = proDate;
    }

    public List<SO15150801PdStockListResult> getProductList() {
        return productList;
    }

    public void setProductList(List<SO15150801PdStockListResult> productList) {
        this.productList = productList;
    }

    public List<SO15150801PdStockListResult> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<SO15150801PdStockListResult> pdStockList) {
        this.pdStockList = pdStockList;
    }

    public String getPricecycleId() {
        return pricecycleId;
    }

    public void setPricecycleId(String pricecycleId) {
        this.pricecycleId = pricecycleId;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }
}
