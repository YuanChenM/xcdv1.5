/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_buyer_leagues对应的SlBsBuyerLeagues。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsBuyerLeagues extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** UNION_ID */
    private java.lang.Long unionId;
    /** OWNER_SL_CODE */
    private java.lang.String ownerSlCode;
    /** ALLIANCE_SL_CODE */
    private java.lang.String allianceSlCode;
    /** CLASSES_CODE */
    private java.lang.String classesCode;
    /** MACHINING_CODE */
    private java.lang.String machiningCode;
    /** BREED_CODE */
    private java.lang.String breedCode;
    /** FEATURE_CODE */
    private java.lang.String featureCode;
    /** WEIGHT_CODE */
    private java.lang.String weightCode;
    /** GRADE_CODE */
    private java.lang.String gradeCode;
    /** DIVIDE */
    private java.math.BigDecimal divide;
    /** SORT_NUM */
    private java.lang.Integer sortNum;
    /** 1：申请中  2：同意 3：拒绝 */
    private java.lang.String applyStatus;
    /** APPLY_TIME */
    private java.util.Date applyTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsBuyerLeagues() {

    }

    /**
     * <p>UNION_ID。</p>
     *
     * @return the UNION_ID
     */
    public java.lang.Long getUnionId() {
        return unionId;
    }

    /**
     * <p>UNION_ID。</p>
     *
     * @param unionId UNION_ID。
     */
    public void setUnionId(java.lang.Long unionId) {
        this.unionId = unionId;
    }

    /**
     * <p>OWNER_SL_CODE。</p>
     *
     * @return the OWNER_SL_CODE
     */
    public java.lang.String getOwnerSlCode() {
        return ownerSlCode;
    }

    /**
     * <p>OWNER_SL_CODE。</p>
     *
     * @param ownerSlCode OWNER_SL_CODE。
     */
    public void setOwnerSlCode(java.lang.String ownerSlCode) {
        this.ownerSlCode = ownerSlCode;
    }

    /**
     * <p>ALLIANCE_SL_CODE。</p>
     *
     * @return the ALLIANCE_SL_CODE
     */
    public java.lang.String getAllianceSlCode() {
        return allianceSlCode;
    }

    /**
     * <p>ALLIANCE_SL_CODE。</p>
     *
     * @param allianceSlCode ALLIANCE_SL_CODE。
     */
    public void setAllianceSlCode(java.lang.String allianceSlCode) {
        this.allianceSlCode = allianceSlCode;
    }

    /**
     * <p>CLASSES_CODE。</p>
     *
     * @return the CLASSES_CODE
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>CLASSES_CODE。</p>
     *
     * @param classesCode CLASSES_CODE。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>MACHINING_CODE。</p>
     *
     * @return the MACHINING_CODE
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>MACHINING_CODE。</p>
     *
     * @param machiningCode MACHINING_CODE。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>BREED_CODE。</p>
     *
     * @return the BREED_CODE
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>BREED_CODE。</p>
     *
     * @param breedCode BREED_CODE。
     */
    public void setBreedCode(java.lang.String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>FEATURE_CODE。</p>
     *
     * @return the FEATURE_CODE
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>FEATURE_CODE。</p>
     *
     * @param featureCode FEATURE_CODE。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>WEIGHT_CODE。</p>
     *
     * @return the WEIGHT_CODE
     */
    public java.lang.String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>WEIGHT_CODE。</p>
     *
     * @param weightCode WEIGHT_CODE。
     */
    public void setWeightCode(java.lang.String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>GRADE_CODE。</p>
     *
     * @return the GRADE_CODE
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>GRADE_CODE。</p>
     *
     * @param gradeCode GRADE_CODE。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>DIVIDE。</p>
     *
     * @return the DIVIDE
     */
    public java.math.BigDecimal getDivide() {
        return divide;
    }

    /**
     * <p>DIVIDE。</p>
     *
     * @param divide DIVIDE。
     */
    public void setDivide(java.math.BigDecimal divide) {
        this.divide = divide;
    }

    /**
     * <p>SORT_NUM。</p>
     *
     * @return the SORT_NUM
     */
    public java.lang.Integer getSortNum() {
        return sortNum;
    }

    /**
     * <p>SORT_NUM。</p>
     *
     * @param sortNum SORT_NUM。
     */
    public void setSortNum(java.lang.Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * <p>1：申请中  2：同意 3：拒绝。</p>
     *
     * @return the 1：申请中  2：同意 3：拒绝
     */
    public java.lang.String getApplyStatus() {
        return applyStatus;
    }

    /**
     * <p>1：申请中  2：同意 3：拒绝。</p>
     *
     * @param applyStatus 1：申请中  2：同意 3：拒绝。
     */
    public void setApplyStatus(java.lang.String applyStatus) {
        this.applyStatus = applyStatus;
    }

    /**
     * <p>APPLY_TIME。</p>
     *
     * @return the APPLY_TIME
     */
    public java.util.Date getApplyTime() {
        return applyTime;
    }

    /**
     * <p>APPLY_TIME。</p>
     *
     * @param applyTime APPLY_TIME。
     */
    public void setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
    }

}
