/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_weight对应的PdWeight。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdWeight extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 类别编码 */
    private java.lang.String classesCode;
    /** 二级分类编码 */
    private java.lang.String machiningCode;
    /** 品种编码 */
    private java.lang.String breedCode;
    /** 特征编码 */
    private java.lang.String featureCode;
    /** 净重编码 */
    private java.lang.String weightCode;
    /** 净重名称 */
    private java.lang.String weightName;
    /** 净重数值 */
    private java.math.BigDecimal weightVal;
    /** 分类目录CODE */
    private java.lang.String classestreeCode;
    /**
     * <p>默认构造函数。</p>
     */
    public PdWeight() {

    }

    /**
     * <p>类别编码。</p>
     *
     * @return the 类别编码
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @param classesCode 类别编码。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @return the 二级分类编码
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>二级分类编码。</p>
     *
     * @param machiningCode 二级分类编码。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>品种编码。</p>
     *
     * @return the 品种编码
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>品种编码。</p>
     *
     * @param breedCode 品种编码。
     */
    public void setBreedCode(java.lang.String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>特征编码。</p>
     *
     * @return the 特征编码
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>特征编码。</p>
     *
     * @param featureCode 特征编码。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @return the 净重编码
     */
    public java.lang.String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>净重编码。</p>
     *
     * @param weightCode 净重编码。
     */
    public void setWeightCode(java.lang.String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>净重名称。</p>
     *
     * @return the 净重名称
     */
    public java.lang.String getWeightName() {
        return weightName;
    }

    /**
     * <p>净重名称。</p>
     *
     * @param weightName 净重名称。
     */
    public void setWeightName(java.lang.String weightName) {
        this.weightName = weightName;
    }

    /**
     * <p>净重数值。</p>
     *
     * @return the 净重数值
     */
    public java.math.BigDecimal getWeightVal() {
        return weightVal;
    }

    /**
     * <p>净重数值。</p>
     *
     * @param weightVal 净重数值。
     */
    public void setWeightVal(java.math.BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * <p>分类目录CODE。</p>
     *
     * @return the 分类目录CODE
     */
    public java.lang.String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * <p>分类目录CODE。</p>
     *
     * @param classestreeCode 分类目录CODE。
     */
    public void setClassestreeCode(java.lang.String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

}
