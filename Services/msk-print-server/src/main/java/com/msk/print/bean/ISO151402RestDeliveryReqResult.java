package com.msk.print.bean;


import java.io.Serializable;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestDeliveryReqResult implements Serializable {
    /**
     *习惯正常收货时间段，
     */
    private String receiveTime = "";
    /**
     *习惯收货最早时间要求
     */
    private String receiveEarliest = "";
    /**
     *习惯收货最晚时间要求
     */
    private String receiveLast = "";
    /**
     *配送方式
     */
    private String deliveryType = "";
    /**
     *预计发货时间
     */
    private String forecastSendTime = "";
    /**
     *预计到货时间
     */
    private String forecastReceiveTime = "";
    /**
     *收货等待时间
     */
    private String receiveWaitTime = "";
    /**
     *提前通知时间
     */
    private String advanceNoticeTime = "";
    /**
     *动检证要求
     */
    private String vicFlg = "";
    /**
     *装卸要求
     */
    private String unloadReq = "";
    /**
     *包装要求
     */
    private String packingReq = "";
    /**
     *距离门店最近停车距离(米)
     */
    private String parkingDistance = "";
    /**
     *其它配送服务要求
     */
    private String otherDeliveryReq = "";
    /**
     *本次配送服务要求
     */
    private String thisDeliveryReq = "";

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveEarliest() {
        return receiveEarliest;
    }

    public void setReceiveEarliest(String receiveEarliest) {
        this.receiveEarliest = receiveEarliest;
    }

    public String getReceiveLast() {
        return receiveLast;
    }

    public void setReceiveLast(String receiveLast) {
        this.receiveLast = receiveLast;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getForecastSendTime() {
        return forecastSendTime;
    }

    public void setForecastSendTime(String forecastSendTime) {
        this.forecastSendTime = forecastSendTime;
    }

    public String getForecastReceiveTime() {
        return forecastReceiveTime;
    }

    public void setForecastReceiveTime(String forecastReceiveTime) {
        this.forecastReceiveTime = forecastReceiveTime;
    }

    public String getReceiveWaitTime() {
        return receiveWaitTime;
    }

    public void setReceiveWaitTime(String receiveWaitTime) {
        this.receiveWaitTime = receiveWaitTime;
    }

    public String getAdvanceNoticeTime() {
        return advanceNoticeTime;
    }

    public void setAdvanceNoticeTime(String advanceNoticeTime) {
        this.advanceNoticeTime = advanceNoticeTime;
    }

    public void setParkingDistance(String parkingDistance) {
        this.parkingDistance = parkingDistance;
    }

    public String getVicFlg() {
        return vicFlg;
    }

    public void setVicFlg(String vicFlg) {
        this.vicFlg = vicFlg;
    }

    public String getUnloadReq() {
        return unloadReq;
    }

    public void setUnloadReq(String unloadReq) {
        this.unloadReq = unloadReq;
    }

    public String getPackingReq() {
        return packingReq;
    }

    public void setPackingReq(String packingReq) {
        this.packingReq = packingReq;
    }

    public String getParkingDistance() {
        return parkingDistance;
    }

    public String getOtherDeliveryReq() {
        return otherDeliveryReq;
    }

    public void setOtherDeliveryReq(String otherDeliveryReq) {
        this.otherDeliveryReq = otherDeliveryReq;
    }

    public String getThisDeliveryReq() {
        return thisDeliveryReq;
    }

    public void setThisDeliveryReq(String thisDeliveryReq) {
        this.thisDeliveryReq = thisDeliveryReq;
    }
}
