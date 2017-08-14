package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * ISO151426_管家订单查询接口
 * Created by wang_shuai on 2016/8/22.
 */
public class ISO151426OrderRestResult extends BaseResult {
    /**
     * 下单时间
     */
    private String orderDate;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 实际付款
     */
    private BigDecimal realPayment;

    /**
     * 交易状态
     */
    private Integer orderStatus;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 产品信息
     */
    private List<ISO151426ProductRestResult> products;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getRealPayment() {
        return realPayment;
    }

    public void setRealPayment(BigDecimal realPayment) {
        this.realPayment = realPayment;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<ISO151426ProductRestResult> getProducts() {
        return products;
    }

    public void setProducts(List<ISO151426ProductRestResult> products) {
        this.products = products;
    }
}
