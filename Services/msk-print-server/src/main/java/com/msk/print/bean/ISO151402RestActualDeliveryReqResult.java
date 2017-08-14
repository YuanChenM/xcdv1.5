package com.msk.print.bean;


import java.io.Serializable;

/**
 * 实际配送信息 result
 * Created by zhang_qiang1 on 2016/10/9.
 */
public class ISO151402RestActualDeliveryReqResult implements Serializable {

    private Long deliverId;// 配送单ID

    private String deliverCode="";//配送单编码

    private String deliverVehicle="";//配送车辆

    private Long subOrderId;// 分批订单ID

    private String deliverPerson="";//配送人

    private String personMobile="";//配送人电话

    private String deliverDate="";//配送时间

    private String deliverMode="";//配送方式

    private String expectReceiveDate="";//预计到货时间

    private String actualReceiveDate="";//实际到货时间


    public Long getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public String getDeliverVehicle() {
        return deliverVehicle;
    }

    public void setDeliverVehicle(String deliverVehicle) {
        this.deliverVehicle = deliverVehicle;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getDeliverPerson() {
        return deliverPerson;
    }

    public void setDeliverPerson(String deliverPerson) {
        this.deliverPerson = deliverPerson;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getDeliverMode() {
        return deliverMode;
    }

    public void setDeliverMode(String deliverMode) {
        this.deliverMode = deliverMode;
    }

    public String getExpectReceiveDate() {
        return expectReceiveDate;
    }

    public void setExpectReceiveDate(String expectReceiveDate) {
        this.expectReceiveDate = expectReceiveDate;
    }

    public String getActualReceiveDate() {
        return actualReceiveDate;
    }

    public void setActualReceiveDate(String actualReceiveDate) {
        this.actualReceiveDate = actualReceiveDate;
    }
}
