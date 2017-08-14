package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * Created by liu_tao2 on 2016/8/25.
 */
public class ISO151414SellerProductInfo extends BaseParam {

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
