/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_order_basic对应的SscDeliveryOrderBasic。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryOrderBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 发货订单ID */
    private Long deliveryId;
    /** 发货订单编号 */
    private String deliveryCode;
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 关联合同类型 */
    private String contractRelationType;
    /** 发货批次 */
    private Integer deliveryBatch;
    /** 供应商企业ID */
    private Long supplierId;
    /** 供应商 */
    private String supplierName;
    /** 供应商编码 */
    private String supplierCode;
    /** 采购商企业ID */
    private Long purchaserId;
    /** 采购商 */
    private String purchaserName;
    /** 采购商编码 */
    private String purchaserCode;
    /** 发货日期 */
    private java.util.Date etd;
    /** 到货日期 */
    private java.util.Date eta;
    /** 发货仓库 */
    private String deliveryWarehouse;
    /** 发货地址 */
    private String deliveryWarehouseAddr;
    /** 到货仓库 */
    private String arriveWarehouse;
    /** 到货仓库地址 */
    private String arriveWarehouseAddr;
    /** 发货订单状态 */
    private String deliveryStatus;
    /** 运费结算方式 */
    private String freightSettleMethod;
    /** 运费单价(元/吨公里) */
    private java.math.BigDecimal freightUnit;
    /** 里程 */
    private java.math.BigDecimal mileage;
    /** 每吨运费 */
    private java.math.BigDecimal transportUnit;
    /** 运费 */
    private java.math.BigDecimal transportCost;
    /** 发货订单总金额 */
    private java.math.BigDecimal amount;
    /** 备注 */
    private String remark;
    /** 采购商审核ID */
    private String purchaserAuditId;
    /** 采购商审核名称 */
    private String purchaserAuditName;
    /** 采购商审核时间 */
    private java.util.Date purchaserAuditTime;
    /** 供应商确认ID */
    private String supplierConfirmId;
    /** 供应商确认名称 */
    private String supplierConfirmName;
    /** 供应商确认时间 */
    private java.util.Date supplierConfirmTime;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 物流区名称 */
    private String lgcsAreaName;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryOrderBasic() {

    }

    /**
     * <p>发货订单ID。</p>
     *
     * @return the 发货订单ID
     */
    public Long getDeliveryId() {
        return deliveryId;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @param deliveryId 发货订单ID。
     */
    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @return the 发货订单编号
     */
    public String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @param deliveryCode 发货订单编号。
     */
    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>合同ID。</p>
     *
     * @return the 合同ID
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * <p>合同ID。</p>
     *
     * @param contractId 合同ID。
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractCode 合同编号。
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * <p>关联合同类型。</p>
     *
     * @return the 关联合同类型
     */
    public String getContractRelationType() {
        return contractRelationType;
    }

    /**
     * <p>关联合同类型。</p>
     *
     * @param contractRelationType 关联合同类型。
     */
    public void setContractRelationType(String contractRelationType) {
        this.contractRelationType = contractRelationType;
    }

    /**
     * <p>发货批次。</p>
     *
     * @return the 发货批次
     */
    public Integer getDeliveryBatch() {
        return deliveryBatch;
    }

    /**
     * <p>发货批次。</p>
     *
     * @param deliveryBatch 发货批次。
     */
    public void setDeliveryBatch(Integer deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    /**
     * <p>供应商企业ID。</p>
     *
     * @return the 供应商企业ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>供应商企业ID。</p>
     *
     * @param supplierId 供应商企业ID。
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>供应商。</p>
     *
     * @return the 供应商
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>供应商。</p>
     *
     * @param supplierName 供应商。
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>采购商企业ID。</p>
     *
     * @return the 采购商企业ID
     */
    public Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * <p>采购商企业ID。</p>
     *
     * @param purchaserId 采购商企业ID。
     */
    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * <p>采购商。</p>
     *
     * @return the 采购商
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * <p>采购商。</p>
     *
     * @param purchaserName 采购商。
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @return the 采购商编码
     */
    public String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @param purchaserCode 采购商编码。
     */
    public void setPurchaserCode(String purchaserCode) {
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
    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    /**
     * <p>发货仓库。</p>
     *
     * @param deliveryWarehouse 发货仓库。
     */
    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    /**
     * <p>发货地址。</p>
     *
     * @return the 发货地址
     */
    public String getDeliveryWarehouseAddr() {
        return deliveryWarehouseAddr;
    }

    /**
     * <p>发货地址。</p>
     *
     * @param deliveryWarehouseAddr 发货地址。
     */
    public void setDeliveryWarehouseAddr(String deliveryWarehouseAddr) {
        this.deliveryWarehouseAddr = deliveryWarehouseAddr;
    }

    /**
     * <p>到货仓库。</p>
     *
     * @return the 到货仓库
     */
    public String getArriveWarehouse() {
        return arriveWarehouse;
    }

    /**
     * <p>到货仓库。</p>
     *
     * @param arriveWarehouse 到货仓库。
     */
    public void setArriveWarehouse(String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }

    /**
     * <p>到货仓库地址。</p>
     *
     * @return the 到货仓库地址
     */
    public String getArriveWarehouseAddr() {
        return arriveWarehouseAddr;
    }

    /**
     * <p>到货仓库地址。</p>
     *
     * @param arriveWarehouseAddr 到货仓库地址。
     */
    public void setArriveWarehouseAddr(String arriveWarehouseAddr) {
        this.arriveWarehouseAddr = arriveWarehouseAddr;
    }

    /**
     * <p>发货订单状态。</p>
     *
     * @return the 发货订单状态
     */
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * <p>发货订单状态。</p>
     *
     * @param deliveryStatus 发货订单状态。
     */
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * <p>运费结算方式。</p>
     *
     * @return the 运费结算方式
     */
    public String getFreightSettleMethod() {
        return freightSettleMethod;
    }

    /**
     * <p>运费结算方式。</p>
     *
     * @param freightSettleMethod 运费结算方式。
     */
    public void setFreightSettleMethod(String freightSettleMethod) {
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
    public java.math.BigDecimal getTransportCost() {
        return transportCost;
    }

    /**
     * <p>运费。</p>
     *
     * @param transportCost 运费。
     */
    public void setTransportCost(java.math.BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    /**
     * <p>发货订单总金额。</p>
     *
     * @return the 发货订单总金额
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    /**
     * <p>发货订单总金额。</p>
     *
     * @param amount 发货订单总金额。
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
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
     * <p>采购商审核ID。</p>
     *
     * @return the 采购商审核ID
     */
    public String getPurchaserAuditId() {
        return purchaserAuditId;
    }

    /**
     * <p>采购商审核ID。</p>
     *
     * @param purchaserAuditId 采购商审核ID。
     */
    public void setPurchaserAuditId(String purchaserAuditId) {
        this.purchaserAuditId = purchaserAuditId;
    }

    /**
     * <p>采购商审核名称。</p>
     *
     * @return the 采购商审核名称
     */
    public String getPurchaserAuditName() {
        return purchaserAuditName;
    }

    /**
     * <p>采购商审核名称。</p>
     *
     * @param purchaserAuditName 采购商审核名称。
     */
    public void setPurchaserAuditName(String purchaserAuditName) {
        this.purchaserAuditName = purchaserAuditName;
    }

    /**
     * <p>采购商审核时间。</p>
     *
     * @return the 采购商审核时间
     */
    public java.util.Date getPurchaserAuditTime() {
        return purchaserAuditTime;
    }

    /**
     * <p>采购商审核时间。</p>
     *
     * @param purchaserAuditTime 采购商审核时间。
     */
    public void setPurchaserAuditTime(java.util.Date purchaserAuditTime) {
        this.purchaserAuditTime = purchaserAuditTime;
    }

    /**
     * <p>供应商确认ID。</p>
     *
     * @return the 供应商确认ID
     */
    public String getSupplierConfirmId() {
        return supplierConfirmId;
    }

    /**
     * <p>供应商确认ID。</p>
     *
     * @param supplierConfirmId 供应商确认ID。
     */
    public void setSupplierConfirmId(String supplierConfirmId) {
        this.supplierConfirmId = supplierConfirmId;
    }

    /**
     * <p>供应商确认名称。</p>
     *
     * @return the 供应商确认名称
     */
    public String getSupplierConfirmName() {
        return supplierConfirmName;
    }

    /**
     * <p>供应商确认名称。</p>
     *
     * @param supplierConfirmName 供应商确认名称。
     */
    public void setSupplierConfirmName(String supplierConfirmName) {
        this.supplierConfirmName = supplierConfirmName;
    }

    /**
     * <p>供应商确认时间。</p>
     *
     * @return the 供应商确认时间
     */
    public java.util.Date getSupplierConfirmTime() {
        return supplierConfirmTime;
    }

    /**
     * <p>供应商确认时间。</p>
     *
     * @param supplierConfirmTime 供应商确认时间。
     */
    public void setSupplierConfirmTime(java.util.Date supplierConfirmTime) {
        this.supplierConfirmTime = supplierConfirmTime;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @return the 物流区编码
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>物流区编码。</p>
     *
     * @param lgcsAreaCode 物流区编码。
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @return the 物流区名称
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>物流区名称。</p>
     *
     * @param lgcsAreaName 物流区名称。
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

}
