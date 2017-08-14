/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_confirm_basic对应的SscDeliveryConfirmBasic。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryConfirmBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 发货确认单ID */
    private java.lang.Long deliveryConfirmId;
    /** 发货确认单编号 */
    private java.lang.String deliveryConfirmCode;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编号 */
    private java.lang.String deliveryCode;
    /** 发货批次 */
    private java.lang.Long deliveryBatch;
    /** 供应商企业ID */
    private java.lang.Long supplierId;
    /** 生产商 */
    private java.lang.String supplierName;
    /** 生产商编码 */
    private java.lang.String supplierCode;
    /** 采购商企业ID */
    private java.lang.Long purchaserId;
    /** 采购商 */
    private java.lang.String purchaserName;
    /** 采购商编码 */
    private java.lang.String purchaserCode;
    /** 发货日期 */
    private java.util.Date etd;
    /** 到货日期 */
    private java.util.Date eta;
    /** 发货仓库 */
    private java.lang.String deliveryWarehouse;
    /** 发货仓库地址 */
    private java.lang.String deliveryWarehouseAddr;
    /** 到货仓库 */
    private java.lang.String arriveWarehouse;
    /** 运抵仓库地址 */
    private java.lang.String arriveWarehouseAddr;
    /** 运费结算方式 */
    private java.lang.String freightSettleMethod;
    /** 运费单价(元/吨公里) */
    private java.math.BigDecimal freightUnit;
    /** 里程 */
    private java.math.BigDecimal mileage;
    /** 每吨运费 */
    private java.math.BigDecimal transportUnit;
    /** 运费 */
    private java.math.BigDecimal freight;
    /** 采购方确认ID */
    private java.lang.String byConfirmId;
    /** 采购方确认人 */
    private java.lang.String byConfirmName;
    /** 采购方确认时间 */
    private java.util.Date byConfirmTime;
    /** 采购方确认状态 */
    private java.lang.String byConfirmStatus;
    /** 采购方确认原因 */
    private java.lang.String byConfirmReason;
    /** 仓库方确认ID */
    private java.lang.String whConfirmId;
    /** 仓库方确认人 */
    private java.lang.String whConfirmName;
    /** 仓库方确认时间 */
    private java.util.Date whConfirmTime;
    /** 仓库方确认状态 */
    private java.lang.String whConfirmStatus;
    /** 仓库方确认原因 */
    private java.lang.String whConfirmReason;
    /** 生产方确认ID */
    private java.lang.String pdConfirmId;
    /** 生产方确认人 */
    private java.lang.String pdConfirmName;
    /** 生产方确认时间 */
    private java.util.Date pdConfirmTime;
    /** 生产方确认状态 */
    private java.lang.String pdConfirmStatus;
    /** 生产方确认原因 */
    private java.lang.String pdConfirmReason;
    /** 发货确认单状态 */
    private java.lang.String deliveryConfirmStatus;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryConfirmBasic() {

    }

    /**
     * <p>发货确认单ID。</p>
     *
     * @return the 发货确认单ID
     */
    public java.lang.Long getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    /**
     * <p>发货确认单ID。</p>
     *
     * @param deliveryConfirmId 发货确认单ID。
     */
    public void setDeliveryConfirmId(java.lang.Long deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }

    /**
     * <p>发货确认单编号。</p>
     *
     * @return the 发货确认单编号
     */
    public java.lang.String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    /**
     * <p>发货确认单编号。</p>
     *
     * @param deliveryConfirmCode 发货确认单编号。
     */
    public void setDeliveryConfirmCode(java.lang.String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
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
     * <p>发货订单编号。</p>
     *
     * @return the 发货订单编号
     */
    public java.lang.String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @param deliveryCode 发货订单编号。
     */
    public void setDeliveryCode(java.lang.String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>发货批次。</p>
     *
     * @return the 发货批次
     */
    public java.lang.Long getDeliveryBatch() {
        return deliveryBatch;
    }

    /**
     * <p>发货批次。</p>
     *
     * @param deliveryBatch 发货批次。
     */
    public void setDeliveryBatch(java.lang.Long deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    /**
     * <p>供应商企业ID。</p>
     *
     * @return the 供应商企业ID
     */
    public java.lang.Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>供应商企业ID。</p>
     *
     * @param supplierId 供应商企业ID。
     */
    public void setSupplierId(java.lang.Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>生产商。</p>
     *
     * @return the 生产商
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>生产商。</p>
     *
     * @param supplierName 生产商。
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @return the 生产商编码
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @param supplierCode 生产商编码。
     */
    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>采购商企业ID。</p>
     *
     * @return the 采购商企业ID
     */
    public java.lang.Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * <p>采购商企业ID。</p>
     *
     * @param purchaserId 采购商企业ID。
     */
    public void setPurchaserId(java.lang.Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * <p>采购商。</p>
     *
     * @return the 采购商
     */
    public java.lang.String getPurchaserName() {
        return purchaserName;
    }

    /**
     * <p>采购商。</p>
     *
     * @param purchaserName 采购商。
     */
    public void setPurchaserName(java.lang.String purchaserName) {
        this.purchaserName = purchaserName;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @return the 采购商编码
     */
    public java.lang.String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @param purchaserCode 采购商编码。
     */
    public void setPurchaserCode(java.lang.String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    /**
     * <p>发货日期。</p>
     *
     * @return the 发货日期
     */
    public java.util.Date getEtd() {
        return etd;
    }

    /**
     * <p>发货日期。</p>
     *
     * @param etd 发货日期。
     */
    public void setEtd(java.util.Date etd) {
        this.etd = etd;
    }

    /**
     * <p>到货日期。</p>
     *
     * @return the 到货日期
     */
    public java.util.Date getEta() {
        return eta;
    }

    /**
     * <p>到货日期。</p>
     *
     * @param eta 到货日期。
     */
    public void setEta(java.util.Date eta) {
        this.eta = eta;
    }

    /**
     * <p>发货仓库。</p>
     *
     * @return the 发货仓库
     */
    public java.lang.String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    /**
     * <p>发货仓库。</p>
     *
     * @param deliveryWarehouse 发货仓库。
     */
    public void setDeliveryWarehouse(java.lang.String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    /**
     * <p>发货仓库地址。</p>
     *
     * @return the 发货仓库地址
     */
    public java.lang.String getDeliveryWarehouseAddr() {
        return deliveryWarehouseAddr;
    }

    /**
     * <p>发货仓库地址。</p>
     *
     * @param deliveryWarehouseAddr 发货仓库地址。
     */
    public void setDeliveryWarehouseAddr(java.lang.String deliveryWarehouseAddr) {
        this.deliveryWarehouseAddr = deliveryWarehouseAddr;
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
     * <p>运费结算方式。</p>
     *
     * @return the 运费结算方式
     */
    public java.lang.String getFreightSettleMethod() {
        return freightSettleMethod;
    }

    /**
     * <p>运费结算方式。</p>
     *
     * @param freightSettleMethod 运费结算方式。
     */
    public void setFreightSettleMethod(java.lang.String freightSettleMethod) {
        this.freightSettleMethod = freightSettleMethod;
    }

    /**
     * <p>运费单价(元/吨公里)。</p>
     *
     * @return the 运费单价(元/吨公里)
     */
    public java.math.BigDecimal getFreightUnit() {
        return freightUnit;
    }

    /**
     * <p>运费单价(元/吨公里)。</p>
     *
     * @param freightUnit 运费单价(元/吨公里)。
     */
    public void setFreightUnit(java.math.BigDecimal freightUnit) {
        this.freightUnit = freightUnit;
    }

    /**
     * <p>里程。</p>
     *
     * @return the 里程
     */
    public java.math.BigDecimal getMileage() {
        return mileage;
    }

    /**
     * <p>里程。</p>
     *
     * @param mileage 里程。
     */
    public void setMileage(java.math.BigDecimal mileage) {
        this.mileage = mileage;
    }

    /**
     * <p>每吨运费。</p>
     *
     * @return the 每吨运费
     */
    public java.math.BigDecimal getTransportUnit() {
        return transportUnit;
    }

    /**
     * <p>每吨运费。</p>
     *
     * @param transportUnit 每吨运费。
     */
    public void setTransportUnit(java.math.BigDecimal transportUnit) {
        this.transportUnit = transportUnit;
    }

    /**
     * <p>运费。</p>
     *
     * @return the 运费
     */
    public java.math.BigDecimal getFreight() {
        return freight;
    }

    /**
     * <p>运费。</p>
     *
     * @param freight 运费。
     */
    public void setFreight(java.math.BigDecimal freight) {
        this.freight = freight;
    }

    /**
     * <p>采购方确认ID。</p>
     *
     * @return the 采购方确认ID
     */
    public java.lang.String getByConfirmId() {
        return byConfirmId;
    }

    /**
     * <p>采购方确认ID。</p>
     *
     * @param byConfirmId 采购方确认ID。
     */
    public void setByConfirmId(java.lang.String byConfirmId) {
        this.byConfirmId = byConfirmId;
    }

    /**
     * <p>采购方确认人。</p>
     *
     * @return the 采购方确认人
     */
    public java.lang.String getByConfirmName() {
        return byConfirmName;
    }

    /**
     * <p>采购方确认人。</p>
     *
     * @param byConfirmName 采购方确认人。
     */
    public void setByConfirmName(java.lang.String byConfirmName) {
        this.byConfirmName = byConfirmName;
    }

    /**
     * <p>采购方确认时间。</p>
     *
     * @return the 采购方确认时间
     */
    public java.util.Date getByConfirmTime() {
        return byConfirmTime;
    }

    /**
     * <p>采购方确认时间。</p>
     *
     * @param byConfirmTime 采购方确认时间。
     */
    public void setByConfirmTime(java.util.Date byConfirmTime) {
        this.byConfirmTime = byConfirmTime;
    }

    /**
     * <p>采购方确认状态。</p>
     *
     * @return the 采购方确认状态
     */
    public java.lang.String getByConfirmStatus() {
        return byConfirmStatus;
    }

    /**
     * <p>采购方确认状态。</p>
     *
     * @param byConfirmStatus 采购方确认状态。
     */
    public void setByConfirmStatus(java.lang.String byConfirmStatus) {
        this.byConfirmStatus = byConfirmStatus;
    }

    /**
     * <p>采购方确认原因。</p>
     *
     * @return the 采购方确认原因
     */
    public java.lang.String getByConfirmReason() {
        return byConfirmReason;
    }

    /**
     * <p>采购方确认原因。</p>
     *
     * @param byConfirmReason 采购方确认原因。
     */
    public void setByConfirmReason(java.lang.String byConfirmReason) {
        this.byConfirmReason = byConfirmReason;
    }

    /**
     * <p>仓库方确认ID。</p>
     *
     * @return the 仓库方确认ID
     */
    public java.lang.String getWhConfirmId() {
        return whConfirmId;
    }

    /**
     * <p>仓库方确认ID。</p>
     *
     * @param whConfirmId 仓库方确认ID。
     */
    public void setWhConfirmId(java.lang.String whConfirmId) {
        this.whConfirmId = whConfirmId;
    }

    /**
     * <p>仓库方确认人。</p>
     *
     * @return the 仓库方确认人
     */
    public java.lang.String getWhConfirmName() {
        return whConfirmName;
    }

    /**
     * <p>仓库方确认人。</p>
     *
     * @param whConfirmName 仓库方确认人。
     */
    public void setWhConfirmName(java.lang.String whConfirmName) {
        this.whConfirmName = whConfirmName;
    }

    /**
     * <p>仓库方确认时间。</p>
     *
     * @return the 仓库方确认时间
     */
    public java.util.Date getWhConfirmTime() {
        return whConfirmTime;
    }

    /**
     * <p>仓库方确认时间。</p>
     *
     * @param whConfirmTime 仓库方确认时间。
     */
    public void setWhConfirmTime(java.util.Date whConfirmTime) {
        this.whConfirmTime = whConfirmTime;
    }

    /**
     * <p>仓库方确认状态。</p>
     *
     * @return the 仓库方确认状态
     */
    public java.lang.String getWhConfirmStatus() {
        return whConfirmStatus;
    }

    /**
     * <p>仓库方确认状态。</p>
     *
     * @param whConfirmStatus 仓库方确认状态。
     */
    public void setWhConfirmStatus(java.lang.String whConfirmStatus) {
        this.whConfirmStatus = whConfirmStatus;
    }

    /**
     * <p>仓库方确认原因。</p>
     *
     * @return the 仓库方确认原因
     */
    public java.lang.String getWhConfirmReason() {
        return whConfirmReason;
    }

    /**
     * <p>仓库方确认原因。</p>
     *
     * @param whConfirmReason 仓库方确认原因。
     */
    public void setWhConfirmReason(java.lang.String whConfirmReason) {
        this.whConfirmReason = whConfirmReason;
    }

    /**
     * <p>生产方确认ID。</p>
     *
     * @return the 生产方确认ID
     */
    public java.lang.String getPdConfirmId() {
        return pdConfirmId;
    }

    /**
     * <p>生产方确认ID。</p>
     *
     * @param pdConfirmId 生产方确认ID。
     */
    public void setPdConfirmId(java.lang.String pdConfirmId) {
        this.pdConfirmId = pdConfirmId;
    }

    /**
     * <p>生产方确认人。</p>
     *
     * @return the 生产方确认人
     */
    public java.lang.String getPdConfirmName() {
        return pdConfirmName;
    }

    /**
     * <p>生产方确认人。</p>
     *
     * @param pdConfirmName 生产方确认人。
     */
    public void setPdConfirmName(java.lang.String pdConfirmName) {
        this.pdConfirmName = pdConfirmName;
    }

    /**
     * <p>生产方确认时间。</p>
     *
     * @return the 生产方确认时间
     */
    public java.util.Date getPdConfirmTime() {
        return pdConfirmTime;
    }

    /**
     * <p>生产方确认时间。</p>
     *
     * @param pdConfirmTime 生产方确认时间。
     */
    public void setPdConfirmTime(java.util.Date pdConfirmTime) {
        this.pdConfirmTime = pdConfirmTime;
    }

    /**
     * <p>生产方确认状态。</p>
     *
     * @return the 生产方确认状态
     */
    public java.lang.String getPdConfirmStatus() {
        return pdConfirmStatus;
    }

    /**
     * <p>生产方确认状态。</p>
     *
     * @param pdConfirmStatus 生产方确认状态。
     */
    public void setPdConfirmStatus(java.lang.String pdConfirmStatus) {
        this.pdConfirmStatus = pdConfirmStatus;
    }

    /**
     * <p>生产方确认原因。</p>
     *
     * @return the 生产方确认原因
     */
    public java.lang.String getPdConfirmReason() {
        return pdConfirmReason;
    }

    /**
     * <p>生产方确认原因。</p>
     *
     * @param pdConfirmReason 生产方确认原因。
     */
    public void setPdConfirmReason(java.lang.String pdConfirmReason) {
        this.pdConfirmReason = pdConfirmReason;
    }

    /**
     * <p>发货确认单状态。</p>
     *
     * @return the 发货确认单状态
     */
    public java.lang.String getDeliveryConfirmStatus() {
        return deliveryConfirmStatus;
    }

    /**
     * <p>发货确认单状态。</p>
     *
     * @param deliveryConfirmStatus 发货确认单状态。
     */
    public void setDeliveryConfirmStatus(java.lang.String deliveryConfirmStatus) {
        this.deliveryConfirmStatus = deliveryConfirmStatus;
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
