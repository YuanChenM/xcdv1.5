package com.msk.seller.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231149RsParam extends BaseParam {
    /**
     * 企业ID
     */
    private String epId;
    /**
     *品牌ID
     */
    private String brandId;

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
