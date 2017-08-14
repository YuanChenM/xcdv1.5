package com.msk.print.bean;

import com.msk.core.entity.SoCpRefund;

/**
 * Created by zhang_chi on 2016/8/2.
 */
public class SoCpRefundBean extends SoCpRefund {

    /** 0：退货退款 1：拒收退款 2：关闭订单 */
    private String refundTypeStr;

    /** 退款发生日期 */
    private String refundTimeStr;

    public String getRefundTypeStr() {
        return refundTypeStr;
    }

    public void setRefundTypeStr(String refundTypeStr) {
        this.refundTypeStr = refundTypeStr;
    }

    public String getRefundTimeStr() {
        return refundTimeStr;
    }

    public void setRefundTimeStr(String refundTimeStr) {
        this.refundTimeStr = refundTimeStr;
    }
}
