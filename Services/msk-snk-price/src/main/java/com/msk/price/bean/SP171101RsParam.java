package com.msk.price.bean;

import com.msk.common.bean.RsPageParam;

import java.math.BigDecimal;
import java.util.List;

public class SP171101RsParam extends RsPageParam {

    private String pdCode;
    /*发布编号*/
    private String publishId;
    /*产品编码不到等级*/
    private String pdTypeCode;
    /*产品名称*/
    private String pdName;
    /*发布数量*/
    private BigDecimal publishTotalNum;
    /*需求预测量*/
    private BigDecimal forecastNum;
    /*现有库存*/
    private BigDecimal stockCnt;
    //产品类别（一级分类名称）
    private String classesName;
    //二级分类名称
    private String machiningName;
    //规格
    private String pakageName;
    // 产品特征
    private String featureName;
    //净重
    private String weightName;
    //区域名称
    private String lgcsName;
    //区域名称
    private String lgcsCode;
    private String breedName;
    /*规格code*/
    private String featureCode;
    /*净重code*/
    private String weightCode;
    private List<SP171101Bean> pdList;
    public List<SP171101Bean> getPdList() {
        return pdList;
    }

    public void setPdList(List<SP171101Bean> pdList) {
        this.pdList = pdList;
    }

    /*发布日期*/
    private String demandYearMonth;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getDemandYearMonth() {
        return demandYearMonth;
    }

    public void setDemandYearMonth(String demandYearMonth) {
        this.demandYearMonth = demandYearMonth;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getPdTypeCode() {
        return pdTypeCode;
    }

    public void setPdTypeCode(String pdTypeCode) {
        this.pdTypeCode = pdTypeCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public BigDecimal getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(BigDecimal publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public BigDecimal getForecastNum() {
        return forecastNum;
    }

    public void setForecastNum(BigDecimal forecastNum) {
        this.forecastNum = forecastNum;
    }

    public BigDecimal getStockCnt() {
        return stockCnt;
    }

    public void setStockCnt(BigDecimal stockCnt) {
        this.stockCnt = stockCnt;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getPakageName() {
        return pakageName;
    }

    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
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

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }
}
