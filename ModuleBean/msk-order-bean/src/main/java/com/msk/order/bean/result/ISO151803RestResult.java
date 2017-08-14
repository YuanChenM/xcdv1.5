package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ISO151803_订单发货明细信息查询
 * Created by sun_jiaju on 2016/8/29.
 */
public class ISO151803RestResult extends BaseResult {
    private Long orderId;//订单ID
    private String orderCode;//订单编码
    private String orderTime;//订单创建时间
    private String buyerId;//卖家ID
    private String buyersCode;//买家编码
    private String buyersName;//买家名称
    private String sellerCode;//卖家编码
    private String sellerName;//卖家名称
    private Integer orderType;//订单类型
    private String districtCode;//订单物流区域
    private Integer paymentType;//付款类型，1-线上，2-线下
    private BigDecimal orderAmount;//订单总金额
    private BigDecimal paidAmount;//已支付金额
    private Date paidTime;//支付时间

    private List<ISO151803RestOrderShipResult> shipList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyersCode() {
        return buyersCode;
    }

    public void setBuyersCode(String buyersCode) {
        this.buyersCode = buyersCode;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public List<ISO151803RestOrderShipResult> getShipList() {
        return shipList;
    }

    public void setShipList(List<ISO151803RestOrderShipResult> shipList) {
        this.shipList = shipList;
    }
}
