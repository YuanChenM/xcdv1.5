package com.msk.order.bean;


import com.msk.common.base.BaseBean;

import java.math.BigDecimal;

/**
 *库存基本信息Bean
 *@author jiang_nan
 *@version 1.0
 **/
public class BaseStockBean extends BaseBean {
    /**库存ID*/
    private String stockId;
    /**卖家Code*/
    private String sellerCode;
    /**供应商Code*/
    private String supplierCode;
    /**产品Code*/
    private String productCode;
    /**库存数量*/
    private BigDecimal stockQty;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }
}
