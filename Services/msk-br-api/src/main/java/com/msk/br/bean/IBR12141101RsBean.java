package com.msk.br.bean;

import com.msk.core.entity.BrBuyerPool;

import java.util.List;


public class IBR12141101RsBean  extends BrBuyerPool {

    private String buyerId;

    private List<IBR12141101RsBean> brBuyerPoolList;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<IBR12141101RsBean> getBrBuyerPoolList() {
        return brBuyerPoolList;
    }

    public void setBrBuyerPoolList(List<IBR12141101RsBean> brBuyerPoolList) {
        this.brBuyerPoolList = brBuyerPoolList;
    }
}
