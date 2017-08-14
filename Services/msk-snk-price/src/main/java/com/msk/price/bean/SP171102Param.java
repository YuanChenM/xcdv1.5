package com.msk.price.bean;


import com.hoperun.core.bean.BaseParam;
import java.math.BigDecimal;
import java.util.List;
/**
 * 供应商需求发布明细Param
 *
 */
public class SP171102Param extends BaseParam {

    private static final long serialVersionUID = 1L;
    /*发布ID*/
    private Long publishId;
    /*发布年月*/
    private String publishYm;
    /* 发布年月周期开始日*/
    private String demandStartDate;
    /* 发布年月周期结束日*/
    private String demandEndDate;
    /*学名*/
    private String scientificName;
    /*俗名*/
    private String localName;
    /*销售名*/
    private String salesName;
    /*产品编码*/
    private String pdCode;
    /*一级分类编码*/
    private String classesCode;
    /*一级分类名称*/
    private String classes;
    /*二级分类编码*/
    private String machiningCode;
    /*二级分类名称*/
    private String machining;
    /*品种编码*/
    private String breedCode;
    /*品种*/
    private String breed;
    /*特征*/
    private String feature;
    /*净重 */
    private String weight;
    /*等级编码*/
    private String gradeCode;
    /*等级名称*/
    private String grade;
    /*需求预测ID*/
    private Long forecastId;
    /* 发布数量*/
    private BigDecimal publishNum;
    /*等级占比1 */
    private BigDecimal gradeRatio1;
    /*等级占比2*/
    private BigDecimal gradeRatio2;
    /*等级占比3*/
    private BigDecimal gradeRatio3;
    /*类型*/
    private String ratioType;
    /*类型明细*/
    private String ratioTypeDetail;
    /*类型明细名称*/
    private String ratioTypeDetailNm;
    /*类型值*/
    private BigDecimal ratioTypeDetailVal;
    /*类型值1*/
    private BigDecimal ratioTypeDetailVal1;
    /*类型值2*/
    private BigDecimal ratioTypeDetailVal2;
    /*类型值3*/
    private BigDecimal ratioTypeDetailVal3;
    /*产品编码不到等级*/
    private String pdTypeCode;
    /*产品名称*/
    private String pdName;
    /*发布数量*/
    private BigDecimal publishTotalNum;
    /*规格*/
    private String pakageName;
    /*产品特征*/
    private String featureName;
    /*区域名称*/
    private String lgcsName;
    /*区域名称*/
    private String lgcsCode;
    /*规格编码*/
    private String featureCode;
    /*净重编码*/
    private String weightCode;
    /*登陆ID*/
    private String loginId;
    /*判断是否超出申报周期(Y:未过期 N:过期)*/
    private String canSetFlg;
    /*申报周期显示*/
    private String demandYearMonthShow;
    /*SP171102Bean list*/
    private List<SP171102Bean> sp171102Beans;

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public String getPublishYm() {
        return publishYm;
    }

    public void setPublishYm(String publishYm) {
        this.publishYm = publishYm;
    }

    public String getDemandStartDate() {
        return demandStartDate;
    }

    public void setDemandStartDate(String demandStartDate) {
        this.demandStartDate = demandStartDate;
    }

    public String getDemandEndDate() {
        return demandEndDate;
    }

    public void setDemandEndDate(String demandEndDate) {
        this.demandEndDate = demandEndDate;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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

    public Long getForecastId() {
        return forecastId;
    }

    public void setForecastId(Long forecastId) {
        this.forecastId = forecastId;
    }

    public BigDecimal getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(BigDecimal publishNum) {
        this.publishNum = publishNum;
    }

    public BigDecimal getGradeRatio1() {
        return gradeRatio1;
    }

    public void setGradeRatio1(BigDecimal gradeRatio1) {
        this.gradeRatio1 = gradeRatio1;
    }

    public BigDecimal getGradeRatio2() {
        return gradeRatio2;
    }

    public void setGradeRatio2(BigDecimal gradeRatio2) {
        this.gradeRatio2 = gradeRatio2;
    }

    public BigDecimal getGradeRatio3() {
        return gradeRatio3;
    }

    public void setGradeRatio3(BigDecimal gradeRatio3) {
        this.gradeRatio3 = gradeRatio3;
    }

    public String getRatioType() {
        return ratioType;
    }

    public void setRatioType(String ratioType) {
        this.ratioType = ratioType;
    }

    public String getRatioTypeDetail() {
        return ratioTypeDetail;
    }

    public void setRatioTypeDetail(String ratioTypeDetail) {
        this.ratioTypeDetail = ratioTypeDetail;
    }

    public String getRatioTypeDetailNm() {
        return ratioTypeDetailNm;
    }

    public void setRatioTypeDetailNm(String ratioTypeDetailNm) {
        this.ratioTypeDetailNm = ratioTypeDetailNm;
    }

    public BigDecimal getRatioTypeDetailVal() {
        return ratioTypeDetailVal;
    }

    public void setRatioTypeDetailVal(BigDecimal ratioTypeDetailVal) {
        this.ratioTypeDetailVal = ratioTypeDetailVal;
    }

    public BigDecimal getRatioTypeDetailVal1() {
        return ratioTypeDetailVal1;
    }

    public void setRatioTypeDetailVal1(BigDecimal ratioTypeDetailVal1) {
        this.ratioTypeDetailVal1 = ratioTypeDetailVal1;
    }

    public BigDecimal getRatioTypeDetailVal2() {
        return ratioTypeDetailVal2;
    }

    public void setRatioTypeDetailVal2(BigDecimal ratioTypeDetailVal2) {
        this.ratioTypeDetailVal2 = ratioTypeDetailVal2;
    }

    public BigDecimal getRatioTypeDetailVal3() {
        return ratioTypeDetailVal3;
    }

    public void setRatioTypeDetailVal3(BigDecimal ratioTypeDetailVal3) {
        this.ratioTypeDetailVal3 = ratioTypeDetailVal3;
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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getCanSetFlg() {
        return canSetFlg;
    }

    public void setCanSetFlg(String canSetFlg) {
        this.canSetFlg = canSetFlg;
    }

    public String getDemandYearMonthShow() {
        return demandYearMonthShow;
    }

    public void setDemandYearMonthShow(String demandYearMonthShow) {
        this.demandYearMonthShow = demandYearMonthShow;
    }

    public List<SP171102Bean> getSp171102Beans() {
        return sp171102Beans;
    }

    public void setSp171102Beans(List<SP171102Bean> sp171102Beans) {
        this.sp171102Beans = sp171102Beans;
    }
}