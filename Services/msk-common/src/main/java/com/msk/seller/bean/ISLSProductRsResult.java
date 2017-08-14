package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SlProduct;

import java.util.List;

/**
 * Created by zhang_chi on 2016/7/5.
 */
public class ISLSProductRsResult extends BaseEntity {

    /** 产品List */
    private List<SlProductRsBean> slProductList;

    public List<SlProductRsBean> getSlProductList() {
        return slProductList;
    }

    public void setSlProductList(List<SlProductRsBean> slProductList) {
        this.slProductList = slProductList;
    }
}
