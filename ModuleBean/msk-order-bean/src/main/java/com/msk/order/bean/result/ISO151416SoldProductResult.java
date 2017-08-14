package com.msk.order.bean.result;

import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * Created by liutao on 2016/9/19.
 */
public class ISO151416SoldProductResult extends RestPageResult {
    private List<ISO151416SellerProductResult> sellerProductResult;

    public List<ISO151416SellerProductResult> getSellerProductResult() {
        return sellerProductResult;
    }

    public void setSellerProductResult(List<ISO151416SellerProductResult> sellerProductResult) {
        this.sellerProductResult = sellerProductResult;
    }
}
