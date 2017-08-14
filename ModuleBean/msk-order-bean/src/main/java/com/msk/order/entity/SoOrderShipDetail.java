/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <p>表so_order_ship_detail对应的SoOrderShipDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoOrderShipDetail extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 订单明细发货ID */
    @Id
    private Long shipDetailId;
    /** 发货单ID */
    private Long shipId;
    /** 订单ID */
    private Long orderId;
    /** 订单明细ID */
    private Long orderDetailId;
    /** 分批订单ID */
    private Long subOrderId;
    /** 分批订单明细ID */
    private Long subOrderDetailId;
    /** 记录原始传递过来的供应商编码，仅用于记录 */
    private String sourceSupplierCode;
    /** 供应商编码，实际要供货的供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 生产商编码 */
    private String manufactureCode;
    /** 产品编号 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** SKU编码，分单时需要分解到SKU */
    private String skuCode;
    /** 采购入库批次，原始的采购入库批次 */
    private String purchaseBatch;
    /** 入库批次，可能是采购入库也可能是其他业务如退货的入库批次 */
    private String inboundBatch;
    /** 供应数量 */
    private java.math.BigDecimal suppQty;
    /** 发货数量 */
    private java.math.BigDecimal sendQty;
    /** 收货数量 */
    private java.math.BigDecimal receiveQty;
    /** 取消数量 */
    private java.math.BigDecimal cancelQty;
    /** 退货数量 */
    private java.math.BigDecimal returnQty;
    /** 拒收数量 */
    private java.math.BigDecimal rejectionQty;
    /** 发货时间 */
    private java.util.Date sendTime;
    /** 收货时间 */
    private java.util.Date receivedTime;
    /** 供货明细状态 */
    private Integer detailStatus;

    @ManyToOne
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "subOrderDetailId", insertable = false, updatable = false)
    private SoSubOrderDetail soSubOrderDetail;

    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderShipDetail() {

    }

    /**
     * <p>订单明细发货ID。</p>
     *
     * @return the 订单明细发货ID
     */
    public Long getShipDetailId() {
        return shipDetailId;
    }

    /**
     * <p>订单明细发货ID。</p>
     *
     * @param shipDetailId 订单明细发货ID。
     */
    public void setShipDetailId(Long shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    /**
     * <p>发货单ID。</p>
     *
     * @return the 发货单ID
     */
    public Long getShipId() {
        return shipId;
    }

    /**
     * <p>发货单ID。</p>
     *
     * @param shipId 发货单ID。
     */
    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @return the 订单明细ID
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @param orderDetailId 订单明细ID。
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public Long getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    /**
     * <p>分批订单明细ID。</p>
     *
     * @return the 分批订单明细ID
     */
    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    /**
     * <p>分批订单明细ID。</p>
     *
     * @param subOrderDetailId 分批订单明细ID。
     */
    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
    }

    /**
     * <p>记录原始传递过来的供应商编码，仅用于记录。</p>
     *
     * @return the 记录原始传递过来的供应商编码，仅用于记录
     */
    public String getSourceSupplierCode() {
        return sourceSupplierCode;
    }

    /**
     * <p>记录原始传递过来的供应商编码，仅用于记录。</p>
     *
     * @param sourceSupplierCode 记录原始传递过来的供应商编码，仅用于记录。
     */
    public void setSourceSupplierCode(String sourceSupplierCode) {
        this.sourceSupplierCode = sourceSupplierCode;
    }

    /**
     * <p>供应商编码，实际要供货的供应商编码。</p>
     *
     * @return the 供应商编码，实际要供货的供应商编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码，实际要供货的供应商编码。</p>
     *
     * @param supplierCode 供应商编码，实际要供货的供应商编码。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @return the 供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @param supplierName 供应商名称。
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @return the 生产商编码
     */
    public String getManufactureCode() {
        return manufactureCode;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @param manufactureCode 生产商编码。
     */
    public void setManufactureCode(String manufactureCode) {
        this.manufactureCode = manufactureCode;
    }

    /**
     * <p>产品编号。</p>
     *
     * @return the 产品编号
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编号。</p>
     *
     * @param pdCode 产品编号。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>SKU编码，分单时需要分解到SKU。</p>
     *
     * @return the SKU编码，分单时需要分解到SKU
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * <p>SKU编码，分单时需要分解到SKU。</p>
     *
     * @param skuCode SKU编码，分单时需要分解到SKU。
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * <p>采购入库批次，原始的采购入库批次。</p>
     *
     * @return the 采购入库批次，原始的采购入库批次
     */
    public String getPurchaseBatch() {
        return purchaseBatch;
    }

    /**
     * <p>采购入库批次，原始的采购入库批次。</p>
     *
     * @param purchaseBatch 采购入库批次，原始的采购入库批次。
     */
    public void setPurchaseBatch(String purchaseBatch) {
        this.purchaseBatch = purchaseBatch;
    }

    /**
     * <p>入库批次，可能是采购入库也可能是其他业务如退货的入库批次。</p>
     *
     * @return the 入库批次，可能是采购入库也可能是其他业务如退货的入库批次
     */
    public String getInboundBatch() {
        return inboundBatch;
    }

    /**
     * <p>入库批次，可能是采购入库也可能是其他业务如退货的入库批次。</p>
     *
     * @param inboundBatch 入库批次，可能是采购入库也可能是其他业务如退货的入库批次。
     */
    public void setInboundBatch(String inboundBatch) {
        this.inboundBatch = inboundBatch;
    }

    /**
     * <p>供应数量。</p>
     *
     * @return the 供应数量
     */
    public java.math.BigDecimal getSuppQty() {
        return suppQty;
    }

    /**
     * <p>供应数量。</p>
     *
     * @param suppQty 供应数量。
     */
    public void setSuppQty(java.math.BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @return the 发货数量
     */
    public java.math.BigDecimal getSendQty() {
        return sendQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @param sendQty 发货数量。
     */
    public void setSendQty(java.math.BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @return the 收货数量
     */
    public java.math.BigDecimal getReceiveQty() {
        return receiveQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @param receiveQty 收货数量。
     */
    public void setReceiveQty(java.math.BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @return the 取消数量
     */
    public java.math.BigDecimal getCancelQty() {
        return cancelQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @param cancelQty 取消数量。
     */
    public void setCancelQty(java.math.BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @return the 退货数量
     */
    public java.math.BigDecimal getReturnQty() {
        return returnQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @param returnQty 退货数量。
     */
    public void setReturnQty(java.math.BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @return the 拒收数量
     */
    public java.math.BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @param rejectionQty 拒收数量。
     */
    public void setRejectionQty(java.math.BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    /**
     * <p>发货时间。</p>
     *
     * @return the 发货时间
     */
    public java.util.Date getSendTime() {
        return sendTime;
    }

    /**
     * <p>发货时间。</p>
     *
     * @param sendTime 发货时间。
     */
    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @return the 收货时间
     */
    public java.util.Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @param receivedTime 收货时间。
     */
    public void setReceivedTime(java.util.Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * <p>供货明细状态。</p>
     *
     * @return the 供货明细状态
     */
    public Integer getDetailStatus() {
        return detailStatus;
    }

    /**
     * <p>供货明细状态。</p>
     *
     * @param detailStatus 供货明细状态。
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public SoSubOrderDetail getSoSubOrderDetail() {
        return soSubOrderDetail;
    }

    public void setSoSubOrderDetail(SoSubOrderDetail soSubOrderDetail) {
        this.soSubOrderDetail = soSubOrderDetail;
    }
}
