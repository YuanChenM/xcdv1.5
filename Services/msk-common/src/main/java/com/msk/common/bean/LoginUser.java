package com.msk.common.bean;


import com.msk.common.base.BaseBean;
import com.msk.core.entity.OrgSysModule;
import com.msk.org.bean.ActionAuthoritie;
import com.msk.org.bean.LoginRole;
import com.msk.org.bean.PageAuthoritie;

import java.util.List;

public class LoginUser extends BaseBean {
    private static final long serialVersionUID = 1L;
    /** 员工编号 */
    private String emplNo;
    /** 员工名称 */
    private String emplName;
    /** 员工ID */
    private String emplId;
    /** 登录密码 */
    private String loginPwd;
    /**邮件地址*/
    private String emialAddr;
    /**操作List*/
    private List<ActionAuthoritie> actionList;
    /**菜单List*/
    private List<PageAuthoritie> menuPageList;
    /**页面权限*/
    private List<PageAuthoritie> pageList;
    /** 用户登录令牌 */
    private String token;
    /** 状态 */
    private int status;
    private String userType;
    /** 用户角色 */
    private List<LoginRole> roles;

    /** 有权限的系统模块code */
    private List<OrgSysModule> systemModules;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Getter method for property <tt>actionList</tt>.
     *
     * @return property value of actionList
     */
    public List<ActionAuthoritie> getActionList() {
        return actionList;
    }

    /**
     * Setter method for property <tt>actionList</tt>.
     *
     * @param actionList value to be assigned to property actionList
     */
    public void setActionList(List<ActionAuthoritie> actionList) {
        this.actionList = actionList;
    }

    /**
     * Getter method for property <tt>menuPageList</tt>.
     *
     * @return property value of menuPageList
     */
    public List<PageAuthoritie> getMenuPageList() {
        return menuPageList;
    }

    /**
     * Setter method for property <tt>menuPageList</tt>.
     *
     * @param menuPageList value to be assigned to property menuPageList
     */
    public void setMenuPageList(List<PageAuthoritie> menuPageList) {
        this.menuPageList = menuPageList;
    }

    /**
     * Getter method for property <tt>pageList</tt>.
     *
     * @return property value of pageList
     */
    public List<PageAuthoritie> getPageList() {
        return pageList;
    }

    /**
     * Setter method for property <tt>pageList</tt>.
     *
     * @param pageList value to be assigned to property pageList
     */
    public void setPageList(List<PageAuthoritie> pageList) {
        this.pageList = pageList;
    }

    /**
     * *获得emialAddr
     **/
    public String getEmialAddr() {
        return emialAddr;
    }

    /**
     * *设置emialAddr
     **/
    public void setEmialAddr(String emialAddr) {
        this.emialAddr = emialAddr;
    }

    /**
     * *获得status
     **/
    public int getStatus() {
        return status;
    }

    /**
     * *设置status
     **/
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the emplNo
     */
    public String getEmplNo() {
        return emplNo;
    }

    /**
     * @param emplNo the emplNo to set
     */
    public void setEmplNo(String emplNo) {
        this.emplNo = emplNo;
    }

    /**
     * @return the emplName
     */
    public String getEmplName() {
        return emplName;
    }

    /**
     * @param emplName the emplName to set
     */
    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    /**
     * @return the emplId
     */
    public String getEmplId() {
        return emplId;
    }

    /**
     * @param emplId the emplId to set
     */
    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }


    /**
     * @return the loginPwd
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * @param loginPwd the loginPwd to set
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<LoginRole> getRoles() {
        return roles;
    }

    public void setRoles(List<LoginRole> roles) {
        this.roles = roles;
    }

    public List<OrgSysModule> getSystemModules() {
        return systemModules;
    }

    public void setSystemModules(List<OrgSysModule> systemModules) {
        this.systemModules = systemModules;
    }
}
