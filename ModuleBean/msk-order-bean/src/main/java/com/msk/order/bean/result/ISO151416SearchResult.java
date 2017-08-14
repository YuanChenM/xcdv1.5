package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderDetail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/8.
 */
public class ISO151416SearchResult extends BaseResult {

    private Long orderId;

    private String orderCode;

    private String sellerCode;

    private String sellerName;

    private Integer orderStatus;

    private Integer buyerType;

    private String districtCode;

    private Integer paymentType;

    private String sellers;

    private String orderTaker;

    private Integer ver;

    /** 是否已开发票，1：是 */
    private String invoiceFlg;

    /** 已支付金额 */
    private BigDecimal paidAmount;

    /** 评价时间 */
    private String commentTime;

    /** 订单创建时间 */
    private String orderTimeStr;

    /** 收货信息 */
    private ISO151414ReceiverInfo receiveInfo;

    /** 配送要求 */
    private ISO151414DeliveryReq deliveryReq;

    private Date paidTime;

    /** 产品信息 */
    private List<ISO151416OrderDetailResult> orderDetail;

    private String buyersId;

    private String buyersCode;

    private String buyersName;

    private Integer buyersType;

    private Long subOrderId;

    private ISO151416DeliverInfo deliver;

    private Date orderTime;

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(Integer buyerType) {
        this.buyerType = buyerType;
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

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public ISO151416DeliverInfo getDeliver() {
        return deliver;
    }

    public void setDeliver(ISO151416DeliverInfo deliver) {
        this.deliver = deliver;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
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

    public Integer getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(Integer buyersType) {
        this.buyersType = buyersType;
    }

    public String getOrderTimeStr() {
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getInvoiceFlg() {
        return invoiceFlg;
    }

    public void setInvoiceFlg(String invoiceFlg) {
        this.invoiceFlg = invoiceFlg;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public ISO151414ReceiverInfo getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(ISO151414ReceiverInfo receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public ISO151414DeliveryReq getDeliveryReq() {
        return deliveryReq;
    }

    public void setDeliveryReq(ISO151414DeliveryReq deliveryReq) {
        this.deliveryReq = deliveryReq;
    }

    public List<ISO151416OrderDetailResult> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<ISO151416OrderDetailResult> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
