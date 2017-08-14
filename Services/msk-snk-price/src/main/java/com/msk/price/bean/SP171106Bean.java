package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sun_jiaju on 2016/5/9.
 */
public class SP171106Bean extends BaseEntity {
    // 是否确认
    private int isConfirm;
    // 是否同意档案卡
    private int isAgree;
    // 申报数量
    private BigDecimal applyNum;
    // 分配数量
    private BigDecimal passNum;
    // 等级编码
    private String gradeCode;

    // 类型明细
    private String ratioTypeDetail;
    // 类型值
    private BigDecimal ratioTypeDetailVal;
    // 等级占比1
    private BigDecimal gradeRatio1;
    // 等级占比2
    private BigDecimal gradeRatio2;
    // 等级占比3
    private BigDecimal gradeRatio3;
    // 发布总数量
    private BigDecimal publishTotalNum;
    // 投标ID
    private long demandId;
    // 物流区CODE
    private String lgcsCode;
    // 物流区NAME
    private String lgcsName;
    // 投标年月周期
    private String demandYearMonth;
    // 投标年月周期开始日
    private Date demandStartDate;
    // 投标年月周期结束日
    private Date demandEndDate;
    // 产品编码
    private String pdCode;
    // 产品9位编码
    private String pdTypeCode;
    // 产品名称
    private String pdName;
    // 产品一级分类名称
    private String classesName;
    // 产品二级分类名称
    private String machiningName;
    // 品种
    private String breedName;
    // 特征
    private String featureName;
    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public BigDecimal getPassNum() {
        return passNum;
    }

    public void setPassNum(BigDecimal passNum) {
        this.passNum = passNum;
    }

    public String getRatioTypeDetail() {
        return ratioTypeDetail;
    }

    public void setRatioTypeDetail(String ratioTypeDetail) {
        this.ratioTypeDetail = ratioTypeDetail;
    }

    public BigDecimal getRatioTypeDetailVal() {
        return ratioTypeDetailVal;
    }

    public void setRatioTypeDetailVal(BigDecimal ratioTypeDetailVal) {
        this.ratioTypeDetailVal = ratioTypeDetailVal;
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

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public int getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    public long getDemandId() {
        return demandId;
    }

    public void setDemandId(long demandId) {
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

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
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
}
