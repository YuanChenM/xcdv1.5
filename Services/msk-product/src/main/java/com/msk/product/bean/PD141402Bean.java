package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by Administrator on 2016/4/11. xhy
 */
public class PD141402Bean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 生产商名称
     */
    private String prodEpName;
    //品牌
    private String brandEpName;
    //品牌名称
    private String brandName;
    //产品类别
    private String pdClassesName;
    /**
     * 产品二级分类名称
     */
    private String machiningName;
    /**
     * 净重编码名称
     */
    private String weightName;
    //产品品种
    private String pdBreedName;
    //产品特征
    private String pdFeatureName;
    //产品状态
    private String statusName;
    //卖家产品履历表id
    private Long hisId;

    private String slPdId;

    private String slCodeDis;

    private String slCode;

    private String standardId;

    private String classestreeCode;

    /**
     * Getter method for property <tt>classestreeCode</tt>.
     *
     * @return property value of classestreeCode
     */
    public String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * Setter method for property <tt>classestreeCode</tt>.
     *
     * @param classestreeCode value to be assigned to property classestreeCode
     */
    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public String getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>slCodeDis</tt>.
     *
     * @return property value of slCodeDis
     */
    public String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * Setter method for property <tt>slCodeDis</tt>.
     *
     * @param slCodeDis value to be assigned to property slCodeDis
     */
    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * Getter method for property <tt>slPdId</tt>.
     *
     * @return property value of slPdId
     */
    public String getSlPdId() {
        return slPdId;
    }

    /**
     * Setter method for property <tt>slPdId</tt>.
     *
     * @param slPdId value to be assigned to property slPdId
     */
    public void setSlPdId(String slPdId) {
        this.slPdId = slPdId;
    }

    /**
     * Getter method for property <tt>prodEpName</tt>.
     *
     * @return property value of prodEpName
     */
    public String getProdEpName() {
        return prodEpName;
    }

    /**
     * Setter method for property <tt>prodEpName</tt>.
     *
     * @param prodEpName value to be assigned to property prodEpName
     */
    public void setProdEpName(String prodEpName) {
        this.prodEpName = prodEpName;
    }

    /**
     * Getter method for property <tt>brandEpName</tt>.
     *
     * @return property value of brandEpName
     */
    public String getBrandEpName() {
        return brandEpName;
    }

    /**
     * Setter method for property <tt>brandEpName</tt>.
     *
     * @param brandEpName value to be assigned to property brandEpName
     */
    public void setBrandEpName(String brandEpName) {
        this.brandEpName = brandEpName;
    }

    /**
     * Getter method for property <tt>brandName</tt>.
     *
     * @return property value of brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Setter method for property <tt>brandName</tt>.
     *
     * @param brandName value to be assigned to property brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Getter method for property <tt>pdClassesName</tt>.
     *
     * @return property value of pdClassesName
     */
    public String getPdClassesName() {
        return pdClassesName;
    }

    /**
     * Setter method for property <tt>pdClassesName</tt>.
     *
     * @param pdClassesName value to be assigned to property pdClassesName
     */
    public void setPdClassesName(String pdClassesName) {
        this.pdClassesName = pdClassesName;
    }

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
     * Getter method for property <tt>weightName</tt>.
     *
     * @return property value of weightName
     */
    public String getWeightName() {
        return weightName;
    }

    /**
     * Setter method for property <tt>weightName</tt>.
     *
     * @param weightName value to be assigned to property weightName
     */
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    /**
     * Getter method for property <tt>pdBreedName</tt>.
     *
     * @return property value of pdBreedName
     */
    public String getPdBreedName() {
        return pdBreedName;
    }

    /**
     * Setter method for property <tt>pdBreedName</tt>.
     *
     * @param pdBreedName value to be assigned to property pdBreedName
     */
    public void setPdBreedName(String pdBreedName) {
        this.pdBreedName = pdBreedName;
    }

    /**
     * Getter method for property <tt>pdFeatureName</tt>.
     *
     * @return property value of pdFeatureName
     */
    public String getPdFeatureName() {
        return pdFeatureName;
    }

    /**
     * Setter method for property <tt>pdFeatureName</tt>.
     *
     * @param pdFeatureName value to be assigned to property pdFeatureName
     */
    public void setPdFeatureName(String pdFeatureName) {
        this.pdFeatureName = pdFeatureName;
    }

    /**
     * Getter method for property <tt>statusName</tt>.
     *
     * @return property value of statusName
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * Setter method for property <tt>statusName</tt>.
     *
     * @param statusName value to be assigned to property statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * Getter method for property <tt>hisId</tt>.
     *
     * @return property value of hisId
     */
    public Long getHisId() {
        return hisId;
    }

    /**
     * Setter method for property <tt>hisId</tt>.
     *
     * @param hisId value to be assigned to property hisId
     */
    public void setHisId(Long hisId) {
        this.hisId = hisId;
    }
}
