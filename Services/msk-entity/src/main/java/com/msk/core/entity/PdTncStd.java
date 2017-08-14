/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表pd_tnc_std对应的PdTncStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdTncStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 加工质量项目ID */
    private java.lang.String tncStdItemId;
    /** 加工质量A1项目值1 */
    private java.lang.String tncStdVal1;
    /** 加工质量A2项目值2 */
    private java.lang.String tncStdVal2;
    /** 加工质量A3项目值3 */
    private java.lang.String tncStdVal3;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public PdTncStd() {

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
     * <p>加工质量项目ID。</p>
     *
     * @return the 加工质量项目ID
     */
    public java.lang.String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * <p>加工质量项目ID。</p>
     *
     * @param tncStdItemId 加工质量项目ID。
     */
    public void setTncStdItemId(java.lang.String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * <p>加工质量A1项目值1。</p>
     *
     * @return the 加工质量A1项目值1
     */
    public java.lang.String getTncStdVal1() {
        return tncStdVal1;
    }

    /**
     * <p>加工质量A1项目值1。</p>
     *
     * @param tncStdVal1 加工质量A1项目值1。
     */
    public void setTncStdVal1(java.lang.String tncStdVal1) {
        this.tncStdVal1 = tncStdVal1;
    }

    /**
     * <p>加工质量A2项目值2。</p>
     *
     * @return the 加工质量A2项目值2
     */
    public java.lang.String getTncStdVal2() {
        return tncStdVal2;
    }

    /**
     * <p>加工质量A2项目值2。</p>
     *
     * @param tncStdVal2 加工质量A2项目值2。
     */
    public void setTncStdVal2(java.lang.String tncStdVal2) {
        this.tncStdVal2 = tncStdVal2;
    }

    /**
     * <p>加工质量A3项目值3。</p>
     *
     * @return the 加工质量A3项目值3
     */
    public java.lang.String getTncStdVal3() {
        return tncStdVal3;
    }

    /**
     * <p>加工质量A3项目值3。</p>
     *
     * @param tncStdVal3 加工质量A3项目值3。
     */
    public void setTncStdVal3(java.lang.String tncStdVal3) {
        this.tncStdVal3 = tncStdVal3;
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
