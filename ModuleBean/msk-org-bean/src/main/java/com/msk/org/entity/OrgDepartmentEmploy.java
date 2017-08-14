package com.msk.org.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "ORG_DEPT_EMPL")
public class OrgDepartmentEmploy extends BaseEntity {
    @EmbeddedId
    private OrgDepartmentEmployPrimaryKey deptEmplPrimaryKey;
    @Column(name = "DEPT_CODE")
    private String departmentCode;
    @Column(name = "EMPL_CODE")
    private String employCode;
    private Integer status;


    public OrgDepartmentEmployPrimaryKey getDeptEmplPrimaryKey() {
        return deptEmplPrimaryKey;
    }

    public void setDeptEmplPrimaryKey(OrgDepartmentEmployPrimaryKey deptEmplPrimaryKey) {
        this.deptEmplPrimaryKey = deptEmplPrimaryKey;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
