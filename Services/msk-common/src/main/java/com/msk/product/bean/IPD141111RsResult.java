package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * 
 * IPD141111RsResult.产品查询价盘
 *
 * @author zhou_ling     修改xhy 2016-3-29
 */
public class IPD141111RsResult extends BaseEntity {


    private String productId;

    private String gradeCode;

    private String logiAreaCode;

    private String pricePeriod;

    private List<IPD141111PriceCycle> priceList ;

    /**
     * Getter method for property <tt>pricePeriod</tt>.
     *
     * @return property value of pricePeriod
     */
    public String getPricePeriod() {
        return pricePeriod;
    }

    /**
     * Setter method for property <tt>pricePeriod</tt>.
     *
     * @param pricePeriod value to be assigned to property pricePeriod
     */
    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    /**
     * Getter method for property <tt>productId</tt>.
     *
     * @return property value of productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Setter method for property <tt>productId</tt>.
     *
     * @param productId value to be assigned to property productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Getter method for property <tt>gradeCode</tt>.
     *
     * @return property value of gradeCode
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * Setter method for property <tt>gradeCode</tt>.
     *
     * @param gradeCode value to be assigned to property gradeCode
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * Getter method for property <tt>logiAreaCode</tt>.
     *
     * @return property value of logiAreaCode
     */
    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    /**
     * Setter method for property <tt>logiAreaCode</tt>.
     *
     * @param logiAreaCode value to be assigned to property logiAreaCode
     */
    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    /**
     * Getter method for property <tt>priceList</tt>.
     *
     * @return property value of priceList
     */
    public List<IPD141111PriceCycle> getPriceList() {
        return priceList;
    }

    /**
     * Setter method for property <tt>priceList</tt>.
     *
     * @param priceList value to be assigned to property priceList
     */
    public void setPriceList(List<IPD141111PriceCycle> priceList) {
        this.priceList = priceList;
    }
}