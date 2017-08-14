package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ISO151801_迟收退货数据接收接口
 * Created by chu_jian on 2016/8/2.
 */
public class ISO151801RestProductParam extends BaseParam {
    private Integer shipDetailId;
    private String supplierCode;
    private String skuCode;
    private BigDecimal returnQty;
    private Integer detailReasonID;
    private String detailReasonName;
    /**
     * 配送单号
     */
    private String deliverCode;

    /**
     * 发货单号
     */
    private Long shipId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 迟收再发送日期(YYYY-MM-DD)
     */
    private Date receiptDate;

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Integer shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
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
}
