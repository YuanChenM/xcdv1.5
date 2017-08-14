package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestPageParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duan_kai on 2016/8/25.
 */
public class ISO152412ParamBean extends BaseRestPageParam {

    private String salePlatform;
    private String slCode;
    private String lgcsCode;

    private List<ISO152412ProductParamBean> products = new ArrayList<ISO152412ProductParamBean>();

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public List<ISO152412ProductParamBean> getProducts() {
        return products;
    }

    public void setProducts(List<ISO152412ProductParamBean> products) {
        this.products = products;
    }
}
