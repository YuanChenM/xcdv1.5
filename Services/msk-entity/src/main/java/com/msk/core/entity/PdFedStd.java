/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_fed_std对应的PdFedStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdFedStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 饲养标准项目ID */
    private java.lang.String fedStdItemId;
    /** 优良值 */
    private java.lang.String fedGoodVal;
    /** 一般值 */
    private java.lang.String fedNormalVal;
    /** 差值 */
    private java.lang.String fedBadVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdFedStd() {

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
     * <p>饲养标准项目ID。</p>
     *
     * @return the 饲养标准项目ID
     */
    public java.lang.String getFedStdItemId() {
        return fedStdItemId;
    }

    /**
     * <p>饲养标准项目ID。</p>
     *
     * @param fedStdItemId 饲养标准项目ID。
     */
    public void setFedStdItemId(java.lang.String fedStdItemId) {
        this.fedStdItemId = fedStdItemId;
    }

    /**
     * <p>优良值。</p>
     *
     * @return the 优良值
     */
    public java.lang.String getFedGoodVal() {
        return fedGoodVal;
    }

    /**
     * <p>优良值。</p>
     *
     * @param fedGoodVal 优良值。
     */
    public void setFedGoodVal(java.lang.String fedGoodVal) {
        this.fedGoodVal = fedGoodVal;
    }

    /**
     * <p>一般值。</p>
     *
     * @return the 一般值
     */
    public java.lang.String getFedNormalVal() {
        return fedNormalVal;
    }

    /**
     * <p>一般值。</p>
     *
     * @param fedNormalVal 一般值。
     */
    public void setFedNormalVal(java.lang.String fedNormalVal) {
        this.fedNormalVal = fedNormalVal;
    }

    /**
     * <p>差值。</p>
     *
     * @return the 差值
     */
    public java.lang.String getFedBadVal() {
        return fedBadVal;
    }

    /**
     * <p>差值。</p>
     *
     * @param fedBadVal 差值。
     */
    public void setFedBadVal(java.lang.String fedBadVal) {
        this.fedBadVal = fedBadVal;
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
