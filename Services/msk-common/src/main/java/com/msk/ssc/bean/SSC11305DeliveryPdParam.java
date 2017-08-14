package com.msk.ssc.bean;

import com.msk.core.entity.SscContractPrDetail;

/**
 * Created by wang_shuai on 2016/7/12.
 */
public class SSC11305DeliveryPdParam extends SscContractPrDetail {
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编号 */
    private java.lang.String deliveryCode;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }
}
