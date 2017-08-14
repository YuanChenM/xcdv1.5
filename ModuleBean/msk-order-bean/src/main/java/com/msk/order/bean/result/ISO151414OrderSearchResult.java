package com.msk.order.bean.result;

import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderDetail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/8.
 */
public class ISO151414OrderSearchResult extends SoOrder {
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

    /** 产品信息 */
    private List<SoOrderDetail> orderDetail;

    private String buyersId;

    private String buyersCode;

    private String buyersName;

    private Integer buyersType;

    private Long subOrderId;

    private ISO151416DeliverInfo deliver;

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

    public List<SoOrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<SoOrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
