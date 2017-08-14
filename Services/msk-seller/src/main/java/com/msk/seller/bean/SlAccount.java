package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

public class SlAccount extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String slAccount;
    private String slTel;
    private String slShowName;
    private String slContact;
    private String accountPsd;
    private String accountImg;
    public String getSlTel() {
        return this.slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getSlShowName() {
        return this.slShowName;
    }

    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    public String getSlContact() {
        return this.slContact;
    }

    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    public String getAccountPsd() {
        return this.accountPsd;
    }

    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }

    public String getAccountImg() {
        return this.accountImg;
    }

    public void setAccountImg(String accountImg) {
        this.accountImg = accountImg;
    }

    public String getSlAccount() {
        return this.slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

}
