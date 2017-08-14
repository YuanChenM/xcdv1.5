package com.msk.org.entity;

import javax.persistence.*;

import com.msk.common.entity.BaseEntity;

@Entity(name="OrgEmploy")
@Table(name = "ORG_EMPL")
public class OrgEmploy extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "EMPL_ID")
    private Long employId;
    @Column(name = "EMPL_CODE")
    private String employCode;
    @Column(name = "EMPL_NAME")
    private String employName;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "BIRTH")
    private String birthday;
    @Column(name = "ENTRY")
    private String entryYearMonthDay;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "EMIAL")
    private String emial;
    @Column(name = "QQ")
    private String qq;
    @Column(name = "PWD")
    private String password;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "USER_TYPE",length = 20)
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
