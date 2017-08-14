package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * Created by wang_shuai on 2016/8/9.
 */
public class BaseOrderDelivery extends BaseResult {
    /**配送单号*/
    private String deliverCode;
    /**配送人*/
    private String deliverPerson;
    /**配送人电话*/
    private String personMobile;
    /**配送时间:yyyy-MM-dd hh:mm:ss*/
    private String deliverDate;
    /**配送方式:1：陆运 2：空运 3：海运*/
    private Integer deliverMode;
    /**预计到货时间:yyyy-MM-dd hh:mm:ss*/
    private String expectReceiveDate;
    /**备注*/
    private String remarks;

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
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

    public Integer getDeliverMode() {
        return deliverMode;
    }

    public void setDeliverMode(Integer deliverMode) {
        this.deliverMode = deliverMode;
    }

    public String getExpectReceiveDate() {
        return expectReceiveDate;
    }

    public void setExpectReceiveDate(String expectReceiveDate) {
        this.expectReceiveDate = expectReceiveDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
