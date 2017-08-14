package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;

/**
 *
 *
 * @author yuan_zhifei
 */
public class IBY121319RsBean extends BaseEntity {
    //买家编码
    private  String buyerCode;
    //买家Id
    private String buyerId;
    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
