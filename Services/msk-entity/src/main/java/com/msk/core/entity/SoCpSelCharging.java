/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_sel_charging对应的SoCpSelCharging。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpSelCharging extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
    public SoCpSelCharging() {

    }

    /**
     * <p>SEL_CHARGING_ID。</p>
     *
     * @return the SEL_CHARGING_ID
     */
    public Long getSelChargingId() {
        return selChargingId;
    }

    /**
     * <p>SEL_CHARGING_ID。</p>
     *
     * @param selChargingId SEL_CHARGING_ID。
     */
    public void setSelChargingId(Long selChargingId) {
        this.selChargingId = selChargingId;
    }

    /**
     * <p>主键。</p>
     *
     * @return the 主键
     */
    public Long getSellerBillingId() {
        return sellerBillingId;
    }

    /**
     * <p>主键。</p>
     *
     * @param sellerBillingId 主键。
     */
    public void setSellerBillingId(Long sellerBillingId) {
        this.sellerBillingId = sellerBillingId;
    }

    /**
     * <p>交易明细的唯一检索标识（订单号、管理费用单号）。</p>
     *
     * @return the 交易明细的唯一检索标识（订单号、管理费用单号）
     */
    public String getTransCode() {
        return transCode;
    }

    /**
     * <p>交易明细的唯一检索标识（订单号、管理费用单号）。</p>
     *
     * @param transCode 交易明细的唯一检索标识（订单号、管理费用单号）。
     */
    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    /**
     * <p>0 主订单 1管理费用清单。</p>
     *
     * @return the 0 主订单 1管理费用清单
     */
    public Integer getTransType() {
        return transType;
    }

    /**
     * <p>0 主订单 1管理费用清单。</p>
     *
     * @param transType 0 主订单 1管理费用清单。
     */
    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    /**
     * <p>BUSINESS_MAIN_ID。</p>
     *
     * @return the BUSINESS_MAIN_ID
     */
    public String getBusinessMainId() {
        return businessMainId;
    }

    /**
     * <p>BUSINESS_MAIN_ID。</p>
     *
     * @param businessMainId BUSINESS_MAIN_ID。
     */
    public void setBusinessMainId(String businessMainId) {
        this.businessMainId = businessMainId;
    }

    /**
     * <p>业务主体，收款方编码。</p>
     *
     * @return the 业务主体，收款方编码
     */
    public String getBusinessMainCode() {
        return businessMainCode;
    }

    /**
     * <p>业务主体，收款方编码。</p>
     *
     * @param businessMainCode 业务主体，收款方编码。
     */
    public void setBusinessMainCode(String businessMainCode) {
        this.businessMainCode = businessMainCode;
    }

    /**
     * <p>BUSINESS_MAIN_NAME。</p>
     *
     * @return the BUSINESS_MAIN_NAME
     */
    public String getBusinessMainName() {
        return businessMainName;
    }

    /**
     * <p>BUSINESS_MAIN_NAME。</p>
     *
     * @param businessMainName BUSINESS_MAIN_NAME。
     */
    public void setBusinessMainName(String businessMainName) {
        this.businessMainName = businessMainName;
    }

    /**
     * <p>业务主体角色。</p>
     *
     * @return the 业务主体角色
     */
    public Integer getBusinessMainRole() {
        return businessMainRole;
    }

    /**
     * <p>业务主体角色。</p>
     *
     * @param businessMainRole 业务主体角色。
     */
    public void setBusinessMainRole(Integer businessMainRole) {
        this.businessMainRole = businessMainRole;
    }

    /**
     * <p>BUSINESS_ASSISTANT_ID。</p>
     *
     * @return the BUSINESS_ASSISTANT_ID
     */
    public String getBusinessAssistantId() {
        return businessAssistantId;
    }

    /**
     * <p>BUSINESS_ASSISTANT_ID。</p>
     *
     * @param businessAssistantId BUSINESS_ASSISTANT_ID。
     */
    public void setBusinessAssistantId(String businessAssistantId) {
        this.businessAssistantId = businessAssistantId;
    }

    /**
     * <p>业务副体，付款方编码。</p>
     *
     * @return the 业务副体，付款方编码
     */
    public String getBusinessAssistantCode() {
        return businessAssistantCode;
    }

    /**
     * <p>业务副体，付款方编码。</p>
     *
     * @param businessAssistantCode 业务副体，付款方编码。
     */
    public void setBusinessAssistantCode(String businessAssistantCode) {
        this.businessAssistantCode = businessAssistantCode;
    }

    /**
     * <p>BUSINESS_ASSISTANT_NAME。</p>
     *
     * @return the BUSINESS_ASSISTANT_NAME
     */
    public String getBusinessAssistantName() {
        return businessAssistantName;
    }

    /**
     * <p>BUSINESS_ASSISTANT_NAME。</p>
     *
     * @param businessAssistantName BUSINESS_ASSISTANT_NAME。
     */
    public void setBusinessAssistantName(String businessAssistantName) {
        this.businessAssistantName = businessAssistantName;
    }

    /**
     * <p>业务副体角色。</p>
     *
     * @return the 业务副体角色
     */
    public Integer getBusinessAssistantRole() {
        return businessAssistantRole;
    }

    /**
     * <p>业务副体角色。</p>
     *
     * @param businessAssistantRole 业务副体角色。
     */
    public void setBusinessAssistantRole(Integer businessAssistantRole) {
        this.businessAssistantRole = businessAssistantRole;
    }

    /**
     * <p>配送单号。</p>
     *
     * @return the 配送单号
     */
    public String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>配送单号。</p>
     *
     * @param deliveryCode 配送单号。
     */
    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>配送单签收日期。</p>
     *
     * @return the 配送单签收日期
     */
    public java.util.Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * <p>配送单签收日期。</p>
     *
     * @param deliveryTime 配送单签收日期。
     */
    public void setDeliveryTime(java.util.Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * <p>配送单的金额。</p>
     *
     * @return the 配送单的金额
     */
    public java.math.BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    /**
     * <p>配送单的金额。</p>
     *
     * @param shippingAmount 配送单的金额。
     */
    public void setShippingAmount(java.math.BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    /**
     * <p>配送单实际签收的金额。</p>
     *
     * @return the 配送单实际签收的金额
     */
    public java.math.BigDecimal getPaidAmount() {
        return paidAmount;
    }

    /**
     * <p>配送单实际签收的金额。</p>
     *
     * @param paidAmount 配送单实际签收的金额。
     */
    public void setPaidAmount(java.math.BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * <p>0：未计费 1：已计费。</p>
     *
     * @return the 0：未计费 1：已计费
     */
    public Integer getChargeFlg() {
        return chargeFlg;
    }

    /**
     * <p>0：未计费 1：已计费。</p>
     *
     * @param chargeFlg 0：未计费 1：已计费。
     */
    public void setChargeFlg(Integer chargeFlg) {
        this.chargeFlg = chargeFlg;
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

}
