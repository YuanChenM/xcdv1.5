package com.msk.org.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.*;

@Entity(name = "orgSysModule")
@Table(name = "ORG_SYS_MODULE")
public class OrgSystemModule extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String sysModule;
    private String loginUserType;
    private String sysShowName;
    private String sysCode;
    private String sysMainUrl;
    private int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSysModule() {
        return sysModule;
    }

    public void setSysModule(String sysModule) {
        this.sysModule = sysModule;
    }

    public String getLoginUserType() {
        return loginUserType;
    }

    public void setLoginUserType(String loginUserType) {
        this.loginUserType = loginUserType;
    }

    public String getSysShowName() {
        return sysShowName;
    }

    public void setSysShowName(String sysShowName) {
        this.sysShowName = sysShowName;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysMainUrl() {
        return sysMainUrl;
    }

    public void setSysMainUrl(String sysMainUrl) {
        this.sysMainUrl = sysMainUrl;
    }
}
