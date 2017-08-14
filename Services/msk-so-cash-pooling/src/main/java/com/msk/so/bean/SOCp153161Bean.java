package com.msk.so.bean;

import com.msk.core.entity.SoCpRefund;

import java.math.BigDecimal;

/**退款一览
 * SO153161Bean
 * yang_yang
 */
public class SOCp153161Bean extends SoCpRefund {

    /** 订单日期 */
    private String orderTime;

    /** 当前页合计退款额 */
    private BigDecimal currentRefund;

    /** 总合计退款额 */
    private BigDecimal totalRefund;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getCurrentRefund() {
        return currentRefund;
    }

    public void setCurrentRefund(BigDecimal currentRefund) {
        this.currentRefund = currentRefund;
    }

    public BigDecimal getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(BigDecimal totalRefund) {
        this.totalRefund = totalRefund;
    }

}
