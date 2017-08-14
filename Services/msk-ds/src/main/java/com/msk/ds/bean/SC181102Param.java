package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * zhou_yajun on 2016/1/28.
 */
public class SC181102Param extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**产品名称 */
    private String productName;
    /**地区编码 */
    private String areaCode;
    /**供应商编码 */
    private String supplierCode;
    /**期*/
    private String distMonth;
    /**当前日期的半旬编码*/
    private String halfCode;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getDistMonth() {
        return distMonth;
    }

    public void setDistMonth(String distMonth) {
        this.distMonth = distMonth;
    }

    public String getHalfCode() {
        return halfCode;
    }

    public void setHalfCode(String halfCode) {
        this.halfCode = halfCode;
    }

}
