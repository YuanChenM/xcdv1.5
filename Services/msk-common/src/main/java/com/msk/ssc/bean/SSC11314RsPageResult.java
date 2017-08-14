package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/5.
 */
public class SSC11314RsPageResult extends RsPageResult {

    private String deliveryConfirmId;

    private List<SSC11314RsResult> deliveryConfirmPageResult;

    public String getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(String deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }

    public List<SSC11314RsResult> getDeliveryConfirmPageResult() {
        return deliveryConfirmPageResult;
    }

    public void setDeliveryConfirmPageResult(List<SSC11314RsResult> deliveryConfirmPageResult) {
        this.deliveryConfirmPageResult = deliveryConfirmPageResult;
    }
}
