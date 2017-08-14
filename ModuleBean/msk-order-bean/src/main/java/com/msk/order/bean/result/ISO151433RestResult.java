package com.msk.order.bean.result;


import com.msk.common.entity.BaseEntity;

/**
 * Created by wang_shuai on 2016/8/25.
 */
public class ISO151433RestResult extends BaseEntity {
    //订单ID
    private Long orderId;
    //发货单ID
    private Long shipId;
    //数据版本号
    private Integer ver;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    @Override
    public Integer getVer() {
        return ver;
    }

    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
