package com.msk.org.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrgEmplRolePrimaryKey implements Serializable{
    @Column(name = "EMPL_ID")
    private Long emplId;
    @Column(name = "ROLE_ID")
    private Long roleId;

    public Long getEmplId() {
        return emplId;
    }

    public void setEmplId(Long emplId) {
        this.emplId = emplId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgEmplRolePrimaryKey that = (OrgEmplRolePrimaryKey) o;

        if (!emplId.equals(that.emplId)) return false;
        return roleId.equals(that.roleId);

    }

    @Override
    public int hashCode() {
        int result = emplId.hashCode();
        result = 31 * result + roleId.hashCode();
        return result;
    }
}
