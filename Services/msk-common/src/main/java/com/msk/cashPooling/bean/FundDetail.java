package com.msk.cashPooling.bean;


import com.msk.core.entity.SoCpRunning;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by qiu_wenting on 2016/4/18.
 */
public class FundDetail {

    /** 金额 */
    private java.math.BigDecimal fundAmount;
    /** 供应商 */
    private String payeeId;
    /** 供应商编码 */
    private String payeeName;
    /** 供应商角色 */
    private Integer payeeRole;

    /**
     * <p>默认构造函数。</p>
     */
    public FundDetail() {
    }

    /**
     * <p>fundAmount。</p>
     *
     * @return the fundAmount
     */
    public java.math.BigDecimal getFundAmount() {
        return fundAmount;
    }

    /**
     * <p>fundAmount。</p>
     *
     * @param fundAmount fundAmount。     */

    public void setFundAmount(java.math.BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    /**
     * <p>payeeId。</p>
     *
     * @return the payeeId
     */
    public String getPayeeId() {
        return payeeId;
    }

    /**
     * <p>payeeId。</p>
     *
     * @param payeeId payeeId。
     */
    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    /**
     * <p>payeeName。</p>
     *
     * @return the payeeName
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * <p>payeeName。</p>
     *
     * @param payeeName payeeName。
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    /**
     * <p>payeeRole。</p>
     *
     * @return the payeeRole
     */
    public Integer getPayeeRole() {
        return payeeRole;
    }

    /**
     * <p>payeeRole。</p>
     *
     * @param payeeRole payeeRole。
     */
    public void setPayeeRole(Integer payeeRole) {
        this.payeeRole = payeeRole;
    }
}
