package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_shuai on 2016/8/9.
 */
public class OrderDetailAndSuppInfo extends BaseResult {
    /** 产品编码 */
    private String pdCode;

    /** 产品名称 */
    private String pdName;

    /** 订单ID */
    private Long orderId;

    /** 订单编码 */
    private String orderCode;

    /** 订单明细ID */
    private Long orderDetailId;

    /** 明细下单数量 */
    private BigDecimal detailOrderQty;

    /** 明细发货数量 */
    private BigDecimal detailSendQty;

    /** 明细收货数量 */
    private BigDecimal detailReceiveQty;

    /** 明细取消数量 */
    private BigDecimal detailCancelQty;

    /** 明细退货数量 */
    private BigDecimal detailReturnQty;

    /** 明细拒收数量 */
    private BigDecimal detailRejectionQty;

    /** 明细状态 */
    private Integer detailStatus;

    /** 供货数量 */
    private BigDecimal suppOrderQty;

    /** 供应明细发货数量 */
    private BigDecimal suppSendQty;

    /** 供应明细收货数量 */
    private BigDecimal suppReceiveQty;

    /** 供应明细取消数量 */
    private BigDecimal suppCancelQty;

    /** 供货明细退货数量 */
    private BigDecimal suppReturnQty;

    /** 供货明细拒收数量 */
    private BigDecimal suppRejectionQty;

    /** 供货明细状态 */
    private Integer suppStatus;

    /** 发货单号 */
    private Long shipId;

    /** 发货明细单号 */
    private Long shipDetailId;

    /** 明细可发货数量 */
    private BigDecimal detailQty;

    /** 分批明细可发货数量 */
    private BigDecimal subDetailQty;

    /** 供货明细可发货数量 */
    private BigDecimal suppQty;

    /** 供货明细Id */
    private Long detailAvailabilityId;

    /** 供应商编码 */
    private String supplierCode;

    /** 订单状态 */
    private Integer orderStatus;

    /** 订单明细订单数量*/
    private BigDecimal orderQty;

    /** 分批订单明细Id*/
    private Long subOrderDetailId;

    /** 分批订单明细状态*/
    private Integer subDetailStatus;

    /** 分批订单明细下单数量 */
    private BigDecimal subDetailOrderQty;

    /** 分批订单明细发货数量 */
    private BigDecimal subDetailSendQty;

    /** 分批订单明细收货数量 */
    private BigDecimal subDetailReceiveQty;

    /** 分批订单明细取消数量 */
    private BigDecimal subDetailCancelQty;

    /** 分批订单明细退货数量 */
    private BigDecimal subDetailReturnQty;

    /** 分批订单明细拒收数量 */
    private BigDecimal subDetailRejectionQty;

    //收货时间
    private Date receivedTime;

    //发货时间
    private Date sendTime;

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

    public BigDecimal getDetailOrderQty() {
        return detailOrderQty;
    }

    public void setDetailOrderQty(BigDecimal detailOrderQty) {
        this.detailOrderQty = detailOrderQty;
    }

    public BigDecimal getDetailSendQty() {
        return detailSendQty;
    }

    public void setDetailSendQty(BigDecimal detailSendQty) {
        this.detailSendQty = detailSendQty;
    }

    public BigDecimal getDetailReceiveQty() {
        return detailReceiveQty;
    }

    public void setDetailReceiveQty(BigDecimal detailReceiveQty) {
        this.detailReceiveQty = detailReceiveQty;
    }

    public BigDecimal getDetailCancelQty() {
        return detailCancelQty;
    }

    public void setDetailCancelQty(BigDecimal detailCancelQty) {
        this.detailCancelQty = detailCancelQty;
    }

    public BigDecimal getDetailReturnQty() {
        return detailReturnQty;
    }

    public void setDetailReturnQty(BigDecimal detailReturnQty) {
        this.detailReturnQty = detailReturnQty;
    }

    public BigDecimal getDetailRejectionQty() {
        return detailRejectionQty;
    }

