/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_product_new对应的SlProductNew。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlProductNew extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 卖家编码 */
    private String slCode;
    /** 卖家产品ID */
    private Integer slPdId;
    /** 生产商企业ID */
    private Integer prodEpId;
    /** 品牌商企业ID */
    private Integer brandEpId;
    /** 产品品牌ID */
    private Integer brandId;
    /** 产品类别 */
    private String pdClassesCode;
    /** 产品品种 */
    private String pdBreedCode;
    /** 产品特征 */
    private String pdFeatureCode;
    /**
     * <p>默认构造函数。</p>
     */
    public SlProductNew() {

    }

    /**
     * <p>卖家编码。</p>
     *
     * @return the 卖家编码
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>卖家编码。</p>
     *
     * @param slCode 卖家编码。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>卖家产品ID。</p>
     *
     * @return the 卖家产品ID
     */
    public Integer getSlPdId() {
        return slPdId;
    }

    /**
     * <p>卖家产品ID。</p>
     *
     * @param slPdId 卖家产品ID。
     */
    public void setSlPdId(Integer slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * <p>生产商企业ID。</p>
     *
     * @return the 生产商企业ID
     */
    public Integer getProdEpId() {
        return prodEpId;
    }

    /**
     * <p>生产商企业ID。</p>
     *
     * @param prodEpId 生产商企业ID。
     */
    public void setProdEpId(Integer prodEpId) {
        this.prodEpId = prodEpId;
    }

    /**
     * <p>品牌商企业ID。</p>
     *
     * @return the 品牌商企业ID
     */
    public Integer getBrandEpId() {
        return brandEpId;
    }

    /**
     * <p>品牌商企业ID。</p>
     *
     * @param brandEpId 品牌商企业ID。
     */
    public void setBrandEpId(Integer brandEpId) {
        this.brandEpId = brandEpId;
    }

    /**
     * <p>产品品牌ID。</p>
     *
     * @return the 产品品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * <p>产品品牌ID。</p>
     *
     * @param brandId 产品品牌ID。
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>产品类别。</p>
     *
     * @return the 产品类别
     */
    public String getPdClassesCode() {
        return pdClassesCode;
    }

    /**
     * <p>产品类别。</p>
     *
     * @param pdClassesCode 产品类别。
     */
    public void setPdClassesCode(String pdClassesCode) {
        this.pdClassesCode = pdClassesCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @return the 产品品种
     */
    public String getPdBreedCode() {
        return pdBreedCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @param pdBreedCode 产品品种。
     */
    public void setPdBreedCode(String pdBreedCode) {
        this.pdBreedCode = pdBreedCode;
    }

    /**
     * <p>产品特征。</p>
     *
     * @return the 产品特征
     */
    public String getPdFeatureCode() {
        return pdFeatureCode;
    }

    /**
     * <p>产品特征。</p>
     *
     * @param pdFeatureCode 产品特征。
     */
    public void setPdFeatureCode(String pdFeatureCode) {
        this.pdFeatureCode = pdFeatureCode;
    }

}
