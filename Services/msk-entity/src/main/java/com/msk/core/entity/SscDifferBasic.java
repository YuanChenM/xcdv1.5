/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_differ_basic对应的SscDifferBasic。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDifferBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 差异单ID */
    private java.lang.Long differId;
    /** 差异单编号 */
    private java.lang.String differCode;
    /** 发货预入库单ID */
    private java.lang.String deliveryPreIntoId;
    /** 发货预入库单编号 */
    private java.lang.String deliveryPreIntoCode;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编码 */
    private java.lang.String deliveryCode;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 确认状态 */
    private java.lang.String confirmStatus;
    /** 备注 */
    private java.lang.String remark;
    /** 确认人ID */
    private java.lang.String confirmId;
    /** 确认人名称 */
    private java.lang.String confirmName;
    /** 确认时间 */
    private java.util.Date confirmTime;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDifferBasic() {

    }

    /**
     * <p>差异单ID。</p>
     *
     * @return the 差异单ID
     */
    public java.lang.Long getDifferId() {
        return differId;
    }

    /**
     * <p>差异单ID。</p>
     *
     * @param differId 差异单ID。
     */
    public void setDifferId(java.lang.Long differId) {
        this.differId = differId;
    }

    /**
     * <p>差异单编号。</p>
     *
     * @return the 差异单编号
     */
    public java.lang.String getDifferCode() {
        return differCode;
    }

    /**
     * <p>差异单编号。</p>
     *
     * @param differCode 差异单编号。
     */
    public void setDifferCode(java.lang.String differCode) {
        this.differCode = differCode;
    }

    /**
     * <p>发货预入库单ID。</p>
     *
     * @return the 发货预入库单ID
     */
    public java.lang.String getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    /**
     * <p>发货预入库单ID。</p>
     *
     * @param deliveryPreIntoId 发货预入库单ID。
     */
    public void setDeliveryPreIntoId(java.lang.String deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    /**
     * <p>发货预入库单编号。</p>
     *
     * @return the 发货预入库单编号
     */
    public java.lang.String getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    /**
     * <p>发货预入库单编号。</p>
     *
     * @param deliveryPreIntoCode 发货预入库单编号。
     */
    public void setDeliveryPreIntoCode(java.lang.String deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @return the 发货订单ID
     */
    public java.lang.Long getDeliveryId() {
        return deliveryId;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @param deliveryId 发货订单ID。
     */
    public void setDeliveryId(java.lang.Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * <p>发货订单编码。</p>
     *
     * @return the 发货订单编码
     */
    public java.lang.String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货订单编码。</p>
     *
     * @param deliveryCode 发货订单编码。
     */
    public void setDeliveryCode(java.lang.String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>合同ID。</p>
     *
     * @return the 合同ID
     */
    public java.lang.Long getContractId() {
        return contractId;
    }

    /**
     * <p>合同ID。</p>
     *
     * @param contractId 合同ID。
     */
    public void setContractId(java.lang.Long contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public java.lang.String getContractCode() {
        return contractCode;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractCode 合同编号。
     */
    public void setContractCode(java.lang.String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * <p>确认状态。</p>
     *
     * @return the 确认状态
     */
    public java.lang.String getConfirmStatus() {
        return confirmStatus;
    }

    /**
     * <p>确认状态。</p>
     *
     * @param confirmStatus 确认状态。
     */
    public void setConfirmStatus(java.lang.String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * <p>确认人ID。</p>
     *
     * @return the 确认人ID
     */
    public java.lang.String getConfirmId() {
        return confirmId;
    }

    /**
     * <p>确认人ID。</p>
     *
     * @param confirmId 确认人ID。
     */
    public void setConfirmId(java.lang.String confirmId) {
        this.confirmId = confirmId;
    }

    /**
     * <p>确认人名称。</p>
     *
     * @return the 确认人名称
     */
    public java.lang.String getConfirmName() {
        return confirmName;
    }

    /**
     * <p>确认人名称。</p>
     *
     * @param confirmName 确认人名称。
     */
    public void setConfirmName(java.lang.String confirmName) {
        this.confirmName = confirmName;
    }

    /**
     * <p>确认时间。</p>
     *
     * @return the 确认时间
     */
    public java.util.Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * <p>确认时间。</p>
     *
     * @param confirmTime 确认时间。
     */
    public void setConfirmTime(java.util.Date confirmTime) {
        this.confirmTime = confirmTime;
    }

}
