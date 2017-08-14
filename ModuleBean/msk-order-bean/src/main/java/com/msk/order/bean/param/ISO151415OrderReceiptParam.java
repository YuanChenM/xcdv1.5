package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;
import com.msk.order.bean.result.BaseOrderDelivery;
import com.msk.order.bean.result.OrderShipInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wang_shuai on 2016/8/9.
 */
public class ISO151415OrderReceiptParam extends BaseParam {
    /** 订单ID */
    private Long orderId;

    /** 发货单ID */
    private Long shipId;

    /** 多次配送单信息 */
    private List<OrderShipInfo> shipList;

    /** 明细状态 */
    private Integer detailStatus;

    /** 主订单状态 */
    private Integer orderStatus;

    /** 子订单状态 */
    private Integer orderChildStatus;

    /**订单版本号*/
    private Integer ver;
    /**1.买家取消 2.不同意拼货的取消 3.不同意分批的取消神农客网站调用时默认是1.买家取消，CallCenter调用时有不同选择。*/
    private Integer cancelType;
    /**取消原因*/
    private String cancelReason;
    /**订单类型*/
    private Integer orderType;
    /**支付金额*/
    private BigDecimal orderAmount;
    /**支付单号*/
    private String paymentOrderCode;
    /**配送单号*/
    private String deliverCode;
    /**配送单信息*/
    private BaseOrderDelivery deliver;
    /**
     * 付款类型
     */
    private Integer paymentType;

    private String orderCode;

    /**
     * 销售平台
     */
    private String salePlatform;

    /**
     * 销售区域编码
     */
    private String saleRegionCode;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public List<OrderShipInfo> getShipList() {
        return shipList;
    }

    public void setShipList(List<OrderShipInfo> shipList) {
        this.shipList = shipList;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderChildStatus() {
        return orderChildStatus;
    }

    public void setOrderChildStatus(Integer orderChildStatus) {
        this.orderChildStatus = orderChildStatus;
    }

    @Override
    public Integer getVer() {
        return ver;
    }

    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public BaseOrderDelivery getDeliver() {
        return deliver;
    }

    public void setDeliver(BaseOrderDelivery deliver) {
        this.deliver = deliver;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getSaleRegionCode() {
        return saleRegionCode;
    }

    public void setSaleRegionCode(String saleRegionCode) {
        this.saleRegionCode = saleRegionCode;
    }
}
