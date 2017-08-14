package com.msk.bms.ssc.bean.seller;

import com.msk.common.bean.RsPageParam;

/**
 *
 *
 * Created by zhang_chi on 2016/9/12.
 */
public class ISL231207Param extends RsPageParam{

    /**企业账号*/
    private String slAccount;

    /**企业名称*/
    private String epName;

    /**企业主类型*/
    private String slMainClass;

    /**联系人姓名*/
    private String slContact;

    /**联系电话*/
    private String slTel;

    /**城市中文名*/
    private String cityName;

    /**物流区中文名*/
    private String lgcsAreaName;

    /**卖家名称*/
    private String slShowName;

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
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

    public String getSlShowName() {
        return slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }
}
