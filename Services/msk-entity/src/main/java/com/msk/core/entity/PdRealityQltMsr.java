/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_reality_qlt_msr对应的PdRealityQltMsr。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdRealityQltMsr extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 实际测量ID */
    private java.lang.Integer rltMsrId;
    /** 质量标准项目ID */
    private java.lang.String qltStdItemId;
    /** 实际测量值 */
    private java.lang.String qltReaVal;
    /** 状态 */
    private java.lang.String status;
    /** 1：优 2：合格 3：不合格 */
    private java.lang.String judgeLevel;
    /**
     * <p>默认构造函数。</p>
     */
    public PdRealityQltMsr() {

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
     * <p>质量标准项目ID。</p>
     *
     * @return the 质量标准项目ID
     */
    public java.lang.String getQltStdItemId() {
        return qltStdItemId;
    }

    /**
     * <p>质量标准项目ID。</p>
     *
     * @param qltStdItemId 质量标准项目ID。
     */
    public void setQltStdItemId(java.lang.String qltStdItemId) {
        this.qltStdItemId = qltStdItemId;
    }

    /**
     * <p>实际测量值。</p>
     *
     * @return the 实际测量值
     */
    public java.lang.String getQltReaVal() {
        return qltReaVal;
    }

    /**
     * <p>实际测量值。</p>
     *
     * @param qltReaVal 实际测量值。
     */
    public void setQltReaVal(java.lang.String qltReaVal) {
        this.qltReaVal = qltReaVal;
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
     * <p>1：优 2：合格 3：不合格。</p>
     *
     * @return the 1：优 2：合格 3：不合格
     */
    public java.lang.String getJudgeLevel() {
        return judgeLevel;
    }

    /**
     * <p>1：优 2：合格 3：不合格。</p>
     *
     * @param judgeLevel 1：优 2：合格 3：不合格。
     */
    public void setJudgeLevel(java.lang.String judgeLevel) {
        this.judgeLevel = judgeLevel;
    }

}