    public void setDetailRejectionQty(BigDecimal detailRejectionQty) {
        this.detailRejectionQty = detailRejectionQty;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public BigDecimal getSuppOrderQty() {
        return suppOrderQty;
    }

    public void setSuppOrderQty(BigDecimal suppOrderQty) {
        this.suppOrderQty = suppOrderQty;
    }

    public BigDecimal getSuppSendQty() {
        return suppSendQty;
    }

    public void setSuppSendQty(BigDecimal suppSendQty) {
        this.suppSendQty = suppSendQty;
    }

    public BigDecimal getSuppReceiveQty() {
        return suppReceiveQty;
    }

    public void setSuppReceiveQty(BigDecimal suppReceiveQty) {
        this.suppReceiveQty = suppReceiveQty;
    }

    public BigDecimal getSuppCancelQty() {
        return suppCancelQty;
    }

    public void setSuppCancelQty(BigDecimal suppCancelQty) {
        this.suppCancelQty = suppCancelQty;
    }

    public BigDecimal getSuppReturnQty() {
        return suppReturnQty;
    }

    public void setSuppReturnQty(BigDecimal suppReturnQty) {
        this.suppReturnQty = suppReturnQty;
    }

    public BigDecimal getSuppRejectionQty() {
        return suppRejectionQty;
    }

    public void setSuppRejectionQty(BigDecimal suppRejectionQty) {
        this.suppRejectionQty = suppRejectionQty;
    }

    public Integer getSuppStatus() {
        return suppStatus;
    }

    public void setSuppStatus(Integer suppStatus) {
        this.suppStatus = suppStatus;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public BigDecimal getDetailQty() {
        return detailQty;
    }

    public void setDetailQty(BigDecimal detailQty) {
        this.detailQty = detailQty;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public Long getDetailAvailabilityId() {
        return detailAvailabilityId;
    }

    public void setDetailAvailabilityId(Long detailAvailabilityId) {
        this.detailAvailabilityId = detailAvailabilityId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public Long getShipDetailId() {
        return shipDetailId;
    }

    public void setShipDetailId(Long shipDetailId) {
        this.shipDetailId = shipDetailId;
    }

    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
    }

    public Integer getSubDetailStatus() {
        return subDetailStatus;
    }

    public void setSubDetailStatus(Integer subDetailStatus) {
        this.subDetailStatus = subDetailStatus;
    }

    public BigDecimal getSubDetailOrderQty() {
        return subDetailOrderQty;
    }

    public void setSubDetailOrderQty(BigDecimal subDetailOrderQty) {
        this.subDetailOrderQty = subDetailOrderQty;
    }

    public BigDecimal getSubDetailSendQty() {
        return subDetailSendQty;
    }

    public void setSubDetailSendQty(BigDecimal subDetailSendQty) {
        this.subDetailSendQty = subDetailSendQty;
    }

    public BigDecimal getSubDetailReceiveQty() {
        return subDetailReceiveQty;
    }

    public void setSubDetailReceiveQty(BigDecimal subDetailReceiveQty) {
        this.subDetailReceiveQty = subDetailReceiveQty;
    }

    public BigDecimal getSubDetailCancelQty() {
        return subDetailCancelQty;
    }

    public void setSubDetailCancelQty(BigDecimal subDetailCancelQty) {
        this.subDetailCancelQty = subDetailCancelQty;
    }

    public BigDecimal getSubDetailReturnQty() {
        return subDetailReturnQty;
    }

    public void setSubDetailReturnQty(BigDecimal subDetailReturnQty) {
        this.subDetailReturnQty = subDetailReturnQty;
    }

    public BigDecimal getSubDetailRejectionQty() {
        return subDetailRejectionQty;
    }

    public void setSubDetailRejectionQty(BigDecimal subDetailRejectionQty) {
        this.subDetailRejectionQty = subDetailRejectionQty;
    }

    public BigDecimal getSubDetailQty() {
        return subDetailQty;
    }

    public void setSubDetailQty(BigDecimal subDetailQty) {
        this.subDetailQty = subDetailQty;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
