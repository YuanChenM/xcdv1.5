package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by wu_honglei on 2016/9/12.
 */
public class SSC1130803RsBean {

    /**受款主体 文本框id*/
    private String supplierInputId;

    /**银开户银行文本框id*/
    private String bankInputId;

    /**银行账号文本框id*/
    private String bankAccountInputId;


    public String getSupplierInputId() {
        return supplierInputId;
    }

    public void setSupplierInputId(String supplierInputId) {
        this.supplierInputId = supplierInputId;
    }

    public String getBankInputId() {
        return bankInputId;
    }

    public void setBankInputId(String bankInputId) {
        this.bankInputId = bankInputId;
    }

    public String getBankAccountInputId() {
        return bankAccountInputId;
    }

    public void setBankAccountInputId(String bankAccountInputId) {
        this.bankAccountInputId = bankAccountInputId;
    }
}
