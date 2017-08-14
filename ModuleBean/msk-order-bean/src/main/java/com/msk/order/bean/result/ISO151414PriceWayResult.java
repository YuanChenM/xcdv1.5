package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/29.
 */
public class ISO151414PriceWayResult extends BaseResult {

    private List<ISO151414PriceProductResult> productList;

    public List<ISO151414PriceProductResult> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151414PriceProductResult> productList) {
        this.productList = productList;
    }
}
