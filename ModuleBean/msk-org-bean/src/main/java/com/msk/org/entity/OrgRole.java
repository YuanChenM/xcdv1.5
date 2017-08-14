package com.msk.org.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.msk.common.entity.BaseEntity;

@Entity
public class OrgRole extends BaseEntity {
    private String deptId;
    @Id
    private Long roleId;
    private String roleName;
    private String isKey;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }
}
