package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * ISO151401_创建购物需求订单接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151401RestResult extends BaseResult {
    private String proCode;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }
}
