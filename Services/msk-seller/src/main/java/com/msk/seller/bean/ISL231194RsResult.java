package com.msk.seller.bean;

/**
 * Created by zhangchi on 2016/5/13.
 */
public class ISL231194RsResult extends ISL231194RsBankResult {

    /**卖家编码*/
    private String slCode;

    /**卖家账号*/
    private String slAccount;

    /**卖家编码显示*/
    private String slCodeDis;

    /**卖家主分类*/
    private String slMainClass;

    /**卖家名称*/
    private String slName;


    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getSlMainClass() {
        return slMainClass;
    }

    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }
}
