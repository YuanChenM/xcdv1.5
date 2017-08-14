package com.msk.so.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;

/**买家详情
 * SO153102Bean
 * yang_yang
 */
public class SO153102Bean extends BaseEntity{
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**退款金额合计*/
    private BigDecimal totalRefundAmount;
    /**支付金额合计*/
    private BigDecimal totalPaidAmount;


    public BigDecimal getTotalRefundAmount() {
        return totalRefundAmount;
    }

    public void setTotalRefundAmount(BigDecimal totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }

    public BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }



}
