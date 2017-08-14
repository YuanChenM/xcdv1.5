package com.msk.seller.bean;

import com.msk.common.bean.RsPageParam;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231201RsParam {
    private String chapClass;//1：分销章程，2：卖家协议

    /**
     * 获得chapClass
     */
    public String getChapClass() {
        return chapClass;
    }

    /**
     * 设置chapClass
     */
    public void setChapClass(String chapClass) {
        this.chapClass = chapClass;
    }
}
