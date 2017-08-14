package com.msk.order.bean.result;

import com.msk.common.entity.BaseEntity;

import java.util.List;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public class ISO151427SettlementDetailResult extends BaseEntity {
    //订单id
    private Long orderId;
    //订单编码
    private String orderCode;
    //商品信息list
    private List<ISO151427SoProductResult> pdList;
    //订单状态
    private int settlementStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<ISO151427SoProductResult> getPdList() {
        return pdList;
    }

    public void setPdList(List<ISO151427SoProductResult> pdList) {
        this.pdList = pdList;
    }

    public int getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(int settlementStatus) {
        this.settlementStatus = settlementStatus;
    }
}
