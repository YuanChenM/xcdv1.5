package com.msk.org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrgRoleAuthPrimaryKey implements Serializable {
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "sys_Code")
    private String sysCode;
    @Column(name = "page_Code")
    private String pageCode;
    @Column(name = "action_Code")
    private String actionCode;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgRoleAuthPrimaryKey that = (OrgRoleAuthPrimaryKey) o;

        if (!roleId.equals(that.roleId)) return false;
        if (!sysCode.equals(that.sysCode)) return false;
        if (!pageCode.equals(that.pageCode)) return false;
        return actionCode.equals(that.actionCode);

    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + sysCode.hashCode();
        result = 31 * result + pageCode.hashCode();
        result = 31 * result + actionCode.hashCode();
        return result;
    }
}
