package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.math.BigDecimal;

/**
 * ISO151422_退货入库
 * Created by wang_jianzhou on 2016/8/17.
 */
public class ISO151422RestResult extends BaseResult{

    private static final long serialVersionUID = 1L;

    private Long returnId;

    private Integer returnStatus;

    private String returnCode;

    private String crtTime;

    private Integer ver;

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }
}
