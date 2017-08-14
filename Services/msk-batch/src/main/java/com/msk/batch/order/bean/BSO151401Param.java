package com.msk.batch.order.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.SoSalesRanking;

import java.util.Date;
import java.util.List;

/**
 * Created by liutao on 2016/9/7.
 */
public class BSO151401Param extends BaseParam {
    private Date currentDate;

    private String supplierCode;

    private String lgcsCode;

    private String startPriceCycle;

    private String endPriceCycle;

    private String salePlatform;

    private Integer inventoryStatus;

    private String priceCycle;

    private String lastOnePriceCycle;

    List<SoSalesRanking> sellers;

    public List<SoSalesRanking> getSellers() {
        return sellers;
    }

    public void setSellers(List<SoSalesRanking> sellers) {
        this.sellers = sellers;
    }

    public String getLastOnePriceCycle() {
        return lastOnePriceCycle;
    }

    public void setLastOnePriceCycle(String lastOnePriceCycle) {
        this.lastOnePriceCycle = lastOnePriceCycle;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getStartPriceCycle() {
        return startPriceCycle;
    }

    public void setStartPriceCycle(String startPriceCycle) {
        this.startPriceCycle = startPriceCycle;
    }

    public String getEndPriceCycle() {
        return endPriceCycle;
    }

    public void setEndPriceCycle(String endPriceCycle) {
        this.endPriceCycle = endPriceCycle;
    }
}
