package com.msk.batch.order.bean;

import com.msk.common.bean.param.BaseRestParam;

/**
 * Created by liutao on 2016/9/6.
 */
public class BSO151402SellerProductInfo extends BaseRestParam {

    private String pdCode;

    private String slCode;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
