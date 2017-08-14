package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhang_chi on 2016/9/12.
 */
public class ISL231207Result extends BaseEntity {


    /**企业账号*/
    private String slAccount;
    /**卖家编码*/
    private Long slCode;
    /**企业ID*/
    private Long epId;
    /**企业名称*/
    private String epName;
    /**企业主类型*/
    private Integer slMainClass;
    /**联系人姓名*/
    private String slContact;
    /**联系电话*/
    private String slTel;
    /**卖家名称*/
    private String slShowName;
    /**城市中文名*/
    private String cityName;
    /**物流区中文名*/
    private String lgcsAreaName;
    /**开户银行*/
    private String balBank;
    /**开户账号*/
    private String balAccount;
    /**开户人*/
    private String balLegalPerson;
    /**生产商企业ID*/
    private Long prodEpId;


    public String getBalLegalPerson() {
        return balLegalPerson;
    }

    public void setBalLegalPerson(String balLegalPerson) {
        this.balLegalPerson = balLegalPerson;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public Long getSlCode() {
        return slCode;
    }

    public void setSlCode(Long slCode) {
        this.slCode = slCode;
    }

    public Long getEpId() {
        return epId;
    }

    public void setEpId(Long epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public Integer getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(Integer slMainClass) {
        this.slMainClass = slMainClass;
    }

    public String getSlContact() {
        return slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getBalBank() {
        return balBank;
    }

    public void setBalBank(String balBank) {
        this.balBank = balBank;
    }

    public String getBalAccount() {
        return balAccount;
    }

    public void setBalAccount(String balAccount) {
        this.balAccount = balAccount;
    }

    public Long getProdEpId() {
        return prodEpId;
    }

    public void setProdEpId(Long prodEpId) {
        this.prodEpId = prodEpId;
    }
}
