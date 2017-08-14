package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseParam;
import com.msk.comm.bean.param.BaseRestPageParam;
import com.msk.comm.bean.param.BaseRestParam;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ISO152414ParamBean extends BaseRestPageParam {

    private String sellerCode;
    private String supplierCode;
    private String lgcsCode;

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }
}
