package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

/**
 * 卖家，买手，管家快捷信息查询接口返回结果
 * Created by wang_jianzhou on 2016/8/22.
 */
public class ISO151423OrdersRestResult extends BaseEntity{

    private String orderStatus;

    private Integer statusQty;

    private Integer orderStatusNum;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getStatusQty() {
        return statusQty;
    }

    public void setStatusQty(Integer statusQty) {
        this.statusQty = statusQty;
    }

    public Integer getOrderStatusNum() {
        return orderStatusNum;
    }

    public void setOrderStatusNum(Integer orderStatusNum) {
        this.orderStatusNum = orderStatusNum;
    }
}
