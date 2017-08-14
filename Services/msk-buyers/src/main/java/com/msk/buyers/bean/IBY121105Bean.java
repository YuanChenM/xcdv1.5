package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerAccount;

/**
 * Created by guan_zhongheng on 2016/7/29.
 */
public class IBY121105Bean extends ByBuyerAccount {

    // 来源地类型 0：后台系统 1：通路注册 2：云冻品平台 3：云冻品B2B平台
    private String registerSource;

    public String getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }
}
