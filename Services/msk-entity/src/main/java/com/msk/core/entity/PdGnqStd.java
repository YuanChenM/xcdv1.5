/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_gnq_std对应的PdGnqStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdGnqStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 通用质量标准项目ID */
    private java.lang.String gnqStdItemId;
    /** 优良值(预留) */
    private java.lang.String gnqGoodVal;
    /** 合格值 */
    private java.lang.String gnqOkVal;
    /** 不合格值 */
    private java.lang.String gnqNgVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdGnqStd() {

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
     * <p>通用质量标准项目ID。</p>
     *
     * @return the 通用质量标准项目ID
     */
    public java.lang.String getGnqStdItemId() {
        return gnqStdItemId;
    }

    /**
     * <p>通用质量标准项目ID。</p>
     *
     * @param gnqStdItemId 通用质量标准项目ID。
     */
    public void setGnqStdItemId(java.lang.String gnqStdItemId) {
        this.gnqStdItemId = gnqStdItemId;
    }

    /**
     * <p>优良值(预留)。</p>
     *
     * @return the 优良值(预留)
     */
    public java.lang.String getGnqGoodVal() {
        return gnqGoodVal;
    }

    /**
     * <p>优良值(预留)。</p>
     *
     * @param gnqGoodVal 优良值(预留)。
     */
    public void setGnqGoodVal(java.lang.String gnqGoodVal) {
        this.gnqGoodVal = gnqGoodVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @return the 合格值
     */
    public java.lang.String getGnqOkVal() {
        return gnqOkVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @param gnqOkVal 合格值。
     */
    public void setGnqOkVal(java.lang.String gnqOkVal) {
        this.gnqOkVal = gnqOkVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @return the 不合格值
     */
    public java.lang.String getGnqNgVal() {
        return gnqNgVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @param gnqNgVal 不合格值。
     */
    public void setGnqNgVal(java.lang.String gnqNgVal) {
        this.gnqNgVal = gnqNgVal;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

}
