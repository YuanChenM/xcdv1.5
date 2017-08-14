package com.msk.seller.bean;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231105RsParam {

    /**
     * 卖家账号
     */
    private String slAccount;
    private String userName;

    /**
     * 登录密码
     */
    private String accountPsd;
    /**
     * 手机号码
     */
    private String slTel;


    public String getAccountPsd() {
        return accountPsd;
    }

    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
