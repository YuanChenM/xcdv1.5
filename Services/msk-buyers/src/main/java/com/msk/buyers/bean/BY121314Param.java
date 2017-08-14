package com.msk.buyers.bean;

import com.msk.common.bean.RsPageParam;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
public class BY121314Param extends RsPageParam{

    /**ID*/
    private String id;
    /** BUYER_ID */
    private String buyerId;
    /** DELIVERY_ADDR */
    private String deliveryAddr;
    /** REFERENCE_ADDR */
    private String referenceAddr;
    /** MANAGE_ADDR */
    private String manageAddr;
    /** REC_PER_NAME */
    private String recPerName;
    /** REC_PER_TEL */
    private String recPerTel;
    /** REC_PER_WECHAT */
    private String recPerWechat;
    /** REC_PER_QQ */
    private String recPerQq;
    /** 是否默认配送地址 */
    private String isDefault;

    private int startNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Getter method for property <tt>deliveryAddr</tt>.
     *
     * @return property value of deliveryAddr
     */
    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    /**
     * Setter method for property <tt>deliveryAddr</tt>.
     *
     * @param deliveryAddr value to be assigned to property deliveryAddr
     */
    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    /**
     * Getter method for property <tt>referenceAddr</tt>.
     *
     * @return property value of referenceAddr
     */
    public String getReferenceAddr() {
        return referenceAddr;
    }

    /**
     * Setter method for property <tt>referenceAddr</tt>.
     *
     * @param referenceAddr value to be assigned to property referenceAddr
     */
    public void setReferenceAddr(String referenceAddr) {
        this.referenceAddr = referenceAddr;
    }

    /**
     * Getter method for property <tt>manageAddr</tt>.
     *
     * @return property value of manageAddr
     */
    public String getManageAddr() {
        return manageAddr;
    }

    /**
     * Setter method for property <tt>manageAddr</tt>.
     *
     * @param manageAddr value to be assigned to property manageAddr
     */
    public void setManageAddr(String manageAddr) {
        this.manageAddr = manageAddr;
    }

    /**
     * Getter method for property <tt>recPerName</tt>.
     *
     * @return property value of recPerName
     */
    public String getRecPerName() {
        return recPerName;
    }

    /**
     * Setter method for property <tt>recPerName</tt>.
     *
     * @param recPerName value to be assigned to property recPerName
     */
    public void setRecPerName(String recPerName) {
        this.recPerName = recPerName;
    }

    /**
     * Getter method for property <tt>recPerTel</tt>.
     *
     * @return property value of recPerTel
     */
    public String getRecPerTel() {
        return recPerTel;
    }

    /**
     * Setter method for property <tt>recPerTel</tt>.
     *
     * @param recPerTel value to be assigned to property recPerTel
     */
    public void setRecPerTel(String recPerTel) {
        this.recPerTel = recPerTel;
    }

    /**
     * Getter method for property <tt>recPerWechat</tt>.
     *
     * @return property value of recPerWechat
     */
    public String getRecPerWechat() {
        return recPerWechat;
    }

    /**
     * Setter method for property <tt>recPerWechat</tt>.
     *
     * @param recPerWechat value to be assigned to property recPerWechat
     */
    public void setRecPerWechat(String recPerWechat) {
        this.recPerWechat = recPerWechat;
    }

    /**
     * Getter method for property <tt>recPerQq</tt>.
     *
     * @return property value of recPerQq
     */
    public String getRecPerQq() {
        return recPerQq;
    }

    /**
     * Setter method for property <tt>recPerQq</tt>.
     *
     * @param recPerQq value to be assigned to property recPerQq
     */
    public void setRecPerQq(String recPerQq) {
        this.recPerQq = recPerQq;
    }

    /**
     * Getter method for property <tt>isDefault</tt>.
     *
     * @return property value of isDefault
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * Setter method for property <tt>isDefault</tt>.
     *
     * @param isDefault value to be assigned to property isDefault
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Getter method for property <tt>startNo</tt>.
     *
     * @return property value of startNo
     */
    public int getStartNo() {
        return startNo;
    }

    /**
     * Setter method for property <tt>startNo</tt>.
     *
     * @param startNo value to be assigned to property startNo
     */
    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }
}
