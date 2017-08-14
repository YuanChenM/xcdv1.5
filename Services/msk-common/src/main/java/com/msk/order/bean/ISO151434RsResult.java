package com.msk.order.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by jiang_tengfei on 2016/5/23.
 * 本月上半月分销量
 */
public class ISO151434RsResult extends BaseEntity {
    //物流区
    private String districtCode;
    //产品编码
    private String pdCode;
    //进行集计
    private BigDecimal orderCount;
    // 卖家编码
    private String sellerCode;
    public String getPdCode() { return pdCode; }

    public String getDistrictCode() { return districtCode; }

    public BigDecimal getOrderCount() { return orderCount; }

    public void setDistrictCode(String districtCode) { this.districtCode = districtCode; }

    public void setPdCode(String pdCode) { this.pdCode = pdCode; }

    public void setOrderCount(BigDecimal orderCount) { this.orderCount = orderCount; }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }
}
