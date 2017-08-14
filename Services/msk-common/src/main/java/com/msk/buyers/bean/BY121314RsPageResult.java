package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.ByBuyerDelivery;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/12.
 */
public class BY121314RsPageResult extends RsPageResult {

    private List<ByBuyerDelivery> brOBuyerInfoList;

    public List<ByBuyerDelivery> getBrOBuyerInfoList() {
        return brOBuyerInfoList;
    }

    public void setBrOBuyerInfoList(List<ByBuyerDelivery> brOBuyerInfoList) {
        this.brOBuyerInfoList = brOBuyerInfoList;
    }
}
