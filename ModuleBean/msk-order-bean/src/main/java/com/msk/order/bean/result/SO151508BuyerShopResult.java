package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * Created by wang_jianzhou on 2016/10/9.
 */
public class SO151508BuyerShopResult extends BaseResult {

    /** 买手ID*/
    private String slCode;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
