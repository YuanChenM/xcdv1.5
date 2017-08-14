package com.msk.seller.bean;

import com.msk.core.entity.SlProduct;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang_chunyan on 2016/4/8.
 */
public class SlProductBean extends SlProduct{
    /**生产商名称*/
    private String prodEpName;
    /**品牌名称*/
    private String brandName;
    /**产品类别*/
    private String classesName;
    /**产品品种*/
    private String breedName;
    /**产品编码*/
    private String productCode;
    /**产品特征*/
    private String featureName;
    //加工质量定级名称
    private String slTncGradeName;
    //加工技术定级名称
    private String slQltGradeName;
    //二级分类名称
    private String machiningName;
    //卖家产品履历表id
    private Long  hisId;

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * Getter method for property <tt>slTncGradeName</tt>.
     *
     * @return property value of slTncGradeName
     */
    public String getSlTncGradeName() {
        return slTncGradeName;
    }

    /**
     * Setter method for property <tt>slTncGradeName</tt>.
     *
     * @param slTncGradeName value to be assigned to property slTncGradeName
     */
    public void setSlTncGradeName(String slTncGradeName) {
        this.slTncGradeName = slTncGradeName;
    }

    /**
     * Getter method for property <tt>slQltGradeName</tt>.
     *
     * @return property value of slQltGradeName
     */
    public String getSlQltGradeName() {
        return slQltGradeName;
    }

    /**
     * Setter method for property <tt>slQltGradeName</tt>.
     *
     * @param slQltGradeName value to be assigned to property slQltGradeName
     */
    public void setSlQltGradeName(String slQltGradeName) {
        this.slQltGradeName = slQltGradeName;
    }

    /**
     * 获得featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * 设置featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * 获得prodEpName
     */
    public String getProdEpName() {
        return prodEpName;
    }

    /**
     * 设置prodEpName
     */
    public void setProdEpName(String prodEpName) {
        this.prodEpName = prodEpName;
    }

    /**
     * 获得brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获得classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * 设置classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * 获得breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * 设置breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * 获得productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getHisId() {
        return hisId;
    }

    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }
}

