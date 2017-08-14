package com.msk.buyers.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.ByBuyerEmployee;

/**
 * IBY121207RsParam.
 *
 * @author zhou_yajun
 */
@JsonIgnoreProperties(value = { "crtTime","updTime"})
public class IBY121207RsParam extends ByBuyerEmployee {

    private static final long serialVersionUID = 1L;
    /** 名片图片地址 */
    private String busCardPicPath;

    private String busCardFlgName;
    private String contactPersonName;
    private String purchaseName;
    private String receivePersonName;

    /**
     * Getter method for property <tt>busCardPicPath</tt>.
     *
     * @return property value of busCardPicPath
     */
    public String getBusCardPicPath() {
        return busCardPicPath;
    }

    /**
     * Setter method for property <tt>busCardPicPath</tt>.
     *
     * @param busCardPicPath value to be assigned to property busCardPicPath
     */
    public void setBusCardPicPath(String busCardPicPath) {
        this.busCardPicPath = busCardPicPath;
    }

    /**
     * Getter method for property <tt>busCardFlgName</tt>.
     *
     * @return property value of busCardFlgName
     */
    public String getBusCardFlgName() {
        return busCardFlgName;
    }

    /**
     * Setter method for property <tt>busCardFlgName</tt>.
     *
     * @param busCardFlgName value to be assigned to property busCardFlgName
     */
    public void setBusCardFlgName(String busCardFlgName) {
        this.busCardFlgName = busCardFlgName;
    }

    /**
     * Getter method for property <tt>contactPersonName</tt>.
     *
     * @return property value of contactPersonName
     */
    public String getContactPersonName() {
        return contactPersonName;
    }

    /**
     * Setter method for property <tt>contactPersonName</tt>.
     *
     * @param contactPersonName value to be assigned to property contactPersonName
     */
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    /**
     * Getter method for property <tt>purchaseName</tt>.
     *
     * @return property value of purchaseName
     */
    public String getPurchaseName() {
        return purchaseName;
    }

    /**
     * Setter method for property <tt>purchaseName</tt>.
     *
     * @param purchaseName value to be assigned to property purchaseName
     */
    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    /**
     * Getter method for property <tt>receivePersonName</tt>.
     *
     * @return property value of receivePersonName
     */
    public String getReceivePersonName() {
        return receivePersonName;
    }

    /**
     * Setter method for property <tt>receivePersonName</tt>.
     *
     * @param receivePersonName value to be assigned to property receivePersonName
     */
    public void setReceivePersonName(String receivePersonName) {
        this.receivePersonName = receivePersonName;
    }
}