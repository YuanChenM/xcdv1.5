package com.msk.seller.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by liu_tao2 on 2016/5/27.
 */
public class ISL231206RsBean extends BaseEntity {

    /**
     * 生产商企业ID
     */
    private String prodEpId;

    /**
     * 卖家编码
     */
    private String slCode;

    /**
     * 卖家编码展示用
     */
    private String slCodeDis;

    /**
     * 企业名称
     */
    private String epName;

    /**
     * 生产商编码
     */
    private String slCodeManufacture;

    public String getProdEpId() {
        return prodEpId;
    }

    public void setProdEpId(String prodEpId) {
        this.prodEpId = prodEpId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getSlCodeManufacture() {
        return slCodeManufacture;
    }

    public void setSlCodeManufacture(String slCodeManufacture) {
        this.slCodeManufacture = slCodeManufacture;
    }
}
