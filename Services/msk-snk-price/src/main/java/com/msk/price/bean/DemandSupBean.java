package com.msk.price.bean;


import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * DemandSupBean
 *
 */
public class DemandSupBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String supCode;

    private List<DemandProductBean> productList;

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public List<DemandProductBean> getProductList() {
        return productList;
    }

    public void setProductList(List<DemandProductBean> productList) {
        this.productList = productList;
    }
}