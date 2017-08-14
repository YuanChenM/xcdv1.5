package com.msk.ssc.bean;

import java.math.BigDecimal;

/**
 * Created by wang_shuai on 2016/7/11.
 */
public class SSC11317DeparturePd {
    /**产品编码*/
    private String pdCode;

    /**计划发货数量*/
    private Integer planDeliveryNum;

    /**实际发货数量*/
    private Integer actualDeliveryNum;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public Integer getPlanDeliveryNum() {
        return planDeliveryNum;
    }

    public void setPlanDeliveryNum(Integer planDeliveryNum) {
        this.planDeliveryNum = planDeliveryNum;
    }

    public Integer getActualDeliveryNum() {
        return actualDeliveryNum;
    }

    public void setActualDeliveryNum(Integer actualDeliveryNum) {
        this.actualDeliveryNum = actualDeliveryNum;
    }
}
