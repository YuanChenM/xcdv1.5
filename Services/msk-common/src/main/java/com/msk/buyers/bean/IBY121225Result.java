package com.msk.buyers.bean;

import com.msk.bs.bean.IBS2101107Bean;
import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
public class IBY121225Result extends RsPageResult {
   private List<IBS2101107Bean> slBuyerList;

    public List<IBS2101107Bean> getSlBuyerList() {
        return slBuyerList;
    }

    public void setSlBuyerList(List<IBS2101107Bean> slBuyerList) {
        this.slBuyerList = slBuyerList;
    }
}
