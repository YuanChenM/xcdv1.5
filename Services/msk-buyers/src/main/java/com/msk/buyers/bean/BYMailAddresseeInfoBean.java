package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;

/**
 *
 * @author zhang_jian4
 */
public class BYMailAddresseeInfoBean extends BaseEntity {

    private Long mailAddresseeId;
    /** 收件人姓名 */
    private String addresseeName;
    /** 收件人地址 */
    private String addresseeEmail;
    /** 发送方式 */
    private String sendType;
    /** 组别 */
    private String sendGroup;

    public Long getMailAddresseeId() {
        return mailAddresseeId;
    }

    public void setMailAddresseeId(Long mailAddresseeId) {
        this.mailAddresseeId = mailAddresseeId;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getAddresseeEmail() {
        return addresseeEmail;
    }

    public void setAddresseeEmail(String addresseeEmail) {
        this.addresseeEmail = addresseeEmail;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendGroup() {
        return sendGroup;
    }

    public void setSendGroup(String sendGroup) {
        this.sendGroup = sendGroup;
    }
}
