package com.msk.stock.bean;


import com.msk.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * 卖家库存管理表
 */

public class SO151202Bean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    //库存ID  STOCK_ID
    private String stockId;

    //物流区编码
    private String lgcsCode;

    //物流区名称
    private String lgcsName;

    //销售平台 SALE_PLATFORM
    private String salePlatform;
    //仓库编号
    private String warehouseCode;

    //仓库名称
    private String warehouseName;


    //卖家编码
    private String slCode;

    //卖家名称
    private String slName;

    //产品编码
    private String pdCode;

    //产品名称
    private String pdName;

    //库存数量
    private String stockQty;

    //显示卖家编码
    private String slCodeDis;

    //  可用库存
    private BigDecimal  enabledStockQty;

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
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

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getEnabledStockQty() {
        return enabledStockQty;
    }

    public void setEnabledStockQty(BigDecimal enabledStockQty) {
        this.enabledStockQty = enabledStockQty;
    }
}