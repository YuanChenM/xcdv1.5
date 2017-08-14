/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_cp_refund对应的SoCpRefund。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoCpRefund extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** REFUND_ID */
    private Long refundId;
    /** TRANS_CODE */
    private String transCode;
    /** 0 主订单 1管理费用清单 */
    private Integer transType;
    /** 退货单号、退款单号、拒收单号 */
    private String refundCode;
    /** REFUND_AMOUNT */
    private java.math.BigDecimal refundAmount;
    /** 生成卖家计费单时更新 */
    private Long sellerBillId;
    /** 退款发生日期 */
    private java.util.Date refundTime;
    /** 0：退货退款 1：拒收退款 2：关闭订单 */
    private Integer refundType;
    /** 0：不重新发货 1：重新发货（针对拒收退款） */
    private String reShipFlg;
    /** 计费项类型为买家时检索 */
    private String payerId;
    /** 计费项类型是卖家时检索 */
    private String payeeId;
    /** 备注 */
    private String remark;
    /** PAYEE_CODE */
    private String payeeCode;
    /** 1:神农客 2:美侍客 3:大促会 */
    private Integer platform;
    /** PAYEE_NAME */
    private String payeeName;
    /** 业务主体角色 */
    private Integer payeeRole;
    /** 订单ID */
    private Long orderId;
    /** 买家业务主体角色 */
    private Integer payerRole;
    /**
     * <p>默认构造函数。</p>
     */
    public SoCpRefund() {

    }

    /**
     * <p>REFUND_ID。</p>
     *
     * @return the REFUND_ID
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * <p>REFUND_ID。</p>
     *
     * @param refundId REFUND_ID。
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    /**
     * <p>TRANS_CODE。</p>
     *
     * @return the TRANS_CODE
     */
    public String getTransCode() {
        return transCode;
    }

    /**
     * <p>TRANS_CODE。</p>
     *
     * @param transCode TRANS_CODE。
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
     * <p>退货单号、退款单号、拒收单号。</p>
     *
     * @return the 退货单号、退款单号、拒收单号
     */
    public String getRefundCode() {
        return refundCode;
    }

    /**
     * <p>退货单号、退款单号、拒收单号。</p>
     *
     * @param refundCode 退货单号、退款单号、拒收单号。
     */
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    /**
     * <p>REFUND_AMOUNT。</p>
     *
     * @return the REFUND_AMOUNT
     */
    public java.math.BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * <p>REFUND_AMOUNT。</p>
     *
     * @param refundAmount REFUND_AMOUNT。
     */
    public void setRefundAmount(java.math.BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * <p>生成卖家计费单时更新。</p>
     *
     * @return the 生成卖家计费单时更新
     */
    public Long getSellerBillId() {
        return sellerBillId;
    }

    /**
     * <p>生成卖家计费单时更新。</p>
     *
     * @param sellerBillId 生成卖家计费单时更新。
     */
    public void setSellerBillId(Long sellerBillId) {
        this.sellerBillId = sellerBillId;
    }

    /**
     * <p>退款发生日期。</p>
     *
     * @return the 退款发生日期
     */
    public java.util.Date getRefundTime() {
        return refundTime;
    }

    /**
     * <p>退款发生日期。</p>
     *
     * @param refundTime 退款发生日期。
     */
    public void setRefundTime(java.util.Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * <p>0：退货退款 1：拒收退款 2：关闭订单。</p>
     *
     * @return the 0：退货退款 1：拒收退款 2：关闭订单
     */
    public Integer getRefundType() {
        return refundType;
    }

    /**
     * <p>0：退货退款 1：拒收退款 2：关闭订单。</p>
     *
     * @param refundType 0：退货退款 1：拒收退款 2：关闭订单。
     */
    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    /**
     * <p>0：不重新发货 1：重新发货（针对拒收退款）。</p>
     *
     * @return the 0：不重新发货 1：重新发货（针对拒收退款）
     */
    public String getReShipFlg() {
        return reShipFlg;
    }

    /**
     * <p>0：不重新发货 1：重新发货（针对拒收退款）。</p>
     *
     * @param reShipFlg 0：不重新发货 1：重新发货（针对拒收退款）。
     */
    public void setReShipFlg(String reShipFlg) {
        this.reShipFlg = reShipFlg;
    }

    /**
     * <p>计费项类型为买家时检索。</p>
     *
     * @return the 计费项类型为买家时检索
     */
    public String getPayerId() {
        return payerId;
    }

    /**
     * <p>计费项类型为买家时检索。</p>
     *
     * @param payerId 计费项类型为买家时检索。
     */
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    /**
     * <p>计费项类型是卖家时检索。</p>
     *
     * @return the 计费项类型是卖家时检索
     */
    public String getPayeeId() {
        return payeeId;
    }

    /**
     * <p>计费项类型是卖家时检索。</p>
     *
     * @param payeeId 计费项类型是卖家时检索。
     */
    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
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
     * <p>PAYEE_CODE。</p>
     *
     * @return the PAYEE_CODE
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * <p>PAYEE_CODE。</p>
     *
     * @param payeeCode PAYEE_CODE。
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode;
    }

    /**
     * <p>1:神农客 2:美侍客 3:大促会。</p>
     *
     * @return the 1:神农客 2:美侍客 3:大促会
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * <p>1:神农客 2:美侍客 3:大促会。</p>
     *
     * @param platform 1:神农客 2:美侍客 3:大促会。
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * <p>PAYEE_NAME。</p>
     *
     * @return the PAYEE_NAME
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * <p>PAYEE_NAME。</p>
     *
     * @param payeeName PAYEE_NAME。
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    /**
     * <p>业务主体角色。</p>
     *
     * @return the 业务主体角色
     */
    public Integer getPayeeRole() {
        return payeeRole;
    }

    /**
     * <p>业务主体角色。</p>
     *
     * @param payeeRole 业务主体角色。
     */
    public void setPayeeRole(Integer payeeRole) {
        this.payeeRole = payeeRole;
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
     * <p>买家业务主体角色。</p>
     *
     * @return the 买家业务主体角色
     */
    public Integer getPayerRole() {
        return payerRole;
    }

    /**
     * <p>买家业务主体角色。</p>
     *
     * @param payerRole 买家业务主体角色。
     */
    public void setPayerRole(Integer payerRole) {
        this.payerRole = payerRole;
    }

}
