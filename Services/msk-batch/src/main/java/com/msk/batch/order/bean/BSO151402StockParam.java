package com.msk.batch.order.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/22.
 */
public class BSO151402StockParam extends BaseParam {
    /** 销售平台 */
    private String salePlatform;

    /** 卖家或者买手编码 */
    private String slCode;

    /** 物流区编码 */
    private String lgcsCode;

    /** 产品信息 */
    private List<BSO151402StockProductInfo> products;

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

    public List<BSO151402StockProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<BSO151402StockProductInfo> products) {
        this.products = products;
    }
}
