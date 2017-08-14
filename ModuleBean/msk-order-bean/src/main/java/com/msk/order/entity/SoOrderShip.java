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
import javax.persistence.OneToMany;
import java.util.List;

/**
 * <p>表so_order_ship对应的SoOrderShip。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoOrderShip extends BaseEntity {
    /**
     *
     */
    @OneToMany
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "shipId")
    private List<SoOrderShipDetail> soOrderShipDetailList;

    /**
     *
     */
    @OneToMany
    @Where(clause = "del_flg='0'")
    @JoinColumn(name = "shipId")
    private List<SoDeliver> soDeliverList;

    private static final long serialVersionUID = 1L;
    /**
     * 发货单ID
     */
    @Id
    private Long shipId;
    /**
     * 发货单号
     */
    private String shipCode;
    /**
     * 分批订单ID
     */
    private Long subOrderId;
    /**
     * 分批订单编码
     */
    private String subOrderCode;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单编码
     */
    private String orderCode;
    /**
     * 预计发货时间
     */
    private java.util.Date forecastSendTime;
    /**
     * 预计到货时间
     */
    private java.util.Date forecastReceiveTime;
    /**
     * 1:在线支付,2:线下支付
     */
    private Integer paymentType;
    /**
     * 收货人名称
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverTel;
    /**
     * 收货人QQ
     */
    private String receiverQq;
    /**
     * 收货人微信
     */
    private String receiverWeixin;
    /**
     * 收货人邮箱
     */
    private String receiverMail;
    /**
     * 收货地址省
     */
    private String receiverProvince;
    /**
     * 收货地址市
     */
    private String receiverCity;
    /**
     * 收货地址区
     */
    private String receiverDistrict;
    /**
     * 收货地址详细地址
     */
    private String receiverAddr;
    /**
     * 辅助地址项目
     */
    private String receiverAddr2;
    /**
     * 收货人详细地址管理Key
     */
    private String receiverAddrKey;
    /**
     * 惯正常收货时间段
     */
    private String receiveTime;
    /**
     * 习惯收货最早时间要求
     */
    private String receiveEarliest;
    /**
     * 习惯收货最晚时间要求
     */
    private String receiveLast;
    /**
     * 配送方式
     */
    private Integer deliveryType;
    /**
     * 发货单状态
     */
    private Integer shipStatus;
    /**
     * 发货取消人ID
     */
    private String cancelManId;
    /**
     * 发货取消人名称
     */
    private String cancelManName;
    /**
     * 发货取消完成时间，WMS回传
     */
    private java.util.Date cancelTime;
    /**
     * 发货取消原因-CodeMaster
     */
    private Integer cancelReason;
    /**
     * 发货取消备注
     */
    private String cancelRemark;

    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderShip() {

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
     * <p>发货单号。</p>
     *
     * @return the 发货单号
     */
    public String getShipCode() {
        return shipCode;
    }

    /**
     * <p>发货单号。</p>
     *
     * @param shipCode 发货单号。
     */
    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
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
     * <p>预计发货时间。</p>
     *
     * @return the 预计发货时间
     */
    public java.util.Date getForecastSendTime() {
        return forecastSendTime;
    }

    /**
     * <p>预计发货时间。</p>
     *
     * @param forecastSendTime 预计发货时间。
     */
    public void setForecastSendTime(java.util.Date forecastSendTime) {
        this.forecastSendTime = forecastSendTime;
    }

    /**
     * <p>预计到货时间。</p>
     *
     * @return the 预计到货时间
     */
    public java.util.Date getForecastReceiveTime() {
        return forecastReceiveTime;
    }

    /**
     * <p>预计到货时间。</p>
     *
     * @param forecastReceiveTime 预计到货时间。
     */
    public void setForecastReceiveTime(java.util.Date forecastReceiveTime) {
        this.forecastReceiveTime = forecastReceiveTime;
    }

    /**
     * <p>1:在线支付,2:线下支付。</p>
     *
     * @return the 1:在线支付,2:线下支付
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * <p>1:在线支付,2:线下支付。</p>
     *
     * @param paymentType 1:在线支付,2:线下支付。
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * <p>收货人名称。</p>
     *
     * @return the 收货人名称
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * <p>收货人名称。</p>
     *
     * @param receiverName 收货人名称。
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * <p>收货人电话。</p>
     *
     * @return the 收货人电话
     */
    public String getReceiverTel() {
        return receiverTel;
    }

    /**
     * <p>收货人电话。</p>
     *
     * @param receiverTel 收货人电话。
     */
    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    /**
     * <p>收货人QQ。</p>
     *
     * @return the 收货人QQ
     */
    public String getReceiverQq() {
        return receiverQq;
    }

    /**
     * <p>收货人QQ。</p>
     *
     * @param receiverQq 收货人QQ。
     */
    public void setReceiverQq(String receiverQq) {
        this.receiverQq = receiverQq;
    }

    /**
     * <p>收货人微信。</p>
     *
     * @return the 收货人微信
     */
    public String getReceiverWeixin() {
        return receiverWeixin;
    }

    /**
     * <p>收货人微信。</p>
     *
     * @param receiverWeixin 收货人微信。
     */
    public void setReceiverWeixin(String receiverWeixin) {
        this.receiverWeixin = receiverWeixin;
    }

    /**
     * <p>收货人邮箱。</p>
     *
     * @return the 收货人邮箱
     */
    public String getReceiverMail() {
        return receiverMail;
    }

    /**
     * <p>收货人邮箱。</p>
     *
     * @param receiverMail 收货人邮箱。
     */
    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
    }

    /**
     * <p>收货地址省。</p>
     *
     * @return the 收货地址省
     */
    public String getReceiverProvince() {
        return receiverProvince;
    }

    /**
     * <p>收货地址省。</p>
     *
     * @param receiverProvince 收货地址省。
     */
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    /**
     * <p>收货地址市。</p>
     *
     * @return the 收货地址市
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * <p>收货地址市。</p>
     *
     * @param receiverCity 收货地址市。
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    /**
     * <p>收货地址区。</p>
     *
     * @return the 收货地址区
     */
    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    /**
     * <p>收货地址区。</p>
     *
     * @param receiverDistrict 收货地址区。
     */
    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    /**
     * <p>收货地址详细地址。</p>
     *
     * @return the 收货地址详细地址
     */
    public String getReceiverAddr() {
        return receiverAddr;
    }

    /**
     * <p>收货地址详细地址。</p>
     *
     * @param receiverAddr 收货地址详细地址。
     */
    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    /**
     * <p>辅助地址项目。</p>
     *
     * @return the 辅助地址项目
     */
    public String getReceiverAddr2() {
        return receiverAddr2;
    }

    /**
     * <p>辅助地址项目。</p>
     *
     * @param receiverAddr2 辅助地址项目。
     */
    public void setReceiverAddr2(String receiverAddr2) {
        this.receiverAddr2 = receiverAddr2;
    }

    /**
     * <p>收货人详细地址管理Key。</p>
     *
     * @return the 收货人详细地址管理Key
     */
    public String getReceiverAddrKey() {
        return receiverAddrKey;
    }

    /**
     * <p>收货人详细地址管理Key。</p>
     *
     * @param receiverAddrKey 收货人详细地址管理Key。
     */
    public void setReceiverAddrKey(String receiverAddrKey) {
        this.receiverAddrKey = receiverAddrKey;
    }

    /**
     * <p>惯正常收货时间段。</p>
     *
     * @return the 惯正常收货时间段
     */
    public String getReceiveTime() {
        return receiveTime;
    }

    /**
     * <p>惯正常收货时间段。</p>
     *
     * @param receiveTime 惯正常收货时间段。
     */
    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * <p>习惯收货最早时间要求。</p>
     *
     * @return the 习惯收货最早时间要求
     */
    public String getReceiveEarliest() {
        return receiveEarliest;
    }

    /**
     * <p>习惯收货最早时间要求。</p>
     *
     * @param receiveEarliest 习惯收货最早时间要求。
     */
    public void setReceiveEarliest(String receiveEarliest) {
        this.receiveEarliest = receiveEarliest;
    }

    /**
     * <p>习惯收货最晚时间要求。</p>
     *
     * @return the 习惯收货最晚时间要求
     */
    public String getReceiveLast() {
        return receiveLast;
    }

    /**
     * <p>习惯收货最晚时间要求。</p>
     *
     * @param receiveLast 习惯收货最晚时间要求。
     */
    public void setReceiveLast(String receiveLast) {
        this.receiveLast = receiveLast;
    }

    /**
     * <p>配送方式。</p>
     *
     * @return the 配送方式
     */
    public Integer getDeliveryType() {
        return deliveryType;
    }

    /**
     * <p>配送方式。</p>
     *
     * @param deliveryType 配送方式。
     */
    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * <p>发货单状态。</p>
     *
     * @return the 发货单状态
     */
    public Integer getShipStatus() {
        return shipStatus;
    }

    /**
     * <p>发货单状态。</p>
     *
     * @param shipStatus 发货单状态。
     */
    public void setShipStatus(Integer shipStatus) {
        this.shipStatus = shipStatus;
    }

    /**
     * <p>发货取消人ID。</p>
     *
     * @return the 发货取消人ID
     */
    public String getCancelManId() {
        return cancelManId;
    }

    /**
     * <p>发货取消人ID。</p>
     *
     * @param cancelManId 发货取消人ID。
     */
    public void setCancelManId(String cancelManId) {
        this.cancelManId = cancelManId;
    }

    /**
     * <p>发货取消人名称。</p>
     *
     * @return the 发货取消人名称
     */
    public String getCancelManName() {
        return cancelManName;
    }

    /**
     * <p>发货取消人名称。</p>
     *
     * @param cancelManName 发货取消人名称。
     */
    public void setCancelManName(String cancelManName) {
        this.cancelManName = cancelManName;
    }

    /**
     * <p>发货取消完成时间，WMS回传。</p>
     *
     * @return the 发货取消完成时间，WMS回传
     */
    public java.util.Date getCancelTime() {
        return cancelTime;
    }

    /**
     * <p>发货取消完成时间，WMS回传。</p>
     *
     * @param cancelTime 发货取消完成时间，WMS回传。
     */
    public void setCancelTime(java.util.Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * <p>发货取消原因-CodeMaster。</p>
     *
     * @return the 发货取消原因-CodeMaster
     */
    public Integer getCancelReason() {
        return cancelReason;
    }

    /**
     * <p>发货取消原因-CodeMaster。</p>
     *
     * @param cancelReason 发货取消原因-CodeMaster。
     */
    public void setCancelReason(Integer cancelReason) {
        this.cancelReason = cancelReason;
    }

    /**
     * <p>发货取消备注。</p>
     *
     * @return the 发货取消备注
     */
    public String getCancelRemark() {
        return cancelRemark;
    }

    /**
     * <p>发货取消备注。</p>
     *
     * @param cancelRemark 发货取消备注。
     */
    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }


    public List<SoOrderShipDetail> getSoOrderShipDetailList() {
        return soOrderShipDetailList;
    }

    public void setSoOrderShipDetailList(List<SoOrderShipDetail> soOrderShipDetailList) {
        this.soOrderShipDetailList = soOrderShipDetailList;
    }

    public List<SoDeliver> getSoDeliverList() {
        return soDeliverList;
    }

    public void setSoDeliverList(List<SoDeliver> soDeliverList) {
        this.soDeliverList = soDeliverList;
    }
}
