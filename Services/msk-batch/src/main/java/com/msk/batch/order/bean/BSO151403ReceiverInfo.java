package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by wang_jianzhou on 2016/8/24.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"receiverName","receiverTel","receiverWeixin","receiverQq","receiverMail","receiverProvince","receiverCity","receiverDistrict","receiverAddr","receiverAddr2","receiverAddrKey","receiverDeType"})
public class BSO151403ReceiverInfo extends BaseEntity {
    /** RECEIVER_NAME */
    private String receiverName;
    /** RECEIVER_TEL */
    private String receiverTel;
    /** RECEIVER_QQ */
    private String receiverQq;
    /** RECEIVER_WEIXIN */
    private String receiverWeixin;
    /** RECEIVER_MAIL */
    private String receiverMail;
    /** RECEIVER_PROVINCE */
    private String receiverProvince;
    /** RECEIVER_CITY */
    private String receiverCity;
    /** RECEIVER_DISTRICT */
    private String receiverDistrict;
    /** RECEIVER_ADDR */
    private String receiverAddr;
    /** DELIVERY_TYPE */
    private String receiverDeType;
    /** RECEIVER_ADDR2 */
    private String receiverAddr2;
    /** RECEIVER_ADDR_KEY */
    private String receiverAddrKey;

    @XmlElement(name = "NAME",defaultValue = "")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @XmlElement(name = "TEL",defaultValue = "")
    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    @XmlElement(name = "QQ",defaultValue = "")
    public String getReceiverQq() {
        return receiverQq;
    }

    public void setReceiverQq(String receiverQq) {
        this.receiverQq = receiverQq;
    }

    @XmlElement(name = "WX",defaultValue = "")
    public String getReceiverWeixin() {
        return receiverWeixin;
    }

    public void setReceiverWeixin(String receiverWeixin) {
        this.receiverWeixin = receiverWeixin;
    }

    @XmlElement(name = "EMAIL",defaultValue = "")
    public String getReceiverMail() {
        return receiverMail;
    }

    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
    }

    @XmlElement(name = "PROVINCE",defaultValue = "")
    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    @XmlElement(name = "CITY",defaultValue = "")
    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    @XmlElement(name = "DISTRICT",defaultValue = "")
    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    @XmlElement(name = "ADDR",defaultValue = "")
    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    @XmlElement(name = "DELIVERY_TYPE",defaultValue = "")
    public String getReceiverDeType() {
        return receiverDeType;
    }

    public void setReceiverDeType(String receiverDeType) {
        this.receiverDeType = receiverDeType;
    }

    @XmlElement(name = "ADDR2",defaultValue = "")
    public String getReceiverAddr2() {
        return receiverAddr2;
    }

    public void setReceiverAddr2(String receiverAddr2) {
        this.receiverAddr2 = receiverAddr2;
    }

    @XmlElement(name = "ADDRKEY",defaultValue = "")
    public String getReceiverAddrKey() {
        return receiverAddrKey;
    }

    public void setReceiverAddrKey(String receiverAddrKey) {
        this.receiverAddrKey = receiverAddrKey;
    }
}
