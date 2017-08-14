/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_buyer对应的SlBsBuyer。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsBuyer extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private java.lang.String slCode;
    /** 用于登录的卖家账号 */
    private java.lang.String houseCode;
    /** BUYER_ID */
    private java.lang.String buyerId;
    /** START_TIME */
    private java.util.Date startTime;
    /** END_TIME */
    private java.util.Date endTime;
    /** A：冻品管家发展专属会员买家 B：买家选择专属冻品管家 */
    private java.lang.String applySide;
    /** 1：申请中 2：专属会员 */
    private java.lang.String applyStatus;
    /** APPLY_TIME */
    private java.util.Date applyTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsBuyer() {

    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public java.lang.String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)。</p>
     *
     * @param slCode 区划(6)+顺序码(4)。
     */
    public void setSlCode(java.lang.String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @return the 用于登录的卖家账号
     */
    public java.lang.String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseCode(java.lang.String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>BUYER_ID。</p>
     *
     * @return the BUYER_ID
     */
    public java.lang.String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID。</p>
     *
     * @param buyerId BUYER_ID。
     */
    public void setBuyerId(java.lang.String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>START_TIME。</p>
     *
     * @return the START_TIME
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * <p>START_TIME。</p>
     *
     * @param startTime START_TIME。
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * <p>END_TIME。</p>
     *
     * @return the END_TIME
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * <p>END_TIME。</p>
     *
     * @param endTime END_TIME。
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * <p>A：冻品管家发展专属会员买家 B：买家选择专属冻品管家。</p>
     *
     * @return the A：冻品管家发展专属会员买家 B：买家选择专属冻品管家
     */
    public java.lang.String getApplySide() {
        return applySide;
    }

    /**
     * <p>A：冻品管家发展专属会员买家 B：买家选择专属冻品管家。</p>
     *
     * @param applySide A：冻品管家发展专属会员买家 B：买家选择专属冻品管家。
     */
    public void setApplySide(java.lang.String applySide) {
        this.applySide = applySide;
    }

    /**
     * <p>1：申请中 2：专属会员。</p>
     *
     * @return the 1：申请中 2：专属会员
     */
    public java.lang.String getApplyStatus() {
        return applyStatus;
    }

    /**
     * <p>1：申请中 2：专属会员。</p>
     *
     * @param applyStatus 1：申请中 2：专属会员。
     */
    public void setApplyStatus(java.lang.String applyStatus) {
        this.applyStatus = applyStatus;
    }

    /**
     * <p>APPLY_TIME。</p>
     *
     * @return the APPLY_TIME
     */
    public java.util.Date getApplyTime() {
        return applyTime;
    }

    /**
     * <p>APPLY_TIME。</p>
     *
     * @param applyTime APPLY_TIME。
     */
    public void setApplyTime(java.util.Date applyTime) {
        this.applyTime = applyTime;
    }

}
