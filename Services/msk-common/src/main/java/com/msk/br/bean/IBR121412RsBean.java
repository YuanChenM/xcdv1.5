package com.msk.br.bean;

import com.msk.core.entity.BrBuyerMarketingStatusHistory;

import java.util.Date;

/**
 * IBR121412RsBean.
 *
 */
public class IBR121412RsBean extends BrBuyerMarketingStatusHistory {

    private int orderCount;
    private String marketingsStatus;
    private String marketingsStatusName;
    private Date firstOrderTime;
    private Date lastOrderTime;
    private String buyerCode;
    private String buyerPoolCode;
    private Date effectTime;
    private Long planId;

    /**
     * Getter method for property <tt>orderCount</tt>.
     *
     * @return property value of orderCount
     */
    public int getOrderCount() {
        return orderCount;
    }

    /**
     * Setter method for property <tt>orderCount</tt>.
     *
     * @param orderCount value to be assigned to property orderCount
     */
    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * Getter method for property <tt>marketingsStatus</tt>.
     *
     * @return property value of marketingsStatus
     */
    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    /**
     * Setter method for property <tt>marketingsStatus</tt>.
     *
     * @param marketingsStatus value to be assigned to property marketingsStatus
     */
    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    /**
     * Getter method for property <tt>marketingsStatusName</tt>.
     *
     * @return property value of marketingsStatusName
     */
    public String getMarketingsStatusName() {
        return marketingsStatusName;
    }

    /**
     * Setter method for property <tt>marketingsStatusName</tt>.
     *
     * @param marketingsStatusName value to be assigned to property marketingsStatusName
     */
    public void setMarketingsStatusName(String marketingsStatusName) {
        this.marketingsStatusName = marketingsStatusName;
    }

    /**
     * Getter method for property <tt>firstOrderTime</tt>.
     *
     * @return property value of firstOrderTime
     */
    public Date getFirstOrderTime() {
        return firstOrderTime;
    }

    /**
     * Setter method for property <tt>firstOrderTime</tt>.
     *
     * @param firstOrderTime value to be assigned to property firstOrderTime
     */
    public void setFirstOrderTime(Date firstOrderTime) {
        this.firstOrderTime = firstOrderTime;
    }

    /**
     * Getter method for property <tt>lastOrderTime</tt>.
     *
     * @return property value of lastOrderTime
     */
    public Date getLastOrderTime() {
        return lastOrderTime;
    }

    /**
     * Setter method for property <tt>lastOrderTime</tt>.
     *
     * @param lastOrderTime value to be assigned to property lastOrderTime
     */
    public void setLastOrderTime(Date lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    /**
     * Getter method for property <tt>buyerCode</tt>.
     *
     * @return property value of buyerCode
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * Setter method for property <tt>buyerCode</tt>.
     *
     * @param buyerCode value to be assigned to property buyerCode
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * Getter method for property <tt>buyerPoolCode</tt>.
     *
     * @return property value of buyerPoolCode
     */
    public String getBuyerPoolCode() {
        return buyerPoolCode;
    }

    /**
     * Setter method for property <tt>buyerPoolCode</tt>.
     *
     * @param buyerPoolCode value to be assigned to property buyerPoolCode
     */
    public void setBuyerPoolCode(String buyerPoolCode) {
        this.buyerPoolCode = buyerPoolCode;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
