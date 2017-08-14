package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/30.
 */
public class ISO151414CpTransactionParam extends BaseRestParam {

    /** 是否新增标识 0：否 1：是 */
    private Integer insertFlg;

    /** 交易编码 */
    private String transCode;

    /** 交易类型 0 主订单 1管理费用清单 */
    private Integer transType;

    /** 操作时间 */
    private Date operateDate;

    /** 平台类型 1:神农客 2:美侍客 3:大促会 */
    private Integer supplyPlatform;

    /** 交易标志 0：正常 1：交易关闭  订单关闭的情况下传1，其余情况传0 */
    private Integer transFlg;

    /** 收款方ID */
    private String businessMainId;

    /** 付款方ID */
    private String businessAssistantId;

    /** 收款方名称 */
    private String businessMainName;

    /** 收款方角色 */
    private Integer businessMainRole;

    /** 付款方编码 */
    private String businessAssistantCode;

    /** 付款方名称 */
    private String businessAssistantName;

    /** 付款方角色 */
    private Integer businessAssistantRole;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 交易发生日期 */
    private Date tranTime;

    /** 支付类型 1:在线支付,2:线下支付 */
    private Integer paymentType;

    /** 备注 */
    private String remark;

    /** 订单ID */
    private Long orderId;

    private String crtId;

    public String getCrtId() {
        return crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

    public Integer getInsertFlg() {
        return insertFlg;
    }

    public void setInsertFlg(Integer insertFlg) {
        this.insertFlg = insertFlg;
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

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Integer getSupplyPlatform() {
        return supplyPlatform;
    }

    public void setSupplyPlatform(Integer supplyPlatform) {
        this.supplyPlatform = supplyPlatform;
    }

    public Integer getTransFlg() {
        return transFlg;
    }

    public void setTransFlg(Integer transFlg) {
        this.transFlg = transFlg;
    }

    public String getBusinessMainId() {
        return businessMainId;
    }

    public void setBusinessMainId(String businessMainId) {
        this.businessMainId = businessMainId;
    }

    public String getBusinessAssistantId() {
        return businessAssistantId;
    }

    public void setBusinessAssistantId(String businessAssistantId) {
        this.businessAssistantId = businessAssistantId;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getTranTime() {
        return tranTime;
    }

    public void setTranTime(Date tranTime) {
        this.tranTime = tranTime;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
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
