/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表sl_bs_buyer_get_his对应的SlBsBuyerGetHis。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SlBsBuyerGetHis extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 履历ID */
    private Long hisId;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** 用于登录的卖家账号 */
    private String houseCode;
    /** 买家编码 */
    private String buyerId;
    /** 开始日时 */
    private java.util.Date startTime;
    /** 结束日时 */
    private java.util.Date endTime;
    /** 关系解除原因 */
    private String reason;
    /**
     * <p>默认构造函数。</p>
     */
    public SlBsBuyerGetHis() {

    }

    /**
     * <p>履历ID。</p>
     *
     * @return the 履历ID
     */
    public Long getHisId() {
        return hisId;
    }

    /**
     * <p>履历ID。</p>
     *
     * @param hisId 履历ID。
     */
    public void setHisId(Long hisId) {
        this.hisId = hisId;
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
    public String getHouseAccount() {
        return houseCode;
    }

    /**
     * <p>用于登录的卖家账号。</p>
     *
     * @param houseCode 用于登录的卖家账号。
     */
    public void setHouseAccount(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * <p>买家编码。</p>
     *
     * @return the 买家编码
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>买家编码。</p>
     *
     * @param buyerId 买家编码。
     */
    public void setBuyerId(String buyerId) {
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

    /**
     * <p>关系解除原因。</p>
     *
     * @return the 关系解除原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * <p>关系解除原因。</p>
     *
     * @param reason 关系解除原因。
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
