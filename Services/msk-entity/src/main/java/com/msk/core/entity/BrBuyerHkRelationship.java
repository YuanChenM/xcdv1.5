/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_buyer_hk_relationship对应的BrBuyerHkRelationship</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrBuyerHkRelationship extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;
    /** 冻品管家主键 */
    private String houseCode;
    /** 买手店主键 */
    private String slCode;
    /** BUYER_ID */
    private String buyerId;
    /** 0：无关系，1：锁定期，2：专属会员 */
    private String relationType;
    /** 关系建立时间 */
    private java.util.Date startTime;
    /** 关系结束时间 */
    private java.util.Date endTime;
    /**
     * <p>默认构造函数</p>
     */
    public BrBuyerHkRelationship() {

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
     * <p>冻品管家主键</p>
     *
     * @return the 冻品管家主键
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>冻品管家主键</p>
     *
     * @param houseCode 冻品管家主键
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>买手店主键</p>
     *
     * @return the 买手店主键
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>买手店主键</p>
     *
     * @param slCode 买手店主键
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
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
     * <p>0：无关系，1：锁定期，2：专属会员</p>
     *
     * @return the 0：无关系，1：锁定期，2：专属会员
     */
    public String getRelationType() {
        return relationType;
    }

    /**
     * <p>0：无关系，1：锁定期，2：专属会员</p>
     *
     * @param relationType 0：无关系，1：锁定期，2：专属会员
     */
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    /**
     * <p>关系建立时间</p>
     *
     * @return the 关系建立时间
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * <p>关系建立时间</p>
     *
     * @param startTime 关系建立时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * <p>关系结束时间</p>
     *
     * @return the 关系结束时间
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * <p>关系结束时间</p>
     *
     * @param endTime 关系结束时间
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

}
