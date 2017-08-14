package com.msk.price.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.core.entity.BaseEntity;
import com.msk.stock.bean.Stock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/5/13.
 */
public class SP171101Bean extends BaseEntity{
    private Date demandStartDate;
    private Date  demandEndDate;
    /*发布编号*/
    private String publishId;
    /*产品编码不到等级*/
    private String pdTypeCode;
    /*产品名称*/
    private String pdName;
    /*发布数量*/
    private String publishTotalNum;
    /*需求预测量*/
    private String forecastNum;
    /*现有库存*/
    private BigDecimal stockCnt;
    //产品类别（一级分类名称）
    private String classes;
    private String classesCode;
    //配料
    private String machining;
    //配料code
    private String machiningCode;
    // 规格
    private String feature;
    private String featureCode;
    //学名
    private String scientificName;
    // 俗名
    private String localName;
    //销售名
     private String salesName;
    private String breed;
    private String breedCode;
    //净重
    private String weight;
    //净重code
    private String weightCode;
    //区域名称
    private String lgcsName;
    //区域名称
    private String lgcsCode;
    //平台编码
    private String salePlatform;
    private List<Stock>  PdStockList;
    List<SP171101RsParam> resultBeanList;
    //发布年月
    private String demandYearMonth;
    //页面申报周期
    private String demandYearMonthShow;
    //填报时间
    private String limitWriteDate;
    //当前月份
    private String nowYearMonth;

    public String getNowYearMonth() {
        return nowYearMonth;
    }
    public void setNowYearMonth(String nowYearMonth) {
        this.nowYearMonth = nowYearMonth;
    }
    public String getLimitWriteDate() {
        return limitWriteDate;
    }

    public void setLimitWriteDate(String limitWriteDate) {
        this.limitWriteDate = limitWriteDate;
    }

    public String getDemandYearMonthShow() {
        return demandYearMonthShow;
    }

    public void setDemandYearMonthShow(String demandYearMonthShow) {
        this.demandYearMonthShow = demandYearMonthShow;
    }

    public String getDemandYearMonth() {
        return demandYearMonth;
    }

    public void setDemandYearMonth(String demandYearMonth) {
        this.demandYearMonth = demandYearMonth;
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

    public String getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(String publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public String getForecastNum() {
        return forecastNum;
    }

    public void setForecastNum(String forecastNum) {
        this.forecastNum = forecastNum;
    }

    public BigDecimal getStockCnt() {
        return stockCnt;
    }

    public void setStockCnt(BigDecimal stockCnt) {
        this.stockCnt = stockCnt;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getMachining() {
        return machining;
    }

    public void setMachining(String machining) {
        this.machining = machining;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
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

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public List<Stock> getPdStockList() {
        return PdStockList;
    }

    public void setPdStockList(List<Stock> pdStockList) {
        PdStockList = pdStockList;
    }

    public List<SP171101RsParam> getResultBeanList() {
        return resultBeanList;
    }

    public void setResultBeanList(List<SP171101RsParam> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

}
