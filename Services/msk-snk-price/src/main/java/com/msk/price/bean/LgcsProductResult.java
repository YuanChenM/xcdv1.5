package com.msk.price.bean;


import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * 物流区产品同步履历
 *
 */
public class LgcsProductResult extends BaseEntity {
    /**
     * 产品ID
     */
    private Long pdId;
    /**
     * 同步时间
     */
    private Date pdTime;
    /**
     * 物流区CODE
     */
    private String lgcsCode;
    /**
     * 物流区名
     */
    private String lgcsName;
    /**
     *产品编码
     */
    private String pdCode;
    /**
     *学名
     */
    private String scientificName;
    /**
     *俗名
     */
    private String localName;
    /**
     *销售名
     */
    private String salesName;
    /**
     *产品名称
     */
    private String pdName;
    /**
     *产品一级分类编码
     */
    private String classesCode;
    /**
     *产品一级分类名称
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
    public Long getPdId() {
        return pdId;
    }

    public void setPdId(Long pdId) {
        this.pdId = pdId;
    }

    public Date getPdTime() {
        return pdTime;
    }

    public void setPdTime(Date pdTime) {
        this.pdTime = pdTime;
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
}
