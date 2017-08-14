package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * ISO151405_产品销量查询接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151405PdCodeListRestParam extends BaseParam {
    /** 产品编码*/
    private String pdCode;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }
}
