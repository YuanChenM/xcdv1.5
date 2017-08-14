package com.msk.seller.bean;

import com.msk.common.bean.RsPageParam;

/**
 * Created by Administrator on 2016/2/15.
 */
public class ISL231122RsParam extends RsPageParam {
    /**
     * 卖家账号
     */
    public String slAccount;
    /**
     * 登录手机号码
     */
    public String slTel;
    /**
     * 卖家编码
     */
    public String slCode;
    /**
     * 区划
     */
    public String slAreaCode;
    /**
     * 企业名称
     */
    public String epName;

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

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlAreaCode() {
        return slAreaCode;
    }

    public void setSlAreaCode(String slAreaCode) {
        this.slAreaCode = slAreaCode;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }
}
