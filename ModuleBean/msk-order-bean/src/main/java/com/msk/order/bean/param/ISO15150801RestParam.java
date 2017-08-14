package com.msk.order.bean.param;

import com.msk.common.bean.param.BasePageParam;
import com.msk.order.bean.result.SO15150801PdStockListResult;

import java.util.List;

/**
 * ISO15150801_选择产品后台接口参数
 * Created by wang_jianzhou on 2016/8/9.
 */
public class ISO15150801RestParam extends BasePageParam {

    /** 卖家编码*/
    private String slCode;
    /** 销售平台*/
    private String salePlatform;
    /** 区域编码*/
    private String lgcsCode;
    /** 产品编码*/
    private List<String> pdCodeList;

    private String pdCode;

    private String pdName;

    private String unit;

    private List<SO15150801PdStockListResult> products;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public List<SO15150801PdStockListResult> getProducts() {
        return products;
    }

    public void setProducts(List<SO15150801PdStockListResult> products) {
        this.products = products;
    }

    public List<String> getPdCodeList() {
        return pdCodeList;
    }

    public void setPdCodeList(List<String> pdCodeList) {
        this.pdCodeList = pdCodeList;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
}
