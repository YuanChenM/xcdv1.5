package com.msk.product.bean;


import com.msk.common.base.BaseBean;

public class PD141401Bean extends BaseBean {
    private static final long serialVersionUID = 1L;

    private String slCodeDis;
    private Integer epId;
    private String slMainClass;
    private String slTel;
    private String slShowName;
    private String slCode;
    private String slContact;
    /**
     * 认证状态 '0:未认证,1:认证中,2:已认证',
     */
    private Integer authStatus;
    private String cityName;//城市中文名


    /**
     * Getter method for property <tt>slContact</tt>.
     *
     * @return property value of slContact
     */
    public String getSlContact() {
        return slContact;
    }

    /**
     * Setter method for property <tt>slContact</tt>.
     *
     * @param slContact value to be assigned to property slContact
     */
    public void setSlContact(String slContact) {
        this.slContact = slContact;
    }

    /**
     * Getter method for property <tt>slCodeDis</tt>.
     *
     * @return property value of slCodeDis
     */
    public String getSlCodeDis() {
        return slCodeDis;
    }

    /**
     * Setter method for property <tt>slCodeDis</tt>.
     *
     * @param slCodeDis value to be assigned to property slCodeDis
     */
    public void setSlCodeDis(String slCodeDis) {
        this.slCodeDis = slCodeDis;
    }

    /**
     * Getter method for property <tt>epId</tt>.
     *
     * @return property value of epId
     */
    public Integer getEpId() {
        return epId;
    }

    /**
     * Setter method for property <tt>epId</tt>.
     *
     * @param epId value to be assigned to property epId
     */
    public void setEpId(Integer epId) {
        this.epId = epId;
    }

    /**
     * Getter method for property <tt>slMainClass</tt>.
     *
     * @return property value of slMainClass
     */
    public String getSlMainClass() {
        return slMainClass;
    }

    /**
     * Setter method for property <tt>slMainClass</tt>.
     *
     * @param slMainClass value to be assigned to property slMainClass
     */
    public void setSlMainClass(String slMainClass) {
        this.slMainClass = slMainClass;
    }

    /**
     * Getter method for property <tt>slTel</tt>.
     *
     * @return property value of slTel
     */
    public String getSlTel() {
        return slTel;
    }

    /**
     * Setter method for property <tt>slTel</tt>.
     *
     * @param slTel value to be assigned to property slTel
     */
    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

    /**
     * Getter method for property <tt>slShowName</tt>.
     *
     * @return property value of slShowName
     */
    public String getSlShowName() {
        return slShowName;
    }

    /**
     * Setter method for property <tt>slShowName</tt>.
     *
     * @param slShowName value to be assigned to property slShowName
     */
    public void setSlShowName(String slShowName) {
        this.slShowName = slShowName;
    }

    /**
     * Getter method for property <tt>slCode</tt>.
     *
     * @return property value of slCode
     */
    public String getSlCode() {
        return slCode;
    }

    /**
     * Setter method for property <tt>slCode</tt>.
     *
     * @param slCode value to be assigned to property slCode
     */
    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    /**
     * Getter method for property <tt>authStatus</tt>.
     *
     * @return property value of authStatus
     */
    public Integer getAuthStatus() {
        return authStatus;
    }

    /**
     * Setter method for property <tt>authStatus</tt>.
     *
     * @param authStatus value to be assigned to property authStatus
     */
    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    /**
     * Getter method for property <tt>cityName</tt>.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property <tt>cityName</tt>.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
