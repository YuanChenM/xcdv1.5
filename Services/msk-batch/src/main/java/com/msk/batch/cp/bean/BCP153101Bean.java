package com.msk.batch.cp.bean;


import com.msk.core.entity.SoCpSellerBill;

/**
 * BBP112213Param
 * @author peng_hao
 */
public class BCP153101Bean extends SoCpSellerBill {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 卖家计费项ID */
    private Long selChargingId;

    /** 退回费用ID */
    private Long refundId;

}
