package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/9/13.
 */
public class IBY121323RsResult extends RsPageResult {

    private List<IBY121323Bean> buyerList;

    public List<IBY121323Bean> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List<IBY121323Bean> buyerList) {
        this.buyerList = buyerList;
    }
}
