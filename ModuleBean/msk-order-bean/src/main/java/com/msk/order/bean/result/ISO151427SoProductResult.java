package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public class ISO151427SoProductResult extends BaseEntity {
    //商品编码
    private String pdCode;
    //商品名称
    private String pdName;
    //商品数量
    private long pdCount;
    //单价
    private long unitPrice;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public long getPdCount() {
        return pdCount;
    }

    public void setPdCount(long pdCount) {
        this.pdCount = pdCount;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }
}
