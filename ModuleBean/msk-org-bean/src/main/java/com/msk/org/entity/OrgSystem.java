package com.msk.org.entity;

import javax.persistence.*;

import com.msk.common.entity.BaseEntity;

import java.util.List;

@Entity(name = "OrgSystem")
@Table(name = "ORG_SYS")
public class OrgSystem extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String sysCode;
    private String sysName;
    private String sysLevel;
    private String parentCode;
    private String sysDesc;

    @Transient
    private List<OrgPage> pageList;

    public List<OrgPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<OrgPage> pageList) {
        this.pageList = pageList;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysLevel() {
        return sysLevel;
    }

    public void setSysLevel(String sysLevel) {
        this.sysLevel = sysLevel;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    @Override
    public int hashCode() {
        String in = this.sysCode;
        return in.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        OrgSystem system = (OrgSystem)obj;
        String systemCode = system.getSysCode();
        return this.sysCode.equals(systemCode);
    }
}
