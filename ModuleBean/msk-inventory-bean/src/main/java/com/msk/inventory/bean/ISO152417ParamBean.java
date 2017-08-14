package com.msk.inventory.bean;

import com.msk.comm.bean.param.BaseRestParam;

import java.io.Serializable;

/**
 * Created by zheng_xu on 2016/9/12.
 */
public class ISO152417ParamBean extends BaseRestParam implements Serializable {
    /** 销售平台 1-云冻品；2-云冻品B2B */
    private String salePlatform;
    /** 物流区编号 */
    private String lgcsCode;

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
