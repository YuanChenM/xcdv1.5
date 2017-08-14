package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

/**
 * ISO151401_创建购物需求订单接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151401ProductsRestResult extends BaseResult {
    private String pdCode;
    private String pdName;
    private String orderPrice;
    private String priceCycle;
    private String orderQty;

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

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public String getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }
}
