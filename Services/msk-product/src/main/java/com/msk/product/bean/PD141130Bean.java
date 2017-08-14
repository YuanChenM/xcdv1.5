package com.msk.product.bean;

/**
 * Created by Administrator on 2016/2/23.
 */
public class PD141130Bean {
    //总控目录ID
    private String tcBuyinvestListId;
    //分类code
    private String levelCode;
    //分类name
    private String levelName;
    //买家编码

    /**
     * 获得tcBuyinvestListId
     */
    public String getTcBuyinvestListId() {
        return tcBuyinvestListId;
    }

    /**
     * 设置tcBuyinvestListId
     */
    public void setTcBuyinvestListId(String tcBuyinvestListId) {
        this.tcBuyinvestListId = tcBuyinvestListId;
    }

    private String buyerCode;
    //买家名称
    private String buyerName;
    //调查日期
    private String investigateDate;
    //调研人

    /**
     * 获得investigateMan
     */
    public String getInvestigateMan() {
        return investigateMan;
    }

    /**
     * 设置investigateMan
     */
    public void setInvestigateMan(String investigateMan) {
        this.investigateMan = investigateMan;
    }

    /**
     * 获得investigateDate
     */
    public String getInvestigateDate() {
        return investigateDate;
    }

    /**
     * 设置investigateDate
     */
    public void setInvestigateDate(String investigateDate) {
        this.investigateDate = investigateDate;
    }

    /**
     * 获得buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * 设置buyerName
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * 获得buyerCode
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * 设置buyerCode
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * 获得levelName
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * 设置levelName
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * 获得levelCode
     */
    public String getLevelCode() {
        return levelCode;
    }

    /**
     * 设置levelCode
     */
    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    private String investigateMan;

}
