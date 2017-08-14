package com.msk.seller.bean;

import com.msk.common.base.BaseBean;

public class SL241120Bean extends BaseBean {
    //卖家编号
    private String slCode;
    //卖家名称
    private String epName;
    //联系人电话
    private String slTel;
    //联系人姓名
    private String slContact;
    //分销章程确认结果
    private String affirmResult;

    /**
     * 获得slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * 设置slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * 获得epName
     */
    public String getEpName() {
        return epName;
    }

    /**
     * 设置epName
     */
    public void setEpName(String epName) {
        this.epName = epName;
    }

    /**
     * 获得slTel
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * 设置slTel
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * 获得slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * 设置slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * 获得affirmResult
     */
    public String getAffirmResult() {
        return affirmResult;
    }

    /**
     * 设置affirmResult
     */
    public void setAffirmResult(String affirmResult) {
        this.affirmResult = affirmResult;
    }
}
