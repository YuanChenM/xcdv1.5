package com.msk.order.bean.result;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestAvailabilityInfoResult implements Serializable {
    private Long orderDetailAvailabilityId;
    private String supplierCode = "";
    private String supplierName = "";
    private BigDecimal suppQty;
    private BigDecimal sendQty;
    private BigDecimal receiveQty;
    private BigDecimal cancelQty;
    private BigDecimal returnQty;
    private BigDecimal rejectionQty;
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
}
