package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.math.BigDecimal;

/**
 * Created by wang_shuai on 2016/9/23.
 */
public class ISO151433FundDetailParam extends BaseParam {
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
    public ISO151433FundDetailParam() {

    }

    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public Integer getPayeeRole() {
        return payeeRole;
    }

    public void setPayeeRole(Integer payeeRole) {
        this.payeeRole = payeeRole;
    }
}
