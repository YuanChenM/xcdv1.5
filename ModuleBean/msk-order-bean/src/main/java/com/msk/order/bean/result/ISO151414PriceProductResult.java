package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * Created by liu_tao2 on 2016/8/29.
 */
public class ISO151414PriceProductResult extends BaseResult {

    private String pdCode;

    private String gradeCode;

    private String priceCycle;

    private String orderLevel;

    private BigDecimal pdPrice;

    private String logiAreaCode;

    private String sellWayCode;

    private String sellWayName;

    private BigDecimal pdKgPrice;

    private BigDecimal pdBoxPrice;

    public BigDecimal getPdKgPrice() {
        return pdKgPrice;
    }

    public void setPdKgPrice(BigDecimal pdKgPrice) {
        this.pdKgPrice = pdKgPrice;
    }

    public BigDecimal getPdBoxPrice() {
        return pdBoxPrice;
    }

    public void setPdBoxPrice(BigDecimal pdBoxPrice) {
        this.pdBoxPrice = pdBoxPrice;
    }

    public String getSellWayCode() {
        return sellWayCode;
    }

    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }

    public String getSellWayName() {
        return sellWayName;
    }

    public void setSellWayName(String sellWayName) {
        this.sellWayName = sellWayName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }
}
