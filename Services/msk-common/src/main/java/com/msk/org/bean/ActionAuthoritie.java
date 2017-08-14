package com.msk.org.bean;


import com.msk.common.base.BaseBean;

/**
 * ActionAuthoritie
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class ActionAuthoritie extends BaseBean {
    /**页面编码*/
    private String pageCode;
    /**操作编码*/
    private String actionCode;
    /**操作名称*/
    private String actionName;
    /**操作类型*/
    private String actionType;

    /**
     * Getter method for property <tt>pageCode</tt>.
     *
     * @return property value of pageCode
     */
    public String getPageCode() {
        return pageCode;
    }

    /**
     * Setter method for property <tt>pageCode</tt>.
     *
     * @param pageCode value to be assigned to property pageCode
     */
    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    /**
     * Getter method for property <tt>actionCode</tt>.
     *
     * @return property value of actionCode
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * Setter method for property <tt>actionCode</tt>.
     *
     * @param actionCode value to be assigned to property actionCode
     */
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    /**
     * Getter method for property <tt>actionName</tt>.
     *
     * @return property value of actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Setter method for property <tt>actionName</tt>.
     *
     * @param actionName value to be assigned to property actionName
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
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
}
