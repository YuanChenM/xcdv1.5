package com.msk.order.bean.param;


import com.msk.common.bean.param.BaseParam;

/**
 * Created by wang_shuai on 2016/8/8.
 */
public class ISO151415ModifyStatusParam extends BaseParam {
    // 订单ID
    private Long orderId;
    // 发货单ID
    private Long shipId;
    // 子订单ID
    private Long childId;
    // 订单状态
    private Integer orderStatus;
    // 发货单状态
    private Integer shipStatus;
    //分批订单id
    private Long subOrderId;
    //分批订单状态
    private Integer subOrderStatus;
    //收发货flg
    private String modifyFlag;

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

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(Integer shipStatus) {
        this.shipStatus = shipStatus;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getSubOrderStatus() {
        return subOrderStatus;
    }

    public void setSubOrderStatus(Integer subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    public String getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }
}
