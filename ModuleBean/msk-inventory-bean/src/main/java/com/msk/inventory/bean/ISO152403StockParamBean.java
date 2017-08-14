package com.msk.inventory.bean;

/**
 * Created by zheng_xu on 2016/8/24.
 */
public class ISO152403StockParamBean {

    /* 产品类型编码（不包含等级），pdCode的前9位 */
    private String pdTypeCode;
    /* 区域编码 */
    private String lgcsCode;
    /* 平台编码 */
    private String salePlatform;

    public String getPdTypeCode() {
        return pdTypeCode;
    }

    public void setPdTypeCode(String pdTypeCode) {
        this.pdTypeCode = pdTypeCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

}
