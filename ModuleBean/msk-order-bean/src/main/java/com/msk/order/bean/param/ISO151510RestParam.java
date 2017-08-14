package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

import java.util.List;

/**
 * ISO151510_发货单取消后台接口参数
 * Created by wang_jianzhou on 2016/8/4.
 */
public class ISO151510RestParam extends BaseParam {

    private Long shipId;

    private Long orderId;

    private Long subOrderId;

    private String cancelManName;

    public String getCancelManName() {
        return cancelManName;
    }

    public void setCancelManName(String cancelManName) {
        this.cancelManName = cancelManName;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
