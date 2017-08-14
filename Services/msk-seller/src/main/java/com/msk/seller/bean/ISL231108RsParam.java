package com.msk.seller.bean;

import java.util.List;

/**
 * Created by gyh on 2016/2/23.
 */
public class ISL231108RsParam {
    private List<ISL231108RsSlPdQty> slPdQtyList;//卖家产品质量标准信息List

    /**
     * 获得slPdQtyList
     */
    public List<ISL231108RsSlPdQty> getSlPdQtyList() {
        return slPdQtyList;
    }

    /**
     * 设置slPdQtyList
     */
    public void setSlPdQtyList(List<ISL231108RsSlPdQty> slPdQtyList) {
        this.slPdQtyList = slPdQtyList;
    }
}
