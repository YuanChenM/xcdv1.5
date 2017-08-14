/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_breed对应的PdBreed。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdBreed extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 类别编码 */
    private java.lang.String classesCode;
    /** 产品二级分类编码 */
    private java.lang.String machiningCode;
    /** 产品分类CODE */
    private java.lang.String classestreeCode;
    /** 国籍编码 */
    private java.lang.String countryCode;
    /** 品种编码 */
    private java.lang.String breedCode;
    /** 品种名称 */
    private java.lang.String breedName;
    /**
     * <p>默认构造函数。</p>
     */
    public PdBreed() {

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
     * <p>产品二级分类编码。</p>
     *
     * @return the 产品二级分类编码
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品二级分类编码。</p>
     *
     * @param machiningCode 产品二级分类编码。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品分类CODE。</p>
     *
     * @return the 产品分类CODE
     */
    public java.lang.String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * <p>产品分类CODE。</p>
     *
     * @param classestreeCode 产品分类CODE。
     */
    public void setClassestreeCode(java.lang.String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

    /**
     * <p>国籍编码。</p>
     *
     * @return the 国籍编码
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }

    /**
     * <p>国籍编码。</p>
     *
     * @param countryCode 国籍编码。
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
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
     * <p>品种名称。</p>
     *
     * @return the 品种名称
     */
    public java.lang.String getBreedName() {
        return breedName;
    }

    /**
     * <p>品种名称。</p>
     *
     * @param breedName 品种名称。
     */
    public void setBreedName(java.lang.String breedName) {
        this.breedName = breedName;
    }

}
