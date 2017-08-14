package com.msk.org.bean;

import java.io.Serializable;
import java.util.List;

import com.msk.org.entity.OrgRoleAuth;
import com.msk.org.entity.OrgSystemModule;

public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String emplNo;
    private String emplName;
    private String emplId;
    private String loginPwd;
    private String emialAddr;
    private int status;
    private String userType;
    private List<OrgRoleAuth> userRoleList;
    private List<OrgSystemModule> systemModules;
    private List<Department> departmentList;

    public String getEmplNo() {
        return emplNo;
    }

    public void setEmplNo(String emplNo) {
        this.emplNo = emplNo;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmialAddr() {
        return emialAddr;
    }

    public void setEmialAddr(String emialAddr) {
        this.emialAddr = emialAddr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<OrgRoleAuth> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<OrgRoleAuth> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<OrgSystemModule> getSystemModules() {
        return systemModules;
    }

    public void setSystemModules(List<OrgSystemModule> systemModules) {
        this.systemModules = systemModules;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
