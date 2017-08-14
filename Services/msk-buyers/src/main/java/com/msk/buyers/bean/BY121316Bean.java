package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerAccount;
import com.msk.core.entity.ByMarketTerminal;

/**
 *
 *
 * @author zhao_chen
 */
public class BY121316Bean extends ByBuyerAccount {

    /** BUYER_SINGLE_WECHAT */
    private java.lang.String buyerSingleWechat;

    /** BUYER_QQ */
    private java.lang.String buyerQq;

    private java.lang.String buyerName;


    public String getBuyerSingleWechat() {
        return buyerSingleWechat;
    }

    public void setBuyerSingleWechat(String buyerSingleWechat) {
        this.buyerSingleWechat = buyerSingleWechat;
    }

    public String getBuyerQq() {
        return buyerQq;
    }

    public void setBuyerQq(String buyerQq) {
        this.buyerQq = buyerQq;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
