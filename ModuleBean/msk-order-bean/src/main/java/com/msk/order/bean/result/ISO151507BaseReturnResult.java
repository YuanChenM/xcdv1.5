package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import javax.persistence.Entity;

/**
 * ISO151506_退货单详情
 * Created by wang_shuai on 2016/8/3.
 */
@Entity
public class ISO151507BaseReturnResult extends BaseResult {
    private String returnCode;// 退货单编码

    private String requireOrderCode; // 需求订单编码

    private String returnSource; //退货单来源

    private String returnMode; // 退货方式

    private String refundMode;// 退款方式

    private String returnAmount; // 退款总金额

    private String isInvoice; // 是否开票

    private String isPaid; // 是否付款

    private String returnActor; // 退货处理人

    private String returnStatus; // 退货单状态

    private String returnTime; // 退货时间

    private String returnReasonCode; // 退货原因

    private String returnReasonDesc; // 退货原因描述

    private String orderCode; // 订单编码

    private String applyRemark; // 退货申请备注

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getRequireOrderCode() {
        return requireOrderCode;
    }

    public void setRequireOrderCode(String requireOrderCode) {
        this.requireOrderCode = requireOrderCode;
    }

    public String getReturnSource() {
        return returnSource;
    }

    public void setReturnSource(String returnSource) {
        this.returnSource = returnSource;
    }

    public String getReturnMode() {
        return returnMode;
    }

    public void setReturnMode(String returnMode) {
        this.returnMode = returnMode;
    }

    public String getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(String refundMode) {
        this.refundMode = refundMode;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getReturnActor() {
        return returnActor;
    }

    public void setReturnActor(String returnActor) {
        this.returnActor = returnActor;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    public void setReturnReasonCode(String returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    public String getReturnReasonDesc() {
        return returnReasonDesc;
    }

    public void setReturnReasonDesc(String returnReasonDesc) {
        this.returnReasonDesc = returnReasonDesc;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }
}
