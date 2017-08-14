package com.msk.org.bean;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable{
    private Long deptId;
    private String departmentCode;
    private String departmentName;
    public Department(){}
    public Department(Long deptId, String departmentCode, String departmentName) {
        this.deptId = deptId;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
