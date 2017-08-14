package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestDeliveryReqResult implements Serializable {
    private String receiveTime = "";
    private String receiveEarliest = "";
    private String receiveLast = "";
    private String deliveryType = "";
    private String forecastSendTime = "";
    private String forecastReceiveTime = "";
    private String receiveWaitTime = "";
    private String advanceNoticeTime = "";
    private String vicFlg = "";
    private String unloadReq = "";
    private String packingReq = "";
    private String parkingDistance = "";
    private String otherDeliveryReq = "";
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
