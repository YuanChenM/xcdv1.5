package com.msk.buyers.bean;

import com.msk.core.entity.SlBsBuyer;

public class BY121122Bean  {

    private String  startTime;
    private String endTime;


    /**管家名称**/
    private  String houseShowName;
    /**买家上线概率评分**/
    private  String buyerOnlineScore;
    /**冻品管家变更原因**/
    private  String  changeReason;
    /**专属会员发展方式**/
    private String developmentWay;
    /**分类买家池名**/
    private String buyerPoolName;

    public String getBuyerPoolName() {
        return buyerPoolName;
    }

    public void setBuyerPoolName(String buyerPoolName) {
        this.buyerPoolName = buyerPoolName;
    }

    public String getHouseShowName() {
        return houseShowName;
    }

    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    public String getBuyerOnlineScore() {
        return buyerOnlineScore;
    }

    public void setBuyerOnlineScore(String buyerOnlineScore) {
        this.buyerOnlineScore = buyerOnlineScore;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getDevelopmentWay() {
        return developmentWay;
    }

    public void setDevelopmentWay(String developmentWay) {
        this.developmentWay = developmentWay;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
