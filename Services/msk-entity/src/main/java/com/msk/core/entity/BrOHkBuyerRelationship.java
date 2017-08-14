/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_o_hk_buyer_relationship对应的BrOHkBuyerRelationship</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrOHkBuyerRelationship extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** RS_ID */
    private Long rsId;
    /** SL_CODE */
    private String slCode;
    /** HOUSE_CODE */
    private String houseCode;
    /** BUYER_ID */
    private String buyerId;
    /** START_TIME */
    private java.util.Date startTime;
    /** END_TIME */
    private java.util.Date endTime;
    /** 1：锁定期 2：专属会员 */
    private String bindStatus;
    /** BIND_TIME */
    private java.util.Date bindTime;
    /**
     * <p>默认构造函数</p>
     */
    public BrOHkBuyerRelationship() {

    }

    /**
     * <p>RS_ID</p>
     *
     * @return the RS_ID
     */
    public Long getRsId() {
        return rsId;
    }

    /**
     * <p>RS_ID</p>
     *
     * @param rsId RS_ID
     */
    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    /**
     * <p>SL_CODE</p>
     *
     * @return the SL_CODE
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>SL_CODE</p>
     *
     * @param slCode SL_CODE
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>HOUSE_CODE</p>
     *
     * @return the HOUSE_CODE
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * <p>HOUSE_CODE</p>
     *
     * @param houseCode HOUSE_CODE
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
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
     * <p>START_TIME</p>
     *
     * @return the START_TIME
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * <p>START_TIME</p>
     *
     * @param startTime START_TIME
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * <p>END_TIME</p>
     *
     * @return the END_TIME
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * <p>END_TIME</p>
     *
     * @param endTime END_TIME
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * <p>1：锁定期 2：专属会员</p>
     *
     * @return the 1：锁定期 2：专属会员
     */
    public String getBindStatus() {
        return bindStatus;
    }

    /**
     * <p>1：锁定期 2：专属会员</p>
     *
     * @param bindStatus 1：锁定期 2：专属会员
     */
    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    /**
     * <p>BIND_TIME</p>
     *
     * @return the BIND_TIME
     */
    public java.util.Date getBindTime() {
        return bindTime;
    }

    /**
     * <p>BIND_TIME</p>
     *
     * @param bindTime BIND_TIME
     */
    public void setBindTime(java.util.Date bindTime) {
        this.bindTime = bindTime;
    }

}
