/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_deliver对应的SoDeliver。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoDeliver extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 配送单ID */
    private Long deliverId;
    /** 配送单编码 */
    private String deliverCode;
    /** 配送车辆 */
    private String deliverVehicle;
    /** 分批订单ID */
    private String subOrderId;
    /** 配送人 */
    private String deliverPerson;
    /** 配送人电话 */
    private String personMobile;
    /** 配送时间 */
    private java.util.Date deliverDate;
    /** 配送方式 */
    private Integer deliverMode;
    /** 预计到货时间 */
    private java.util.Date expectReceiveDate;
    /** 实际到货时间 */
    private java.util.Date actualReceiveDate;
    /** 备注 */
    private String remarks;
    /** 备注2 */
    private String remarks2;
    /** 备注3 */
    private String remarks3;
    /** 配送状态 */
    private Integer deliverStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoDeliver() {

    }

    /**
     * <p>配送单ID。</p>
     *
     * @return the 配送单ID
     */
    public Long getDeliverId() {
        return deliverId;
    }

    /**
     * <p>配送单ID。</p>
     *
     * @param deliverId 配送单ID。
     */
    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    /**
     * <p>配送单编码。</p>
     *
     * @return the 配送单编码
     */
    public String getDeliverCode() {
        return deliverCode;
    }

    /**
     * <p>配送单编码。</p>
     *
     * @param deliverCode 配送单编码。
     */
    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    /**
     * <p>配送车辆。</p>
     *
     * @return the 配送车辆
     */
    public String getDeliverVehicle() {
        return deliverVehicle;
    }

    /**
     * <p>配送车辆。</p>
     *
     * @param deliverVehicle 配送车辆。
     */
    public void setDeliverVehicle(String deliverVehicle) {
        this.deliverVehicle = deliverVehicle;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public String getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    /**
     * <p>配送人。</p>
     *
     * @return the 配送人
     */
    public String getDeliverPerson() {
        return deliverPerson;
    }

    /**
     * <p>配送人。</p>
     *
     * @param deliverPerson 配送人。
     */
    public void setDeliverPerson(String deliverPerson) {
        this.deliverPerson = deliverPerson;
    }

    /**
     * <p>配送人电话。</p>
     *
     * @return the 配送人电话
     */
    public String getPersonMobile() {
        return personMobile;
    }

    /**
     * <p>配送人电话。</p>
     *
     * @param personMobile 配送人电话。
     */
    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    /**
     * <p>配送时间。</p>
     *
     * @return the 配送时间
     */
    public java.util.Date getDeliverDate() {
        return deliverDate;
    }

    /**
     * <p>配送时间。</p>
     *
     * @param deliverDate 配送时间。
     */
    public void setDeliverDate(java.util.Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    /**
     * <p>配送方式。</p>
     *
     * @return the 配送方式
     */
    public Integer getDeliverMode() {
        return deliverMode;
    }

    /**
     * <p>配送方式。</p>
     *
     * @param deliverMode 配送方式。
     */
    public void setDeliverMode(Integer deliverMode) {
        this.deliverMode = deliverMode;
    }

    /**
     * <p>预计到货时间。</p>
     *
     * @return the 预计到货时间
     */
    public java.util.Date getExpectReceiveDate() {
        return expectReceiveDate;
    }

    /**
     * <p>预计到货时间。</p>
     *
     * @param expectReceiveDate 预计到货时间。
     */
    public void setExpectReceiveDate(java.util.Date expectReceiveDate) {
        this.expectReceiveDate = expectReceiveDate;
    }

    /**
     * <p>实际到货时间。</p>
     *
     * @return the 实际到货时间
     */
    public java.util.Date getActualReceiveDate() {
        return actualReceiveDate;
    }

    /**
     * <p>实际到货时间。</p>
     *
     * @param actualReceiveDate 实际到货时间。
     */
    public void setActualReceiveDate(java.util.Date actualReceiveDate) {
        this.actualReceiveDate = actualReceiveDate;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * <p>备注。</p>
     *
     * @param remarks 备注。
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * <p>备注2。</p>
     *
     * @return the 备注2
     */
    public String getRemarks2() {
        return remarks2;
    }

    /**
     * <p>备注2。</p>
     *
     * @param remarks2 备注2。
     */
    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    /**
     * <p>备注3。</p>
     *
     * @return the 备注3
     */
    public String getRemarks3() {
        return remarks3;
    }

    /**
     * <p>备注3。</p>
     *
     * @param remarks3 备注3。
     */
    public void setRemarks3(String remarks3) {
        this.remarks3 = remarks3;
    }

    /**
     * <p>配送状态。</p>
     *
     * @return the 配送状态
     */
    public Integer getDeliverStatus() {
        return deliverStatus;
    }

    /**
     * <p>配送状态。</p>
     *
     * @param deliverStatus 配送状态。
     */
    public void setDeliverStatus(Integer deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

}
