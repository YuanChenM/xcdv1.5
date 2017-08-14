package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * 
 * ISO151404RsParam接受参数.
 *
 * @author sjj
 */
public class ISO151404RsParam extends BaseParam {
    /** 退货单号*/
    private String backNo;

    /** 订单号*/
    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getBackNo() {
        return backNo;
    }

    public void setBackNo(String backNo) {
        this.backNo = backNo;
    }
}
