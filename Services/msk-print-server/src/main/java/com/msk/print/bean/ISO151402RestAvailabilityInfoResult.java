package com.msk.print.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestAvailabilityInfoResult implements Serializable {
    /**
     * 供货明细id
     */
    private Long orderDetailAvailabilityId;
    /**
     * 供应商名称
     */
    private String supplierCode = "";
    /**
     * 供应商名称
     */
    private String supplierName = "";
    /**
     * 供货明细提供量
     */
    private BigDecimal suppQty;
    /**
     * 供货明细 发货量
     */
    private BigDecimal sendQty;
    /**
     * 供货明细 收货数量
     */
    private BigDecimal receiveQty;
    /**
     * 取消数量
     */
    private BigDecimal cancelQty;
    /**
     * 退货数量
     */
    private BigDecimal returnQty;
    /**
     * 拒收数量
     */
    private BigDecimal rejectionQty;
    /**
     * 发货时间
     */
    private String sendTime;
    /**
     * 收货时间
     */
    private String receivedTime;
    /**
     * 供货明细状态
     */
    private String status = "";

    public Long getOrderDetailAvailabilityId() {
        return orderDetailAvailabilityId;
    }

    public void setOrderDetailAvailabilityId(Long orderDetailAvailabilityId) {
        this.orderDetailAvailabilityId = orderDetailAvailabilityId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public BigDecimal getCancelQty() {
        return cancelQty;
    }

    public void setCancelQty(BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    public void setRejectionQty(BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }
}
