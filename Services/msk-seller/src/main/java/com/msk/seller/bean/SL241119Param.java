package com.msk.seller.bean;

import com.msk.core.entity.SlPdPkg;

/**
 * SL241117Param
 *
 * @author gyh
 * @version 1.0
 **/
public class SL241119Param extends SlPdPkg{
    private String classesCode;
    private String breedCode;
    private String featureCode;
    private String[] normsArray;
    //二级分类
    private String machiningCode;
    //包装净重
    private String weightCode;


    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Getter method for property <tt>weightCode</tt>.
     *
     * @return property value of weightCode
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * Setter method for property <tt>weightCode</tt>.
     *
     * @param weightCode value to be assigned to property weightCode
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * 获得normsArray
     */
    public String[] getNormsArray() {
        return normsArray;
    }

    /**
     * 设置normsArray
     */
    public void setNormsArray(String[] normsArray) {
        this.normsArray = normsArray;
    }

    /**

     * 获得classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * 设置classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * 获得breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * 设置breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * 获得featureCode
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * 设置featureCode
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }
}
