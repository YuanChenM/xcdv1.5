/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_research_brand对应的ByResearchBrand</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByResearchBrand extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** PRODUCT_ID */
    private String productId;
    /** CATEGORY_ID */
    private Long categoryId;
    /** PRODUCT_SUF */
    private String productSuf;
    /** BRAND_NAME */
    private String brandName;
    /**
     * <p>默认构造函数</p>
     */
    public ByResearchBrand() {

    }

    /**
     * <p>PRODUCT_ID</p>
     *
     * @return the PRODUCT_ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * <p>PRODUCT_ID</p>
     *
     * @param productId PRODUCT_ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * <p>CATEGORY_ID</p>
     *
     * @return the CATEGORY_ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * <p>CATEGORY_ID</p>
     *
     * @param categoryId CATEGORY_ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * <p>PRODUCT_SUF</p>
     *
     * @return the PRODUCT_SUF
     */
    public String getProductSuf() {
        return productSuf;
    }

    /**
     * <p>PRODUCT_SUF</p>
     *
     * @param productSuf PRODUCT_SUF
     */
    public void setProductSuf(String productSuf) {
        this.productSuf = productSuf;
    }

    /**
     * <p>BRAND_NAME</p>
     *
     * @return the BRAND_NAME
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * <p>BRAND_NAME</p>
     *
     * @param brandName BRAND_NAME
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}
