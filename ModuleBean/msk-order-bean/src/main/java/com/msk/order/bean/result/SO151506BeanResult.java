package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * ISO151506_退货单列表
 * Created by wang_shuai on 2016/8/1.
 */
public class SO151506BeanResult extends BaseResult {
    //退货单ID
    private String returnId;
    // 退货单编码
    private String returnCode;
    // 需求订单编码
    private String orderCode;
    // 买家编码
    private String buyerCode;
    // 买家名称
    private String buyerName;
    //退货单来源 1:平台 2:呼叫中心 3:手机客户端
    private String returnSource;
    // 是否已经付款
    private String isPaid;
    //退款方式
    private String refundMode;
    //是否开票
    private String isInvoice;
    //退货单申请时间
    private String returnTime;
    // 退款金额
    private String returnAmount;
    // 状态
    private String returnStatus;
    //退货方式
    private String returnMode;
    //订单ID
    private String orderId;
    //查询开始时间
    private String startTime;
    //查询结束时间
    private String endTime;
    //查询条件的退货单编码
    private String returnCodeQuery;
    //查询条件的需求订单编码
    private String orderCodeQuery;
    //查询条件的买家编码
    private String buyersCodeQuery;
    //查询条件的买家名称
    private String buyersNameQuery;
    //需求订单code
    private String requireOrderCode;
    //退货单状态名称
    private String returnStatusName;

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getReturnSource() {
        return returnSource;
    }

    public void setReturnSource(String returnSource) {
        this.returnSource = returnSource;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(String returnMode) {
        this.returnMode = returnMode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReturnCodeQuery() {
        return returnCodeQuery;
    }

    public void setReturnCodeQuery(String returnCodeQuery) {
        this.returnCodeQuery = returnCodeQuery;
    }

    public String getOrderCodeQuery() {
        return orderCodeQuery;
    }

    public void setOrderCodeQuery(String orderCodeQuery) {
        this.orderCodeQuery = orderCodeQuery;
    }

    public String getBuyersCodeQuery() {
        return buyersCodeQuery;
    }

    public void setBuyersCodeQuery(String buyersCodeQuery) {
        this.buyersCodeQuery = buyersCodeQuery;
    }

    public String getBuyersNameQuery() {
        return buyersNameQuery;
    }

    public void setBuyersNameQuery(String buyersNameQuery) {
        this.buyersNameQuery = buyersNameQuery;
    }

    public String getRequireOrderCode() {
        return requireOrderCode;
    }

    public void setRequireOrderCode(String requireOrderCode) {
        this.requireOrderCode = requireOrderCode;
    }

    public String getReturnStatusName() {
        return returnStatusName;
    }

    public void setReturnStatusName(String returnStatusName) {
        this.returnStatusName = returnStatusName;
    }
}
