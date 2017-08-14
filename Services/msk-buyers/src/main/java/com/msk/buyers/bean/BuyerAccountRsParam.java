package com.msk.buyers.bean;

/**
 * Created by yuan_zhifei on 2017/3/2.
 */
public class BuyerAccountRsParam {
    /**
     *ID
     */
    private Long id;
    /**
     * BUYER_ID
     */
    private String buyerId;
    /**
     * TEL_NO
     */
    private String telNo;
    /**
     * ACCOUNT_NAME
     */
    private String accountName;
    /**
     * ACCOUNT_PASS
     */
    private String accountPass;

    private Integer loginMode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    public Integer getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(Integer loginMode) {
        this.loginMode = loginMode;
    }
}
