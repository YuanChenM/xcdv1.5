package com.msk.buyers.bean;

import com.msk.core.entity.ByBuyerBasicInfo;
import com.msk.core.entity.ByBuyerRecAddr;

import java.util.List;

/**
 * BY121301Bean.
 *
 * @author yuan_chen
 */
public class BY121304Bean extends ByBuyerBasicInfo {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String buyerId;
    /** 参考CONSTANT表 */
    private String recPerType;
    /** 将24小时按3小时为段分成8段，分别是：00:00-03:00，03:00-06:00，06:00-09:00，09:00-12:00，12:00-15:00，15:00-18:00，18:00-21:00，21:00-24:00。此字段存储真实的时间段内容。一个买家有多个收货时间段时，即存储为多条记录。 */
    private String timeDescribe;
    /** 参考CONSTANT表 */
    private String salesTargetType;
    /** 参考CONSTANT表 */
    private String salesTargetName;
    /** 参考产品类别 */
    private String classCode;
    /** 产品类别名称 */
    private String className;
    /** 是否选择 */
    private String isChecked;
    /** 画面显示的收货地址 */
    private List<ByBuyerRecAddr> recAddr;
    /** 画面选中的收货时间 */
    private String[] selectRecTime;
    /** 画面选中的销售对象 */
    private String[] salesTarget;
    /** 画面选中的销售产品 */
    private String[] selectPdCla;
    /** 画面选中的二级销售产品*/
    private String[] buyerPdMac;
    /**二级销售产品*/
    private java.lang.String machiningCode;
    /**二级销售产品对象*/
    private java.lang.String machiningName;

    /**
     * Getter method for property <tt>buyerId</tt>.
     *
     * @return property value of buyerId
     */
    @Override
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Setter method for property <tt>buyerId</tt>.
     *
     * @param buyerId value to be assigned to property buyerId
     */
    @Override
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

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
     * Getter method for property <tt>salesTargetType</tt>.
     *
     * @return property value of salesTargetType
     */
    public String getSalesTargetType() {
        return salesTargetType;
    }

    /**
     * Setter method for property <tt>salesTargetType</tt>.
     *
     * @param salesTargetType value to be assigned to property salesTargetType
     */
    public void setSalesTargetType(String salesTargetType) {
        this.salesTargetType = salesTargetType;
    }

    /**
     * Getter method for property <tt>salesTargetName</tt>.
     *
     * @return property value of salesTargetName
     */
    public String getSalesTargetName() {
        return salesTargetName;
    }

    /**
     * Setter method for property <tt>salesTargetName</tt>.
     *
     * @param salesTargetName value to be assigned to property salesTargetName
     */
    public void setSalesTargetName(String salesTargetName) {
        this.salesTargetName = salesTargetName;
    }

    /**
     * Getter method for property <tt>classCode</tt>.
     *
     * @return property value of classCode
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Setter method for property <tt>classCode</tt>.
     *
     * @param classCode value to be assigned to property classCode
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    /**
     * Getter method for property <tt>className</tt>.
     *
     * @return property value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter method for property <tt>className</tt>.
     *
     * @param className value to be assigned to property className
     */
    public void setClassName(String className) {
        this.className = className;
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
     * Getter method for property <tt>salesTarget</tt>.
     *
     * @return property value of salesTarget
     */
    public String[] getSalesTarget() {
        return salesTarget;
    }

    /**
     * Setter method for property <tt>salesTarget</tt>.
     *
     * @param salesTarget value to be assigned to property salesTarget
     */
    public void setSalesTarget(String[] salesTarget) {
        this.salesTarget = salesTarget;
    }

    /**
     * Getter method for property <tt>selectPdCla</tt>.
     *
     * @return property value of selectPdCla
     */
    public String[] getSelectPdCla() {
        return selectPdCla;
    }

    /**
     * Setter method for property <tt>selectPdCla</tt>.
     *
     * @param selectPdCla value to be assigned to property selectPdCla
     */
    public void setSelectPdCla(String[] selectPdCla) {
        this.selectPdCla = selectPdCla;
    }

    /**
     * Getter method for property <tt>recAddr</tt>.
     *
     * @return property value of recAddr
     */
    public List<ByBuyerRecAddr> getRecAddr() {
        return recAddr;
    }

    /**
     * Setter method for property <tt>recAddr</tt>.
     *
     * @param recAddr value to be assigned to property recAddr
     */
    public void setRecAddr(List<ByBuyerRecAddr> recAddr) {
        this.recAddr = recAddr;
    }

    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() { return machiningCode; }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) { this.machiningCode = machiningCode; }

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() { return machiningName; }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) { this.machiningName = machiningName; }

    /**
     * Getter method for property <tt>buyerPdMac</tt>.
     *
     * @return property value of buyerPdMac
     */
    public String[] getBuyerPdMac() {
        return buyerPdMac;
    }

    /**
     * Setter method for property <tt>buyerPdMac</tt>.
     *
     * @param buyerPdMac value to be assigned to property buyerPdMac
     */
    public void setBuyerPdMac(String[] buyerPdMac) {
        this.buyerPdMac = buyerPdMac;
    }
}
