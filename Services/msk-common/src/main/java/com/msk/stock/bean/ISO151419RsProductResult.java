package com.msk.stock.bean;
import com.msk.common.base.BaseBean;

import java.math.BigDecimal;

/**
 * ISO151419RsProductResult
 *
 * @author jiang_nan
 * @version 1.0
 */
public class ISO151419RsProductResult extends BaseBean {
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 类型Code */
    private String classesCode;
    /** 类型名称 */
    private String classesName;
    /** 品牌Code */
    private String breedCode;
    /** 品牌名称 */
    private String breedName;
    /** 特征Code */
    private String featureCode;
    /** 特征名称 */
    private String featureName;
    /** 规格Code */
    private String normsCode;
    /** 规格名称 */
    private String normsName;
    /** 等级Code */
    private String pdGradeCode;
    /** 等级名称 */
    private String pdGradeName;
    /** 产品级别 */
    private String pdLevel;

    /*数量*/
    private BigDecimal stockNum;
    /*卖家Code*/
    private String sellerCode;
    /*卖家名称*/
    private String sellerName;
    /*供应商Code*/
    private String supplierCode;
    /*卖家名称*/
    private String supplierName;
    /*单位*/
    private String unit;
    /*外包装体积*/
    private String packingVolume;
    /*包装重量*/
    private String weight;
    /*体积*/
    private String volume;
    private String lgcsCode;
    private String lgcsName;

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

    public BigDecimal getStockNum() {
        return stockNum;
    }

    public void setStockNum(BigDecimal stockNum) {
        this.stockNum = stockNum;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(String packingVolume) {
        this.packingVolume = packingVolume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }


    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
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

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getPdGradeCode() {
        return pdGradeCode;
    }

    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public String getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }
}
