/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表by_buyer_report_manager对应的ByBuyerReportManager</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class ByBuyerReportManager extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** FILE_ID */
    private String fileId;
    /** BUYER_ID */
    private String buyerId;
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
    /** PERIOD_START */
    private java.util.Date periodStart;
    /** PERIOD_END */
    private java.util.Date periodEnd;
    /** CURRENT_PERIOD */
    private String currentPeriod;
    /** REPORT_TYPE */
    private String reportType;
    /**
     * <p>默认构造函数</p>
     */
    public ByBuyerReportManager() {

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
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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
     * <p>PERIOD_START</p>
     *
     * @return the PERIOD_START
     */
    public java.util.Date getPeriodStart() {
        return periodStart;
    }

    /**
     * <p>PERIOD_START</p>
     *
     * @param periodStart PERIOD_START
     */
    public void setPeriodStart(java.util.Date periodStart) {
        this.periodStart = periodStart;
    }

    /**
     * <p>PERIOD_END</p>
     *
     * @return the PERIOD_END
     */
    public java.util.Date getPeriodEnd() {
        return periodEnd;
    }

    /**
     * <p>PERIOD_END</p>
     *
     * @param periodEnd PERIOD_END
     */
    public void setPeriodEnd(java.util.Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    /**
     * <p>CURRENT_PERIOD</p>
     *
     * @return the CURRENT_PERIOD
     */
    public String getCurrentPeriod() {
        return currentPeriod;
    }

    /**
     * <p>CURRENT_PERIOD</p>
     *
     * @param currentPeriod CURRENT_PERIOD
     */
    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    /**
     * <p>REPORT_TYPE</p>
     *
     * @return the REPORT_TYPE
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * <p>REPORT_TYPE</p>
     *
     * @param reportType REPORT_TYPE
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

}
