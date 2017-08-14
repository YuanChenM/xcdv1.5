package com.msk.br.bean;

import com.hoperun.core.bean.BaseParam;

import java.util.Date;

/**
 * Created by tao_zhifa on 2016/9/21.
 */
public class IBR121312RsParam extends BaseParam {
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

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getOldStatusClass() {
        return oldStatusClass;
    }

    public void setOldStatusClass(String oldStatusClass) {
        this.oldStatusClass = oldStatusClass;
    }

    public String getOldStatusClassName() {
        return oldStatusClassName;
    }

    public void setOldStatusClassName(String oldStatusClassName) {
        this.oldStatusClassName = oldStatusClassName;
    }

    public String getOldStatusBreed() {
        return oldStatusBreed;
    }

    public void setOldStatusBreed(String oldStatusBreed) {
        this.oldStatusBreed = oldStatusBreed;
    }

    public String getOldStatusBreedName() {
        return oldStatusBreedName;
    }

    public void setOldStatusBreedName(String oldStatusBreedName) {
        this.oldStatusBreedName = oldStatusBreedName;
    }

    public String getOldExceptionStatus() {
        return oldExceptionStatus;
    }

    public void setOldExceptionStatus(String oldExceptionStatus) {
        this.oldExceptionStatus = oldExceptionStatus;
    }

    public String getOldExceptionStatusName() {
        return oldExceptionStatusName;
    }

    public void setOldExceptionStatusName(String oldExceptionStatusName) {
        this.oldExceptionStatusName = oldExceptionStatusName;
    }

    public String getNewStatusClass() {
        return newStatusClass;
    }

    public void setNewStatusClass(String newStatusClass) {
        this.newStatusClass = newStatusClass;
    }

    public String getNewStatusClassName() {
        return newStatusClassName;
    }

    public void setNewStatusClassName(String newStatusClassName) {
        this.newStatusClassName = newStatusClassName;
    }

    public String getNewStatusBreed() {
        return newStatusBreed;
    }

    public void setNewStatusBreed(String newStatusBreed) {
        this.newStatusBreed = newStatusBreed;
    }

    public String getNewStatusBreedName() {
        return newStatusBreedName;
    }

    public void setNewStatusBreedName(String newStatusBreedName) {
        this.newStatusBreedName = newStatusBreedName;
    }

    public String getNewExceptionStatus() {
        return newExceptionStatus;
    }

    public void setNewExceptionStatus(String newExceptionStatus) {
        this.newExceptionStatus = newExceptionStatus;
    }

    public String getNewExceptionStatusName() {
        return newExceptionStatusName;
    }

    public void setNewExceptionStatusName(String newExceptionStatusName) {
        this.newExceptionStatusName = newExceptionStatusName;
    }

    public String getCurrentStatusFlg() {
        return currentStatusFlg;
    }

    public void setCurrentStatusFlg(String currentStatusFlg) {
        this.currentStatusFlg = currentStatusFlg;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
