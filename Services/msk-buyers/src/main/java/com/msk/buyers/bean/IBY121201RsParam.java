package com.msk.buyers.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.ByBuyerAccount;

/**
 * IBY121201RsParam.
 *
 * @author zhou_yajun
 */
@JsonIgnoreProperties(value = { "crtTime","updTime"})
public class IBY121201RsParam extends ByBuyerAccount {

    private static final long serialVersionUID = 1L;
    /** 注册来源 */
    private String registerSource;
    /** 旧密码 */
    private String oldAccountPass;
    /** 新密码 */
    private String newAccountPass;
    /** 物流区编码 */
    private String lgcsAreaCode;
    /** 城市编码 */
    private String cityCode;
    /** 区编码 */
    private String districtCode;
    /** 接口参数 */
    private String interfaceToken;

    /**
     * Getter method for property <tt>registerSource</tt>.
     *
     * @return property value of registerSource
     */
    public String getRegisterSource() {
        return registerSource;
    }

    /**
     * Setter method for property <tt>registerSource</tt>.
     *
     * @param registerSource value to be assigned to property registerSource
     */
    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }

    /**
     * Getter method for property <tt>oldAccountPass</tt>.
     *
     * @return property value of oldAccountPass
     */
    public String getOldAccountPass() {
        return oldAccountPass;
    }

    /**
     * Setter method for property <tt>oldAccountPass</tt>.
     *
     * @param oldAccountPass value to be assigned to property oldAccountPass
     */
    public void setOldAccountPass(String oldAccountPass) {
        this.oldAccountPass = oldAccountPass;
    }

    /**
     * Getter method for property <tt>newAccountPass</tt>.
     *
     * @return property value of newAccountPass
     */
    public String getNewAccountPass() {
        return newAccountPass;
    }

    /**
     * Setter method for property <tt>newAccountPass</tt>.
     *
     * @param newAccountPass value to be assigned to property newAccountPass
     */
    public void setNewAccountPass(String newAccountPass) {
        this.newAccountPass = newAccountPass;
    }

    /**
     * Getter method for property <tt>lgcsAreaCode</tt>.
     *
     * @return property value of lgcsAreaCode
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * Setter method for property <tt>lgcsAreaCode</tt>.
     *
     * @param lgcsAreaCode value to be assigned to property lgcsAreaCode
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * Getter method for property <tt>cityCode</tt>.
     *
     * @return property value of cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter method for property <tt>cityCode</tt>.
     *
     * @param cityCode value to be assigned to property cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter method for property <tt>districtCode</tt>.
     *
     * @return property value of districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * Setter method for property <tt>districtCode</tt>.
     *
     * @param districtCode value to be assigned to property districtCode
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * Getter method for property <tt>interfaceToken</tt>.
     *
     * @return property value of interfaceToken
     */
    public String getInterfaceToken() {
        return interfaceToken;
    }

    /**
     * Setter method for property <tt>interfaceToken</tt>.
     *
     * @param interfaceToken value to be assigned to property interfaceToken
     */
    public void setInterfaceToken(String interfaceToken) {
        this.interfaceToken = interfaceToken;
    }
}