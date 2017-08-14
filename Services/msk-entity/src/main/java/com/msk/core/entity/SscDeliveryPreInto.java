/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_pre_into对应的SscDeliveryPreInto。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryPreInto extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 发货预入库单ID */
    private java.lang.Long deliveryPreIntoId;
    /** 发货预入库单编号 */
    private java.lang.Long deliveryPreIntoCode;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 发货确认单ID */
    private java.lang.Long deliveryConfirmId;
    /** 发货确认单编号 */
    private java.lang.String deliveryConfirmCode;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编号 */
    private java.lang.String deliveryCode;
    /** 发货批次 */
    private java.lang.Integer deliveryBatch;
    /** 车次 */
    private java.lang.Integer vehicleNumber;
    /** 生产商ID */
    private java.lang.Long supplierId;
    /** 生产商 */
    private java.lang.String supplierName;
    /** 生产商编码 */
    private java.lang.String supplierCode;
    /** 采购商ID */
    private java.lang.Long purchaserId;
    /** 采购商 */
    private java.lang.String purchaserName;
    /** 采购商编码 */
    private java.lang.String purchaserCode;
    /** 预计发货日期 */
    private java.util.Date etd;
    /** 预计到货日期 */
    private java.util.Date eta;
    /** 实际发货日期 */
    private java.util.Date deliveryDate;
    /** 实际到货日期 */
    private java.util.Date arriveDate;
    /** 发货仓库 */
    private java.lang.String deliveryWarehouse;
    /** 发货仓库地址 */
    private java.lang.String deliveryWarehouseAddr;
    /** 到货仓库 */
    private java.lang.String arriveWarehouse;
    /** 到货仓库地址 */
    private java.lang.String arriveWarehouseAddr;
    /** 里程 */
    private java.math.BigDecimal mileage;
    /** 司机名称 */
    private java.lang.String driverName;
    /** 司机手机号 */
    private java.lang.String driverTel;
    /** 发货责任人 */
    private java.lang.String deliveryResponsible;
    /** 发货责任人联系方式 */
    private java.lang.String deliveryResponsibleTel;
    /** 发货执行人 */
    private java.lang.String deliveryExecutor;
    /** 发货执行人联系方式 */
    private java.lang.String deliveryExecutorTel;
    /** 运输单位名称 */
    private java.lang.String trafficCompanyName;
    /** 运输单位责任人 */
    private java.lang.String trafficCompanyResponsible;
    /** 运输单位责任人联系电话 */
    private java.lang.String trafficCompanyResponsibleTel;
    /** 运输单位执行人 */
    private java.lang.String trafficCompanyExecutor;
    /** 运输单位执行人联系电话 */
    private java.lang.String trafficCompanyExecutorTel;
    /** 仓管负责人 */
    private java.lang.String warehouseKeeper;
    /** 仓管负责人联系电话 */
    private java.lang.String warehouseKeeperTel;
    /** 验收负责人 */
    private java.lang.String accepter;
    /** 验收负责人联系电话 */
    private java.lang.String accepterTel;
    /** 车牌号 */
    private java.lang.String licPlateNumber;
    /** 车辆类型 */
    private java.lang.String vehicleType;
    /** 发货备注 */
    private java.lang.String sendRemark;
    /** 收货状态 */
    private java.lang.Integer productRecvStatus;
    /** 企业盖章三证ID */
    private java.lang.String businessFileId;
    /** 企业盖章三证名称 */
    private java.lang.String businessFileName;
    /** 动物检疫合格证明ID */
    private java.lang.String quarantineFileId;
    /** 动物检疫合格证明名称 */
    private java.lang.String quarantineFileName;
    /** 出库清单ID */
    private java.lang.String inventoryFileId;
    /** 出库清单名称 */
    private java.lang.String inventoryFileName;
    /** 有效期官方检测报告ID */
    private java.lang.String reportFileId;
    /** 有效期官方检测报告名称 */
    private java.lang.String reportFileName;
    /** 到货备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryPreInto() {

    }

    /**
     * <p>发货预入库单ID。</p>
     *
     * @return the 发货预入库单ID
     */
    public java.lang.Long getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    /**
     * <p>发货预入库单ID。</p>
     *
     * @param deliveryPreIntoId 发货预入库单ID。
     */
    public void setDeliveryPreIntoId(java.lang.Long deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    /**
     * <p>发货预入库单编号。</p>
     *
     * @return the 发货预入库单编号
     */
    public java.lang.Long getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    /**
     * <p>发货预入库单编号。</p>
     *
     * @param deliveryPreIntoCode 发货预入库单编号。
     */
    public void setDeliveryPreIntoCode(java.lang.Long deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
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
    public java.lang.Integer getDeliveryBatch() {
        return deliveryBatch;
    }

    /**
     * <p>发货批次。</p>
     *
     * @param deliveryBatch 发货批次。
     */
    public void setDeliveryBatch(java.lang.Integer deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    /**
     * <p>车次。</p>
     *
     * @return the 车次
     */
    public java.lang.Integer getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * <p>车次。</p>
     *
     * @param vehicleNumber 车次。
     */
    public void setVehicleNumber(java.lang.Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * <p>生产商ID。</p>
     *
     * @return the 生产商ID
     */
    public java.lang.Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>生产商ID。</p>
     *
     * @param supplierId 生产商ID。
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
     * <p>采购商ID。</p>
     *
     * @return the 采购商ID
     */
    public java.lang.Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * <p>采购商ID。</p>
     *
     * @param purchaserId 采购商ID。
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
     * <p>预计发货日期。</p>
     *
     * @return the 预计发货日期
     */
    public java.util.Date getEtd() {
        return etd;
    }

    /**
     * <p>预计发货日期。</p>
     *
     * @param etd 预计发货日期。
     */
    public void setEtd(java.util.Date etd) {
        this.etd = etd;
    }

    /**
     * <p>预计到货日期。</p>
     *
     * @return the 预计到货日期
     */
    public java.util.Date getEta() {
        return eta;
    }

    /**
     * <p>预计到货日期。</p>
     *
     * @param eta 预计到货日期。
     */
    public void setEta(java.util.Date eta) {
        this.eta = eta;
    }

    /**
     * <p>实际发货日期。</p>
     *
     * @return the 实际发货日期
     */
    public java.util.Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * <p>实际发货日期。</p>
     *
     * @param deliveryDate 实际发货日期。
     */
    public void setDeliveryDate(java.util.Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * <p>实际到货日期。</p>
     *
     * @return the 实际到货日期
     */
    public java.util.Date getArriveDate() {
        return arriveDate;
    }

    /**
     * <p>实际到货日期。</p>
     *
     * @param arriveDate 实际到货日期。
     */
    public void setArriveDate(java.util.Date arriveDate) {
        this.arriveDate = arriveDate;
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
     * <p>到货仓库地址。</p>
     *
     * @return the 到货仓库地址
     */
    public java.lang.String getArriveWarehouseAddr() {
        return arriveWarehouseAddr;
    }

    /**
     * <p>到货仓库地址。</p>
     *
     * @param arriveWarehouseAddr 到货仓库地址。
     */
    public void setArriveWarehouseAddr(java.lang.String arriveWarehouseAddr) {
        this.arriveWarehouseAddr = arriveWarehouseAddr;
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
     * <p>司机名称。</p>
     *
     * @return the 司机名称
     */
    public java.lang.String getDriverName() {
        return driverName;
    }

    /**
     * <p>司机名称。</p>
     *
     * @param driverName 司机名称。
     */
    public void setDriverName(java.lang.String driverName) {
        this.driverName = driverName;
    }

    /**
     * <p>司机手机号。</p>
     *
     * @return the 司机手机号
     */
    public java.lang.String getDriverTel() {
        return driverTel;
    }

    /**
     * <p>司机手机号。</p>
     *
     * @param driverTel 司机手机号。
     */
    public void setDriverTel(java.lang.String driverTel) {
        this.driverTel = driverTel;
    }

    /**
     * <p>发货责任人。</p>
     *
     * @return the 发货责任人
     */
    public java.lang.String getDeliveryResponsible() {
        return deliveryResponsible;
    }

    /**
     * <p>发货责任人。</p>
     *
     * @param deliveryResponsible 发货责任人。
     */
    public void setDeliveryResponsible(java.lang.String deliveryResponsible) {
        this.deliveryResponsible = deliveryResponsible;
    }

    /**
     * <p>发货责任人联系方式。</p>
     *
     * @return the 发货责任人联系方式
     */
    public java.lang.String getDeliveryResponsibleTel() {
        return deliveryResponsibleTel;
    }

    /**
     * <p>发货责任人联系方式。</p>
     *
     * @param deliveryResponsibleTel 发货责任人联系方式。
     */
    public void setDeliveryResponsibleTel(java.lang.String deliveryResponsibleTel) {
        this.deliveryResponsibleTel = deliveryResponsibleTel;
    }

    /**
     * <p>发货执行人。</p>
     *
     * @return the 发货执行人
     */
    public java.lang.String getDeliveryExecutor() {
        return deliveryExecutor;
    }

    /**
     * <p>发货执行人。</p>
     *
     * @param deliveryExecutor 发货执行人。
     */
    public void setDeliveryExecutor(java.lang.String deliveryExecutor) {
        this.deliveryExecutor = deliveryExecutor;
    }

    /**
     * <p>发货执行人联系方式。</p>
     *
     * @return the 发货执行人联系方式
     */
    public java.lang.String getDeliveryExecutorTel() {
        return deliveryExecutorTel;
    }

    /**
     * <p>发货执行人联系方式。</p>
     *
     * @param deliveryExecutorTel 发货执行人联系方式。
     */
    public void setDeliveryExecutorTel(java.lang.String deliveryExecutorTel) {
        this.deliveryExecutorTel = deliveryExecutorTel;
    }

    /**
     * <p>运输单位名称。</p>
     *
     * @return the 运输单位名称
     */
    public java.lang.String getTrafficCompanyName() {
        return trafficCompanyName;
    }

    /**
     * <p>运输单位名称。</p>
     *
     * @param trafficCompanyName 运输单位名称。
     */
    public void setTrafficCompanyName(java.lang.String trafficCompanyName) {
        this.trafficCompanyName = trafficCompanyName;
    }

    /**
     * <p>运输单位责任人。</p>
     *
     * @return the 运输单位责任人
     */
    public java.lang.String getTrafficCompanyResponsible() {
        return trafficCompanyResponsible;
    }

    /**
     * <p>运输单位责任人。</p>
     *
     * @param trafficCompanyResponsible 运输单位责任人。
     */
    public void setTrafficCompanyResponsible(java.lang.String trafficCompanyResponsible) {
        this.trafficCompanyResponsible = trafficCompanyResponsible;
    }

    /**
     * <p>运输单位责任人联系电话。</p>
     *
     * @return the 运输单位责任人联系电话
     */
    public java.lang.String getTrafficCompanyResponsibleTel() {
        return trafficCompanyResponsibleTel;
    }

    /**
     * <p>运输单位责任人联系电话。</p>
     *
     * @param trafficCompanyResponsibleTel 运输单位责任人联系电话。
     */
    public void setTrafficCompanyResponsibleTel(java.lang.String trafficCompanyResponsibleTel) {
        this.trafficCompanyResponsibleTel = trafficCompanyResponsibleTel;
    }

    /**
     * <p>运输单位执行人。</p>
     *
     * @return the 运输单位执行人
     */
    public java.lang.String getTrafficCompanyExecutor() {
        return trafficCompanyExecutor;
    }

    /**
     * <p>运输单位执行人。</p>
     *
     * @param trafficCompanyExecutor 运输单位执行人。
     */
    public void setTrafficCompanyExecutor(java.lang.String trafficCompanyExecutor) {
        this.trafficCompanyExecutor = trafficCompanyExecutor;
    }

    /**
     * <p>运输单位执行人联系电话。</p>
     *
     * @return the 运输单位执行人联系电话
     */
    public java.lang.String getTrafficCompanyExecutorTel() {
        return trafficCompanyExecutorTel;
    }

    /**
     * <p>运输单位执行人联系电话。</p>
     *
     * @param trafficCompanyExecutorTel 运输单位执行人联系电话。
     */
    public void setTrafficCompanyExecutorTel(java.lang.String trafficCompanyExecutorTel) {
        this.trafficCompanyExecutorTel = trafficCompanyExecutorTel;
    }

    /**
     * <p>仓管负责人。</p>
     *
     * @return the 仓管负责人
     */
    public java.lang.String getWarehouseKeeper() {
        return warehouseKeeper;
    }

    /**
     * <p>仓管负责人。</p>
     *
     * @param warehouseKeeper 仓管负责人。
     */
    public void setWarehouseKeeper(java.lang.String warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    /**
     * <p>仓管负责人联系电话。</p>
     *
     * @return the 仓管负责人联系电话
     */
    public java.lang.String getWarehouseKeeperTel() {
        return warehouseKeeperTel;
    }

    /**
     * <p>仓管负责人联系电话。</p>
     *
     * @param warehouseKeeperTel 仓管负责人联系电话。
     */
    public void setWarehouseKeeperTel(java.lang.String warehouseKeeperTel) {
        this.warehouseKeeperTel = warehouseKeeperTel;
    }

    /**
     * <p>验收负责人。</p>
     *
     * @return the 验收负责人
     */
    public java.lang.String getAccepter() {
        return accepter;
    }

    /**
     * <p>验收负责人。</p>
     *
     * @param accepter 验收负责人。
     */
    public void setAccepter(java.lang.String accepter) {
        this.accepter = accepter;
    }

    /**
     * <p>验收负责人联系电话。</p>
     *
     * @return the 验收负责人联系电话
     */
    public java.lang.String getAccepterTel() {
        return accepterTel;
    }

    /**
     * <p>验收负责人联系电话。</p>
     *
     * @param accepterTel 验收负责人联系电话。
     */
    public void setAccepterTel(java.lang.String accepterTel) {
        this.accepterTel = accepterTel;
    }

    /**
     * <p>车牌号。</p>
     *
     * @return the 车牌号
     */
    public java.lang.String getLicPlateNumber() {
        return licPlateNumber;
    }

    /**
     * <p>车牌号。</p>
     *
     * @param licPlateNumber 车牌号。
     */
    public void setLicPlateNumber(java.lang.String licPlateNumber) {
        this.licPlateNumber = licPlateNumber;
    }

    /**
     * <p>车辆类型。</p>
     *
     * @return the 车辆类型
     */
    public java.lang.String getVehicleType() {
        return vehicleType;
    }

    /**
     * <p>车辆类型。</p>
     *
     * @param vehicleType 车辆类型。
     */
    public void setVehicleType(java.lang.String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * <p>发货备注。</p>
     *
     * @return the 发货备注
     */
    public java.lang.String getSendRemark() {
        return sendRemark;
    }

    /**
     * <p>发货备注。</p>
     *
     * @param sendRemark 发货备注。
     */
    public void setSendRemark(java.lang.String sendRemark) {
        this.sendRemark = sendRemark;
    }

    /**
     * <p>收货状态。</p>
     *
     * @return the 收货状态
     */
    public java.lang.Integer getProductRecvStatus() {
        return productRecvStatus;
    }

    /**
     * <p>收货状态。</p>
     *
     * @param productRecvStatus 收货状态。
     */
    public void setProductRecvStatus(java.lang.Integer productRecvStatus) {
        this.productRecvStatus = productRecvStatus;
    }

    /**
     * <p>企业盖章三证ID。</p>
     *
     * @return the 企业盖章三证ID
     */
    public java.lang.String getBusinessFileId() {
        return businessFileId;
    }

    /**
     * <p>企业盖章三证ID。</p>
     *
     * @param businessFileId 企业盖章三证ID。
     */
    public void setBusinessFileId(java.lang.String businessFileId) {
        this.businessFileId = businessFileId;
    }

    /**
     * <p>企业盖章三证名称。</p>
     *
     * @return the 企业盖章三证名称
     */
    public java.lang.String getBusinessFileName() {
        return businessFileName;
    }

    /**
     * <p>企业盖章三证名称。</p>
     *
     * @param businessFileName 企业盖章三证名称。
     */
    public void setBusinessFileName(java.lang.String businessFileName) {
        this.businessFileName = businessFileName;
    }

    /**
     * <p>动物检疫合格证明ID。</p>
     *
     * @return the 动物检疫合格证明ID
     */
    public java.lang.String getQuarantineFileId() {
        return quarantineFileId;
    }

    /**
     * <p>动物检疫合格证明ID。</p>
     *
     * @param quarantineFileId 动物检疫合格证明ID。
     */
    public void setQuarantineFileId(java.lang.String quarantineFileId) {
        this.quarantineFileId = quarantineFileId;
    }

    /**
     * <p>动物检疫合格证明名称。</p>
     *
     * @return the 动物检疫合格证明名称
     */
    public java.lang.String getQuarantineFileName() {
        return quarantineFileName;
    }

    /**
     * <p>动物检疫合格证明名称。</p>
     *
     * @param quarantineFileName 动物检疫合格证明名称。
     */
    public void setQuarantineFileName(java.lang.String quarantineFileName) {
        this.quarantineFileName = quarantineFileName;
    }

    /**
     * <p>出库清单ID。</p>
     *
     * @return the 出库清单ID
     */
    public java.lang.String getInventoryFileId() {
        return inventoryFileId;
    }

    /**
     * <p>出库清单ID。</p>
     *
     * @param inventoryFileId 出库清单ID。
     */
    public void setInventoryFileId(java.lang.String inventoryFileId) {
        this.inventoryFileId = inventoryFileId;
    }

    /**
     * <p>出库清单名称。</p>
     *
     * @return the 出库清单名称
     */
    public java.lang.String getInventoryFileName() {
        return inventoryFileName;
    }

    /**
     * <p>出库清单名称。</p>
     *
     * @param inventoryFileName 出库清单名称。
     */
    public void setInventoryFileName(java.lang.String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;
    }

    /**
     * <p>有效期官方检测报告ID。</p>
     *
     * @return the 有效期官方检测报告ID
     */
    public java.lang.String getReportFileId() {
        return reportFileId;
    }

    /**
     * <p>有效期官方检测报告ID。</p>
     *
     * @param reportFileId 有效期官方检测报告ID。
     */
    public void setReportFileId(java.lang.String reportFileId) {
        this.reportFileId = reportFileId;
    }

    /**
     * <p>有效期官方检测报告名称。</p>
     *
     * @return the 有效期官方检测报告名称
     */
    public java.lang.String getReportFileName() {
        return reportFileName;
    }

    /**
     * <p>有效期官方检测报告名称。</p>
     *
     * @param reportFileName 有效期官方检测报告名称。
     */
    public void setReportFileName(java.lang.String reportFileName) {
        this.reportFileName = reportFileName;
    }

    /**
     * <p>到货备注。</p>
     *
     * @return the 到货备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>到货备注。</p>
     *
     * @param remark 到货备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

}
