package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;

/**
 * Created by peng_hao on 2016/8/9.
 */
public class SSC11320RsParam extends BasePageParam {

    /** 付款记录ID */
    private Long paymentId;

    /** 付款申请单ID */
    private Long paymentRequestId;
    /** 付款申请单号 */
    private String paymentRequestCode;

    /** 付款状态 */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(Long paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getPaymentRequestCode() {
        return paymentRequestCode;
    }

    public void setPaymentRequestCode(String paymentRequestCode) {
        this.paymentRequestCode = paymentRequestCode;
    }
}
