/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_o_hk_info对应的BrOHkInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrOHkInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 区划(6)+顺序码(4) */
    private String slCode;
    /** HOUSE_ACCOUNT */
    private String houseAccount;
    /** HOUSE_CODE */
    private String houseCode;
    /** HOUSE_TEL */
    private String houseTel;
    /** HOUSE_SHOW_NAME */
    private String houseShowName;
    /** HOUSE_CONTACT */
    private String houseContact;
    /** LGCS_AREA_CODE */
    private String lgcsAreaCode;
    /** LGCS_AREA_NAME */
    private String lgcsAreaName;
    /** CITY_CODE */
    private String cityCode;
    /** CITY_NAME */
    private String cityName;
    /** LOCK_MAX_BUYERS */
    private Integer lockMaxBuyers;
    /** MARKETING_MAX_DAYS */
    private Integer marketingMaxDays;
    /** VIP_MAX_BUYERS */
    private Integer vipMaxBuyers;
    /** 1-是，0-否 */
    private String isChangeBuyers;
    /** 星级 */
    private java.math.BigDecimal houseStar;
    /**
     * <p>默认构造函数</p>
     */
    public BrOHkInfo() {

    }

    /**
     * <p>区划(6)+顺序码(4)</p>
     *
     * @return the 区划(6)+顺序码(4)
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * <p>区划(6)+顺序码(4)</p>
     *
     * @param slCode 区划(6)+顺序码(4)
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * <p>HOUSE_ACCOUNT</p>
     *
     * @return the HOUSE_ACCOUNT
     */
    public String getHouseAccount() {
        return houseAccount;
    }

    /**
     * <p>HOUSE_ACCOUNT</p>
     *
     * @param houseAccount HOUSE_ACCOUNT
     */
    public void setHouseAccount(String houseAccount) {
        this.houseAccount = houseAccount;
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
     * <p>HOUSE_TEL</p>
     *
     * @return the HOUSE_TEL
     */
    public String getHouseTel() {
        return houseTel;
    }

    /**
     * <p>HOUSE_TEL</p>
     *
     * @param houseTel HOUSE_TEL
     */
    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    /**
     * <p>HOUSE_SHOW_NAME</p>
     *
     * @return the HOUSE_SHOW_NAME
     */
    public String getHouseShowName() {
        return houseShowName;
    }

    /**
     * <p>HOUSE_SHOW_NAME</p>
     *
     * @param houseShowName HOUSE_SHOW_NAME
     */
    public void setHouseShowName(String houseShowName) {
        this.houseShowName = houseShowName;
    }

    /**
     * <p>HOUSE_CONTACT</p>
     *
     * @return the HOUSE_CONTACT
     */
    public String getHouseContact() {
        return houseContact;
    }

    /**
     * <p>HOUSE_CONTACT</p>
     *
     * @param houseContact HOUSE_CONTACT
     */
    public void setHouseContact(String houseContact) {
        this.houseContact = houseContact;
    }

    /**
     * <p>LGCS_AREA_CODE</p>
     *
     * @return the LGCS_AREA_CODE
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_CODE</p>
     *
     * @param lgcsAreaCode LGCS_AREA_CODE
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_NAME</p>
     *
     * @return the LGCS_AREA_NAME
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>LGCS_AREA_NAME</p>
     *
     * @param lgcsAreaName LGCS_AREA_NAME
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * <p>CITY_CODE</p>
     *
     * @return the CITY_CODE
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>CITY_CODE</p>
     *
     * @param cityCode CITY_CODE
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>CITY_NAME</p>
     *
     * @return the CITY_NAME
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * <p>CITY_NAME</p>
     *
     * @param cityName CITY_NAME
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>LOCK_MAX_BUYERS</p>
     *
     * @return the LOCK_MAX_BUYERS
     */
    public Integer getLockMaxBuyers() {
        return lockMaxBuyers;
    }

    /**
     * <p>LOCK_MAX_BUYERS</p>
     *
     * @param lockMaxBuyers LOCK_MAX_BUYERS
     */
    public void setLockMaxBuyers(Integer lockMaxBuyers) {
        this.lockMaxBuyers = lockMaxBuyers;
    }

    /**
     * <p>MARKETING_MAX_DAYS</p>
     *
     * @return the MARKETING_MAX_DAYS
     */
    public Integer getMarketingMaxDays() {
        return marketingMaxDays;
    }

    /**
     * <p>MARKETING_MAX_DAYS</p>
     *
     * @param marketingMaxDays MARKETING_MAX_DAYS
     */
    public void setMarketingMaxDays(Integer marketingMaxDays) {
        this.marketingMaxDays = marketingMaxDays;
    }

    /**
     * <p>VIP_MAX_BUYERS</p>
     *
     * @return the VIP_MAX_BUYERS
     */
    public Integer getVipMaxBuyers() {
        return vipMaxBuyers;
    }

    /**
     * <p>VIP_MAX_BUYERS</p>
     *
     * @param vipMaxBuyers VIP_MAX_BUYERS
     */
    public void setVipMaxBuyers(Integer vipMaxBuyers) {
        this.vipMaxBuyers = vipMaxBuyers;
    }

    /**
     * <p>1-是，0-否</p>
     *
     * @return the 1-是，0-否
     */
    public String getIsChangeBuyers() {
        return isChangeBuyers;
    }

    /**
     * <p>1-是，0-否</p>
     *
     * @param isChangeBuyers 1-是，0-否
     */
    public void setIsChangeBuyers(String isChangeBuyers) {
        this.isChangeBuyers = isChangeBuyers;
    }

    /**
     * <p>星级</p>
     *
     * @return the 星级
     */
    public java.math.BigDecimal getHouseStar() {
        return houseStar;
    }

    /**
     * <p>星级</p>
     *
     * @param houseStar 星级
     */
    public void setHouseStar(java.math.BigDecimal houseStar) {
        this.houseStar = houseStar;
    }

}
