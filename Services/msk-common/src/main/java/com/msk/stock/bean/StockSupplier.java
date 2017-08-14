package com.msk.stock.bean;

import java.math.BigDecimal;

/**
 * Created by zhang_qiang1 on 2016/6/14.
 */
public class StockSupplier {

    private  String supplierCode;//  供应商编码


    private String  pdCode;//  产品编码

    private BigDecimal orderQty;// 订单量


    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}
