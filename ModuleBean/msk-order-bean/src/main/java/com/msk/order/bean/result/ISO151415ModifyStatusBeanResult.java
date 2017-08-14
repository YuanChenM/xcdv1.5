package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

/**
 * Created by wang_shuai on 2016/8/8.
 */
public class ISO151415ModifyStatusBeanResult extends BaseResult {
    private Integer allCount;

    private Integer cancelCount;

    private Integer allReceiptCount;

    private Integer partialReceiptCount;

    private Integer allShipmentCount;

    private Integer partialShipmentCount;

    private Integer waitDisCount;

    private Integer waitSendCount;

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(Integer cancelCount) {
        this.cancelCount = cancelCount;
    }

    public Integer getAllReceiptCount() {
        return allReceiptCount;
    }

    public void setAllReceiptCount(Integer allReceiptCount) {
        this.allReceiptCount = allReceiptCount;
    }

    public Integer getPartialReceiptCount() {
        return partialReceiptCount;
    }

    public void setPartialReceiptCount(Integer partialReceiptCount) {
        this.partialReceiptCount = partialReceiptCount;
    }

    public Integer getAllShipmentCount() {
        return allShipmentCount;
    }

    public void setAllShipmentCount(Integer allShipmentCount) {
        this.allShipmentCount = allShipmentCount;
    }

    public Integer getPartialShipmentCount() {
        return partialShipmentCount;
    }

    public void setPartialShipmentCount(Integer partialShipmentCount) {
        this.partialShipmentCount = partialShipmentCount;
    }

    public Integer getWaitDisCount() {
        return waitDisCount;
    }

    public void setWaitDisCount(Integer waitDisCount) {
        this.waitDisCount = waitDisCount;
    }

    public Integer getWaitSendCount() {
        return waitSendCount;
    }

    public void setWaitSendCount(Integer waitSendCount) {
        this.waitSendCount = waitSendCount;
    }
}
