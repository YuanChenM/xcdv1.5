/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_standard对应的PdStandard。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdStandard extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
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
    /** 质量等级编码 */
    private java.lang.String gradeCode;
    /** 产品分类CODE */
    private java.lang.String classestreeCode;
    /** 0:无,1:有 */
    private java.lang.String qltFlg;
    /** 0:无,1:有 */
    private java.lang.String tncFlg;
    /** 0:无,1:有 */
    private java.lang.String norFlg;
    /** 0:无,1:有 */
    private java.lang.String orgFlg;
    /** 0:无,1:有 */
    private java.lang.String fedFlg;
    /** 0:无,1:有 */
    private java.lang.String mcqFlg;
    /** 0:无,1:有 */
    private java.lang.String gnqFlg;
    /** 0:无,1:有 */
    private java.lang.String tspFlg;
    /**
     * <p>默认构造函数。</p>
     */
    public PdStandard() {

    }

    /**
     * <p>产品标准ID。</p>
     *
     * @return the 产品标准ID
     */
    public java.lang.Long getStandardId() {
        return standardId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @param standardId 产品标准ID。
     */
    public void setStandardId(java.lang.Long standardId) {
        this.standardId = standardId;
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
     * <p>质量等级编码。</p>
     *
     * @return the 质量等级编码
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>质量等级编码。</p>
     *
     * @param gradeCode 质量等级编码。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
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
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getQltFlg() {
        return qltFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param qltFlg 0:无,1:有。
     */
    public void setQltFlg(java.lang.String qltFlg) {
        this.qltFlg = qltFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getTncFlg() {
        return tncFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param tncFlg 0:无,1:有。
     */
    public void setTncFlg(java.lang.String tncFlg) {
        this.tncFlg = tncFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getNorFlg() {
        return norFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param norFlg 0:无,1:有。
     */
    public void setNorFlg(java.lang.String norFlg) {
        this.norFlg = norFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getOrgFlg() {
        return orgFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param orgFlg 0:无,1:有。
     */
    public void setOrgFlg(java.lang.String orgFlg) {
        this.orgFlg = orgFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getFedFlg() {
        return fedFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param fedFlg 0:无,1:有。
     */
    public void setFedFlg(java.lang.String fedFlg) {
        this.fedFlg = fedFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getMcqFlg() {
        return mcqFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param mcqFlg 0:无,1:有。
     */
    public void setMcqFlg(java.lang.String mcqFlg) {
        this.mcqFlg = mcqFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getGnqFlg() {
        return gnqFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param gnqFlg 0:无,1:有。
     */
    public void setGnqFlg(java.lang.String gnqFlg) {
        this.gnqFlg = gnqFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @return the 0:无,1:有
     */
    public java.lang.String getTspFlg() {
        return tspFlg;
    }

    /**
     * <p>0:无,1:有。</p>
     *
     * @param tspFlg 0:无,1:有。
     */
    public void setTspFlg(java.lang.String tspFlg) {
        this.tspFlg = tspFlg;
    }

}
