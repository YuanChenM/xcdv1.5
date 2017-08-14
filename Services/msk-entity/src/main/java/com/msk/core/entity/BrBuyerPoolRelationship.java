/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_buyer_pool_relationship对应的BrBuyerPoolRelationship</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrBuyerPoolRelationship extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** BUYER_ID */
    private String buyerId;
    /** BUYER_POOL_ID */
    private Long buyerPoolId;
    /** POOL_BUYER_CODE */
    private String poolBuyerCode;
    /** 0：公共买家池，1：锁定期分池，2：专属会员分池 */
    private String buyerPoolType;
    /** 0：未分配，1：已分配 */
    private String isDistribution;
    /** 插入时间，买家加入买家池的时间 */
    private java.util.Date joinTime;
    /** 删除时间，买家离开买家池的时间 */
    private java.util.Date leaveTime;
    /**
     * <p>默认构造函数</p>
     */
    public BrBuyerPoolRelationship() {

    }

    /**
     * <p>ID</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>BUYER_POOL_ID</p>
     *
     * @return the BUYER_POOL_ID
     */
    public Long getBuyerPoolId() {
        return buyerPoolId;
    }

    /**
     * <p>BUYER_POOL_ID</p>
     *
     * @param buyerPoolId BUYER_POOL_ID
     */
    public void setBuyerPoolId(Long buyerPoolId) {
        this.buyerPoolId = buyerPoolId;
    }

    /**
     * <p>POOL_BUYER_CODE</p>
     *
     * @return the POOL_BUYER_CODE
     */
    public String getPoolBuyerCode() {
        return poolBuyerCode;
    }

    /**
     * <p>POOL_BUYER_CODE</p>
     *
     * @param poolBuyerCode POOL_BUYER_CODE
     */
    public void setPoolBuyerCode(String poolBuyerCode) {
        this.poolBuyerCode = poolBuyerCode;
    }

    /**
     * <p>0：公共买家池，1：锁定期分池，2：专属会员分池</p>
     *
     * @return the 0：公共买家池，1：锁定期分池，2：专属会员分池
     */
    public String getBuyerPoolType() {
        return buyerPoolType;
    }

    /**
     * <p>0：公共买家池，1：锁定期分池，2：专属会员分池</p>
     *
     * @param buyerPoolType 0：公共买家池，1：锁定期分池，2：专属会员分池
     */
    public void setBuyerPoolType(String buyerPoolType) {
        this.buyerPoolType = buyerPoolType;
    }

    /**
     * <p>0：未分配，1：已分配</p>
     *
     * @return the 0：未分配，1：已分配
     */
    public String getIsDistribution() {
        return isDistribution;
    }

    /**
     * <p>0：未分配，1：已分配</p>
     *
     * @param isDistribution 0：未分配，1：已分配
     */
    public void setIsDistribution(String isDistribution) {
        this.isDistribution = isDistribution;
    }

    /**
     * <p>插入时间，买家加入买家池的时间</p>
     *
     * @return the 插入时间，买家加入买家池的时间
     */
    public java.util.Date getJoinTime() {
        return joinTime;
    }

    /**
     * <p>插入时间，买家加入买家池的时间</p>
     *
     * @param joinTime 插入时间，买家加入买家池的时间
     */
    public void setJoinTime(java.util.Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * <p>删除时间，买家离开买家池的时间</p>
     *
     * @return the 删除时间，买家离开买家池的时间
     */
    public java.util.Date getLeaveTime() {
        return leaveTime;
    }

    /**
     * <p>删除时间，买家离开买家池的时间</p>
     *
     * @param leaveTime 删除时间，买家离开买家池的时间
     */
    public void setLeaveTime(java.util.Date leaveTime) {
        this.leaveTime = leaveTime;
    }

}
