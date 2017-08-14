/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tnc_std_forbid_market对应的PdTncStdForbidMarket。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTncStdForbidMarket extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private java.lang.Long keyId;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 技术标准项目ID */
    private java.lang.String tncStdItemId;
    /** 论证值 */
    private java.lang.String tncStdVal;
    /** 技术标准项目值1(保留) */
    private java.lang.String tncStdVal1;
    /** 技术标准项目值2(保留) */
    private java.lang.String tncStdVal2;
    /** 技术标准项目值3(保留) */
    private java.lang.String tncStdVal3;
    /** 禁止日 */
    private java.util.Date forbidDate;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTncStdForbidMarket() {

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
     * <p>论证值。</p>
     *
     * @return the 论证值
     */
    public java.lang.String getTncStdVal() {
        return tncStdVal;
    }

    /**
     * <p>论证值。</p>
     *
     * @param tncStdVal 论证值。
     */
    public void setTncStdVal(java.lang.String tncStdVal) {
        this.tncStdVal = tncStdVal;
    }

    /**
     * <p>技术标准项目值1(保留)。</p>
     *
     * @return the 技术标准项目值1(保留)
     */
    public java.lang.String getTncStdVal1() {
        return tncStdVal1;
    }

    /**
     * <p>技术标准项目值1(保留)。</p>
     *
     * @param tncStdVal1 技术标准项目值1(保留)。
     */
    public void setTncStdVal1(java.lang.String tncStdVal1) {
        this.tncStdVal1 = tncStdVal1;
    }

    /**
     * <p>技术标准项目值2(保留)。</p>
     *
     * @return the 技术标准项目值2(保留)
     */
    public java.lang.String getTncStdVal2() {
        return tncStdVal2;
    }

    /**
     * <p>技术标准项目值2(保留)。</p>
     *
     * @param tncStdVal2 技术标准项目值2(保留)。
     */
    public void setTncStdVal2(java.lang.String tncStdVal2) {
        this.tncStdVal2 = tncStdVal2;
    }

    /**
     * <p>技术标准项目值3(保留)。</p>
     *
     * @return the 技术标准项目值3(保留)
     */
    public java.lang.String getTncStdVal3() {
        return tncStdVal3;
    }

    /**
     * <p>技术标准项目值3(保留)。</p>
     *
     * @param tncStdVal3 技术标准项目值3(保留)。
     */
    public void setTncStdVal3(java.lang.String tncStdVal3) {
        this.tncStdVal3 = tncStdVal3;
    }

    /**
     * <p>禁止日。</p>
     *
     * @return the 禁止日
     */
    public java.util.Date getForbidDate() {
        return forbidDate;
    }

    /**
     * <p>禁止日。</p>
     *
     * @param forbidDate 禁止日。
     */
    public void setForbidDate(java.util.Date forbidDate) {
        this.forbidDate = forbidDate;
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
