package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;

public class BS2101101BsBean extends BaseParam {
    // 联系电话.
    private String slTel;
    // 买手账号.
    private String slAccount;
    // 买手编码.
    private String slCode;

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

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
