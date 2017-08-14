package com.msk.buyers.bean;

import com.msk.core.entity.ByMarketTerminalBasic;

/**
 * Created by zhou_yajun on 2016/7/8.
 */
public class BY121403Bean extends ByMarketTerminalBasic{

    /** 旧市场调研阶段*/
    private String oldResearchPhase;

    /** 动作类型*/
    private String actionType;

    /** 是否需要同步*/
    private String syncFlag;

    /** 错误提示信息*/
    private String errorMessage;

    /** 是否存在标志*/
    private String existenceFlg;


    /**
     * Getter method for property <tt>oldResearchPhase</tt>.
     *
     * @return property value of oldResearchPhase
     */
    public String getOldResearchPhase() {
        return oldResearchPhase;
    }

    /**
     * Setter method for property <tt>oldResearchPhase</tt>.
     *
     * @param oldResearchPhase value to be assigned to property oldResearchPhase
     */
    public void setOldResearchPhase(String oldResearchPhase) {
        this.oldResearchPhase = oldResearchPhase;
    }

    /**
     * Getter method for property <tt>actionType</tt>.
     *
     * @return property value of actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Setter method for property <tt>actionType</tt>.
     *
     * @param actionType value to be assigned to property actionType
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * Getter method for property <tt>syncFlag</tt>.
     *
     * @return property value of syncFlag
     */
    public String getSyncFlag() {
        return syncFlag;
    }

    /**
     * Setter method for property <tt>syncFlag</tt>.
     *
     * @param syncFlag value to be assigned to property syncFlag
     */
    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag;
    }

    /**
     * Getter method for property <tt>errorMessage</tt>.
     *
     * @return property value of errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Setter method for property <tt>errorMessage</tt>.
     *
     * @param errorMessage value to be assigned to property errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExistenceFlg() {
        return existenceFlg;
    }

    public void setExistenceFlg(String existenceFlg) {
        this.existenceFlg = existenceFlg;
    }
}
