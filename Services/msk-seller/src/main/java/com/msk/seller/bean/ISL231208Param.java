package com.msk.seller.bean;

import com.msk.common.bean.RsPageParam;

/**
 *
 *
 * Created by zhang_chi on 2016/9/12.
 */
public class ISL231208Param extends RsPageParam{

    /** 卖家编码 */
    private String  slCode;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
