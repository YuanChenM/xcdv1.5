package com.msk.seller.bean;

import com.msk.core.entity.SlPdArtno;

import java.util.List;

/**
 * Created by li_huiqian on 2016/8/15.
 */
public class ISLProductRsResult {

    /** 卖家产品货号列表 */
    private List<SlPdArtno> productList;

    public List<SlPdArtno> getProductList() {
        return productList;
    }

    public void setProductList(List<SlPdArtno> productList) {
        this.productList = productList;
    }
}
