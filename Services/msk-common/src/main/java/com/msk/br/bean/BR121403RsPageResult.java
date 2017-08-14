package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrOBuyerInfo;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/12.
 */
public class BR121403RsPageResult extends RsPageResult {

    private List<BrOBuyerInfo> brOBuyerInfoList;

    public List<BrOBuyerInfo> getBrOBuyerInfoList() {
        return brOBuyerInfoList;
    }

    public void setBrOBuyerInfoList(List<BrOBuyerInfo> brOBuyerInfoList) {
        this.brOBuyerInfoList = brOBuyerInfoList;
    }
}
