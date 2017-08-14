package com.msk.br.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * IBR121412RsBean.
 *
 */
public class IBR121413RsBean extends BaseEntity {

    private Long id;
    private String buyerId;
    private String buyerCode;
    private String lgcsAreaCode;
    private String cityCode;
    private String districtCode;
    private String classesCode;
    private String classesName;
    private String machiningCode;
    private String machiningName;
    private String machiningCodeU;
    private String machiningNameU;
    private String byPoolMachiningCode;
    private String buyerPoolId;
    private String buyerType;
    private String poolBuyerCode;
    /** 插入时间，买家加入买家池的时间 */
    private java.util.Date joinTime;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
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
     * Getter method for property <tt>buyerCode</tt>.
     *
     * @return property value of buyerCode
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * Setter method for property <tt>buyerCode</tt>.
     *
     * @param buyerCode value to be assigned to property buyerCode
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
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
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>classesName</tt>.
     *
     * @return property value of classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * Setter method for property <tt>classesName</tt>.
     *
     * @param classesName value to be assigned to property classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Getter method for property <tt>machiningName</tt>.
     *
     * @return property value of machiningName
     */
    public String getMachiningName() {
        return machiningName;
    }

    /**
     * Setter method for property <tt>machiningName</tt>.
     *
     * @param machiningName value to be assigned to property machiningName
     */
    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * Getter method for property <tt>buyerType</tt>.
     *
     * @return property value of buyerType
     */
    public String getBuyerType() {
        return buyerType;
    }

    /**
     * Setter method for property <tt>buyerType</tt>.
     *
     * @param buyerType value to be assigned to property buyerType
     */
    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    /**
     * Getter method for property <tt>joinTime</tt>.
     *
     * @return property value of joinTime
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * Setter method for property <tt>joinTime</tt>.
     *
     * @param joinTime value to be assigned to property joinTime
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * Getter method for property <tt>poolBuyerCode</tt>.
     *
     * @return property value of poolBuyerCode
     */
    public String getPoolBuyerCode() {
        return poolBuyerCode;
    }

    /**
     * Setter method for property <tt>poolBuyerCode</tt>.
     *
     * @param poolBuyerCode value to be assigned to property poolBuyerCode
     */
    public void setPoolBuyerCode(String poolBuyerCode) {
        this.poolBuyerCode = poolBuyerCode;
    }

    /**
     * Getter method for property <tt>machiningCodeU</tt>.
     *
     * @return property value of machiningCodeU
     */
    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    /**
     * Setter method for property <tt>machiningCodeU</tt>.
     *
     * @param machiningCodeU value to be assigned to property machiningCodeU
     */
    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    /**
     * Getter method for property <tt>machiningNameU</tt>.
     *
     * @return property value of machiningNameU
     */
    public String getMachiningNameU() {
        return machiningNameU;
    }

    /**
     * Setter method for property <tt>machiningNameU</tt>.
     *
     * @param machiningNameU value to be assigned to property machiningNameU
     */
    public void setMachiningNameU(String machiningNameU) {
        this.machiningNameU = machiningNameU;
    }

    /**
     * Getter method for property <tt>byPoolMachiningCode</tt>.
     *
     * @return property value of byPoolMachiningCode
     */
    public String getByPoolMachiningCode() {
        return byPoolMachiningCode;
    }

    /**
     * Setter method for property <tt>byPoolMachiningCode</tt>.
     *
     * @param byPoolMachiningCode value to be assigned to property byPoolMachiningCode
     */
    public void setByPoolMachiningCode(String byPoolMachiningCode) {
        this.byPoolMachiningCode = byPoolMachiningCode;
    }

    /**
     * Getter method for property <tt>buyerPoolId</tt>.
     *
     * @return property value of buyerPoolId
     */
    public String getBuyerPoolId() {
        return buyerPoolId;
    }

    /**
     * Setter method for property <tt>buyerPoolId</tt>.
     *
     * @param buyerPoolId value to be assigned to property buyerPoolId
     */
    public void setBuyerPoolId(String buyerPoolId) {
        this.buyerPoolId = buyerPoolId;
    }
}
