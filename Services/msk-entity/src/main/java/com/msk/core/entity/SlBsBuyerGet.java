/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_buyer_get对应的SlBsBuyerGet。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsBuyerGet extends BaseEntity{
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
    /** 结束日时 */
    private java.util.Date endTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsBuyerGet() {

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
    public java.lang.String getHouseAccount() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseAccount(java.lang.String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>买家编码。</p>
     *
     * @return the 买家编码
     */
    public java.lang.String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>买家编码。</p>
     *
     * @param buyerId 买家编码。
     */
    public void setBuyerId(java.lang.String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>开始日时。</p>
     *
     * @return the 开始日时
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * <p>开始日时。</p>
     *
     * @param startTime 开始日时。
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * <p>结束日时。</p>
     *
     * @return the 结束日时
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * <p>结束日时。</p>
     *
     * @param endTime 结束日时。
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

}
