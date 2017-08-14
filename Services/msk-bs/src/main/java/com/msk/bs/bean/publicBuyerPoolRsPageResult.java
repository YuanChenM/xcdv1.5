package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;


public class publicBuyerPoolRsPageResult extends RsPageResult {
    private List<publicBuyerPoolRsBean> buyerList;

    public List<publicBuyerPoolRsBean> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List<publicBuyerPoolRsBean> buyerList) {
        this.buyerList = buyerList;
    }
}
