package com.msk.common.bean;

import com.msk.core.entity.BaseEntity;

/**
 * 回归测试生成HTML需要的JavaBean
 */
public class ControllerResult extends BaseEntity{

    //接口名
    private String interName;
    //接口url
    private String interUrl;
    //是否成功
    private String isSussFlag;
    //失败原因
    private String reason;

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getInterUrl() {
        return interUrl;
    }

    public void setInterUrl(String interUrl) {
        this.interUrl = interUrl;
    }

    public String getIsSussFlag() {
        return isSussFlag;
    }

    public void setIsSussFlag(String isSussFlag) {
        this.isSussFlag = isSussFlag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
