package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * Created by ni_shaotang on 2016/9/29.
 */
public class IBA2141101Bean  extends BaseEntity {
    //主键id
    private int versionsId;
    //版本编号
    private String versionsCode;
    //版本状态   1：正常使用；2：维护中；0：下架
    private String versionsStatus;
    //下载地址
    private String downloadUrl;
    //结束时间
    private Date endTime;
    //说明
    private String remark;

    public int getVersionsId() {
        return versionsId;
    }

    public void setVersionsId(int versionsId) {
        this.versionsId = versionsId;
    }

    public String getVersionsCode() {
        return versionsCode;
    }

    public void setVersionsCode(String versionsCode) {
        this.versionsCode = versionsCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersionsStatus() {
        return versionsStatus;
    }

    public void setVersionsStatus(String versionsStatus) {
        this.versionsStatus = versionsStatus;
    }
}
