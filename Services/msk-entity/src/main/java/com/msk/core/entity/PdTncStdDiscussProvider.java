/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tnc_std_discuss_provider对应的PdTncStdDiscussProvider。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTncStdDiscussProvider extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private java.lang.Long keyId;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 卖家产品ID */
    private java.lang.Integer slPdId;
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
    /** 提出日 */
    private java.util.Date raiseDate;
    /** 结案日 */
    private java.util.Date fixDate;
    /** 供应商编码 */
    private java.lang.String providerCode;
    /** 供应商名称 */
    private java.lang.String providerName;
    /** 0：提出，1：结案，2：禁止转入 */
    private java.lang.Integer discussStatus;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTncStdDiscussProvider() {

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
     * <p>卖家产品ID。</p>
     *
     * @return the 卖家产品ID
     */
    public java.lang.Integer getSlPdId() {
        return slPdId;
    }

    /**
     * <p>卖家产品ID。</p>
     *
     * @param slPdId 卖家产品ID。
     */
    public void setSlPdId(java.lang.Integer slPdId) {
        this.slPdId = slPdId;
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
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public java.lang.String getProviderCode() {
        return providerCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param providerCode 供应商编码。
     */
    public void setProviderCode(java.lang.String providerCode) {
        this.providerCode = providerCode;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @return the 供应商名称
     */
    public java.lang.String getProviderName() {
        return providerName;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @param providerName 供应商名称。
     */
    public void setProviderName(java.lang.String providerName) {
        this.providerName = providerName;
    }

    /**
     * <p>0：提出，1：结案，2：禁止转入。</p>
     *
     * @return the 0：提出，1：结案，2：禁止转入
     */
    public java.lang.Integer getDiscussStatus() {
        return discussStatus;
    }

    /**
     * <p>0：提出，1：结案，2：禁止转入。</p>
     *
     * @param discussStatus 0：提出，1：结案，2：禁止转入。
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
