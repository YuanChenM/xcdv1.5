package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * ISO151426_管家订单查询接口
 * Created by wang_shuai on 2016/8/22.
 */
public class ISO151426ProductRestResult extends BaseResult {
    /**
     * 商品编码
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 产品单价
     */
    private BigDecimal pdPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }
}
