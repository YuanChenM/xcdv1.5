/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_single_by_file_info对应的BrSingleByFileInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrSingleByFileInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** FILE_ID */
    private String fileId;
    /** FILE_SERVER_ID */
    private String fileServerId;
    /** FILE_SERVER_IP */
    private String fileServerIp;
    /** FILE_NAME */
    private String fileName;
    /** FILE_SUF */
    private String fileSuf;
    /** FILE_MONTH */
    private java.util.Date fileMonth;
    /** 0：未生成，1：已生成 */
    private String fileStatus;
    /** BUYER_ID */
    private String buyerId;
    /** BUYER_CODE */
    private String buyerCode;
    /** BUYER_NAME */
    private String buyerName;
    /** BUYER_TYPE */
    private String buyerType;
    /** CLASSES_CODE */
    private String classesCode;
    /** MACHINING_CODE */
    private String machiningCode;
    /**
     * <p>默认构造函数</p>
     */
    public BrSingleByFileInfo() {

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
     * <p>FILE_SERVER_IP</p>
     *
     * @return the FILE_SERVER_IP
     */
    public String getFileServerIp() {
        return fileServerIp;
    }

    /**
     * <p>FILE_SERVER_IP</p>
     *
     * @param fileServerIp FILE_SERVER_IP
     */
    public void setFileServerIp(String fileServerIp) {
        this.fileServerIp = fileServerIp;
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
     * <p>FILE_MONTH</p>
     *
     * @return the FILE_MONTH
     */
    public java.util.Date getFileMonth() {
        return fileMonth;
    }

    /**
     * <p>FILE_MONTH</p>
     *
     * @param fileMonth FILE_MONTH
     */
    public void setFileMonth(java.util.Date fileMonth) {
        this.fileMonth = fileMonth;
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
     * <p>BUYER_CODE</p>
     *
     * @return the BUYER_CODE
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>BUYER_CODE</p>
     *
     * @param buyerCode BUYER_CODE
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>BUYER_NAME</p>
     *
     * @return the BUYER_NAME
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>BUYER_NAME</p>
     *
     * @param buyerName BUYER_NAME
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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
     * <p>MACHINING_CODE</p>
     *
     * @return the MACHINING_CODE
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>MACHINING_CODE</p>
     *
     * @param machiningCode MACHINING_CODE
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

}
