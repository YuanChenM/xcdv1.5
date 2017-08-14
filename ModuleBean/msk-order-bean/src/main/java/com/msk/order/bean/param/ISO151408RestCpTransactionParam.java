package com.msk.order.bean.param;


import com.msk.common.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/9/9.
 */
public class ISO151408RestCpTransactionParam extends BaseEntity {
    private Integer insertFlg;//是否新增标识 0：否 1：是

    private String transCode;// 交易编码

    private Integer transType;//交易类型 0 主订单 1管理费用清单

    private Date operateDate;//操作时间

    private Integer supplyPlatform;//平台类型 1:神农客 2:美侍客 3:大促会

    private Integer transFlg;//交易标志 0：正常 1：交易关闭  订单关闭的情况下传1，其余情况传0

    private String businessMainId;//收款方ID

    private String businessAssistantId;//付款方ID

    private String businessMainName;//收款方名称

    private Integer businessMainRole;//收款方角色

    private String businessAssistantCode;//付款方编码

    private String businessAssistantName;//付款方名称

    private Integer businessAssistantRole;//付款方角色

    private BigDecimal orderAmount;//订单金额

    private Date tranTime;//交易发生日期

    private Integer paymentType;//支付类型 1:在线支付,2:线下支付

    private String refundCode;//退款编码

    private Date refundTime;//退款日期

    private Integer refundType;//退回费用类型 0：退货退款 1：拒收退款 2：关闭订单

    private Integer reShipFlg;//重新发货标志 0：不重新发货 1：重新发货

    private String remark;//备注

    private Long orderId;//订单ID


    /**
     * 退回费用明细列表
     */
    private List<ISO151408RestFundDetailParam> refundDetailList;

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

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getReShipFlg() {
        return reShipFlg;
    }

    public void setReShipFlg(Integer reShipFlg) {
        this.reShipFlg = reShipFlg;
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

    public List<ISO151408RestFundDetailParam> getRefundDetailList() {
        return refundDetailList;
    }

    public void setRefundDetailList(List<ISO151408RestFundDetailParam> refundDetailList) {
        this.refundDetailList = refundDetailList;
    }
}
