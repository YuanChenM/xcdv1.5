package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

/**
 * ISO151428_购买记录查询接口
 * Created by wang_shuai on 2016/8/24.
 */
public class ISO151428BuyRecordResult extends BaseEntity {
    //买家id
    private String buyerId;
    //买家编码
    private String buyerCode;
    //买家名称
    private String buyerName;
    //规格编码
    private String featureCode;
    //规格名称
    private String featureName;
    //订单数量
    private long orderQty;
    //成交时间
    private String dealTime;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(long orderQty) {
        this.orderQty = orderQty;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}
