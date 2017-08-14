package com.msk.order.bean;

import com.msk.common.bean.RsPageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun_jiaju on 2016/6/8.
 */
public class SupResult extends RsPageResult {
    private String supCode;

    private List<ProductResult> productList=new ArrayList<>();

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public List<ProductResult> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductResult> productList) {
        this.productList = productList;
    }
}
