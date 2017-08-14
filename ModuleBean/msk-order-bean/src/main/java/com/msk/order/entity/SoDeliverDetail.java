/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>表so_deliver_detail对应的SoDeliverDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoDeliverDetail extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 配送单明细ID */
    @Id
    private Long deliverDetailId;
    /** 配送单ID */
    private Long deliverId;
    /** 配送单编码 */
    private String deliverCode;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 分批订单ID */
    private Long subOrderId;
    /** 分批订单编码 */
    private String subOrderCode;
    /** 订单明细ID */
    private Long orderDetailId;
    /** 订单明细发货ID */
    private Long shipDetailId;
    /** 供应商编码 */
    private String supplierCode;
    /** 生产商编码 */
    private String manufactureCode;
    /** SKU编码，库存产品Code, */
    private String skuCode;
    /** 产品编码 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 配送数量 */
    private java.math.BigDecimal deliverQty;
    /** 收货数量 */
    private java.math.BigDecimal receiveQty;
    /**
     * <p>默认构造函数。</p>
     */
    public SoDeliverDetail() {

    }

    /**
     * <p>配送单明细ID。</p>
     *
     * @return the 配送单明细ID
     */
    public Long getDeliverDetailId() {
        return deliverDetailId;
    }

    /**
     * <p>配送单明细ID。</p>
     *
     * @param deliverDetailId 配送单明细ID。
     */
    public void setDeliverDetailId(Long deliverDetailId) {
        this.deliverDetailId = deliverDetailId;
    }

    /**
     * <p>配送单ID。</p>
     *
     * @return the 配送单ID
     */
    public Long getDeliverId() {
        return deliverId;
    }

    /**
     * <p>配送单ID。</p>
     *
     * @param deliverId 配送单ID。
     */
    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    /**
     * <p>配送单编码。</p>
     *
     * @return the 配送单编码
     */
    public String getDeliverCode() {
        return deliverCode;
    }

    /**
     * <p>配送单编码。</p>
     *
     * @param deliverCode 配送单编码。
     */
    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
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
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
     * <p>分批订单编码。</p>
     *
     * @return the 分批订单编码
     */
    public String getSubOrderCode() {
        return subOrderCode;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @param subOrderCode 分批订单编码。
     */
    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
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
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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
     * <p>SKU编码，库存产品Code,。</p>
     *
     * @return the SKU编码，库存产品Code,
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * <p>SKU编码，库存产品Code,。</p>
     *
     * @param skuCode SKU编码，库存产品Code,。
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
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
     * <p>配送数量。</p>
     *
     * @return the 配送数量
     */
    public java.math.BigDecimal getDeliverQty() {
        return deliverQty;
    }

    /**
     * <p>配送数量。</p>
     *
     * @param deliverQty 配送数量。
     */
    public void setDeliverQty(java.math.BigDecimal deliverQty) {
        this.deliverQty = deliverQty;
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

}
