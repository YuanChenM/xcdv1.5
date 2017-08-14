package com.msk.order.bean.result;


import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_shuai on 2016/8/9.
 */
public class OrderShipProductInfo extends BaseParam {
    /** 订单id */
    private Long orderId;

    /** 订单编码 */
    private String orderCode;

    /** 订单明细ID */
    private Long orderDetailId;

    /** 明细ID，与发货XML中SHIPDETAILID一致(发货明细ID) */
    private Long shipDetailId;

    /** 供应商编码 */
    private String supplierCode;

    /** 产品编码 */
    private String pdCode;

    /** 产品名称 */
    private String pdName;

    /** 产品SKU编码 */
    private String skuCode;

    /** 发货数量 */
    private BigDecimal sendQty;

    /** 收货数量 */
    private BigDecimal receiveQty;

    /** 发货单号 */
    private Long shipId;

    /** 状态 */
    private Integer status;

    /** 迟收数量 */
    private BigDecimal returnQty;

    /** 迟收明细原因 */
    private Integer detailReasonID;

    /** 退货明细原因名称 */
    private String detailReasonName;

    /** 配送单号 */
    private String deliverCode;

    /** 迟收再发送日期(YYYY-MM-DD) */
    private Date receiptDate;

    /** 出货产品对应的入库批次*/
    private String inboundBatch;

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

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Long shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
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

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public Integer getDetailReasonID() {
        return detailReasonID;
    }

    public void setDetailReasonID(Integer detailReasonID) {
        this.detailReasonID = detailReasonID;
    }

    public String getDetailReasonName() {
        return detailReasonName;
    }

    public void setDetailReasonName(String detailReasonName) {
        this.detailReasonName = detailReasonName;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getInboundBatch() {
        return inboundBatch;
    }

    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }
}
