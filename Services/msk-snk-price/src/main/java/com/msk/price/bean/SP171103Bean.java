package com.msk.price.bean;

import com.msk.common.base.BaseBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by XU_WEI
 */
public class SP171103Bean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    //序号
    private  Integer idCode;
    //发布ID
    private String publishId;
    //投标ID
    private String demandId;
    //物流区CODE
    private String lgcsCode;
    //物流区名
    private String lgcsName;
    //产品一级分类编码
    private String classesCode;
    //产品二级分类编码
    private String machiningCode;
    //品种编码
    private String breedCode;
    //特征编码
    private String featureCode;
    //净重编码
    private String weightCode;
    //产品一级分类名称
    private String classesName;
    //产品二级分类名称
    private String machiningName;
    //品种
    private String breedName;
    //特征
    private String featureName;
    //净重
    private String weightName;
    //发布数量
    private BigDecimal publishNum;
    //卖家编码
    private String pdCode;
    //卖家编码
    private String pdName;
    //卖家编码
    private String slCode;
    //卖家名称
    private String slName;
    //申报数量
    private String applyNum;
    //投标状态
    private Integer isConfirm;
    //投标状态
    private String demandApply;
    //等级编码
    private String gradeCode;
    //等级名称
    private String gradeName;

    public String getGradeNameA() {
        return gradeNameA;
    }

    public void setGradeNameA(String gradeNameA) {
        this.gradeNameA = gradeNameA;
    }

    public String getGradeCodeA() {
        return gradeCodeA;
    }

    public void setGradeCodeA(String gradeCodeA) {
        this.gradeCodeA = gradeCodeA;
    }

    //等级编码
    private String gradeCodeA;
    //等级名称
    private String gradeNameA;


    //学名
    private String scientificName;
    //俗名
    private String localName;
    //申报周期
    private Integer demandCycle;
    //发布年月周期
    private String demandYearMonth;
    //标的发布年月周期开始日
    private Date demandStartDate;
    //标的发布年月周期结束日
    private Date demandEndDate;
    //申报周期
    private String demandYearMonthShow;
    //填报时间
    private String demandLimitedDateShow;

    //发布年月周期
    private List<SP171103Bean> demandYearMonthlist;

    public List<SP171103Bean> getDemandYearMonthlist() {
        return demandYearMonthlist;
    }

    public void setDemandYearMonthlist(List<SP171103Bean> demandYearMonthlist) {
        this.demandYearMonthlist = demandYearMonthlist;
    }

    public String getDemandLimitedDateShow() {
        return demandLimitedDateShow;
    }

    public void setDemandLimitedDateShow(String demandLimitedDateShow) {
        this.demandLimitedDateShow = demandLimitedDateShow;
    }

    public Integer getDemandCycle() {
        return demandCycle;
    }

    public void setDemandCycle(Integer demandCycle) {
        this.demandCycle = demandCycle;
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

    public String getDemandYearMonthShow() {
        return demandYearMonthShow;
    }

    public void setDemandYearMonthShow(String demandYearMonthShow) {
        this.demandYearMonthShow = demandYearMonthShow;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
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

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer idCode) {
        this.idCode = idCode;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
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

    public BigDecimal getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(BigDecimal publishNum) {
        this.publishNum = publishNum;
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

    public String getDemandApply() {
        return demandApply;
    }

    public void setDemandApply(String demandApply) {
        this.demandApply = demandApply;
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

    public Integer getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Integer isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }
}
