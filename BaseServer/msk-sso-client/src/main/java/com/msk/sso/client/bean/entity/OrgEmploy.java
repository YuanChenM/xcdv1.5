package com.msk.sso.client.bean.entity;

import com.msk.sso.client.bean.result.DepartmentRoleResult;

import java.io.Serializable;
import java.util.List;


public class OrgEmploy implements Serializable{
    private Long employId;
    private String employCode;
    private String employName;
    private String sex;
    private String birthday;
    private String entryYearMonthDay;
    private String mobile;
    private String emial;
    private String qq;
    private String password;
    private Integer status;
    private List<DepartmentRoleResult> DepartmentRoleList;
    private List<OrgRoleAuth> roleAuthList;

    public List<OrgRoleAuth> getRoleAuthList() {
        return roleAuthList;
    }

    public void setRoleAuthList(List<OrgRoleAuth> roleAuthList) {
        this.roleAuthList = roleAuthList;
    }

    public List<DepartmentRoleResult> getDepartmentRoleList() {
        return DepartmentRoleList;
    }

    public void setDepartmentRoleList(List<DepartmentRoleResult> departmentRoleList) {
        DepartmentRoleList = departmentRoleList;
    }

    public Long getEmployId() {
        return employId;
    }

    public void setEmployId(Long employId) {
        this.employId = employId;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public String getEmployName() {
        return employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEntryYearMonthDay() {
        return entryYearMonthDay;
    }

    public void setEntryYearMonthDay(String entryYearMonthDay) {
        this.entryYearMonthDay = entryYearMonthDay;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
