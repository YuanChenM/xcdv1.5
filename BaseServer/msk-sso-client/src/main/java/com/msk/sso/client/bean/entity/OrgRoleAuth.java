package com.msk.sso.client.bean.entity;

import java.io.Serializable;

public class OrgRoleAuth implements Serializable{
    private OrgRoleAuthPrimaryKey roleAuthPrimaryKey;
    private String isAvailable;

    public OrgRoleAuthPrimaryKey getRoleAuthPrimaryKey() {
        return roleAuthPrimaryKey;
    }

    public void setRoleAuthPrimaryKey(OrgRoleAuthPrimaryKey roleAuthPrimaryKey) {
        this.roleAuthPrimaryKey = roleAuthPrimaryKey;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
}
