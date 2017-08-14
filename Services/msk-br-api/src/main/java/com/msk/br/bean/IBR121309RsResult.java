package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by guan_zhongheng on 2016/8/23.
 */
public class IBR121309RsResult extends RsPageResult {
    /**冻品管家营销列表**/
    private List<IBR121309Bean> slHouseSaleList;

    public List<IBR121309Bean> getSlHouseSaleList() {
        return slHouseSaleList;
    }

    public void setSlHouseSaleList(List<IBR121309Bean> slHouseSaleList) {
        this.slHouseSaleList = slHouseSaleList;
    }
}
