package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153106Bean
 * li_huiqian
 */
public class SO153102Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 支付流水号
     */
    private Long runningId;
    /**
     * 退货、退款、拒收ID
     */
    private Long refundId;

    /**
     * 主键
     */
    private String buyerBillId;
    /**
     * 操作人
     */
    private String emplId;

    public Long getRunningId() {
        return runningId;
    }

    public void setRunningId(Long runningId) {
        this.runningId = runningId;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getBuyerBillId() {
        return buyerBillId;
    }

    public void setBuyerBillId(String buyerBillId) {
        this.buyerBillId = buyerBillId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

}
