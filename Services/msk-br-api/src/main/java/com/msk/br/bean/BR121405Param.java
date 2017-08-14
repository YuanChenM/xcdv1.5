package com.msk.br.bean;


import com.hoperun.core.bean.BaseParam;

import java.util.List;

public class BR121405Param extends BaseParam {
    private static final long serialVersionUID = 1L;

    /** LGCS_AREA_CODE */
    private String lgcsAreaCode;
    /** CITY_CODE */
    private String cityCode;
    /** BUYER_TYPE */
    private String buyerType;
    /** CLASSES_CODE */
    private String classesCode;
    /** MARKET_ID */
    private String marketId;

    private String machiningCode;
    private String fileStartTime;
    private String marketingsStatusCla;
    private String marketingsStatus;
    private List<String> marketingsStatusList;

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
     * Getter method for property <tt>marketId</tt>.
     *
     * @return property value of marketId
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * Setter method for property <tt>marketId</tt>.
     *
     * @param marketId value to be assigned to property marketId
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
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

    public String getFileStartTime() {
        return fileStartTime;
    }

    public void setFileStartTime(String fileStartTime) {
        this.fileStartTime = fileStartTime;
    }

    public String getMarketingsStatusCla() {
        return marketingsStatusCla;
    }

    public void setMarketingsStatusCla(String marketingsStatusCla) {
        this.marketingsStatusCla = marketingsStatusCla;
    }

    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    /**
     * Getter method for property <tt>marketingsStatusList</tt>.
     *
     * @return property value of marketingsStatusList
     */
    public List<String> getMarketingsStatusList() {
        return marketingsStatusList;
    }

    /**
     * Setter method for property <tt>marketingsStatusList</tt>.
     *
     * @param marketingsStatusList value to be assigned to property marketingsStatusList
     */
    public void setMarketingsStatusList(List<String> marketingsStatusList) {
        this.marketingsStatusList = marketingsStatusList;
    }
}
