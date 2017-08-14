package com.msk.price.bean;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OEM供应商：供应商待报价一览
 */
public class SP171109Bean extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    //物流区编码
    private String lgcsCode;

    //物流区名称
    private String lgcsName;

    //产品名称
    private String pdName;

    //产品编码(10位)
    private String pdCode;

    //产品分类
    private String classesCode;

    //产品分类名称
    private String classesName;

    //二级分类
    private String machiningCode;

    //二级分类名称
    private String machining;

    //产品特征
    private String featureCode;

    //产品特征 Name
    private String feature;

    //净重
    private String weightCode;

    //净重
    private String weight;

    //产品编码
    private String pdTypeCode;

    //已有库存量
    private String stockQty;

    //供应等级
    private String gradeCode;

    //供应等级
    private String grade;


    //品种编码
    private String breedCode;

    //品种名称
    private String breed;

    //OEM ID
    private BigDecimal demandId;

    //价盘周期
    private String pricePeriod;

    //申报起始时间
    private String pricePeriodStart;

    //申报截止时间
    private String pricePeriodEnd;

    //申报起始时间
    private Date priceStartDate;

    //申报截止时间
    private Date priceEndDate;

    //价盘周期时间
    private String pricePeriodTime;
    //是否是新价盘
    private String isNewPrice;//1:是新的，0:旧的
    //上个价盘周期
    private String lastPricePeriod;

    //上个价盘时间段
    private String lastPricePeriodTime;

    //sellerName
    private String sellerName;

    //报价
    private String applyPrice;

    //申报周期
    private String demandYearMonth;

    //申报周期 name
    private String demandYearMonthName;

    //申报起始时间
    private Date demandStartDate;

    //申报截止时间
    private Date demandEndDate;

    //前台周期展示信息
    private String demandYearMonthShow;

    //前台起止时间展示信息
    private String demandLimitedDateShow;

    //卖家Account
    private String slAccount;

    //卖家id
    private String slId;

    //卖家code
    private String sellerCode;

    //企业id
    private String epId;

    //企业名
    private String epName;

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

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
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

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
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

    public String getPdTypeCode() {
        return pdTypeCode;
    }

    public void setPdTypeCode(String pdTypeCode) {
        this.pdTypeCode = pdTypeCode;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
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

    public BigDecimal getDemandId() {
        return demandId;
    }

    public void setDemandId(BigDecimal demandId) {
        this.demandId = demandId;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public String getPricePeriodStart() {
        return pricePeriodStart;
    }

    public void setPricePeriodStart(String pricePeriodStart) {
        this.pricePeriodStart = pricePeriodStart;
    }

    public String getPricePeriodEnd() {
        return pricePeriodEnd;
    }

    public void setPricePeriodEnd(String pricePeriodEnd) {
        this.pricePeriodEnd = pricePeriodEnd;
    }

    public Date getPriceEndDate() {
        return priceEndDate;
    }

    public void setPriceEndDate(Date priceEndDate) {
        this.priceEndDate = priceEndDate;
    }

    public Date getPriceStartDate() {
        return priceStartDate;
    }

    public void setPriceStartDate(Date priceStartDate) {
        this.priceStartDate = priceStartDate;
    }

    public String getPricePeriodTime() {
        return pricePeriodTime;
    }

    public void setPricePeriodTime(String pricePeriodTime) {
        this.pricePeriodTime = pricePeriodTime;
    }

    public String getLastPricePeriod() {
        return lastPricePeriod;
    }

    public void setLastPricePeriod(String lastPricePeriod) {
        this.lastPricePeriod = lastPricePeriod;
    }

    public String getLastPricePeriodTime() {
        return lastPricePeriodTime;
    }

    public void setLastPricePeriodTime(String lastPricePeriodTime) {
        this.lastPricePeriodTime = lastPricePeriodTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getApplyPrice() {
        return applyPrice;
    }

    public void setApplyPrice(String applyPrice) {
        this.applyPrice = applyPrice;
    }

    public String getDemandYearMonth() {
        return demandYearMonth;
    }

    public void setDemandYearMonth(String demandYearMonth) {
        this.demandYearMonth = demandYearMonth;
    }

    public String getDemandYearMonthName() {
        return demandYearMonthName;
    }

    public void setDemandYearMonthName(String demandYearMonthName) {
        this.demandYearMonthName = demandYearMonthName;
    }

    public Date getDemandStartDate() {
        return demandStartDate;
    }

    public void setDemandStartDate(Date demandStartDate) {
        this.demandStartDate = demandStartDate;
    }

    public Date getDemandEndDate() {
        return demandEndDate;
    }

    public void setDemandEndDate(Date demandEndDate) {
        this.demandEndDate = demandEndDate;
    }

    public String getDemandYearMonthShow() {
        return demandYearMonthShow;
    }

    public void setDemandYearMonthShow(String demandYearMonthShow) {
        this.demandYearMonthShow = demandYearMonthShow;
    }

    public String getDemandLimitedDateShow() {
        return demandLimitedDateShow;
    }

    public void setDemandLimitedDateShow(String demandLimitedDateShow) {
        this.demandLimitedDateShow = demandLimitedDateShow;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getIsNewPrice() {
        return isNewPrice;
    }

    public void setIsNewPrice(String isNewPrice) {
        this.isNewPrice = isNewPrice;
    }
}
