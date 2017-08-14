package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * ISO151413_已付款接口
 * Created by sun_jiaju on 2016/8/9.
 */
public class ISO151413RestParam extends BaseParam {
    // 订单ID
    @Required(required = true, message = "订单ID不能为空")
    private Long orderId;
    // 支付金额
    @Required(required = true, message = "支付金额不能为空")
    private BigDecimal orderAmount;
    // 支付交易流水号
    @Required(required = true, message = "支付交易流水号不能为空")
    private String paymentOrderCode;
    // 支付类型
    @Required(required = true, message = "支付类型不能为空")
    private String paymentMode;
    // 支付时间
    @Required(required = true, message = "支付时间不能为空")
    private String paymentTime;
    // 版本号
    @Required(required = true, message = "版本号不能为空")
    private Integer ver;
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPaymentOrderCode() {
        return paymentOrderCode;
    }

    public void setPaymentOrderCode(String paymentOrderCode) {
        this.paymentOrderCode = paymentOrderCode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public Integer getVer() {
        return ver;
    }

    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
