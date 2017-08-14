package com.msk.print.bean;


import com.msk.comm.bean.BaseBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderPrintBean
 *
 * @author sunjiaju
 * @version 1.0
 **/
public class OrderPrintBean extends BaseBean {
    /** 订单ID */
    private String orderId;
    /** 订单编码 */
    private String orderCode;
    /** 订单辅码（时间编码) */
    private String orderViceCode;
    /** 购物意愿ID */
    private String proId;
    /** 购物意愿编码 */
    private String proCode;
    /** 需求订单编码 */
    private String requireOrderCode;
    /** 确认订单编码 */
    private String confirmOrderCode;
    /** 配送订单编码 */
    private String deliveryOrderCode;
    /** 卖家编码 */
    private String sellerCode;
    /** 卖家名称 */
    private String sellerName;
    /** 买家ID */
    private String buyersId;
    /** 买家编码 */
    private String buyersCode;
    /** 买家名称 */
    private String buyersName;
    /** 1:分销买家,2:菜场买家,3:团膳买家,4:火锅买家,5:中餐买家,6:西餐买家,7:小吃烧烤买家,8:加工厂买家 */
    private String buyersType;
    /** 是否开票 */
    private String needInvoice;
    /** 订单所属区域 */
    private String districtCode;
    /** 对应系统编码 */
    private String orderSource;
    /** 1:分销,2:第三方,3:大促会 */
    private String orderType;
    /** 订单创建时间 */
    private String orderTime;
    /** 订单总金额 */
    private BigDecimal orderAmount;
    /** 1:在线支付,2:线下支付 */
    private String paymentType;
    /** 直销员 */
    private String sellers;
    /** 订单员 */
    private String orderTaker;
    /** 是否自配送 */
    private String selfDeliveryFlg;
    /** 0:不分批,1:发生分批,2:买家同意分批,3:买家确认取消 */
    private String splitDeliveryFlg;
    /** 回收站标志 */
    private String dustbinFlg;
    /** 退货标志 */
    private String returnFlg;
    /** 订单发货时间 */
    private String orderSendTime;
    /** 订单收货时间 */
    private String orderReceiveTime;
    /** 1.买家取消 2.不同意拼货的取消 3.不同意分批的取消 */
    private String cancelType;
    /** 取消原因 */
    private String cancelReason;
    /** 订单状态 */
    private String orderStatus;

    /** 收货信息 */
    private OrderPrintReceiveBean receiveInfo;

    /** 配送要求 */
    private OrderPrintDeliveryReqBean deliveryReq;

    /** 订单详细信息列表 */
    private List<OrderPrintAvailabilityBean> detailInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
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

    public OrderPrintReceiveBean getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(OrderPrintReceiveBean receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public OrderPrintDeliveryReqBean getDeliveryReq() {
        return deliveryReq;
    }

    public void setDeliveryReq(OrderPrintDeliveryReqBean deliveryReq) {
        this.deliveryReq = deliveryReq;
    }

    public List<OrderPrintAvailabilityBean> getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(List<OrderPrintAvailabilityBean> detailInfo) {
        this.detailInfo = detailInfo;
    }
}
