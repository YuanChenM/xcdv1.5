package com.msk.stock.bean;


import com.msk.common.bean.RsPageParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存统计报表参数
 * @author jiang_nan
 * @version 1.0
 **/
public class ISO151419RsParam extends RsPageParam {
    /** 年月yyyy-mm-dd */
    private String years;
    /** 卖家Code */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
    /** 供应商Code */
    private String supplierCode;
    /** 卖家名称 */
    private String supplierName;
    /** 分组类型：1：卖家，2：供应商，3：产品 */
    private String groupType;
    /**产品参数*/
    private List<BaseStockProductParam> product=new ArrayList<>();
    private String lgcsCode;
    private String lgcsName;


    private List<String> pdCode;
    private List<String> pdName;
    private List<String> weight;


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

    public List<String> getPdCode() {
        return pdCode;
    }

    public void setPdCode(List<String> pdCode) {
        this.pdCode = pdCode;
    }

    public List<String> getPdName() {
        return pdName;
    }

    public void setPdName(List<String> pdName) {
        this.pdName = pdName;
    }

    public List<String> getWeight() {
        return weight;
    }

    public void setWeight(List<String> weight) {
        this.weight = weight;
    }

    /**
     * Getter method for property <tt>years</tt>.
     *
     * @return property value of years
     */
    public String getYears() {
        return years;
    }

    /**
     * Setter method for property <tt>years</tt>.
     *
     * @param years value to be assigned to property years
     */
    public void setYears(String years) {
        this.years = years;
    }

    /**
     * Getter method for property <tt>sellerCode</tt>.
     *
     * @return property value of sellerCode
     */
    public String getSellerCode() {
        return sellerCode;
    }

    /**
     * Setter method for property <tt>sellerCode</tt>.
     *
     * @param sellerCode value to be assigned to property sellerCode
     */
    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * Getter method for property <tt>sellerName</tt>.
     *
     * @return property value of sellerName
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * Setter method for property <tt>sellerName</tt>.
     *
     * @param sellerName value to be assigned to property sellerName
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Getter method for property <tt>supplierCode</tt>.
     *
     * @return property value of supplierCode
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Setter method for property <tt>supplierCode</tt>.
     *
     * @param supplierCode value to be assigned to property supplierCode
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * Getter method for property <tt>supplierName</tt>.
     *
     * @return property value of supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Setter method for property <tt>supplierName</tt>.
     *
     * @param supplierName value to be assigned to property supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * Getter method for property <tt>groupType</tt>.
     *
     * @return property value of groupType
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * Setter method for property <tt>groupType</tt>.
     *
     * @param groupType value to be assigned to property groupType
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public List<BaseStockProductParam> getProduct() {
        return product;
    }

    public void setProduct(List<BaseStockProductParam> product) {
        this.product = product;
    }
}
