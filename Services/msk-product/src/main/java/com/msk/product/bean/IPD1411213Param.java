package com.msk.product.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by gao_min on 2016/10/14.
 */
public class IPD1411213Param extends BaseParam {
    /** 买家ID */
    private String buyerId;

    /** 买手ID */
    private String slCode;

    /** 管家ID */
    private String houseCode;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
