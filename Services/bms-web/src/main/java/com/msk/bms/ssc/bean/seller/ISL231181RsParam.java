package com.msk.bms.ssc.bean.seller;

import com.hoperun.core.bean.BasePageParam;

/**
 * Created by Peng_hao on 2016/9/2.
 */
public class ISL231181RsParam extends BasePageParam {

    //卖家账号
    private String slAccount;

    /* 采购商卖家编码 */
    private String slCode;

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


