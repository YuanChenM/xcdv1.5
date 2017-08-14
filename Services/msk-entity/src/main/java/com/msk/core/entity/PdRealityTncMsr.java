/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_reality_tnc_msr对应的PdRealityTncMsr。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdRealityTncMsr extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 实际测量ID */
    private java.lang.Integer rltMsrId;
    /** 技术标准项目ID */
    private java.lang.String tncStdItemId;
    /** 实际测量值 */
    private java.lang.String tncReaVal;
    /** 状态 */
    private java.lang.String status;
    /** 产品等级编码 */
    private java.lang.String gradeCode;
    /**
     * <p>默认构造函数。</p>
     */
    public PdRealityTncMsr() {

    }

    /**
     * <p>实际测量ID。</p>
     *
     * @return the 实际测量ID
     */
    public java.lang.Integer getRltMsrId() {
        return rltMsrId;
    }

    /**
     * <p>实际测量ID。</p>
     *
     * @param rltMsrId 实际测量ID。
     */
    public void setRltMsrId(java.lang.Integer rltMsrId) {
        this.rltMsrId = rltMsrId;
    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @return the 技术标准项目ID
     */
    public java.lang.String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @param tncStdItemId 技术标准项目ID。
     */
    public void setTncStdItemId(java.lang.String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * <p>实际测量值。</p>
     *
     * @return the 实际测量值
     */
    public java.lang.String getTncReaVal() {
        return tncReaVal;
    }

    /**
     * <p>实际测量值。</p>
     *
     * @param tncReaVal 实际测量值。
     */
    public void setTncReaVal(java.lang.String tncReaVal) {
        this.tncReaVal = tncReaVal;
    }

    /**
     * <p>状态。</p>
     *
     * @return the 状态
     */
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * <p>状态。</p>
     *
     * @param status 状态。
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param gradeCode 产品等级编码。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
    }

}
