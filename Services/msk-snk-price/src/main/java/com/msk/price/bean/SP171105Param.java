package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

/**
 * OEM供应商：OEM待申报产品一览
 */
public class SP171105Param extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    //物流区编码
    private String lgcsAreaCode;

    //产品编码
    private String pdCode;

    //现有库存量
    private String stockQty;

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }
}
