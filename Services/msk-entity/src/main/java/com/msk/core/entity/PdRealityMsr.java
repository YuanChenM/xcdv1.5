/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_reality_msr对应的PdRealityMsr。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdRealityMsr extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 实际测量ID */
    private java.lang.Integer rltMsrId;
    /** 产品标准ID */
    private java.lang.Integer standardId;
    /** 产品编号 */
    private java.lang.String pdCode;
    /** 批次编号 */
    private java.lang.String pdBatchCode;
    /**
     * <p>默认构造函数。</p>
     */
    public PdRealityMsr() {

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
     * <p>产品标准ID。</p>
     *
     * @return the 产品标准ID
     */
    public java.lang.Integer getStandardId() {
        return standardId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @param standardId 产品标准ID。
     */
    public void setStandardId(java.lang.Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * <p>产品编号。</p>
     *
     * @return the 产品编号
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编号。</p>
     *
     * @param pdCode 产品编号。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>批次编号。</p>
     *
     * @return the 批次编号
     */
    public java.lang.String getPdBatchCode() {
        return pdBatchCode;
    }

    /**
     * <p>批次编号。</p>
     *
     * @param pdBatchCode 批次编号。
     */
    public void setPdBatchCode(java.lang.String pdBatchCode) {
        this.pdBatchCode = pdBatchCode;
    }

}
