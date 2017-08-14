package com.msk.org.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.msk.common.entity.BaseEntity;

@Entity
public class OrgRoleAuth extends BaseEntity {
    @EmbeddedId
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
