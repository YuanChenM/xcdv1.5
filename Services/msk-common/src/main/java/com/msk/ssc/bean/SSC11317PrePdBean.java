package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryPrePd;

/**
 * Created by wang_shuai on 2016/7/11.
 */
public class SSC11317PrePdBean extends SscDeliveryPrePd {
    private String pdName;

    /**
     * 发货确认单ID
     */
    private String deliveryConfirmId;

    /**
     * 发货车次
     */
    private String deliveryBatch;
    /**
     * 差异箱数
     */
    private int differBoxes;


    public String getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(String deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }


    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public int getDifferBoxes() {
        return differBoxes;
    }

    public void setDifferBoxes(int differBoxes) {
        this.differBoxes = differBoxes;
    }

    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }
}
