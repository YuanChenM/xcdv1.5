package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

/**
 * Created by wang_shuai on 2016/9/13.
 */
public class ISO151415BaseCpResult extends BaseEntity {
    /**交易编码*/
    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
