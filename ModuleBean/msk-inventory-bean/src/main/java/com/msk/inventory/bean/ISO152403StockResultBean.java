package com.msk.inventory.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zheng_xu on 2016/8/24.
 */
public class ISO152403StockResultBean implements Serializable {
    // 产品名称
    private String pdName;
    // 在库库存
    private BigDecimal pdTypeSumStock;
    // 区域名称
    private String lgcsName;
    // 区域编码
    private String lgcsCode;
    // 平台名称
    private String salePlatform;
    //在库数量
    private BigDecimal onhandQty;
    //占用数量
    private BigDecimal allocatedQty;

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public BigDecimal getPdTypeSumStock() {
        return pdTypeSumStock;
    }

    public void setPdTypeSumStock(BigDecimal pdTypeSumStock) {
        this.pdTypeSumStock = pdTypeSumStock;
    }

    public BigDecimal getOnhandQty() {
        return onhandQty;
    }

    public void setOnhandQty(BigDecimal onhandQty) {
        this.onhandQty = onhandQty;
    }

    public BigDecimal getAllocatedQty() {
        return allocatedQty;
    }

    public void setAllocatedQty(BigDecimal allocatedQty) {
        this.allocatedQty = allocatedQty;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }
}
