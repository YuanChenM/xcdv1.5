/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_pd_control对应的SscDeliveryPdControl。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryPdControl extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 唯一ID */
    private java.lang.Long id;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 发货车次 */
    private java.lang.Integer deliveryBatch;
    /** 计划到货日期 */
    private java.util.Date planArriveDate;
    /** 实际到货日期 */
    private java.util.Date realArriveDate;
    /** 计划确定车辆时间 */
    private java.util.Date planChooseVehicleDate;
    /** 实际确定车辆时间 */
    private java.util.Date realChooseVehicleDate;
    /** 计划装车结束时间 */
    private java.util.Date planIntoVehicleDate;
    /** 实际装车结束时间 */
    private java.util.Date realIntoVehicleDate;
    /** 计划发货时间 */
    private java.util.Date planOffDate;
    /** 实际发货时间 */
    private java.util.Date realOffDate;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryPdControl() {

    }

    /**
     * <p>唯一ID。</p>
     *
     * @return the 唯一ID
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * <p>唯一ID。</p>
     *
     * @param id 唯一ID。
     */
    public void setId(java.lang.Long id) {
        this.id = id;
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
     * <p>发货车次。</p>
     *
     * @return the 发货车次
     */
    public java.lang.Integer getDeliveryBatch() {
        return deliveryBatch;
    }

    /**
     * <p>发货车次。</p>
     *
     * @param deliveryBatch 发货车次。
     */
    public void setDeliveryBatch(java.lang.Integer deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    /**
     * <p>计划到货日期。</p>
     *
     * @return the 计划到货日期
     */
    public java.util.Date getPlanArriveDate() {
        return planArriveDate;
    }

    /**
     * <p>计划到货日期。</p>
     *
     * @param planArriveDate 计划到货日期。
     */
    public void setPlanArriveDate(java.util.Date planArriveDate) {
        this.planArriveDate = planArriveDate;
    }

    /**
     * <p>实际到货日期。</p>
     *
     * @return the 实际到货日期
     */
    public java.util.Date getRealArriveDate() {
        return realArriveDate;
    }

    /**
     * <p>实际到货日期。</p>
     *
     * @param realArriveDate 实际到货日期。
     */
    public void setRealArriveDate(java.util.Date realArriveDate) {
        this.realArriveDate = realArriveDate;
    }

    /**
     * <p>计划确定车辆时间。</p>
     *
     * @return the 计划确定车辆时间
     */
    public java.util.Date getPlanChooseVehicleDate() {
        return planChooseVehicleDate;
    }

    /**
     * <p>计划确定车辆时间。</p>
     *
     * @param planChooseVehicleDate 计划确定车辆时间。
     */
    public void setPlanChooseVehicleDate(java.util.Date planChooseVehicleDate) {
        this.planChooseVehicleDate = planChooseVehicleDate;
    }

    /**
     * <p>实际确定车辆时间。</p>
     *
     * @return the 实际确定车辆时间
     */
    public java.util.Date getRealChooseVehicleDate() {
        return realChooseVehicleDate;
    }

    /**
     * <p>实际确定车辆时间。</p>
     *
     * @param realChooseVehicleDate 实际确定车辆时间。
     */
    public void setRealChooseVehicleDate(java.util.Date realChooseVehicleDate) {
        this.realChooseVehicleDate = realChooseVehicleDate;
    }

    /**
     * <p>计划装车结束时间。</p>
     *
     * @return the 计划装车结束时间
     */
    public java.util.Date getPlanIntoVehicleDate() {
        return planIntoVehicleDate;
    }

    /**
     * <p>计划装车结束时间。</p>
     *
     * @param planIntoVehicleDate 计划装车结束时间。
     */
    public void setPlanIntoVehicleDate(java.util.Date planIntoVehicleDate) {
        this.planIntoVehicleDate = planIntoVehicleDate;
    }

    /**
     * <p>实际装车结束时间。</p>
     *
     * @return the 实际装车结束时间
     */
    public java.util.Date getRealIntoVehicleDate() {
        return realIntoVehicleDate;
    }

    /**
     * <p>实际装车结束时间。</p>
     *
     * @param realIntoVehicleDate 实际装车结束时间。
     */
    public void setRealIntoVehicleDate(java.util.Date realIntoVehicleDate) {
        this.realIntoVehicleDate = realIntoVehicleDate;
    }

    /**
     * <p>计划发货时间。</p>
     *
     * @return the 计划发货时间
     */
    public java.util.Date getPlanOffDate() {
        return planOffDate;
    }

    /**
     * <p>计划发货时间。</p>
     *
     * @param planOffDate 计划发货时间。
     */
    public void setPlanOffDate(java.util.Date planOffDate) {
        this.planOffDate = planOffDate;
    }

    /**
     * <p>实际发货时间。</p>
     *
     * @return the 实际发货时间
     */
    public java.util.Date getRealOffDate() {
        return realOffDate;
    }

    /**
     * <p>实际发货时间。</p>
     *
     * @param realOffDate 实际发货时间。
     */
    public void setRealOffDate(java.util.Date realOffDate) {
        this.realOffDate = realOffDate;
    }

}
