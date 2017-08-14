package com.msk.ds.bean;

import com.msk.core.entity.DsDeliveryStockDetail;

import java.math.BigDecimal;

/**
 * Created by li_kai1 on 2016/7/29.
 */
public class ISC1892I1Detail extends DsDeliveryStockDetail{

    /**产品名称(全称) */
    private String pdName;
    /** 产品一级分类名称 */
    private String classesName;
    /** 产品一级分类编码 */
    private String classesCode;
    /**产品品种编码 */
    private String breedCode;
    /**产品品种名称 */
    private String breedName;
    /**产品特征名称 */
    private String featureName;
    /**产品特征编码 */
    private String featureCode;
    /**产品等级名称 */
    private String gradeName;
    /**产品等级编码 */
    private String gradeCode;
    /**净重 */
    private BigDecimal netWeight;
    /** 产品包装名称 */
    private String normsName;
    /** 物流区名称 */
    private String lgcsName;
    /** 供应商名称 */
    private String supplierName;
    /** 供应商编码 */
    private String supplierCode;
    /** 产品单位 */
    private String unit;
    /** 产品单箱体积 */
    private BigDecimal packingVolume;
    /** 产品体积 */
    private BigDecimal volume;
    /** 发货入库单来源标识 */
    private String sourceFlg;


    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }


    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Override
    public String getSourceFlg() {
        return sourceFlg;
    }

    @Override
    public void setSourceFlg(String sourceFlg) {
        this.sourceFlg = sourceFlg;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
