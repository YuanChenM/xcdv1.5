package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerBasicInfo;

import java.util.List;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
public class BY121314Bean extends ByBuyerBasicInfo{

    /** 买家收货时间段*/
    private List<BY121314Bean> byBuyerRecTimeList;
    /** 收货时间段*/
    private String recPerType;
    /** 收货时间段描述*/
    private String timeDescribe;
    /** 收货时间是否被选中*/
    private String isChecked;
    /** 支付方式*/
    private String payMethod;
    /** 支付方式名称*/
    private String payMethodName;
    /** 选中的支付方式*/
    private String isPayChecked;
    /** 画面选中的收货时间 */
    private String[] selectRecTime;
    /** 画面选中的收货时间 */
    private String[] selectPaymentType;

    /**
     * Getter method for property <tt>recPerType</tt>.
     *
     * @return property value of recPerType
     */
    public String getRecPerType() {
        return recPerType;
    }

    /**
     * Setter method for property <tt>recPerType</tt>.
     *
     * @param recPerType value to be assigned to property recPerType
     */
    public void setRecPerType(String recPerType) {
        this.recPerType = recPerType;
    }

    /**
     * Getter method for property <tt>timeDescribe</tt>.
     *
     * @return property value of timeDescribe
     */
    public String getTimeDescribe() {
        return timeDescribe;
    }

    /**
     * Setter method for property <tt>timeDescribe</tt>.
     *
     * @param timeDescribe value to be assigned to property timeDescribe
     */
    public void setTimeDescribe(String timeDescribe) {
        this.timeDescribe = timeDescribe;
    }

    /**
     * Getter method for property <tt>isChecked</tt>.
     *
     * @return property value of isChecked
     */
    public String getIsChecked() {
        return isChecked;
    }

    /**
     * Setter method for property <tt>isChecked</tt>.
     *
     * @param isChecked value to be assigned to property isChecked
     */
    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * Getter method for property <tt>byBuyerRecTimeList</tt>.
     *
     * @return property value of byBuyerRecTimeList
     */
    public List<BY121314Bean> getByBuyerRecTimeList() {
        return byBuyerRecTimeList;
    }

    /**
     * Setter method for property <tt>byBuyerRecTimeList</tt>.
     *
     * @param byBuyerRecTimeList value to be assigned to property byBuyerRecTimeList
     */
    public void setByBuyerRecTimeList(List<BY121314Bean> byBuyerRecTimeList) {
        this.byBuyerRecTimeList = byBuyerRecTimeList;
    }

    /**
     * Getter method for property <tt>payMethod</tt>.
     *
     * @return property value of payMethod
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * Setter method for property <tt>payMethod</tt>.
     *
     * @param payMethod value to be assigned to property payMethod
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * Getter method for property <tt>payMethodName</tt>.
     *
     * @return property value of payMethodName
     */
    public String getPayMethodName() {
        return payMethodName;
    }

    /**
     * Setter method for property <tt>payMethodName</tt>.
     *
     * @param payMethodName value to be assigned to property payMethodName
     */
    public void setPayMethodName(String payMethodName) {
        this.payMethodName = payMethodName;
    }

    /**
     * Getter method for property <tt>isPayChecked</tt>.
     *
     * @return property value of isPayChecked
     */
    public String getIsPayChecked() {
        return isPayChecked;
    }

    /**
     * Setter method for property <tt>isPayChecked</tt>.
     *
     * @param isPayChecked value to be assigned to property isPayChecked
     */
    public void setIsPayChecked(String isPayChecked) {
        this.isPayChecked = isPayChecked;
    }

    /**
     * Getter method for property <tt>selectRecTime</tt>.
     *
     * @return property value of selectRecTime
     */
    public String[] getSelectRecTime() {
        return selectRecTime;
    }

    /**
     * Setter method for property <tt>selectRecTime</tt>.
     *
     * @param selectRecTime value to be assigned to property selectRecTime
     */
    public void setSelectRecTime(String[] selectRecTime) {
        this.selectRecTime = selectRecTime;
    }

    /**
     * Getter method for property <tt>selectPaymentType</tt>.
     *
     * @return property value of selectPaymentType
     */
    public String[] getSelectPaymentType() {
        return selectPaymentType;
    }

    /**
     * Setter method for property <tt>selectPaymentType</tt>.
     *
     * @param selectPaymentType value to be assigned to property selectPaymentType
     */
    public void setSelectPaymentType(String[] selectPaymentType) {
        this.selectPaymentType = selectPaymentType;
    }
}
