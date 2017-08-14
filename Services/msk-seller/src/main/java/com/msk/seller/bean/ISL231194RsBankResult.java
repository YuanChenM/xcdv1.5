package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhangchi on 2016/5/13.
 */
public class ISL231194RsBankResult extends BaseEntity {

    /**开户人名称*/
    private String accountName;

    /**开户银行名称*/
    private String bankName;

    /**开户银行卡卡号*/
    private String cardNo;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
