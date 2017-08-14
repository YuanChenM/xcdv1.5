package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.math.BigDecimal;

/**
 * Created by liu_tao2 on 2016/8/29.
 */
public class ISO151414PriceProductParam extends BaseRestParam {

    private String pdCode;

    private String districtCode;

    private BigDecimal orderQty;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }
}
