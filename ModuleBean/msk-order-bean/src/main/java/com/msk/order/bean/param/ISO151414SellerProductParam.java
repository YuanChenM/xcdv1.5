package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/25.
 */
public class ISO151414SellerProductParam extends BaseParam {

    private List<ISO151414SellerProductInfo> products;

    public List<ISO151414SellerProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ISO151414SellerProductInfo> products) {
        this.products = products;
    }
}
