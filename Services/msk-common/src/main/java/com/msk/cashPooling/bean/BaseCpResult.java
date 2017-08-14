package com.msk.cashPooling.bean;

import com.msk.common.base.BaseBean;

/**
 * BaseOrderResult
 *
 * @author Qiu_wenting
 * @version 1.0
 **/
public class BaseCpResult extends BaseBean {

    /**交易编码*/
    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

}
