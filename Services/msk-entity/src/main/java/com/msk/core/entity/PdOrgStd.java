/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_org_std对应的PdOrgStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdOrgStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 种源标准项目ID */
    private java.lang.String orgStdItemId;
    /** 产品分类ID */
    private java.lang.Long classestreeId;
    /** 优良值 */
    private java.lang.String orgGoodVal;
    /** 一般值 */
    private java.lang.String orgNormalVal;
    /** 差值 */
    private java.lang.String orgBadVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdOrgStd() {

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
     * <p>种源标准项目ID。</p>
     *
     * @return the 种源标准项目ID
     */
    public java.lang.String getOrgStdItemId() {
        return orgStdItemId;
    }

    /**
     * <p>种源标准项目ID。</p>
     *
     * @param orgStdItemId 种源标准项目ID。
     */
    public void setOrgStdItemId(java.lang.String orgStdItemId) {
        this.orgStdItemId = orgStdItemId;
    }

    /**
     * <p>产品分类ID。</p>
     *
     * @return the 产品分类ID
     */
    public java.lang.Long getClassestreeId() {
        return classestreeId;
    }

    /**
     * <p>产品分类ID。</p>
     *
     * @param classestreeId 产品分类ID。
     */
    public void setClassestreeId(java.lang.Long classestreeId) {
        this.classestreeId = classestreeId;
    }

    /**
     * <p>优良值。</p>
     *
     * @return the 优良值
     */
    public java.lang.String getOrgGoodVal() {
        return orgGoodVal;
    }

    /**
     * <p>优良值。</p>
     *
     * @param orgGoodVal 优良值。
     */
    public void setOrgGoodVal(java.lang.String orgGoodVal) {
        this.orgGoodVal = orgGoodVal;
    }

    /**
     * <p>一般值。</p>
     *
     * @return the 一般值
     */
    public java.lang.String getOrgNormalVal() {
        return orgNormalVal;
    }

    /**
     * <p>一般值。</p>
     *
     * @param orgNormalVal 一般值。
     */
    public void setOrgNormalVal(java.lang.String orgNormalVal) {
        this.orgNormalVal = orgNormalVal;
    }

    /**
     * <p>差值。</p>
     *
     * @return the 差值
     */
    public java.lang.String getOrgBadVal() {
        return orgBadVal;
    }

    /**
     * <p>差值。</p>
     *
     * @param orgBadVal 差值。
     */
    public void setOrgBadVal(java.lang.String orgBadVal) {
        this.orgBadVal = orgBadVal;
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
