package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * Created by wang_jianzhou on 2016/9/22.
 */
public class ISO151422InvRestResult extends BaseResult {

    private static final long serialVersionUID = 1L;

    private String purchaseBatch;

    private String innerBatch;

    private BigDecimal pdPrice;

    private Integer orderType;

    private Long subOrderId;

    private Integer slType;

    private String slCode;

    private String slName;

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public Integer getSlType() {
        return slType;
    }

    public void setSlType(Integer slType) {
        this.slType = slType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getPurchaseBatch() {
        return purchaseBatch;
    }

    public void setPurchaseBatch(String purchaseBatch) {
        this.purchaseBatch = purchaseBatch;
    }

    public String getInnerBatch() {
        return innerBatch;
    }

    public void setInnerBatch(String innerBatch) {
        this.innerBatch = innerBatch;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }
}
