package com.msk.br.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by zhang_jian4 on 2016/10/18.
 */
public class IBR12141201RsParam  extends BaseParam {
    private String buyerFlag;
    private String buyerId;

    public String getBuyerFlag() {
        return buyerFlag;
    }

    public void setBuyerFlag(String buyerFlag) {
        this.buyerFlag = buyerFlag;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
