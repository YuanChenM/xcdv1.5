package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 供应商:待申报产品一览
 */
public class SP171149Bean extends BaseEntity {
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

    //产品特征
    private String featureCode;

    //产品特征 Name
    private String featureName;

    //规格
    private String spec;

    //净重
    private String weightCode;

    //净重
    private String weightName;

    //产品编码
    private String pdCode;

    //已有库存量
    private BigDecimal inventoryQty;

    //本期需求量
    private BigDecimal publishTotalNum;

    //供应等级
    private String gradeCode;

    //供应等级
    private String gradeName;

    //对应等级需求量
    private BigDecimal publishNum;

    //申报数量
    private BigDecimal applyNum;

    //中标情况
    private Integer applyStatus;

    //demand ID
    private Long demandId;

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
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

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getInventoryQty() {
        return inventoryQty;
    }

    public void setInventoryQty(BigDecimal inventoryQty) {
        this.inventoryQty = inventoryQty;
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

    public BigDecimal getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(BigDecimal publishNum) {
        this.publishNum = publishNum;
    }

    public BigDecimal getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(BigDecimal applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getLgcsName() {
        return lgcsName;
    }

    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
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

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }
}
