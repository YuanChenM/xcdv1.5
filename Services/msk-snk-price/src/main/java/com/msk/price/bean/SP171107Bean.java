package com.msk.price.bean;

import com.msk.common.base.BaseBean;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xu_wei
 */
public class SP171107Bean extends BaseBean{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    //序号
    private  Integer idCode;
    //投标ID
    private String demandId;
    /** 发布年月周期 */
    private String demandYearmonth;
    /** 标的发布年月周期开始日 */
    private Date demandStartdate;
    /** 标的发布年月周期结束日 */
    private Date demandEnddate;
    /** 物流区CODE */
    private String lgcsCode;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 产品一级分类编码 */
    private String classesCode;
    /** 产品二级分类编码 */
    private String machiningCode;
    /** 品种编码 */
    private String breedCode;
    /** 特征编码 */
    private String featureCode;
    /** 净重编码 */
    private String weightCode;
    /** 等级编码 */
    private String gradeCode;
    /** 原料学名  */
    private String scientificName;
    /** 俗名  */
    private String localName;
    /** 产品一级分类编码 */
    private String classesName;
    /** 产品二级分类编码 */
    private String machiningName;
    /** 品种名称  */
    private String breedName;
    /** 特征名称  */
    private String featureName;
    /** 净重名称  */
    private String weightName;
    /** 等级名称 */
    private String gradeName;
    /** 需求预测量 */
    private BigDecimal forecastNum;
    /** 发布总数量 */
    private BigDecimal publishTotalNum;
    /** 发布数量 */
    private BigDecimal publishNum;
    /** 数量发布状态 */
    private Integer demandStatus;
    /** 卖家dis */
    private String slId;
    /** 卖家编码 */
    private String slCode;
    /** 卖家编码 */
    private String slName;
    /** 申报数量 */
    private String applyNum;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer idCode) {
        this.idCode = idCode;
    }

    public String getDemandYearmonth() {
        return demandYearmonth;
    }

    public void setDemandYearmonth(String demandYearmonth) {
        this.demandYearmonth = demandYearmonth;
    }

    public Date getDemandStartdate() {
        return demandStartdate;
    }

    public void setDemandStartdate(Date demandStartdate) {
        this.demandStartdate = demandStartdate;
    }

    public Date getDemandEnddate() {
        return demandEnddate;
    }

    public void setDemandEnddate(Date demandEnddate) {
        this.demandEnddate = demandEnddate;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
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

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
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

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public BigDecimal getForecastNum() {
        return forecastNum;
    }

    public void setForecastNum(BigDecimal forecastNum) {
        this.forecastNum = forecastNum;
    }

    public BigDecimal getPublishTotalNum() {
        return publishTotalNum;
    }

    public void setPublishTotalNum(BigDecimal publishTotalNum) {
        this.publishTotalNum = publishTotalNum;
    }

    public BigDecimal getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(BigDecimal publishNum) {
        this.publishNum = publishNum;
    }

    public Integer getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(Integer demandStatus) {
        this.demandStatus = demandStatus;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }
}
