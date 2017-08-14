package com.msk.order.bean.result;


import java.io.Serializable;
import java.util.List;

/**
 * ISO151402_打印查询订单详细信息PDF接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151402RestResult implements Serializable {
    private Long orderId;
    private String orderCode = "";
    private String orderViceCode = "";
    private String proId = "";
    private String proCode = "";
    private String requireOrderCode = "";
    private String confirmOrderCode = "";
    private String deliveryOrderCode = "";
    private String sellerCode = "";
    private String sellerName = "";
    private String buyersId = "";
    private String buyersCode = "";
    private String buyersName = "";
    private String buyersType = "";
    private String needInvoice = "";
    private String districtCode = "";
    private String orderType = "";
    private String orderTime = "";
    private String orderAmount;
    private String paymentType = "";
    private String sellers = "";
    private String orderTaker = "";
    private String selfDeliveryFlg = "";
    private String splitDeliveryFlg = "";
    private String dustbinFlg = "";
    private String returnFlg = "";
    private String orderSendTime = "";
    private String orderReceiveTime = "";
    private String cancelType = "";
    private String cancelReason = "";
    private  String orderSource="";
    private String orderStatus = "";
    private ISO151402RestReceiveInfoResult receiveInfo;//买家 （收货人）信息
    private ISO151402RestDeliveryReqResult deliveryReq;// 配送要求信息
    private List<ISO151402RestDetailInfoResult> detailInfo;// 分批订单明细
    private  List<ISO151402RestActualDeliveryReqResult>actualDeliveryReqResultList;// 实际配送信息
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

    public String getOrderViceCode() {
        return orderViceCode;
    }

    public void setOrderViceCode(String orderViceCode) {
        this.orderViceCode = orderViceCode;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getRequireOrderCode() {
        return requireOrderCode;
    }

    public void setRequireOrderCode(String requireOrderCode) {
        this.requireOrderCode = requireOrderCode;
    }

    public String getConfirmOrderCode() {
        return confirmOrderCode;
    }

    public void setConfirmOrderCode(String confirmOrderCode) {
        this.confirmOrderCode = confirmOrderCode;
    }

    public String getDeliveryOrderCode() {
        return deliveryOrderCode;
    }

    public void setDeliveryOrderCode(String deliveryOrderCode) {
        this.deliveryOrderCode = deliveryOrderCode;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public String getBuyersCode() {
        return buyersCode;
    }

    public void setBuyersCode(String buyersCode) {
        this.buyersCode = buyersCode;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(String buyersType) {
        this.buyersType = buyersType;
    }

    public String getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(String needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    public String getSelfDeliveryFlg() {
        return selfDeliveryFlg;
    }

    public void setSelfDeliveryFlg(String selfDeliveryFlg) {
        this.selfDeliveryFlg = selfDeliveryFlg;
    }

    public String getSplitDeliveryFlg() {
        return splitDeliveryFlg;
    }

    public void setSplitDeliveryFlg(String splitDeliveryFlg) {
        this.splitDeliveryFlg = splitDeliveryFlg;
    }

    public String getDustbinFlg() {
        return dustbinFlg;
    }

    public void setDustbinFlg(String dustbinFlg) {
        this.dustbinFlg = dustbinFlg;
    }

    public String getReturnFlg() {
        return returnFlg;
    }

    public void setReturnFlg(String returnFlg) {
        this.returnFlg = returnFlg;
    }

    public String getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(String orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public String getOrderReceiveTime() {
        return orderReceiveTime;
    }

    public void setOrderReceiveTime(String orderReceiveTime) {
        this.orderReceiveTime = orderReceiveTime;
    }

    public String getCancelType() {
        return cancelType;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ISO151402RestReceiveInfoResult getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(ISO151402RestReceiveInfoResult receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public ISO151402RestDeliveryReqResult getDeliveryReq() {
        return deliveryReq;
    }

    public void setDeliveryReq(ISO151402RestDeliveryReqResult deliveryReq) {
        this.deliveryReq = deliveryReq;
    }

    public List<ISO151402RestDetailInfoResult> getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(List<ISO151402RestDetailInfoResult> detailInfo) {
        this.detailInfo = detailInfo;
    }

    public List<ISO151402RestActualDeliveryReqResult> getActualDeliveryReqResultList() {
        return actualDeliveryReqResultList;
    }

    public void setActualDeliveryReqResultList(List<ISO151402RestActualDeliveryReqResult> actualDeliveryReqResultList) {
        this.actualDeliveryReqResultList = actualDeliveryReqResultList;
    }
}
