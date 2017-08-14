package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;

import java.math.BigDecimal;

/**
 * 产品Web或者Web Service请求需要的基础参数
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseProductParam extends BaseParam {
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 类型Code */
    private String classesCode;
    /** 类型名称 */
    private String classesName;
    /** 品牌Code */
    private String breedCode;
    /** 品牌名称 */
    private String breedName;
    /** 特征Code */
    private String featureCode;
    /** 特征名称 */
    private String featureName;
    /** 规格Code */
    private String normsCode;
    /** 规格名称 */
    private String normsName;
    /** 等级Code */
    private String pdGradeCode;
    /** 等级名称 */
    private String pdGradeName;
    /** 产品级别 */
    private String pdLevel;

    private String weight;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Getter method for property <tt>pdName</tt>.
     *
     * @return property value of pdName
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * Setter method for property <tt>pdName</tt>.
     *
     * @param pdName value to be assigned to property pdName
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>classesName</tt>.
     *
     * @return property value of classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * Setter method for property <tt>classesName</tt>.
     *
     * @param classesName value to be assigned to property classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Getter method for property <tt>breedCode</tt>.
     *
     * @return property value of breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * Setter method for property <tt>breedCode</tt>.
     *
     * @param breedCode value to be assigned to property breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Getter method for property <tt>breedName</tt>.
     *
     * @return property value of breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * Setter method for property <tt>breedName</tt>.
     *
     * @param breedName value to be assigned to property breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * Getter method for property <tt>featureCode</tt>.
     *
     * @return property value of featureCode
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * Setter method for property <tt>featureCode</tt>.
     *
     * @param featureCode value to be assigned to property featureCode
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Getter method for property <tt>featureName</tt>.
     *
     * @return property value of featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Setter method for property <tt>featureName</tt>.
     *
     * @param featureName value to be assigned to property featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>normsName</tt>.
     *
     * @return property value of normsName
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * Setter method for property <tt>normsName</tt>.
     *
     * @param normsName value to be assigned to property normsName
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * Getter method for property <tt>pdGradeCode</tt>.
     *
     * @return property value of pdGradeCode
     */
    public String getPdGradeCode() {
        return pdGradeCode;
    }

    /**
     * Setter method for property <tt>pdGradeCode</tt>.
     *
     * @param pdGradeCode value to be assigned to property pdGradeCode
     */
    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    /**
     * Getter method for property <tt>pdGradeName</tt>.
     *
     * @return property value of pdGradeName
     */
    public String getPdGradeName() {
        return pdGradeName;
    }

    /**
     * Setter method for property <tt>pdGradeName</tt>.
     *
     * @param pdGradeName value to be assigned to property pdGradeName
     */
    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    /**
     * Getter method for property <tt>pdLevel</tt>.
     *
     * @return property value of pdLevel
     */
    public String getPdLevel() {
        return pdLevel;
    }

    /**
     * Setter method for property <tt>pdLevel</tt>.
     *
     * @param pdLevel value to be assigned to property pdLevel
     */
    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }
}
