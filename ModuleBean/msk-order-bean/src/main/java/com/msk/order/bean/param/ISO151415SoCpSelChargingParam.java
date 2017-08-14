package com.msk.order.bean.param;

import com.msk.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_shuai on 2016/8/31.
 */
public class ISO151415SoCpSelChargingParam extends BaseEntity {
    /** SEL_CHARGING_ID */
    private Long selChargingId;
    /** 主键 */
    private Long sellerBillingId;
    /** 交易明细的唯一检索标识（订单号、管理费用单号） */
    private String transCode;
    /** 0 主订单 1管理费用清单 */
    private Integer transType;
    /** BUSINESS_MAIN_ID */
    private String businessMainId;
    /** 业务主体，收款方编码 */
    private String businessMainCode;
    /** BUSINESS_MAIN_NAME */
    private String businessMainName;
    /** 业务主体角色 */
    private Integer businessMainRole;
    /** BUSINESS_ASSISTANT_ID */
    private String businessAssistantId;
    /** 业务副体，付款方编码 */
    private String businessAssistantCode;
    /** BUSINESS_ASSISTANT_NAME */
    private String businessAssistantName;
    /** 业务副体角色 */
    private Integer businessAssistantRole;
    /** 配送单号 */
    private String deliveryCode;
    /** 配送单签收日期 */
    private java.util.Date deliveryTime;
    /** 配送单的金额 */
    private java.math.BigDecimal shippingAmount;
    /** 配送单实际签收的金额 */
    private java.math.BigDecimal paidAmount;
    /** 0：未计费 1：已计费 */
    private Integer chargeFlg;
    /** 备注 */
    private String remark;
    /** 订单ID */
    private Long orderId;
    /**
     * <p>默认构造函数。</p>
     */
    public ISO151415SoCpSelChargingParam() {

    }

    public Long getSelChargingId() {

        return selChargingId;
    }

    public void setSelChargingId(Long selChargingId) {
        this.selChargingId = selChargingId;
    }

    public Long getSellerBillingId() {
        return sellerBillingId;
    }

    public void setSellerBillingId(Long sellerBillingId) {
        this.sellerBillingId = sellerBillingId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public String getBusinessMainId() {
        return businessMainId;
    }

    public void setBusinessMainId(String businessMainId) {
        this.businessMainId = businessMainId;
    }

    public String getBusinessMainCode() {
        return businessMainCode;
    }

    public void setBusinessMainCode(String businessMainCode) {
        this.businessMainCode = businessMainCode;
    }

    public String getBusinessMainName() {
        return businessMainName;
    }

    public void setBusinessMainName(String businessMainName) {
        this.businessMainName = businessMainName;
    }

    public Integer getBusinessMainRole() {
        return businessMainRole;
    }

    public void setBusinessMainRole(Integer businessMainRole) {
        this.businessMainRole = businessMainRole;
    }

    public String getBusinessAssistantId() {
        return businessAssistantId;
    }

    public void setBusinessAssistantId(String businessAssistantId) {
        this.businessAssistantId = businessAssistantId;
    }

    public String getBusinessAssistantCode() {
        return businessAssistantCode;
    }

    public void setBusinessAssistantCode(String businessAssistantCode) {
        this.businessAssistantCode = businessAssistantCode;
    }

    public String getBusinessAssistantName() {
        return businessAssistantName;
    }

    public void setBusinessAssistantName(String businessAssistantName) {
        this.businessAssistantName = businessAssistantName;
    }

    public Integer getBusinessAssistantRole() {
        return businessAssistantRole;
    }

    public void setBusinessAssistantRole(Integer businessAssistantRole) {
        this.businessAssistantRole = businessAssistantRole;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getChargeFlg() {
        return chargeFlg;
    }

    public void setChargeFlg(Integer chargeFlg) {
        this.chargeFlg = chargeFlg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
