package com.msk.inventory.bean;

import com.msk.comm.bean.param.BasePageParam;
import com.msk.comm.bean.param.BaseParam;
import com.msk.comm.bean.param.BaseRestPageParam;

/**
 * Created by wang_fan2 on 2016/9/12.
 */
public class SO152502Bean extends BasePageParam {

    private String lgcsCode;
    private String lgcsName;
    private String warehouseCode;
    private String warehouseName;
    private String salePlatform;
    private String slCodeDis;
    private String slName;
    private String pdCode;
    private String pdName;
    private String enabledStockQty;
    private String stockQty;

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

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
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

    public String getEnabledStockQty() {
        return enabledStockQty;
    }

    public void setEnabledStockQty(String enabledStockQty) {
        this.enabledStockQty = enabledStockQty;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }
}
