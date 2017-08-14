package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * Created by wang_shuai on 2016/8/25.
 */
public class ISO151415RestPdInfoResult extends BaseResult {
    /** 卖家产品货号列表 */
    private List<ISO151415SlArtNoResult> productList;

    public List<ISO151415SlArtNoResult> getProductList() {
        return productList;
    }

    public void setProductList(List<ISO151415SlArtNoResult> productList) {
        this.productList = productList;
    }
}
