/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_file_buyer_pool对应的BrFileBuyerPool</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrFileBuyerPool extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** FILE_ID */
    private String fileId;
    /** FILE_SERVER_ID */
    private String fileServerId;
    /** FILE_NAME */
    private String fileName;
    /** FILE_SUF */
    private String fileSuf;
    /** FILE_START_TIME */
    private java.util.Date fileStartTime;
    /** FILE_END_TIME */
    private java.util.Date fileEndTime;
    /** FILE_CREATE_TIME */
    private java.util.Date fileCreateTime;
    /** 0：未生成，1：已生成 */
    private String fileStatus;
    /** 1：买家池，2：买家产品池，3：上线状态买家池 */
    private String poolType;
    /** LGCS_AREA_CODE */
    private String lgcsAreaCode;
    /** LGCS_AREA_NAME */
    private String lgcsAreaName;
    /** CITY_CODE */
    private String cityCode;
    /** CITY_NAME */
    private String cityName;
    /** BUYER_TYPE */
    private String buyerType;
    /** BUYER_SUB_TYPE */
    private String buyerSubType;
    /** CLASSES_CODE */
    private String classesCode;
    /** MACHINING_CODE_U */
    private String machiningCodeU;
    /** MARKETINGS_PERIOD_NAME */
    private String marketingsPeriodName;
    /** MARKETINGS_STATUS */
    private String marketingsStatus;
    /** MARKET_ID */
    private String marketId;
    /** MARKET_NAME */
    private String marketName;
    /**
     * <p>默认构造函数</p>
     */
    public BrFileBuyerPool() {

    }

    /**
     * <p>FILE_ID</p>
     *
     * @return the FILE_ID
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * <p>FILE_ID</p>
     *
     * @param fileId FILE_ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * <p>FILE_SERVER_ID</p>
     *
     * @return the FILE_SERVER_ID
     */
    public String getFileServerId() {
        return fileServerId;
    }

    /**
     * <p>FILE_SERVER_ID</p>
     *
     * @param fileServerId FILE_SERVER_ID
     */
    public void setFileServerId(String fileServerId) {
        this.fileServerId = fileServerId;
    }

    /**
     * <p>FILE_NAME</p>
     *
     * @return the FILE_NAME
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <p>FILE_NAME</p>
     *
     * @param fileName FILE_NAME
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * <p>FILE_SUF</p>
     *
     * @return the FILE_SUF
     */
    public String getFileSuf() {
        return fileSuf;
    }

    /**
     * <p>FILE_SUF</p>
     *
     * @param fileSuf FILE_SUF
     */
    public void setFileSuf(String fileSuf) {
        this.fileSuf = fileSuf;
    }

    /**
     * <p>FILE_START_TIME</p>
     *
     * @return the FILE_START_TIME
     */
    public java.util.Date getFileStartTime() {
        return fileStartTime;
    }

    /**
     * <p>FILE_START_TIME</p>
     *
     * @param fileStartTime FILE_START_TIME
     */
    public void setFileStartTime(java.util.Date fileStartTime) {
        this.fileStartTime = fileStartTime;
    }

    /**
     * <p>FILE_END_TIME</p>
     *
     * @return the FILE_END_TIME
     */
    public java.util.Date getFileEndTime() {
        return fileEndTime;
    }

    /**
     * <p>FILE_END_TIME</p>
     *
     * @param fileEndTime FILE_END_TIME
     */
    public void setFileEndTime(java.util.Date fileEndTime) {
        this.fileEndTime = fileEndTime;
    }

    /**
     * <p>FILE_CREATE_TIME</p>
     *
     * @return the FILE_CREATE_TIME
     */
    public java.util.Date getFileCreateTime() {
        return fileCreateTime;
    }

    /**
     * <p>FILE_CREATE_TIME</p>
     *
     * @param fileCreateTime FILE_CREATE_TIME
     */
    public void setFileCreateTime(java.util.Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    /**
     * <p>0：未生成，1：已生成</p>
     *
     * @return the 0：未生成，1：已生成
     */
    public String getFileStatus() {
        return fileStatus;
    }

    /**
     * <p>0：未生成，1：已生成</p>
     *
     * @param fileStatus 0：未生成，1：已生成
     */
    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    /**
     * <p>1：买家池，2：买家产品池，3：上线状态买家池</p>
     *
     * @return the 1：买家池，2：买家产品池，3：上线状态买家池
     */
    public String getPoolType() {
        return poolType;
    }

    /**
     * <p>1：买家池，2：买家产品池，3：上线状态买家池</p>
     *
     * @param poolType 1：买家池，2：买家产品池，3：上线状态买家池
     */
    public void setPoolType(String poolType) {
        this.poolType = poolType;
    }

    /**
     * <p>LGCS_AREA_CODE</p>
     *
     * @return the LGCS_AREA_CODE
     */
    public String getLgcsAreaCode() {
        return lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_CODE</p>
     *
     * @param lgcsAreaCode LGCS_AREA_CODE
     */
    public void setLgcsAreaCode(String lgcsAreaCode) {
        this.lgcsAreaCode = lgcsAreaCode;
    }

    /**
     * <p>LGCS_AREA_NAME</p>
     *
     * @return the LGCS_AREA_NAME
     */
    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    /**
     * <p>LGCS_AREA_NAME</p>
     *
     * @param lgcsAreaName LGCS_AREA_NAME
     */
    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    /**
     * <p>CITY_CODE</p>
     *
     * @return the CITY_CODE
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * <p>CITY_CODE</p>
     *
     * @param cityCode CITY_CODE
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * <p>CITY_NAME</p>
     *
     * @return the CITY_NAME
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * <p>CITY_NAME</p>
     *
     * @param cityName CITY_NAME
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * <p>BUYER_TYPE</p>
     *
     * @return the BUYER_TYPE
     */
    public String getBuyerType() {
        return buyerType;
    }

    /**
     * <p>BUYER_TYPE</p>
     *
     * @param buyerType BUYER_TYPE
     */
    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    /**
     * <p>BUYER_SUB_TYPE</p>
     *
     * @return the BUYER_SUB_TYPE
     */
    public String getBuyerSubType() {
        return buyerSubType;
    }

    /**
     * <p>BUYER_SUB_TYPE</p>
     *
     * @param buyerSubType BUYER_SUB_TYPE
     */
    public void setBuyerSubType(String buyerSubType) {
        this.buyerSubType = buyerSubType;
    }

    /**
     * <p>CLASSES_CODE</p>
     *
     * @return the CLASSES_CODE
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>CLASSES_CODE</p>
     *
     * @param classesCode CLASSES_CODE
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>MACHINING_CODE_U</p>
     *
     * @return the MACHINING_CODE_U
     */
    public String getMachiningCodeU() {
        return machiningCodeU;
    }

    /**
     * <p>MACHINING_CODE_U</p>
     *
     * @param machiningCodeU MACHINING_CODE_U
     */
    public void setMachiningCodeU(String machiningCodeU) {
        this.machiningCodeU = machiningCodeU;
    }

    /**
     * <p>MARKETINGS_PERIOD_NAME</p>
     *
     * @return the MARKETINGS_PERIOD_NAME
     */
    public String getMarketingsPeriodName() {
        return marketingsPeriodName;
    }

    /**
     * <p>MARKETINGS_PERIOD_NAME</p>
     *
     * @param marketingsPeriodName MARKETINGS_PERIOD_NAME
     */
    public void setMarketingsPeriodName(String marketingsPeriodName) {
        this.marketingsPeriodName = marketingsPeriodName;
    }

    /**
     * <p>MARKETINGS_STATUS</p>
     *
     * @return the MARKETINGS_STATUS
     */
    public String getMarketingsStatus() {
        return marketingsStatus;
    }

    /**
     * <p>MARKETINGS_STATUS</p>
     *
     * @param marketingsStatus MARKETINGS_STATUS
     */
    public void setMarketingsStatus(String marketingsStatus) {
        this.marketingsStatus = marketingsStatus;
    }

    /**
     * <p>MARKET_ID</p>
     *
     * @return the MARKET_ID
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * <p>MARKET_ID</p>
     *
     * @param marketId MARKET_ID
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * <p>MARKET_NAME</p>
     *
     * @return the MARKET_NAME
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * <p>MARKET_NAME</p>
     *
     * @param marketName MARKET_NAME
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

}
