/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tsp_std对应的PdTspStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTspStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 储存运输标准项目ID */
    private java.lang.String tspStdItemId;
    /** 优良值(预留) */
    private java.lang.String tspGoodVal;
    /** 合格值 */
    private java.lang.String tspOkVal;
    /** 不合格值 */
    private java.lang.String tspNgVal;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTspStd() {

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
     * <p>储存运输标准项目ID。</p>
     *
     * @return the 储存运输标准项目ID
     */
    public java.lang.String getTspStdItemId() {
        return tspStdItemId;
    }

    /**
     * <p>储存运输标准项目ID。</p>
     *
     * @param tspStdItemId 储存运输标准项目ID。
     */
    public void setTspStdItemId(java.lang.String tspStdItemId) {
        this.tspStdItemId = tspStdItemId;
    }

    /**
     * <p>优良值(预留)。</p>
     *
     * @return the 优良值(预留)
     */
    public java.lang.String getTspGoodVal() {
        return tspGoodVal;
    }

    /**
     * <p>优良值(预留)。</p>
     *
     * @param tspGoodVal 优良值(预留)。
     */
    public void setTspGoodVal(java.lang.String tspGoodVal) {
        this.tspGoodVal = tspGoodVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @return the 合格值
     */
    public java.lang.String getTspOkVal() {
        return tspOkVal;
    }

    /**
     * <p>合格值。</p>
     *
     * @param tspOkVal 合格值。
     */
    public void setTspOkVal(java.lang.String tspOkVal) {
        this.tspOkVal = tspOkVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @return the 不合格值
     */
    public java.lang.String getTspNgVal() {
        return tspNgVal;
    }

    /**
     * <p>不合格值。</p>
     *
     * @param tspNgVal 不合格值。
     */
    public void setTspNgVal(java.lang.String tspNgVal) {
        this.tspNgVal = tspNgVal;
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
