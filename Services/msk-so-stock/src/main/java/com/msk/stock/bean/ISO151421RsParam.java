package com.msk.stock.bean;


import com.msk.common.bean.RsPageParam;

import java.util.List;


/**
 * 
 * ISO151421RsParam接受参数.
 *
 * @author pxg
 */
public class ISO151421RsParam extends RsPageParam {
    /** 供应商Code */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 物流区域编码 */
    private String districtCode;

    /**
     * 获得supplierCode
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * 设置supplierCode
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * 获得supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 设置supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * 获得districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 设置districtCode
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * 获得products
     */
    public List<ISO151421ProductsRsParam> getProducts() {
        return products;
    }

    /**
     * 设置products
     */
    public void setProducts(List<ISO151421ProductsRsParam> products) {
        this.products = products;
    }

    /** 产品列表 */
    private List<ISO151421ProductsRsParam> products;

}
