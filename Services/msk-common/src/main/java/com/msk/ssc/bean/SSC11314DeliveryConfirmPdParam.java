package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryOrderPd;

/**
 * Created by wang_shuai on 2016/7/6.
 */
public class SSC11314DeliveryConfirmPdParam extends SscDeliveryOrderPd {
    //发货确认单id
    private String deliveryConfirmId;
    //发货确认单code
    private String deliveryConfirmCode;
    //产品确认箱数
    private Integer productConfirmBox;

    public Integer getProductConfirmBox() {
        return productConfirmBox;
    }

    public void setProductConfirmBox(Integer productConfirmBox) {
        this.productConfirmBox = productConfirmBox;
    }

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
    }

    public String getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(String deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }
}
