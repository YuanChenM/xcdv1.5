package com.msk.org.bean.param;


import com.msk.common.bean.param.BaseRestParam;

public class SellerLoginParam extends BaseRestParam {
    private String slAccount;
    private String slTel;
    private String accountPsd;

    public String getSlAccount() {
        return slAccount;
    }

    public void setSlAccount(String slAccount) {
        this.slAccount = slAccount;
    }

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    public String getAccountPsd() {
        return accountPsd;
    }

    public void setAccountPsd(String accountPsd) {
        this.accountPsd = accountPsd;
    }
}
