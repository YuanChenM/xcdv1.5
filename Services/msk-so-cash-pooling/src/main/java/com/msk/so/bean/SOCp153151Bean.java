package com.msk.so.bean;

import com.msk.core.entity.SoCpRunning;

import java.math.BigDecimal;

/**
 * 支付一览
 * SO153151Bean
 * yang_yang
 */
public class SOCp153151Bean extends SoCpRunning {

    /** 页面添加导出按钮 modify by renyi on 2016/8/10 start */

    /** 支付方式: 1：现金 2：转账 3：支票 4：冲抵核销 */
    private String paidTypeStr;
    /** 金额类型： 0：付款 1：退款 2:混合 */

    private String amountTypeStr;

    /** 当前页支付金额合计 */
    private BigDecimal currentPaid;

    /** 总支付金额合计 */
    private BigDecimal totalPaid;

    /**  支付日期 str */
    private String paidTimeStr;

    /** xlsx用 序列 */
    private Integer  xlsxNo;

    public String getPaidTimeStr() {
        return paidTimeStr;
    }

    public void setPaidTimeStr(String paidTimeStr) {
        this.paidTimeStr = paidTimeStr;
    }

    public Integer getXlsxNo() {
        return xlsxNo;
    }

    public void setXlsxNo(Integer xlsxNo) {
        this.xlsxNo = xlsxNo;
    }

    public String getAmountTypeStr() {
        return amountTypeStr;
    }

    public void setAmountTypeStr(String amountTypeStr) {
        this.amountTypeStr = amountTypeStr;
    }

    public String getPaidTypeStr() {
        return paidTypeStr;
    }

    public void setPaidTypeStr(String paidTypeStr) {
        this.paidTypeStr = paidTypeStr;
    }

    /**
     * 获取 当前页支付金额合计
     * @return 当前页支付金额合计
     */
    public BigDecimal getCurrentPaid() {
        return currentPaid;
    }

    /**
     * 设置 当前页支付金额合计
     * @param currentPaid 当前页支付金额合计
     */
    public void setCurrentPaid(BigDecimal currentPaid) {
        this.currentPaid = currentPaid;
    }

    /**
     * 设置 总支付金额合计
     * @return 总支付金额合计
     */
    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    /**
     * 获取 总支付金额合计
     * @param totalPaid 总支付金额合计
     */
    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    /** 页面添加导出按钮 modify by renyi on 2016/8/10 end */
}
