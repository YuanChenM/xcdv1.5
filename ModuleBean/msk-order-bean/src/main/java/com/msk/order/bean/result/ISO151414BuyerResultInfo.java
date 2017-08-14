package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * Created by liu_tao2 on 2016/8/25.
 */
public class ISO151414BuyerResultInfo extends BaseResult {
    private String buyerCode;

    private String superiorIdName;

    private String buyerId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSuperiorIdName() {
        return superiorIdName;
    }

    public void setSuperiorIdName(String superiorIdName) {
        this.superiorIdName = superiorIdName;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }
}
