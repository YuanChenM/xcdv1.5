package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

/**
 * Created by yang_chunyan on 2016/8/1.
 */
public class BS2102105Bean extends BaseEntity {
    private String dpGroupId;

    private String slCode;
    private String houseCode;
    private String houseClass;
    private String houseContact;
    private String sex;
    private String weChat;
    private String houseTel;

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseClass() {
        return houseClass;
    }

    public void setHouseClass(String houseClass) {
        this.houseClass = houseClass;
    }

    public String getHouseContact() {
        return houseContact;
    }

    public void setHouseContact(String houseContact) {
        this.houseContact = houseContact;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getHouseTel() {
        return houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    public String getDpGroupId() {
        return dpGroupId;
    }

    public void setDpGroupId(String dpGroupId) {
        this.dpGroupId = dpGroupId;
    }
}
