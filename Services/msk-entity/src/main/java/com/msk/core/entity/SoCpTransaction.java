/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_transaction对应的SoCpTransaction。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpTransaction extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long transId;
    /** 卖家ID：SL_CODE */
    private String businessMainId;
    /** 业务主体，收款方编码  卖家显示用code：SL_CODE_DIS */
    private String businessMainCode;
    /** BUSINESS_MAIN_NAME */
    private String businessMainName;
    /** BUSINESS_ASSISTANT_ID */
    private String businessAssistantId;
    /** 付款方编码 */
    private String businessAssistantCode;
    /** BUSINESS_ASSISTANT_NAME */
    private String businessAssistantName;
    /** 业务主体角色 */
    private Integer businessMainRole;
    /** 业务副体角色 */
    private Integer businessAssistantRole;
    /** 交易明细的唯一检索标识（订单号、管理费用单号） */
    private String transCode;
    /** 0 主订单 1管理费用清单 */
    private Integer transType;
    /** :0：正常 1：交易关闭 */
    private String transFlg;
    /** 订单金额 */
    private java.math.BigDecimal orderAmount;
    /** 订单生成的日期 */
    private java.util.Date tranTime;
    /** 1:神农客 2:美侍客 3:大促会 */
    private Integer supplyPlatform;
    /** 支付类型 1:在线支付,2:线下支付 */
    private Integer paymentType;
    /** 买家应付金额 */
    private java.math.BigDecimal due;
    /** 卖家应收金额 */
    private java.math.BigDecimal receiveable;
    /** 备注 */
    private String remark;
    /** 订单ID */
    private Long orderId;
    /** 买手ID */
    private String bsId;
    /** 买手编码 */
    private String bsCode;
    /** 买手名称 */
    private String bsName;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpTransaction() {

    }

    /**
     * <p>主键。</p>
     *
     * @return the 主键
     */
    public Long getTransId() {
        return transId;
    }

    /**
     * <p>主键。</p>
     *
     * @param transId 主键。
     */
    public void setTransId(Long transId) {
        this.transId = transId;
    }

    /**
     * <p>卖家ID：SL_CODE。</p>
     *
     * @return the 卖家ID：SL_CODE
     */
    public String getBusinessMainId() {
        return businessMainId;
    }

    /**
     * <p>卖家ID：SL_CODE。</p>
     *
     * @param businessMainId 卖家ID：SL_CODE。
     */
    public void setBusinessMainId(String businessMainId) {
        this.businessMainId = businessMainId;
    }

    /**
     * <p>业务主体，收款方编码  卖家显示用code：SL_CODE_DIS。</p>
     *
     * @return the 业务主体，收款方编码  卖家显示用code：SL_CODE_DIS
     */
    public String getBusinessMainCode() {
        return businessMainCode;
    }

    /**
     * <p>业务主体，收款方编码  卖家显示用code：SL_CODE_DIS。</p>
     *
     * @param businessMainCode 业务主体，收款方编码  卖家显示用code：SL_CODE_DIS。
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
     * <p>付款方编码。</p>
     *
     * @return the 付款方编码
     */
    public String getBusinessAssistantCode() {
        return businessAssistantCode;
    }

    /**
     * <p>付款方编码。</p>
     *
     * @param businessAssistantCode 付款方编码。
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
     * <p>:0：正常 1：交易关闭。</p>
     *
     * @return the :0：正常 1：交易关闭
     */
    public String getTransFlg() {
        return transFlg;
    }

    /**
     * <p>:0：正常 1：交易关闭。</p>
     *
     * @param transFlg :0：正常 1：交易关闭。
     */
    public void setTransFlg(String transFlg) {
        this.transFlg = transFlg;
    }

    /**
     * <p>订单金额。</p>
     *
     * @return the 订单金额
     */
    public java.math.BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * <p>订单金额。</p>
     *
     * @param orderAmount 订单金额。
     */
    public void setOrderAmount(java.math.BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * <p>订单生成的日期。</p>
     *
     * @return the 订单生成的日期
     */
    public java.util.Date getTranTime() {
        return tranTime;
    }

    /**
     * <p>订单生成的日期。</p>
     *
     * @param tranTime 订单生成的日期。
     */
    public void setTranTime(java.util.Date tranTime) {
        this.tranTime = tranTime;
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
     * <p>买家应付金额。</p>
     *
     * @return the 买家应付金额
     */
    public java.math.BigDecimal getDue() {
        return due;
    }

    /**
     * <p>买家应付金额。</p>
     *
     * @param due 买家应付金额。
     */
    public void setDue(java.math.BigDecimal due) {
        this.due = due;
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

    /**
     * <p>买手ID。</p>
     *
     * @return the 买手ID
     */
    public String getBsId() {
        return bsId;
    }

    /**
     * <p>买手ID。</p>
     *
     * @param bsId 买手ID。
     */
    public void setBsId(String bsId) {
        this.bsId = bsId;
    }

    /**
     * <p>买手编码。</p>
     *
     * @return the 买手编码
     */
    public String getBsCode() {
        return bsCode;
    }

    /**
     * <p>买手编码。</p>
     *
     * @param bsCode 买手编码。
     */
    public void setBsCode(String bsCode) {
        this.bsCode = bsCode;
    }

    /**
     * <p>买手名称。</p>
     *
     * @return the 买手名称
     */
    public String getBsName() {
        return bsName;
    }

    /**
     * <p>买手名称。</p>
     *
     * @param bsName 买手名称。
     */
    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

}
