package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by zhang_chi on 2016/4/28.
 */
public class ISL231185RsResult extends BaseEntity {

    /** 卖家编码 */
    private String slCode;

    /** 大区 */
    private String areaCode;

    /** 物流区编码 */
    private String lgcsRreaCode;


    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLgcsRreaCode() {
        return lgcsRreaCode;
    }

    public void setLgcsRreaCode(String lgcsRreaCode) {
        this.lgcsRreaCode = lgcsRreaCode;
    }
}
