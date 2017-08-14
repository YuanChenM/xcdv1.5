package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * Created by liutao on 2016/9/19.
 */
public class ISO151416ProductResult extends BaseResult {

    private String pdCode;

    private String pdName;

    private BigDecimal pdPrice;

    private BigDecimal suppQty;

    private BigDecimal actualPay;

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

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public BigDecimal getActualPay() {
        return actualPay;
    }

    public void setActualPay(BigDecimal actualPay) {
        this.actualPay = actualPay;
    }
}
