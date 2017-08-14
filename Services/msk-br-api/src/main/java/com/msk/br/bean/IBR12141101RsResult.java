package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrBuyerPool;

import java.util.List;

public class IBR12141101RsResult extends RsPageResult {


    private List<IBR12141101RsBean> PoolList;

    public List<IBR12141101RsBean> getPoolList() {
        return PoolList;
    }

    public void setPoolList(List<IBR12141101RsBean> poolList) {
        PoolList = poolList;
    }
}
