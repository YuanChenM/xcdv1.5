package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.List;

/**
 * Created by wu_honglei on 2016/7/4.
 */
public class SSC1130801RsParam extends BasePageParam {
    /** 支付id */
    private String paymentRequestId;
    /** 支付状态 */
    private Integer status;
    /** 付款科目 */
    private String subject;

    private List<Long> paymentRequestIds;


    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Long> getPaymentRequestIds() {
        return paymentRequestIds;
    }

    public void setPaymentRequestIds(List<Long> paymentRequestIds) {
        this.paymentRequestIds = paymentRequestIds;
    }

}
