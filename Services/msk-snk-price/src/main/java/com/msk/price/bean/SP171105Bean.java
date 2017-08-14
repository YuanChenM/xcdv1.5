package com.msk.price.bean;

import com.hoperun.core.utils.DateTimeUtil;
import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商：待申报产品一览
 */
public class SP171105Bean extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    //物流区编码
    private String lgcsCode;

    //物流区名称
    private String lgcsName;

    //产品名称
    private String pdName;

    //产品分类
    private String classesCode;

    //产品分类名称
    private String classesName;

    //二级分类
    private String machiningCode;

    //二级分类名称
    private String machiningName;
    //品种
    private String breedCode;
    //品种名称
    private String breedName;
    //产品特征
    private String featureCode;

    //产品特征 Name
    private String featureName;

    //净重
    private String weightCode;

    //净重
    private String weight;

    //产品编码
    private String pdTypeCode;

    //产品编码(10位)
    private String pdCode;

    //已有库存量
    private String stockQty;

    //本期需求量
    private BigDecimal publishTotalNum;

    //供应等级
    private String gradeCode;

    //供应等级
    private String gradeName;

    //对应等级需求量
    private String publishNum;

    //申报数量
    private String applyQty;

    //OEM ID
    private BigDecimal demandId;

    //申报周期
    private String demandYearMonth;

    //申报起始时间
    private Date startDate;

    //申报截止时间
    private Date endDate;

    //需求预测数量
    private String forecastNum;

    // 周期
    private String demandYearMonthShow;

    // 起止日期
    private String demandLimitedDateShow;

    //确认情况编码
    private BigDecimal isConfirm;

    //确认情况
    private String isConfirmName;

    //卖家Account
    private String slAccount;

    //卖家id
    private String slId;

    //卖家code
    private String slCode;

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

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
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

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getStockQty() {
        return stockQty;
    }

    public void setStockQty(String stockQty) {
        this.stockQty = stockQty;
    }

    public BigDecimal getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(BigDecimal publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(String publishNum) {
        this.publishNum = publishNum;
    }

    public String getApplyQty() {
        return applyQty;
    }

    public void setApplyQty(String applyQty) {
        this.applyQty = applyQty;
    }

    public BigDecimal getDemandId() {
        return demandId;
    }

    public void setDemandId(BigDecimal demandId) {
        this.demandId = demandId;
    }

    public String getDemandYearMonth() {
        return demandYearMonth;
    }

    public void setDemandYearMonth(String demandYearMonth) {
        this.demandYearMonth = demandYearMonth;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getForecastNum() {
        return forecastNum;
    }

    public void setForecastNum(String forecastNum) {
        this.forecastNum = forecastNum;
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

    public BigDecimal getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(BigDecimal isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getIsConfirmName() {
        return isConfirmName;
    }

    public void setIsConfirmName(String isConfirmName) {
        this.isConfirmName = isConfirmName;
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

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
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
}
