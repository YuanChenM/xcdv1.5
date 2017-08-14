package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231153RsParam  extends BaseParam {
    /**
     * 卖家编码
     */
    private String slCode;
    /**
     * 品牌ID
     */
    private String brandId;
    /**
     * 品牌所属企业ID
     */
    private String brandEpId;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandEpId() {
        return brandEpId;
    }

    public void setBrandEpId(String brandEpId) {
        this.brandEpId = brandEpId;
    }
}




