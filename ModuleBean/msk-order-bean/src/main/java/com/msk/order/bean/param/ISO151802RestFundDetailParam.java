package com.msk.order.bean.param;


import com.msk.common.bean.param.BaseRestParam;

import java.math.BigDecimal;

/**
 * Created by sun_jiaju on 2016/9/12.
 */
public class ISO151802RestFundDetailParam extends BaseRestParam {

    /** 金额 */
    private BigDecimal fundAmount;
    /** 供应商 */
    private String payeeId;
    /** 供应商编码 */
    private String payeeName;
    /** 供应商角色 */
    private Integer payeeRole;

    /**
     * <p>默认构造函数。</p>
     */
    public ISO151802RestFundDetailParam() {
    }

    /**
     * <p>fundAmount。</p>
     *
     * @return the fundAmount
     */
    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    /**
     * <p>fundAmount。</p>
     *
     * @param fundAmount fundAmount。     */

    public void setFundAmount(BigDecimal fundAmount) {
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
