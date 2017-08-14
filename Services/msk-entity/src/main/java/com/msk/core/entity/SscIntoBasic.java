/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_into_basic对应的SscIntoBasic。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscIntoBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 入库单ID */
    private java.lang.Long intoId;
    /** 入库单编号 */
    private java.lang.Long intoCode;
    /** 差异单编号 */
    private java.lang.String differCode;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编码 */
    private java.lang.String deliveryCode;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 预入库通知单ID */
    private java.lang.Long deliveryPreIntoId;
    /** 预入库通知单编号 */
    private java.lang.String deliveryPreIntoCode;
    /** 入库方式 */
    private java.lang.String intoType;
    /** 到货仓库 */
    private java.lang.String arriveWarehouse;
    /** 运抵仓库地址 */
    private java.lang.String arriveWarehouseAddr;
    /** 计划入库日期 */
    private java.util.Date eta;
    /** 到货日期 */
    private java.util.Date arriveDate;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscIntoBasic() {

    }

    /**
     * <p>入库单ID。</p>
     *
     * @return the 入库单ID
     */
    public java.lang.Long getIntoId() {
        return intoId;
    }

    /**
     * <p>入库单ID。</p>
     *
     * @param intoId 入库单ID。
     */
    public void setIntoId(java.lang.Long intoId) {
        this.intoId = intoId;
    }

    /**
     * <p>入库单编号。</p>
     *
     * @return the 入库单编号
     */
    public java.lang.Long getIntoCode() {
        return intoCode;
    }

    /**
     * <p>入库单编号。</p>
     *
     * @param intoCode 入库单编号。
     */
    public void setIntoCode(java.lang.Long intoCode) {
        this.intoCode = intoCode;
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
     * <p>预入库通知单ID。</p>
     *
     * @return the 预入库通知单ID
     */
    public java.lang.Long getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    /**
     * <p>预入库通知单ID。</p>
     *
     * @param deliveryPreIntoId 预入库通知单ID。
     */
    public void setDeliveryPreIntoId(java.lang.Long deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    /**
     * <p>预入库通知单编号。</p>
     *
     * @return the 预入库通知单编号
     */
    public java.lang.String getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    /**
     * <p>预入库通知单编号。</p>
     *
     * @param deliveryPreIntoCode 预入库通知单编号。
     */
    public void setDeliveryPreIntoCode(java.lang.String deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    /**
     * <p>入库方式。</p>
     *
     * @return the 入库方式
     */
    public java.lang.String getIntoType() {
        return intoType;
    }

    /**
     * <p>入库方式。</p>
     *
     * @param intoType 入库方式。
     */
    public void setIntoType(java.lang.String intoType) {
        this.intoType = intoType;
    }

    /**
     * <p>到货仓库。</p>
     *
     * @return the 到货仓库
     */
    public java.lang.String getArriveWarehouse() {
        return arriveWarehouse;
    }

    /**
     * <p>到货仓库。</p>
     *
     * @param arriveWarehouse 到货仓库。
     */
    public void setArriveWarehouse(java.lang.String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }

    /**
     * <p>运抵仓库地址。</p>
     *
     * @return the 运抵仓库地址
     */
    public java.lang.String getArriveWarehouseAddr() {
        return arriveWarehouseAddr;
    }

    /**
     * <p>运抵仓库地址。</p>
     *
     * @param arriveWarehouseAddr 运抵仓库地址。
     */
    public void setArriveWarehouseAddr(java.lang.String arriveWarehouseAddr) {
        this.arriveWarehouseAddr = arriveWarehouseAddr;
    }

    /**
     * <p>计划入库日期。</p>
     *
     * @return the 计划入库日期
     */
    public java.util.Date getEta() {
        return eta;
    }

    /**
     * <p>计划入库日期。</p>
     *
     * @param eta 计划入库日期。
     */
    public void setEta(java.util.Date eta) {
        this.eta = eta;
    }

    /**
     * <p>到货日期。</p>
     *
     * @return the 到货日期
     */
    public java.util.Date getArriveDate() {
        return arriveDate;
    }

    /**
     * <p>到货日期。</p>
     *
     * @param arriveDate 到货日期。
     */
    public void setArriveDate(java.util.Date arriveDate) {
        this.arriveDate = arriveDate;
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

}
