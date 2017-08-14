package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;
import com.msk.comm.bean.param.BaseRestPageParam;

import java.util.List;

/**
 * Created by wang_fan2 on 2016/8/23.
 */
public class ISO152413ParamBean extends BaseRestPageParam {

    /** 销售平台 */
    private String salePlatform;
    /** 卖家或者买手编码 */
    private String slCode;
    /** 物流区编码 */
    private String lgcsCode;
    /** 产品信息 */
    private List<ISO152413ProductParamBean> products;
    /** 供应商编码 */
    private List<String> supplierCodeList;
    /** 是否显示到SKU层次 */
    private int showSKU;
    /** 是否显示到入库批次 */
    private int showBatch;

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

    public List<ISO152413ProductParamBean> getProducts() {
        return products;
    }

    public void setProducts(List<ISO152413ProductParamBean> products) {
        this.products = products;
    }

    public List<String> getSupplierCodeList() {
        return supplierCodeList;
    }

    public void setSupplierCodeList(List<String> supplierCodeList) {
        this.supplierCodeList = supplierCodeList;
    }

    public int getShowSKU() {
        return showSKU;
    }

    public void setShowSKU(int showSKU) {
        this.showSKU = showSKU;
    }

    public int getShowBatch() {
        return showBatch;
    }

    public void setShowBatch(int showBatch) {
        this.showBatch = showBatch;
    }

}
