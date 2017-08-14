package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

/**
 * Created by wang_shuai on 2016/8/25.
 */
public class ISO151415SlArtNoResult extends BaseEntity {
    private Long artnoId;
    /** 对应卖家表的SL_CODE */
    private String slCode;
    /** 5位顺番 */
    private String slPdArtno;
    /** 7位，分类码1+区划码3+顺番码3 */
    private String slCodeDis;
    private String skuCode;
    private String pdCode;
    private String classesCode;
    private String machiningCode;
    private String breedCode;
    private String featureCode;
    private String weightCode;
    private String normsCode;
    private String gradeCode;
    private String salePlatform;
    private String brandEpId;
    private String brandId;
    private String manufactureCode;
    private String factoryCode;
    private String saleRegionCode;
    private String saleStatus;
    /** 国别 */
    private String pdCountry;
    /** 产地 */
    private String pdPlace;
    /** 预留,卖家自己输入 */
    private String pdStandard;
    /** 预留 */
    private String pdIngredient;
    /** 预留 */
    private String foodLicense;
    /** 预留 */
    private String shelfLife;
    /** 预留 */
    private String storageCondition;
    /** 预留 */
    private String normsSpecification;

    public Long getArtnoId() {
        return artnoId;
    }

    public void setArtnoId(Long artnoId) {
        this.artnoId = artnoId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlPdArtno() {
        return slPdArtno;
    }

    public void setSlPdArtno(String slPdArtno) {
        this.slPdArtno = slPdArtno;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getBrandEpId() {
        return brandEpId;
    }

    public void setBrandEpId(String brandEpId) {
        this.brandEpId = brandEpId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getManufactureCode() {
        return manufactureCode;
    }

    public void setManufactureCode(String manufactureCode) {
        this.manufactureCode = manufactureCode;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getSaleRegionCode() {
        return saleRegionCode;
    }

    public void setSaleRegionCode(String saleRegionCode) {
        this.saleRegionCode = saleRegionCode;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getPdCountry() {
        return pdCountry;
    }

    public void setPdCountry(String pdCountry) {
        this.pdCountry = pdCountry;
    }

    public String getPdPlace() {
        return pdPlace;
    }

    public void setPdPlace(String pdPlace) {
        this.pdPlace = pdPlace;
    }

    public String getPdStandard() {
        return pdStandard;
    }

    public void setPdStandard(String pdStandard) {
        this.pdStandard = pdStandard;
    }

    public String getPdIngredient() {
        return pdIngredient;
    }

    public void setPdIngredient(String pdIngredient) {
        this.pdIngredient = pdIngredient;
    }

    public String getFoodLicense() {
        return foodLicense;
    }

    public void setFoodLicense(String foodLicense) {
        this.foodLicense = foodLicense;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getNormsSpecification() {
        return normsSpecification;
    }

    public void setNormsSpecification(String normsSpecification) {
        this.normsSpecification = normsSpecification;
    }
}
