package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liutao on 2016/9/28.
 */
public class ISO151416OrderDetailResult extends BaseResult{
    private String pdCode;

    private String pdName;

    private BigDecimal pdPrice;

    private BigDecimal orderQty;

    private Date proDate;

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

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public Date getProDate() {
        return proDate;
    }

    public void setProDate(Date proDate) {
        this.proDate = proDate;
    }
}
