/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_vehicle_detail对应的SscVehicleDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscVehicleDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private java.lang.Long id;
    /** 车次ID */
    private java.lang.Long vehicleId;
    /** 预入库单编号 */
    private java.lang.String deliveryPreIntoCode;
    /** 车次编号 */
    private java.lang.String vehicleCode;
    /** 司机名称 */
    private java.lang.String driverName;
    /** 司机手机号 */
    private java.lang.String driverTel;
    /** 司机驾驶证号 */
    private java.lang.String driverLicNumber;
    /** 车牌号 */
    private java.lang.String licPlateNumber;
    /** 预计发货日期 */
    private java.util.Date expectDeliveryDate;
    /** 预计到货日期 */
    private java.util.Date expectArriveDate;
    /** 核定载重量 */
    private java.math.BigDecimal approvedLoad;
    /** 产品属性码 */
    private java.lang.String productAttrCode;
    /** 计划发货重量 */
    private java.lang.Integer planDeliveryQua;
    /** 计划发货箱数 */
    private java.lang.Integer planDeliveryBox;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscVehicleDetail() {

    }

    /**
     * <p>ID。</p>
     *
     * @return the ID
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * <p>ID。</p>
     *
     * @param id ID。
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     * <p>车次ID。</p>
     *
     * @return the 车次ID
     */
    public java.lang.Long getVehicleId() {
        return vehicleId;
    }

    /**
     * <p>车次ID。</p>
     *
     * @param vehicleId 车次ID。
     */
    public void setVehicleId(java.lang.Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * <p>预入库单编号。</p>
     *
     * @return the 预入库单编号
     */
    public java.lang.String getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    /**
     * <p>预入库单编号。</p>
     *
     * @param deliveryPreIntoCode 预入库单编号。
     */
    public void setDeliveryPreIntoCode(java.lang.String deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    /**
     * <p>车次编号。</p>
     *
     * @return the 车次编号
     */
    public java.lang.String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * <p>车次编号。</p>
     *
     * @param vehicleCode 车次编号。
     */
    public void setVehicleCode(java.lang.String vehicleCode) {
        this.vehicleCode = vehicleCode;
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
     * <p>司机驾驶证号。</p>
     *
     * @return the 司机驾驶证号
     */
    public java.lang.String getDriverLicNumber() {
        return driverLicNumber;
    }

    /**
     * <p>司机驾驶证号。</p>
     *
     * @param driverLicNumber 司机驾驶证号。
     */
    public void setDriverLicNumber(java.lang.String driverLicNumber) {
        this.driverLicNumber = driverLicNumber;
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
     * <p>预计发货日期。</p>
     *
     * @return the 预计发货日期
     */
    public java.util.Date getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    /**
     * <p>预计发货日期。</p>
     *
     * @param expectDeliveryDate 预计发货日期。
     */
    public void setExpectDeliveryDate(java.util.Date expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }

    /**
     * <p>预计到货日期。</p>
     *
     * @return the 预计到货日期
     */
    public java.util.Date getExpectArriveDate() {
        return expectArriveDate;
    }

    /**
     * <p>预计到货日期。</p>
     *
     * @param expectArriveDate 预计到货日期。
     */
    public void setExpectArriveDate(java.util.Date expectArriveDate) {
        this.expectArriveDate = expectArriveDate;
    }

    /**
     * <p>核定载重量。</p>
     *
     * @return the 核定载重量
     */
    public java.math.BigDecimal getApprovedLoad() {
        return approvedLoad;
    }

    /**
     * <p>核定载重量。</p>
     *
     * @param approvedLoad 核定载重量。
     */
    public void setApprovedLoad(java.math.BigDecimal approvedLoad) {
        this.approvedLoad = approvedLoad;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @return the 产品属性码
     */
    public java.lang.String getProductAttrCode() {
        return productAttrCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @param productAttrCode 产品属性码。
     */
    public void setProductAttrCode(java.lang.String productAttrCode) {
        this.productAttrCode = productAttrCode;
    }

    /**
     * <p>计划发货重量。</p>
     *
     * @return the 计划发货重量
     */
    public java.lang.Integer getPlanDeliveryQua() {
        return planDeliveryQua;
    }

    /**
     * <p>计划发货重量。</p>
     *
     * @param planDeliveryQua 计划发货重量。
     */
    public void setPlanDeliveryQua(java.lang.Integer planDeliveryQua) {
        this.planDeliveryQua = planDeliveryQua;
    }

    /**
     * <p>计划发货箱数。</p>
     *
     * @return the 计划发货箱数
     */
    public java.lang.Integer getPlanDeliveryBox() {
        return planDeliveryBox;
    }

    /**
     * <p>计划发货箱数。</p>
     *
     * @param planDeliveryBox 计划发货箱数。
     */
    public void setPlanDeliveryBox(java.lang.Integer planDeliveryBox) {
        this.planDeliveryBox = planDeliveryBox;
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
