package com.msk.so.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * SO153112Bean
 * li_huiqian
 */
public class SOCp153112Param extends BaseParam {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 资金池 支付流水ID
     */
    private Long runningId;
    /**
     * 资金池 退货、退款、拒收费用明细ID/卖家计费单历史ID
     */
    private Long id;
    /**
     * 卖家计费单历史ID
     */
    private Long sellerBillHisId;
    /**
     * 资金池 退货、退款、拒收费用明细ID
     */
    private Long refundId;

    /**
     * 表名：退货、退款、拒收费用明细 REFUND ; 卖家计费单历史 SELLER_BILL_HIS
     */
    private String tb;

    /**
     * 主键
     */
    private String sellerBillId;
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

    public String getSellerBillId() {
        return sellerBillId;
    }

    public void setSellerBillId(String sellerBillId) {
        this.sellerBillId = sellerBillId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerBillHisId() {
        return sellerBillHisId;
    }

    public void setSellerBillHisId(Long sellerBillHisId) {
        this.sellerBillHisId = sellerBillHisId;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }
}
