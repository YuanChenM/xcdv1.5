package com.msk.product.bean;

import com.msk.core.entity.PdLogiarea;

/**
 * PD141121Bean.
 * @author yuan_chen
 */
public class PD141121Bean extends PdLogiarea {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 物流区名称 */
    private String lgcsAreaName;

    private String lgcsAreaCode;

    /** 类别名称 */
    private String classesName;

    private String machiningCode;

    private String machiningName;
    /** 品种名称 */
    private String breedName;
    /** 特征名称 */
    private String featureName;

    private String weightCode;

    private String weightName;

    private String gradeName;
    /** checkFlag */
    private int checkFlag;

    /**
     * Getter method for property <tt>lgcsName</tt>.
     *
     * @return property value of lgcsName
     */
    /*public String getLgcsName() {
        return lgcsName;
    }

    *//**
     * Setter method for property <tt>lgcsName</tt>.
     *
     * @param lgcsName value to be assigned to property lgcsName
     *//*
    public void setLgcsName(String lgcsName) {
        this.lgcsName = lgcsName;
    }*/

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
     * Getter method for property <tt>checkFlag</tt>.
     *
     * @return property value of checkFlag
     */
    public int getCheckFlag() {
        return checkFlag;
    }

    /**
     * Setter method for property <tt>checkFlag</tt>.
     *
     * @param checkFlag value to be assigned to property checkFlag
     */
    public void setCheckFlag(int checkFlag) {
        this.checkFlag = checkFlag;
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

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
