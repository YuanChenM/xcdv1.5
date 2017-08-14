package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/10.
 */
public class ISO151414SearchStockRestParam extends BaseRestParam {

    /** 销售平台 */
    private String salePlatform;

    /** 卖家或者买手编码 */
    private String slCode;

    /** 物流区编码 */
    private String lgcsCode;

    /** 产品信息 */
    private List<ISO151414StockProductInfo> products;

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

    public List<ISO151414StockProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ISO151414StockProductInfo> products) {
        this.products = products;
    }
}
