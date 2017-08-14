package com.msk.so.bean;

import com.msk.core.entity.SoCpRunning;

/**
 * Created by zhang_chi on 2016/8/2.
 */
public class SoCpRunningBean extends SoCpRunning {

    /** 1：现金 2：转账 3：支票 4：冲抵核销 */
    private String paidTypeStr;

    /** 支付日期 */
    private String paidTimeStr;

    public String getPaidTypeStr() {
        return paidTypeStr;
    }

    public void setPaidTypeStr(String paidTypeStr) {
        this.paidTypeStr = paidTypeStr;
    }

    public String getPaidTimeStr() {
        return paidTimeStr;
    }

    public void setPaidTimeStr(String paidTimeStr) {
        this.paidTimeStr = paidTimeStr;
    }
}
