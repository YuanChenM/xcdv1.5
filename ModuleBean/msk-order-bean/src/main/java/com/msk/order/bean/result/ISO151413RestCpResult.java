package com.msk.order.bean.result;


import java.io.Serializable;

/**
 * BaseOrderResult
 *
 * @author sun_jiaju
 * @version 1.0
 **/
public class ISO151413RestCpResult implements Serializable {

    /**交易编码*/
    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

}
