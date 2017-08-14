package com.msk.buyers.bean;

import com.msk.common.bean.RsPageParam;

import java.util.Date;

/**
 * BY12130302Bean.
 *
 * @author zhou_yajun
 */
public class BY12130302RsParam extends RsPageParam {

    private String buyerId;
    private String searchType;
    private String marketingStatus;
    /**管家名称**/
    private  String houseShowName;
    /**买家上线概率评分**/
    private  String buyerOnlineScore;
    /**冻品管家变更原因**/
    private  String  changeReason;
    /**专属会员发展方式**/
    private String developmentWay;
    /** START_TIME */
    private Date startTime;
    /** END_TIME */
    private Date endTime;
    private String applyStatus;

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>searchType</tt>.
     *
     * @return property value of searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * Setter method for property <tt>searchType</tt>.
     *
     * @param searchType value to be assigned to property searchType
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * Getter method for property <tt>marketingStatus</tt>.
     *
     * @return property value of marketingStatus
     */
    public String getMarketingStatus() {
        return marketingStatus;
    }

    /**
     * Setter method for property <tt>marketingStatus</tt>.
     *
     * @param marketingStatus value to be assigned to property marketingStatus
     */
    public void setMarketingStatus(String marketingStatus) {
        this.marketingStatus = marketingStatus;
    }

    /**
     * Getter method for property <tt>houseShowName</tt>.
     *
     * @return property value of houseShowName
     */
    public String getHouseShowName() {
        return houseShowName;
    }

    /**
     * Setter method for property <tt>houseShowName</tt>.
     *
     * @param houseShowName value to be assigned to property houseShowName
     */
    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    /**
     * Getter method for property <tt>buyerOnlineScore</tt>.
     *
     * @return property value of buyerOnlineScore
     */
    public String getBuyerOnlineScore() {
        return buyerOnlineScore;
    }

    /**
     * Setter method for property <tt>buyerOnlineScore</tt>.
     *
     * @param buyerOnlineScore value to be assigned to property buyerOnlineScore
     */
    public void setBuyerOnlineScore(String buyerOnlineScore) {
        this.buyerOnlineScore = buyerOnlineScore;
    }

    /**
     * Getter method for property <tt>changeReason</tt>.
     *
     * @return property value of changeReason
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * Setter method for property <tt>changeReason</tt>.
     *
     * @param changeReason value to be assigned to property changeReason
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * Getter method for property <tt>developmentWay</tt>.
     *
     * @return property value of developmentWay
     */
    public String getDevelopmentWay() {
        return developmentWay;
    }

    /**
     * Setter method for property <tt>developmentWay</tt>.
     *
     * @param developmentWay value to be assigned to property developmentWay
     */
    public void setDevelopmentWay(String developmentWay) {
        this.developmentWay = developmentWay;
    }

    /**
     * Getter method for property <tt>startTime</tt>.
     *
     * @return property value of startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter method for property <tt>startTime</tt>.
     *
     * @param startTime value to be assigned to property startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter method for property <tt>endTime</tt>.
     *
     * @return property value of endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter method for property <tt>endTime</tt>.
     *
     * @param endTime value to be assigned to property endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter method for property <tt>applyStatus</tt>.
     *
     * @return property value of applyStatus
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * Setter method for property <tt>applyStatus</tt>.
     *
     * @param applyStatus value to be assigned to property applyStatus
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }
}
