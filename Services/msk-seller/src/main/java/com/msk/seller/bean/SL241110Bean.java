package com.msk.seller.bean;

import com.msk.core.entity.SlDistReguSug;

public class SL241110Bean extends SlDistReguSug {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    //卖家编号
    private String slCode;
    //卖家名称
    private String epName;
    //联系人电话
    private String slTel;
    //联系人姓名
    private String slContact;
    //卖家企业id
    private Integer epId;
    //确认结果
    private String agreeFlgName;
    /**
     * CHAP_NO
     */
    private Integer chapNo;
    /**
     * CHAP_TITLE
     */
    private String chapTitle;

    /**
     * 获得chapNo
     */
    public Integer getChapNo() {
        return chapNo;
    }

    /**
     * 设置chapNo
     */
    public void setChapNo(Integer chapNo) {
        this.chapNo = chapNo;
    }

    /**
     * 获得chapTitle
     */
    public String getChapTitle() {
        return chapTitle;
    }

    /**
     * 设置chapTitle
     */
    public void setChapTitle(String chapTitle) {
        this.chapTitle = chapTitle;
    }

    /**
     * 获得epId
     */
    public Integer getEpId() {
        return epId;
    }

    /**
     * 设置epId
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    /**
     * 获得agreeFlgName
     */
    public String getAgreeFlgName() {
        return agreeFlgName;
    }

    /**
     * 设置agreeFlgName
     */
    public void setAgreeFlgName(String agreeFlgName) {
        this.agreeFlgName = agreeFlgName;
    }

    /**
     * 获得slCode
     */
    @Override
    public String getSlCode() {
        return slCode;
    }

    /**
     * 设置slCode
     */
    @Override
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
}
