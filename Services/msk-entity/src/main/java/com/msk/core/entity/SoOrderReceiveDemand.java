/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_order_receive_demand对应的SoOrderReceiveDemand。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoOrderReceiveDemand extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 收货要求ID */
    private Long demandId;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 收货人名称 */
    private String receiverName;
    /** 收货人电话 */
    private String receiverTel;
    /** 收货人QQ */
    private String receiverQq;
    /** 收货人微信 */
    private String receiverWeixin;
    /** 收货人邮箱 */
    private String receiverMail;
    /** 收货地址省 */
    private String receiverProvince;
    /** 收货地址市 */
    private String receiverCity;
    /** 收货地址区 */
    private String receiverDistrict;
    /** 收货地址详细地址 */
    private String receiverAddr;
    /** 辅助地址项目 */
    private String receiverAddr2;
    /** 收货人详细地址管理Key */
    private String receiverAddrKey;
    /** 习惯正常收货时间段 */
    private String receiveTime;
    /** 习惯收货最早时间要求 */
    private String receiveEarliest;
    /** 习惯收货最晚时间要求 */
    private String receiveLast;
    /** 配送方式 */
    private Integer deliveryType;
    /** 预计发货时间 */
    private java.util.Date forecastSendTime;
    /** 预计到货时间 */
    private java.util.Date forecastReceiveTime;
    /** 收货等待时间 */
    private String receiveWaitTime;
    /** 提前通知时间 */
    private String advanceNoticeTime;
    /** 动检证要求 */
    private String vicFlg;
    /** 装卸要求 */
    private String unloadReq;
    /** 包装要求 */
    private String packingReq;
    /** 距离门店最近停车距离(米) */
    private Integer parkingDistance;
    /** 其它配送服务要求 */
    private String otherDeliveryReq;
    /** 本次配送服务要求 */
    private String thisDeliveryReq;
    /** 备注 */
    private String remark;
    /** 备注2 */
    private String remark2;
    /** 备注3 */
    private String remark3;
    /** 备注4 */
    private String remark4;
    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderReceiveDemand() {

    }

    /**
     * <p>收货要求ID。</p>
     *
     * @return the 收货要求ID
     */
    public Long getDemandId() {
        return demandId;
    }

    /**
     * <p>收货要求ID。</p>
     *
     * @param demandId 收货要求ID。
     */
    public void setDemandId(Long demandId) {
        this.demandId = demandId;
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
     * <p>习惯正常收货时间段。</p>
     *
     * @return the 习惯正常收货时间段
     */
    public String getReceiveTime() {
        return receiveTime;
    }

    /**
     * <p>习惯正常收货时间段。</p>
     *
     * @param receiveTime 习惯正常收货时间段。
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
     * <p>收货等待时间。</p>
     *
     * @return the 收货等待时间
     */
    public String getReceiveWaitTime() {
        return receiveWaitTime;
    }

    /**
     * <p>收货等待时间。</p>
     *
     * @param receiveWaitTime 收货等待时间。
     */
    public void setReceiveWaitTime(String receiveWaitTime) {
        this.receiveWaitTime = receiveWaitTime;
    }

    /**
     * <p>提前通知时间。</p>
     *
     * @return the 提前通知时间
     */
    public String getAdvanceNoticeTime() {
        return advanceNoticeTime;
    }

    /**
     * <p>提前通知时间。</p>
     *
     * @param advanceNoticeTime 提前通知时间。
     */
    public void setAdvanceNoticeTime(String advanceNoticeTime) {
        this.advanceNoticeTime = advanceNoticeTime;
    }

    /**
     * <p>动检证要求。</p>
     *
     * @return the 动检证要求
     */
    public String getVicFlg() {
        return vicFlg;
    }

    /**
     * <p>动检证要求。</p>
     *
     * @param vicFlg 动检证要求。
     */
    public void setVicFlg(String vicFlg) {
        this.vicFlg = vicFlg;
    }

    /**
     * <p>装卸要求。</p>
     *
     * @return the 装卸要求
     */
    public String getUnloadReq() {
        return unloadReq;
    }

    /**
     * <p>装卸要求。</p>
     *
     * @param unloadReq 装卸要求。
     */
    public void setUnloadReq(String unloadReq) {
        this.unloadReq = unloadReq;
    }

    /**
     * <p>包装要求。</p>
     *
     * @return the 包装要求
     */
    public String getPackingReq() {
        return packingReq;
    }

    /**
     * <p>包装要求。</p>
     *
     * @param packingReq 包装要求。
     */
    public void setPackingReq(String packingReq) {
        this.packingReq = packingReq;
    }

    /**
     * <p>距离门店最近停车距离(米)。</p>
     *
     * @return the 距离门店最近停车距离(米)
     */
    public Integer getParkingDistance() {
        return parkingDistance;
    }

    /**
     * <p>距离门店最近停车距离(米)。</p>
     *
     * @param parkingDistance 距离门店最近停车距离(米)。
     */
    public void setParkingDistance(Integer parkingDistance) {
        this.parkingDistance = parkingDistance;
    }

    /**
     * <p>其它配送服务要求。</p>
     *
     * @return the 其它配送服务要求
     */
    public String getOtherDeliveryReq() {
        return otherDeliveryReq;
    }

    /**
     * <p>其它配送服务要求。</p>
     *
     * @param otherDeliveryReq 其它配送服务要求。
     */
    public void setOtherDeliveryReq(String otherDeliveryReq) {
        this.otherDeliveryReq = otherDeliveryReq;
    }

    /**
     * <p>本次配送服务要求。</p>
     *
     * @return the 本次配送服务要求
     */
    public String getThisDeliveryReq() {
        return thisDeliveryReq;
    }

    /**
     * <p>本次配送服务要求。</p>
     *
     * @param thisDeliveryReq 本次配送服务要求。
     */
    public void setThisDeliveryReq(String thisDeliveryReq) {
        this.thisDeliveryReq = thisDeliveryReq;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>备注2。</p>
     *
     * @return the 备注2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * <p>备注2。</p>
     *
     * @param remark2 备注2。
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * <p>备注3。</p>
     *
     * @return the 备注3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * <p>备注3。</p>
     *
     * @param remark3 备注3。
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * <p>备注4。</p>
     *
     * @return the 备注4
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * <p>备注4。</p>
     *
     * @param remark4 备注4。
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

}
