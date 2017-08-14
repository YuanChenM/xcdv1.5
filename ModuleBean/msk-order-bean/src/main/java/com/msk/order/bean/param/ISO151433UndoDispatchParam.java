package com.msk.order.bean.param;

import java.util.Date;

/**
 * Created by wang_shuai on 2016/9/26.
 */
public class ISO151433UndoDispatchParam {
    private Long outboundId;
    private String outboundNo;
    private Date cancelTime;

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
