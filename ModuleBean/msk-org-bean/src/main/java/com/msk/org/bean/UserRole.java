package com.msk.org.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户角色
 * 
 * @author jiang_nan
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    private String roleId;
    private String roleName;
    private List<ActionAuthority> actionAuthorityList;
    private List<PageAuthority> pageAuthorityList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<ActionAuthority> getActionAuthorityList() {
        return actionAuthorityList;
    }

    public void setActionAuthorityList(List<ActionAuthority> actionAuthorityList) {
        this.actionAuthorityList = actionAuthorityList;
    }

    public List<PageAuthority> getPageAuthorityList() {
        return pageAuthorityList;
    }

    public void setPageAuthorityList(List<PageAuthority> pageAuthorityList) {
        this.pageAuthorityList = pageAuthorityList;
    }
}
