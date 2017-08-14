/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_seller_bill对应的SoCpSellerBill。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpSellerBill extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long sellerBillId;
    /** SELLER_BILL_NO */
    private String sellerBillNo;
    /** BUSINESS_MAIN_ID */
    private String businessMainId;
    /** 业务主体，收款方编码 */
    private String businessMainCode;
    /** BUSINESS_MAIN_NAME */
    private String businessMainName;
    /** 业务主体角色 */
    private Integer businessMainRole;
    /** 1：付 2：退 3：平（应付-应退=实付-实退） */
    private Integer settlementStatus;
    /** 1:未结算，2:处理中，3:已结算 */
    private Integer settlementFlg;
    /** 账单起始日期 */
    private java.util.Date startDate;
    /** 账单终止日期 */
    private java.util.Date endDate;
    /** 支付类型 1:在线支付,2:线下支付 */
    private Integer paymentType;
    /** 1:神农客 2:美侍客 3:大促会 */
    private Integer supplyPlatform;
    /** 计费类型：1：卖家交易计费 2：平台管理费 */
    private Integer billType;
    /** 计费项表内金额集计 */
    private java.math.BigDecimal billAmount;
    /** 卖家应收金额 */
    private java.math.BigDecimal receiveable;
    /** 实际收款金额 */
    private java.math.BigDecimal received;
    /** 退款金额的集计 */
    private java.math.BigDecimal refundable;
    /** 实际退款金额 */
    private java.math.BigDecimal realRefund;
    /** 0：未支付 1：部分支付 2：已支付 */
    private Integer transPaidStatus;
    /** CHARGE_RATE */
    private java.math.BigDecimal chargeRate;
    /** HANDING_CHARGE */
    private java.math.BigDecimal handingCharge;
    /** 0：未支付 1：部分支付 2：已支付 */
    private Integer chargeStatus;
    /** 0：正常收支 1：冲抵核销 */
    private String matchVerFlg;
    /** 0：可对账 1：已对账 2：挂起 */
    private String statementFlg;
    /** 备注 */
    private String remark;
    /** 历史表内每次调整金额的集计 */
    private java.math.BigDecimal ajustAmount;

    private String settlementStatusStr;

    private String settlementFlgStr;

    private String billTypeStr;

    private String paymentTypeStr;

    private String chargeStatusStr;

    private String transPaidStatusStr;


    /**
     * <p>默认构造函数。</p>
     */
    public SoCpSellerBill() {

    }

    /**
     * <p>主键。</p>
     *
     * @return the 主键
     */
    public Long getSellerBillId() {
        return sellerBillId;
    }

    /**
     * <p>主键。</p>
     *
     * @param sellerBillId 主键。
     */
    public void setSellerBillId(Long sellerBillId) {
        this.sellerBillId = sellerBillId;
    }

    /**
     * <p>SELLER_BILL_NO。</p>
     *
     * @return the SELLER_BILL_NO
     */
    public String getSellerBillNo() {
        return sellerBillNo;
    }

    /**
     * <p>SELLER_BILL_NO。</p>
     *
     * @param sellerBillNo SELLER_BILL_NO。
     */
    public void setSellerBillNo(String sellerBillNo) {
        this.sellerBillNo = sellerBillNo;
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
     * <p>1：付 2：退 3：平（应付-应退=实付-实退）。</p>
     *
     * @return the 1：付 2：退 3：平（应付-应退=实付-实退）
     */
    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    /**
     * <p>1：付 2：退 3：平（应付-应退=实付-实退）。</p>
     *
     * @param settlementStatus 1：付 2：退 3：平（应付-应退=实付-实退）。
     */
    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    /**
     * <p>1:未结算，2:处理中，3:已结算。</p>
     *
     * @return the 1:未结算，2:处理中，3:已结算
     */
    public Integer getSettlementFlg() {
        return settlementFlg;
    }

    /**
     * <p>1:未结算，2:处理中，3:已结算。</p>
     *
     * @param settlementFlg 1:未结算，2:处理中，3:已结算。
     */
    public void setSettlementFlg(Integer settlementFlg) {
        this.settlementFlg = settlementFlg;
    }

    /**
     * <p>账单起始日期。</p>
     *
     * @return the 账单起始日期
     */
    public java.util.Date getStartDate() {
        return startDate;
    }

    /**
     * <p>账单起始日期。</p>
     *
     * @param startDate 账单起始日期。
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    /**
     * <p>账单终止日期。</p>
     *
     * @return the 账单终止日期
     */
    public java.util.Date getEndDate() {
        return endDate;
    }

    /**
     * <p>账单终止日期。</p>
     *
     * @param endDate 账单终止日期。
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    /**
     * <p>支付类型 1:在线支付,2:线下支付。</p>
     *
     * @return the 支付类型 1:在线支付,2:线下支付
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * <p>支付类型 1:在线支付,2:线下支付。</p>
     *
     * @param paymentType 支付类型 1:在线支付,2:线下支付。
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * <p>1:神农客 2:美侍客 3:大促会。</p>
     *
     * @return the 1:神农客 2:美侍客 3:大促会
     */
    public Integer getSupplyPlatform() {
        return supplyPlatform;
    }

    /**
     * <p>1:神农客 2:美侍客 3:大促会。</p>
     *
     * @param supplyPlatform 1:神农客 2:美侍客 3:大促会。
     */
    public void setSupplyPlatform(Integer supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    /**
     * <p>计费类型：1：卖家交易计费 2：平台管理费。</p>
     *
     * @return the 计费类型：1：卖家交易计费 2：平台管理费
     */
    public Integer getBillType() {
        return billType;
    }

    /**
     * <p>计费类型：1：卖家交易计费 2：平台管理费。</p>
     *
     * @param billType 计费类型：1：卖家交易计费 2：平台管理费。
     */
    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    /**
     * <p>计费项表内金额集计。</p>
     *
     * @return the 计费项表内金额集计
     */
    public java.math.BigDecimal getBillAmount() {
        return billAmount;
    }

    /**
     * <p>计费项表内金额集计。</p>
     *
     * @param billAmount 计费项表内金额集计。
     */
    public void setBillAmount(java.math.BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    /**
     * <p>卖家应收金额。</p>
     *
     * @return the 卖家应收金额
     */
    public java.math.BigDecimal getReceiveable() {
        return receiveable;
    }

    /**
     * <p>卖家应收金额。</p>
     *
     * @param receiveable 卖家应收金额。
     */
    public void setReceiveable(java.math.BigDecimal receiveable) {
        this.receiveable = receiveable;
    }

    /**
     * <p>实际收款金额。</p>
     *
     * @return the 实际收款金额
     */
    public java.math.BigDecimal getReceived() {
        return received;
    }

    /**
     * <p>实际收款金额。</p>
     *
     * @param received 实际收款金额。
     */
    public void setReceived(java.math.BigDecimal received) {
        this.received = received;
    }

    /**
     * <p>退款金额的集计。</p>
     *
     * @return the 退款金额的集计
     */
    public java.math.BigDecimal getRefundable() {
        return refundable;
    }

    /**
     * <p>退款金额的集计。</p>
     *
     * @param refundable 退款金额的集计。
     */
    public void setRefundable(java.math.BigDecimal refundable) {
        this.refundable = refundable;
    }

    /**
     * <p>实际退款金额。</p>
     *
     * @return the 实际退款金额
     */
    public java.math.BigDecimal getRealRefund() {
        return realRefund;
    }

    /**
     * <p>实际退款金额。</p>
     *
     * @param realRefund 实际退款金额。
     */
    public void setRealRefund(java.math.BigDecimal realRefund) {
        this.realRefund = realRefund;
    }

    /**
     * <p>0：未支付 1：部分支付 2：已支付。</p>
     *
     * @return the 0：未支付 1：部分支付 2：已支付
     */
    public Integer getTransPaidStatus() {
        return transPaidStatus;
    }

    /**
     * <p>0：未支付 1：部分支付 2：已支付。</p>
     *
     * @param transPaidStatus 0：未支付 1：部分支付 2：已支付。
     */
    public void setTransPaidStatus(Integer transPaidStatus) {
        this.transPaidStatus = transPaidStatus;
    }

    /**
     * <p>CHARGE_RATE。</p>
     *
     * @return the CHARGE_RATE
     */
    public java.math.BigDecimal getChargeRate() {
        return chargeRate;
    }

    /**
     * <p>CHARGE_RATE。</p>
     *
     * @param chargeRate CHARGE_RATE。
     */
    public void setChargeRate(java.math.BigDecimal chargeRate) {
        this.chargeRate = chargeRate;
    }

    /**
     * <p>HANDING_CHARGE。</p>
     *
     * @return the HANDING_CHARGE
     */
    public java.math.BigDecimal getHandingCharge() {
        return handingCharge;
    }

    /**
     * <p>HANDING_CHARGE。</p>
     *
     * @param handingCharge HANDING_CHARGE。
     */
    public void setHandingCharge(java.math.BigDecimal handingCharge) {
        this.handingCharge = handingCharge;
    }

    /**
     * <p>0：未支付 1：部分支付 2：已支付。</p>
     *
     * @return the 0：未支付 1：部分支付 2：已支付
     */
    public Integer getChargeStatus() {
        return chargeStatus;
    }

    /**
     * <p>0：未支付 1：部分支付 2：已支付。</p>
     *
     * @param chargeStatus 0：未支付 1：部分支付 2：已支付。
     */
    public void setChargeStatus(Integer chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    /**
     * <p>0：正常收支 1：冲抵核销。</p>
     *
     * @return the 0：正常收支 1：冲抵核销
     */
    public String getMatchVerFlg() {
        return matchVerFlg;
    }

    /**
     * <p>0：正常收支 1：冲抵核销。</p>
     *
     * @param matchVerFlg 0：正常收支 1：冲抵核销。
     */
    public void setMatchVerFlg(String matchVerFlg) {
        this.matchVerFlg = matchVerFlg;
    }

    /**
     * <p>0：可对账 1：已对账 2：挂起。</p>
     *
     * @return the 0：可对账 1：已对账 2：挂起
     */
    public String getStatementFlg() {
        return statementFlg;
    }

    /**
     * <p>0：可对账 1：已对账 2：挂起。</p>
     *
     * @param statementFlg 0：可对账 1：已对账 2：挂起。
     */
    public void setStatementFlg(String statementFlg) {
        this.statementFlg = statementFlg;
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
     * <p>历史表内每次调整金额的集计。</p>
     *
     * @return the 历史表内每次调整金额的集计
     */
    public java.math.BigDecimal getAjustAmount() {
        return ajustAmount;
    }

    /**
     * <p>历史表内每次调整金额的集计。</p>
     *
     * @param ajustAmount 历史表内每次调整金额的集计。
     */
    public void setAjustAmount(java.math.BigDecimal ajustAmount) {
        this.ajustAmount = ajustAmount;
    }

    public String getSettlementStatusStr() {
        return settlementStatusStr;
    }

    public void setSettlementStatusStr(String settlementStatusStr) {
        this.settlementStatusStr = settlementStatusStr;
    }
    public String getSettlementFlgStr() {
        return settlementFlgStr;
    }

    public void setSettlementFlgStr(String settlementFlgStr) {
        this.settlementFlgStr = settlementFlgStr;
    }

    public String getPaymentTypeStr() {
        return paymentTypeStr;
    }

    public void setPaymentTypeStr(String paymentTypeStr) {
        this.paymentTypeStr = paymentTypeStr;
    }

    public String getBillTypeStr() {
        return billTypeStr;
    }

    public void setBillTypeStr(String billTypeStr) {
        this.billTypeStr = billTypeStr;
    }

    public String getChargeStatusStr() {
        return chargeStatusStr;
    }

    public void setChargeStatusStr(String chargeStatusStr) {
        this.chargeStatusStr = chargeStatusStr;
    }

    public String getTransPaidStatusStr() {
        return transPaidStatusStr;
    }

    public void setTransPaidStatusStr(String transPaidStatusStr) {
        this.transPaidStatusStr = transPaidStatusStr;
    }
}
