/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_apply_pd_breed对应的SlApplyPdBreed。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlApplyPdBreed extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 类别编码 */
    private String classesCode;
    /** 品种名称 */
    private String cbreedName;
    /** 说明 */
    private String remark;
    /** 申请日时 */
    private java.util.Date applyTime;
    /** 申请状态 */
    private String applyStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SlApplyPdBreed() {

    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @return the 类别编码
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>类别编码。</p>
     *
     * @param classesCode 类别编码。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>品种名称。</p>
     *
     * @return the 品种名称
     */
    public String getCbreedName() {
        return cbreedName;
    }

    /**
     * <p>品种名称。</p>
     *
     * @param cbreedName 品种名称。
     */
    public void setCbreedName(String cbreedName) {
        this.cbreedName = cbreedName;
    }

    /**
     * <p>说明。</p>
     *
     * @return the 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>说明。</p>
     *
     * @param remark 说明。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>申请日时。</p>
     *
     * @return the 申请日时
     */
    public java.util.Date getApplyTime() {
        return applyTime;
    }

    /**
     * <p>申请日时。</p>
     *
     * @param applyTime 申请日时。
     */
    public void setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * <p>申请状态。</p>
     *
     * @return the 申请状态
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * <p>申请状态。</p>
     *
     * @param applyStatus 申请状态。
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

}
