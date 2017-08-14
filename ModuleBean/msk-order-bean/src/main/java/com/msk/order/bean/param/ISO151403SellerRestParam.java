package com.msk.order.bean.param;


import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * Created by sun_jiaju on 2016/8/31.
 */
public class ISO151403SellerRestParam extends BaseParam {
    /**卖家编码List*/
    private List<String> sellCodeList;

    public List<String> getSellCodeList() {
        return sellCodeList;
    }

    public void setSellCodeList(List<String> sellCodeList) {
        this.sellCodeList = sellCodeList;
    }
}
