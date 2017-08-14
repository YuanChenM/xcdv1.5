package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;

/**
 * Created by zhu_kai1 on 2016/8/24.
 */
public class BS2101200RsParam extends RsPageParam {
    // 买家id
    private String buyerId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
