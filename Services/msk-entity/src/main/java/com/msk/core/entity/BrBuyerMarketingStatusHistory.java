/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_buyer_marketing_status_history对应的BrBuyerMarketingStatusHistory</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrBuyerMarketingStatusHistory extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** HISTORY_ID */
    private Long historyId;
    /** BUYER_ID */
    private String buyerId;
    /** OLD_STATUS_CLASS */
    private String oldStatusClass;
    /** OLD_STATUS_CLASS_NAME */
    private String oldStatusClassName;
    /** OLD_STATUS_BREED */
    private String oldStatusBreed;
    /** OLD_STATUS_BREED_NAME */
    private String oldStatusBreedName;
    /** OLD_EXCEPTION_STATUS */
    private String oldExceptionStatus;
    /** OLD_EXCEPTION_STATUS_NAME */
    private String oldExceptionStatusName;
    /** NEW_STATUS_CLASS */
    private String newStatusClass;
    /** NEW_STATUS_CLASS_NAME */
    private String newStatusClassName;
    /** NEW_STATUS_BREED */
    private String newStatusBreed;
    /** NEW_STATUS_BREED_NAME */
    private String newStatusBreedName;
    /** NEW_EXCEPTION_STATUS */
    private String newExceptionStatus;
    /** NEW_EXCEPTION_STATUS_NAME */
    private String newExceptionStatusName;
    /** 1:当前最新状态0:履历数据 */
    private String currentStatusFlg;
    /** MODIFY_TIME */
    private java.util.Date modifyTime;
    /**
     * <p>默认构造函数</p>
     */
    public BrBuyerMarketingStatusHistory() {

    }

    /**
     * <p>HISTORY_ID</p>
     *
     * @return the HISTORY_ID
     */
    public Long getHistoryId() {
        return historyId;
    }

    /**
     * <p>HISTORY_ID</p>
     *
     * @param historyId HISTORY_ID
     */
    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
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
     * <p>OLD_STATUS_CLASS</p>
     *
     * @return the OLD_STATUS_CLASS
     */
    public String getOldStatusClass() {
        return oldStatusClass;
    }

    /**
     * <p>OLD_STATUS_CLASS</p>
     *
     * @param oldStatusClass OLD_STATUS_CLASS
     */
    public void setOldStatusClass(String oldStatusClass) {
        this.oldStatusClass = oldStatusClass;
    }

    /**
     * <p>OLD_STATUS_CLASS_NAME</p>
     *
     * @return the OLD_STATUS_CLASS_NAME
     */
    public String getOldStatusClassName() {
        return oldStatusClassName;
    }

    /**
     * <p>OLD_STATUS_CLASS_NAME</p>
     *
     * @param oldStatusClassName OLD_STATUS_CLASS_NAME
     */
    public void setOldStatusClassName(String oldStatusClassName) {
        this.oldStatusClassName = oldStatusClassName;
    }

    /**
     * <p>OLD_STATUS_BREED</p>
     *
     * @return the OLD_STATUS_BREED
     */
    public String getOldStatusBreed() {
        return oldStatusBreed;
    }

    /**
     * <p>OLD_STATUS_BREED</p>
     *
     * @param oldStatusBreed OLD_STATUS_BREED
     */
    public void setOldStatusBreed(String oldStatusBreed) {
        this.oldStatusBreed = oldStatusBreed;
    }

    /**
     * <p>OLD_STATUS_BREED_NAME</p>
     *
     * @return the OLD_STATUS_BREED_NAME
     */
    public String getOldStatusBreedName() {
        return oldStatusBreedName;
    }

    /**
     * <p>OLD_STATUS_BREED_NAME</p>
     *
     * @param oldStatusBreedName OLD_STATUS_BREED_NAME
     */
    public void setOldStatusBreedName(String oldStatusBreedName) {
        this.oldStatusBreedName = oldStatusBreedName;
    }

    /**
     * <p>OLD_EXCEPTION_STATUS</p>
     *
     * @return the OLD_EXCEPTION_STATUS
     */
    public String getOldExceptionStatus() {
        return oldExceptionStatus;
    }

    /**
     * <p>OLD_EXCEPTION_STATUS</p>
     *
     * @param oldExceptionStatus OLD_EXCEPTION_STATUS
     */
    public void setOldExceptionStatus(String oldExceptionStatus) {
        this.oldExceptionStatus = oldExceptionStatus;
    }

    /**
     * <p>OLD_EXCEPTION_STATUS_NAME</p>
     *
     * @return the OLD_EXCEPTION_STATUS_NAME
     */
    public String getOldExceptionStatusName() {
        return oldExceptionStatusName;
    }

    /**
     * <p>OLD_EXCEPTION_STATUS_NAME</p>
     *
     * @param oldExceptionStatusName OLD_EXCEPTION_STATUS_NAME
     */
    public void setOldExceptionStatusName(String oldExceptionStatusName) {
        this.oldExceptionStatusName = oldExceptionStatusName;
    }

    /**
     * <p>NEW_STATUS_CLASS</p>
     *
     * @return the NEW_STATUS_CLASS
     */
    public String getNewStatusClass() {
        return newStatusClass;
    }

    /**
     * <p>NEW_STATUS_CLASS</p>
     *
     * @param newStatusClass NEW_STATUS_CLASS
     */
    public void setNewStatusClass(String newStatusClass) {
        this.newStatusClass = newStatusClass;
    }

    /**
     * <p>NEW_STATUS_CLASS_NAME</p>
     *
     * @return the NEW_STATUS_CLASS_NAME
     */
    public String getNewStatusClassName() {
        return newStatusClassName;
    }

    /**
     * <p>NEW_STATUS_CLASS_NAME</p>
     *
     * @param newStatusClassName NEW_STATUS_CLASS_NAME
     */
    public void setNewStatusClassName(String newStatusClassName) {
        this.newStatusClassName = newStatusClassName;
    }

    /**
     * <p>NEW_STATUS_BREED</p>
     *
     * @return the NEW_STATUS_BREED
     */
    public String getNewStatusBreed() {
        return newStatusBreed;
    }

    /**
     * <p>NEW_STATUS_BREED</p>
     *
     * @param newStatusBreed NEW_STATUS_BREED
     */
    public void setNewStatusBreed(String newStatusBreed) {
        this.newStatusBreed = newStatusBreed;
    }

    /**
     * <p>NEW_STATUS_BREED_NAME</p>
     *
     * @return the NEW_STATUS_BREED_NAME
     */
    public String getNewStatusBreedName() {
        return newStatusBreedName;
    }

    /**
     * <p>NEW_STATUS_BREED_NAME</p>
     *
     * @param newStatusBreedName NEW_STATUS_BREED_NAME
     */
    public void setNewStatusBreedName(String newStatusBreedName) {
        this.newStatusBreedName = newStatusBreedName;
    }

    /**
     * <p>NEW_EXCEPTION_STATUS</p>
     *
     * @return the NEW_EXCEPTION_STATUS
     */
    public String getNewExceptionStatus() {
        return newExceptionStatus;
    }

    /**
     * <p>NEW_EXCEPTION_STATUS</p>
     *
     * @param newExceptionStatus NEW_EXCEPTION_STATUS
     */
    public void setNewExceptionStatus(String newExceptionStatus) {
        this.newExceptionStatus = newExceptionStatus;
    }

    /**
     * <p>NEW_EXCEPTION_STATUS_NAME</p>
     *
     * @return the NEW_EXCEPTION_STATUS_NAME
     */
    public String getNewExceptionStatusName() {
        return newExceptionStatusName;
    }

    /**
     * <p>NEW_EXCEPTION_STATUS_NAME</p>
     *
     * @param newExceptionStatusName NEW_EXCEPTION_STATUS_NAME
     */
    public void setNewExceptionStatusName(String newExceptionStatusName) {
        this.newExceptionStatusName = newExceptionStatusName;
    }

    /**
     * <p>1:当前最新状态0:履历数据</p>
     *
     * @return the 1:当前最新状态0:履历数据
     */
    public String getCurrentStatusFlg() {
        return currentStatusFlg;
    }

    /**
     * <p>1:当前最新状态0:履历数据</p>
     *
     * @param currentStatusFlg 1:当前最新状态0:履历数据
     */
    public void setCurrentStatusFlg(String currentStatusFlg) {
        this.currentStatusFlg = currentStatusFlg;
    }

    /**
     * <p>MODIFY_TIME</p>
     *
     * @return the MODIFY_TIME
     */
    public java.util.Date getModifyTime() {
        return modifyTime;
    }

    /**
     * <p>MODIFY_TIME</p>
     *
     * @param modifyTime MODIFY_TIME
     */
    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
