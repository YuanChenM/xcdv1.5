package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * 
 * ISO151403RsParam接受参数.
 *
 * @author sjj
 */
public class ISO151403RsParam extends BaseParam {
    /** 订单号*/
    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
