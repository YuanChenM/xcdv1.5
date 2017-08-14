package com.msk.inventory.bean;

import java.util.Date;

/**
 * Created by wang_fan2 on 2016/9/18.
 */
public class ISO152418InvParamBean {

    private Long outboundId;
    /** OUTBOUND_NO */
    private String outboundNo;

    private java.util.Date cancelTime;

    public Long getOutboundId() {
        return outboundId;
    }

    public void setOutboundId(Long outboundId) {
        this.outboundId = outboundId;
    }

    public String getOutboundNo() {
        return outboundNo;
    }

    public void setOutboundNo(String outboundNo) {
        this.outboundNo = outboundNo;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }
}
