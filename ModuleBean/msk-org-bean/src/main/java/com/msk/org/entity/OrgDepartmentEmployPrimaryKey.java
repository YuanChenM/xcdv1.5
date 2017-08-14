package com.msk.org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrgDepartmentEmployPrimaryKey implements Serializable{
    @Column(name = "dept_Id")
    private Long deptId;
    @Column(name = "empl_Id")
    private Long emplId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getEmplId() {
        return emplId;
    }

    public void setEmplId(Long emplId) {
        this.emplId = emplId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgDepartmentEmployPrimaryKey that = (OrgDepartmentEmployPrimaryKey) o;

        if (!deptId.equals(that.deptId)) return false;
        return emplId.equals(that.emplId);

    }

    @Override
    public int hashCode() {
        int result = deptId.hashCode();
        result = 31 * result + emplId.hashCode();
        return result;
    }
}
