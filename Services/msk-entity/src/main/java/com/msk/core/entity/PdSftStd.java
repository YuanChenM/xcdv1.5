/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_sft_std对应的PdSftStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdSftStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 产品安全标准项目ID */
    private java.lang.String sftStdItemId;
    /** 优良值(预留) */
    private java.lang.String sftGoodVal;
    /** 合格值 */
    private java.lang.String sftOkVal;
    /** 不合格值 */
    private java.lang.String sftNgVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdSftStd() {

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
     * <p>产品安全标准项目ID。</p>
     *
     * @return the 产品安全标准项目ID
     */
    public java.lang.String getSftStdItemId() {
        return sftStdItemId;
    }

    /**
     * <p>产品安全标准项目ID。</p>
     *
     * @param sftStdItemId 产品安全标准项目ID。
     */
    public void setSftStdItemId(java.lang.String sftStdItemId) {
        this.sftStdItemId = sftStdItemId;
    }

    /**
     * <p>优良值(预留)。</p>
     *
     * @return the 优良值(预留)
     */
    public java.lang.String getSftGoodVal() {
        return sftGoodVal;
    }

    /**
     * <p>优良值(预留)。</p>
     *
     * @param sftGoodVal 优良值(预留)。
     */
    public void setSftGoodVal(java.lang.String sftGoodVal) {
        this.sftGoodVal = sftGoodVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @return the 合格值
     */
    public java.lang.String getSftOkVal() {
        return sftOkVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @param sftOkVal 合格值。
     */
    public void setSftOkVal(java.lang.String sftOkVal) {
        this.sftOkVal = sftOkVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @return the 不合格值
     */
    public java.lang.String getSftNgVal() {
        return sftNgVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @param sftNgVal 不合格值。
     */
    public void setSftNgVal(java.lang.String sftNgVal) {
        this.sftNgVal = sftNgVal;
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
