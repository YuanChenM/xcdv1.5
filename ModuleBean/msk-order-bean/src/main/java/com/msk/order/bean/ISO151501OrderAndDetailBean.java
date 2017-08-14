package com.msk.order.bean;


import com.msk.common.entity.BaseEntity;

/**
 * Created by zhang_qiang1 on 2016/6/20.
 */

public class ISO151501OrderAndDetailBean extends BaseEntity {

    private String orderStatus;// 订单状态

    private String orderType;// 订单类型

    private String buyerType;// 判断  是分销  还是 菜场

    private String orderID;// 订单id

    private String subOrderID;// 订单id

    private String orderCode;// 订单编码

    private String orderDate;// 下订单日期

    private String orderTime;// 下单时间

    private String buyerName;// 买家名称

    private String buyerMarketName;//市场

    private String buyerId;//  买家id

    private String receiverAddr;//收货地址

    private String receiverTel;//电话

    private String saName;// 冻品管家

    private String orderAmount;// 订单总金额

    private String orderDetailId;// 订单明细id

    private String classesCode;// 产品类型

    private String classesName;// 产品类型名称

    private String breedCode;// 产品品种


    private String breedName;//产品品种名称


    private String featureCode;// 特征编码


    private String featureName;// 特征编码名称


    private String normsName;// 产品包装名称


    private String pdCode;// 产品编码


    private String pdName;// 产品名称


    private String pdGradeName;// 产品等级名称


    private double price;// 产品价格


    private String orderQty;//订单量


    private String orderDetailAmount;//订单明细金额


    private String supplierName;// 供应商名称


    private String suppQty;// 供应量


    private String sendQty;// 发货量


    private String receiveQty;//  收货量


    private String cancelQty;//  取消数量


    private String rejectionQty;//  拒收数量


    private String returnQty;// 退货数量


    private String sendTime;//发货时间


    private String receivedTime;//收货时间

    private String buyerCode; //买家编码


    private String subOrderId;// 分批订单id

    public String getBuyerType() {
        return buyerType;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getBuyerName() {
        return buyerName;
    }


    public String getBuyerId() {
        return buyerId;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public String getSaName() {
        return saName;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getNormsName() {
        return normsName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public double getPrice() {
        return price;
    }

    public String getOrderQty() {
        return orderQty;
    }

    public String getOrderDetailAmount() {
        return orderDetailAmount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSuppQty() {
        return suppQty;
    }

    public String getSendQty() {
        return sendQty;
    }

    public String getReceiveQty() {
        return receiveQty;
    }

    public String getCancelQty() {
        return cancelQty;
    }

    public String getRejectionQty() {
        return rejectionQty;
    }

    public String getReturnQty() {
        return returnQty;
    }

    public String getSendTime() {
        return sendTime;
    }



    public String getBuyerCode() {
        return buyerCode;
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }


    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public void setSaName(String saName) {
        this.saName = saName;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }

    public void setOrderDetailAmount(String orderDetailAmount) {
        this.orderDetailAmount = orderDetailAmount;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSuppQty(String suppQty) {
        this.suppQty = suppQty;
    }

    public void setSendQty(String sendQty) {
        this.sendQty = sendQty;
    }

    public void setReceiveQty(String receiveQty) {
        this.receiveQty = receiveQty;
    }

    public void setCancelQty(String cancelQty) {
        this.cancelQty = cancelQty;
    }

    public void setRejectionQty(String rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    public void setReturnQty(String returnQty) {
        this.returnQty = returnQty;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }



    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getBuyerMarketName() {
        return buyerMarketName;
    }

    public void setBuyerMarketName(String buyerMarketName) {
        this.buyerMarketName = buyerMarketName;
    }

    public String getSubOrderID() {
        return subOrderID;
    }

    public void setSubOrderID(String subOrderID) {
        this.subOrderID = subOrderID;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }
}
