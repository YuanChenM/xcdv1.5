package com.msk.inventory.bean;

import java.io.Serializable;

/**
 * Created by zheng_xu on 2016/9/12.
 */
public class ISO152417SupplierBean implements Serializable {
    /** 产品编码 */
    private String pdCode;
    /** 供应商编码 */
    private String supplierCode;
    /** 物流区编号 */
    private String lgcsCode;

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

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
}
