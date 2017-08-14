/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_house_training对应的SlHouseTraining。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlHouseTraining extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 培训ID */
    private Long trainId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 培训开始时间 */
    private java.util.Date trainStart;
    /** 培训结束时间 */
    private java.util.Date trainEnd;
    /** 培训机构 */
    private String trainComp;
    /** 所获证书 */
    private String trainCertificate;
    /**
     * <p>默认构造函数。</p>
     */
    public SlHouseTraining() {

    }

    /**
     * <p>培训ID。</p>
     *
     * @return the 培训ID
     */
    public Long getTrainId() {
        return trainId;
    }

    /**
     * <p>培训ID。</p>
     *
     * @param trainId 培训ID。
     */
    public void setTrainId(Long trainId) {
        this.trainId = trainId;
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
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>培训开始时间。</p>
     *
     * @return the 培训开始时间
     */
    public java.util.Date getTrainStart() {
        return trainStart;
    }

    /**
     * <p>培训开始时间。</p>
     *
     * @param trainStart 培训开始时间。
     */
    public void setTrainStart(java.util.Date trainStart) {
        this.trainStart = trainStart;
    }

    /**
     * <p>培训结束时间。</p>
     *
     * @return the 培训结束时间
     */
    public java.util.Date getTrainEnd() {
        return trainEnd;
    }

    /**
     * <p>培训结束时间。</p>
     *
     * @param trainEnd 培训结束时间。
     */
    public void setTrainEnd(java.util.Date trainEnd) {
        this.trainEnd = trainEnd;
    }

    /**
     * <p>培训机构。</p>
     *
     * @return the 培训机构
     */
    public String getTrainComp() {
        return trainComp;
    }

    /**
     * <p>培训机构。</p>
     *
     * @param trainComp 培训机构。
     */
    public void setTrainComp(String trainComp) {
        this.trainComp = trainComp;
    }

    /**
     * <p>所获证书。</p>
     *
     * @return the 所获证书
     */
    public String getTrainCertificate() {
        return trainCertificate;
    }

    /**
     * <p>所获证书。</p>
     *
     * @param trainCertificate 所获证书。
     */
    public void setTrainCertificate(String trainCertificate) {
        this.trainCertificate = trainCertificate;
    }

}
