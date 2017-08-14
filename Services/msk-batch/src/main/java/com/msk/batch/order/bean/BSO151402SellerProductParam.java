package com.msk.batch.order.bean;

import com.msk.common.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by liutao on 2016/9/6.
 */
public class BSO151402SellerProductParam extends BaseRestParam {

    private List<BSO151402SellerProductInfo> products;

    public List<BSO151402SellerProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<BSO151402SellerProductInfo> products) {
        this.products = products;
    }
}
