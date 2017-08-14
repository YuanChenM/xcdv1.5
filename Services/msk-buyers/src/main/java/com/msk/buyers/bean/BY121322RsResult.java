package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by guan_zhongheng on 2016/8/19.
 */
public class BY121322RsResult extends RsPageResult {
    /**冻品管家营销列表**/
    private List<BY121322Bean> slHouseSaleList;

    public List<BY121322Bean> getSlHouseSaleList() {
        return slHouseSaleList;
    }

    public void setSlHouseSaleList(List<BY121322Bean> slHouseSaleList) {
        this.slHouseSaleList = slHouseSaleList;
    }
}
