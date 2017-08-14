/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_mct_std_discuss_market对应的PdMctStdDiscussMarket。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdMctStdDiscussMarket extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private java.lang.Long keyId;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 技术标准项目ID */
    private java.lang.String mctStdItemId;
    /** 论证值 */
    private java.lang.String mctStdVal;
    /** 技术标准项目值1(保留) */
    private java.lang.String mctStdVal1;
    /** 技术标准项目值2(保留) */
    private java.lang.String mctStdVal2;
    /** 技术标准项目值3(保留) */
    private java.lang.String mctStdVal3;
    /** 提出日 */
    private java.util.Date raiseDate;
    /** 结案日 */
    private java.util.Date fixDate;
    /** 论证状态 */
    private java.lang.Integer discussStatus;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdMctStdDiscussMarket() {

    }

    /**
     * <p>主键ID。</p>
     *
     * @return the 主键ID
     */
    public java.lang.Long getKeyId() {
        return keyId;
    }

    /**
     * <p>主键ID。</p>
     *
     * @param keyId 主键ID。
     */
    public void setKeyId(java.lang.Long keyId) {
        this.keyId = keyId;
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
     * <p>技术标准项目ID。</p>
     *
     * @return the 技术标准项目ID
     */
    public java.lang.String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * <p>技术标准项目ID。</p>
     *
     * @param mctStdItemId 技术标准项目ID。
     */
    public void setMctStdItemId(java.lang.String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * <p>论证值。</p>
     *
     * @return the 论证值
     */
    public java.lang.String getMctStdVal() {
        return mctStdVal;
    }

    /**
     * <p>论证值。</p>
     *
     * @param mctStdVal 论证值。
     */
    public void setMctStdVal(java.lang.String mctStdVal) {
        this.mctStdVal = mctStdVal;
    }

    /**
     * <p>技术标准项目值1(保留)。</p>
     *
     * @return the 技术标准项目值1(保留)
     */
    public java.lang.String getMctStdVal1() {
        return mctStdVal1;
    }

    /**
     * <p>技术标准项目值1(保留)。</p>
     *
     * @param mctStdVal1 技术标准项目值1(保留)。
     */
    public void setMctStdVal1(java.lang.String mctStdVal1) {
        this.mctStdVal1 = mctStdVal1;
    }

    /**
     * <p>技术标准项目值2(保留)。</p>
     *
     * @return the 技术标准项目值2(保留)
     */
    public java.lang.String getMctStdVal2() {
        return mctStdVal2;
    }

    /**
     * <p>技术标准项目值2(保留)。</p>
     *
     * @param mctStdVal2 技术标准项目值2(保留)。
     */
    public void setMctStdVal2(java.lang.String mctStdVal2) {
        this.mctStdVal2 = mctStdVal2;
    }

    /**
     * <p>技术标准项目值3(保留)。</p>
     *
     * @return the 技术标准项目值3(保留)
     */
    public java.lang.String getMctStdVal3() {
        return mctStdVal3;
    }

    /**
     * <p>技术标准项目值3(保留)。</p>
     *
     * @param mctStdVal3 技术标准项目值3(保留)。
     */
    public void setMctStdVal3(java.lang.String mctStdVal3) {
        this.mctStdVal3 = mctStdVal3;
    }

    /**
     * <p>提出日。</p>
     *
     * @return the 提出日
     */
    public java.util.Date getRaiseDate() {
        return raiseDate;
    }

    /**
     * <p>提出日。</p>
     *
     * @param raiseDate 提出日。
     */
    public void setRaiseDate(java.util.Date raiseDate) {
        this.raiseDate = raiseDate;
    }

    /**
     * <p>结案日。</p>
     *
     * @return the 结案日
     */
    public java.util.Date getFixDate() {
        return fixDate;
    }

    /**
     * <p>结案日。</p>
     *
     * @param fixDate 结案日。
     */
    public void setFixDate(java.util.Date fixDate) {
        this.fixDate = fixDate;
    }

    /**
     * <p>论证状态。</p>
     *
     * @return the 论证状态
     */
    public java.lang.Integer getDiscussStatus() {
        return discussStatus;
    }

    /**
     * <p>论证状态。</p>
     *
     * @param discussStatus 论证状态。
     */
    public void setDiscussStatus(java.lang.Integer discussStatus) {
        this.discussStatus = discussStatus;
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
