package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 *
 */
public class ISO151803RestProductResult extends BaseResult{
    private Long orderDetailId;//订单明细ID
    private Long shipDetailId;//发货明细ID
    private String supplierCode;//供应商编码
    private String pdCode;//产品编码
    private String skuCode;//产品SKU编码
    private BigDecimal pdPrice;//产品单价
    private Integer suppQty;//分销供应数量
    private Integer sendQty;//发货数量
    private Integer cancelQty;//取消数量
    private Integer receiverQty;//已收货数量
    private Integer returnQty;//已退货数量，调用方可计算可退货的数量=发货数量-取消数量-已收数量-已退货数量

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

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public Integer getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(Integer suppQty) {
        this.suppQty = suppQty;
    }

    public Integer getSendQty() {
        return sendQty;
    }

    public void setSendQty(Integer sendQty) {
        this.sendQty = sendQty;
    }

    public Integer getCancelQty() {
        return cancelQty;
    }

    public void setCancelQty(Integer cancelQty) {
        this.cancelQty = cancelQty;
    }

    public Integer getReceiverQty() {
        return receiverQty;
    }

    public void setReceiverQty(Integer receiverQty) {
        this.receiverQty = receiverQty;
    }

    public Integer getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }
}
