package com.msk.order.bean.param;


import com.msk.common.bean.param.BaseParam;
import com.msk.order.bean.result.SO15150801ProductStockBean;
import com.msk.order.bean.result.SO151508BuyerShopResult;
import com.msk.order.entity.SoOrder;
import com.msk.order.entity.SoOrderReceiveDemand;

import java.util.List;

/**
 * SO151508_订单新增画面参数
 * Created by wang_jianzhou on 2016/8/2.
 */
public class SO151508Param extends BaseParam {
    /** 订单信息*/
    private SoOrder order;
    /** 供应商信息 */
    private List<SO15150801ProductStockBean> orderDetailList;
    /** 订单收货要求表 */
    private SoOrderReceiveDemand orderReceiveDemand;

    private String forecastReceiveTime;

    private String forecastSendTime;
    /** 习惯收货时间多选*/
    private String newReceiveTime;
    /** 习惯最早收货时间多选*/
    private String receiveEarliest;
    /** 习惯最晚收货时间多选*/
    private String receiveLast;

    private String buyerId;

    private String buyersId;
    /** 买手编码*/
    private String slCodeDis;
    /** 买家编码*/
    private String buyerCode;
    /** 买手ID*/
    private List<SO151508BuyerShopResult> buyershopList;

    public List<SO151508BuyerShopResult> getBuyershopList() {
        return buyershopList;
    }

    public void setBuyershopList(List<SO151508BuyerShopResult> buyershopList) {
        this.buyershopList = buyershopList;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getSlCodeDis() {
        return slCodeDis;
    }

    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    public String getNewReceiveTime() {
        return newReceiveTime;
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

    public void setNewReceiveTime(String newReceiveTime) {
        this.newReceiveTime = newReceiveTime;
    }

    public String getForecastReceiveTime() {
        return forecastReceiveTime;
    }

    public void setForecastReceiveTime(String forecastReceiveTime) {
        this.forecastReceiveTime = forecastReceiveTime;
    }

    public String getForecastSendTime() {
        return forecastSendTime;
    }

    public void setForecastSendTime(String forecastSendTime) {
        this.forecastSendTime = forecastSendTime;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<SO15150801ProductStockBean> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<SO15150801ProductStockBean> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public SoOrder getOrder() {
        return order;
    }

    public void setOrder(SoOrder order) {
        this.order = order;
    }

    public SoOrderReceiveDemand getOrderReceiveDemand() {
        return orderReceiveDemand;
    }

    public void setOrderReceiveDemand(SoOrderReceiveDemand orderReceiveDemand) {
        this.orderReceiveDemand = orderReceiveDemand;
    }
}
