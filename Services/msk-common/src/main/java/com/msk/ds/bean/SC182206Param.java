package com.msk.ds.bean;

import com.hoperun.core.bean.BaseParam;
import com.msk.product.bean.ProductBean;

import java.util.List;

/**
 * geng_xingdong
 */
public class SC182206Param extends BaseParam {
    /**传入的参数*/
    private List<ProductBean> params;

    public List<ProductBean> getParams() {
        return params;
    }

    public void setParams(List<ProductBean> params) {
        this.params = params;
    }




}
