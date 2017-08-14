package com.msk.stock.bean;


import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * ISO151419RsResult
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class ISO151419RsResult extends RsPageResult {
    private List<ISO151419RsProductResult> productList;

    /**
     * Getter method for property <tt>productList</tt>.
     *
     * @return property value of productList
     */
    public List<ISO151419RsProductResult> getProductList() {
        return productList;
    }

    /**
     * Setter method for property <tt>productList</tt>.
     *
     * @param productList value to be assigned to property productList
     */
    public void setProductList(List<ISO151419RsProductResult> productList) {
        this.productList = productList;
    }
}
