/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_mct_std对应的PdMctStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdMctStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 加工技术标准项目ID */
    private java.lang.String mctStdItemId;
    /** 优良值(预留未用) */
    private java.lang.String mctGoodVal;
    /** 合格值 */
    private java.lang.String mctOkVal;
    /** 不合格值 */
    private java.lang.String mctNgVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdMctStd() {

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
     * <p>加工技术标准项目ID。</p>
     *
     * @return the 加工技术标准项目ID
     */
    public java.lang.String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * <p>加工技术标准项目ID。</p>
     *
     * @param mctStdItemId 加工技术标准项目ID。
     */
    public void setMctStdItemId(java.lang.String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * <p>优良值(预留未用)。</p>
     *
     * @return the 优良值(预留未用)
     */
    public java.lang.String getMctGoodVal() {
        return mctGoodVal;
    }

    /**
     * <p>优良值(预留未用)。</p>
     *
     * @param mctGoodVal 优良值(预留未用)。
     */
    public void setMctGoodVal(java.lang.String mctGoodVal) {
        this.mctGoodVal = mctGoodVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @return the 合格值
     */
    public java.lang.String getMctOkVal() {
        return mctOkVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @param mctOkVal 合格值。
     */
    public void setMctOkVal(java.lang.String mctOkVal) {
        this.mctOkVal = mctOkVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @return the 不合格值
     */
    public java.lang.String getMctNgVal() {
        return mctNgVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @param mctNgVal 不合格值。
     */
    public void setMctNgVal(java.lang.String mctNgVal) {
        this.mctNgVal = mctNgVal;
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
