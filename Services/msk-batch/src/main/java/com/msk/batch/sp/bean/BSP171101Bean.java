package com.msk.batch.sp.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sun_jiaju
 */
public class BSP171101Bean extends BaseEntity {
    /**
     * 价盘ID
     */
    private long priceId;
    /**
     * 价盘周期
     */
    private String pricePeriod;
    /**
     * 价盘周期开始日期
     */
    private Date priceStartdate;
    /**
     * 价盘周期结束日期
     */
    private Date priceEnddate;
    /**
     * 物流区CODE
     */
    private String lgcsCode;
    /**
     * 物流区名称
     */
    private String lgcsName;
    /**
     * 产品编码
     */
    private String pdCode;
    /**
     * 产品名称
     */
    private String pdName;
    /**
     * 产品一级分类编码
     */
    private String classesCode;
    /**
     * 产品一级分类名称
     */
    private String classes;
    /**
     * 产品二级分类编码
     */
    private String machiningCode;
    /**
     * 产品二级分类名称
     */
    private String machining;
    /**
     * 品种编码
     */
    private String breedCode;
    /**
     * 品种
     */
    private String breed;
    /**
     * 特征编码
     */
    private String featureCode;
    /**
     * 特征
     */
    private String feature;
    /**
     * 净重编码
     */
    private String weightCode;
    /**
     * 净重
     */
    private String weight;
    /**
     * 等级编码
     */
    private String gradeCode;
    /**
     * 等级
     */
    private String grade;
    /**
     * 分销通道
     */
    private String wayCode;
    /**
     * 价盘通道级别
     */
    private String waygradeCode;
    /**
     * 价盘通道级别名称
     */
    private String waygradeName;
    /**
     * 销售价格
     */
    private BigDecimal waygradePrice;

    /**
     * 箱价
     */
    private BigDecimal waygradePriceBox;

    /**
     * 是否参与分销 0：未  1：是
     */
    private String isValid;

    public long getPriceId() {
        return priceId;
    }

    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public Date getPriceStartdate() {
        return priceStartdate;
    }

    public void setPriceStartdate(Date priceStartdate) {
        this.priceStartdate = priceStartdate;
    }

    public Date getPriceEnddate() {
        return priceEnddate;
    }

    public void setPriceEnddate(Date priceEnddate) {
        this.priceEnddate = priceEnddate;
    }

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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachining() {
        return machining;
    }

    public void setMachining(String machining) {
        this.machining = machining;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getWayCode() {
        return wayCode;
    }

    public void setWayCode(String wayCode) {
        this.wayCode = wayCode;
    }

    public String getWaygradeCode() {
        return waygradeCode;
    }

    public void setWaygradeCode(String waygradeCode) {
        this.waygradeCode = waygradeCode;
    }

    public String getWaygradeName() {
        return waygradeName;
    }

    public void setWaygradeName(String waygradeName) {
        this.waygradeName = waygradeName;
    }

    public BigDecimal getWaygradePrice() {
        return waygradePrice;
    }

    public void setWaygradePrice(BigDecimal waygradePrice) {
        this.waygradePrice = waygradePrice;
    }

    public BigDecimal getWaygradePriceBox() {
        return waygradePriceBox;
    }

    public void setWaygradePriceBox(BigDecimal waygradePriceBox) {
        this.waygradePriceBox = waygradePriceBox;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}
