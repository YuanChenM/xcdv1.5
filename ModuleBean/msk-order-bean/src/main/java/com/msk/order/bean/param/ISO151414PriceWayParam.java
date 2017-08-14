package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/29.
 */
public class ISO151414PriceWayParam extends BaseRestParam {

    private List<ISO151414PriceProductParam> productList;

    public List<ISO151414PriceProductParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151414PriceProductParam> productList) {
        this.productList = productList;
    }
}
