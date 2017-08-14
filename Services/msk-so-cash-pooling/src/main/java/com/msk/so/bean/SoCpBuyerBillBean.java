package com.msk.so.bean;

import com.msk.core.entity.SoCpBuyerBill;

/**
 * Created by li_huiqian on 2016/9/5
 */
public class SoCpBuyerBillBean extends SoCpBuyerBill {

    /** 0:正常；1：交易关闭 */
    private String transFlg;

    public String getTransFlg() {
        return transFlg;
    }

    public void setTransFlg(String transFlg) {
        this.transFlg = transFlg;
    }
}
