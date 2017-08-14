package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseRestPageParam;

import java.util.List;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public class ISO151427RestParam extends BaseRestPageParam {
    //配送单list
    private List<ISO151427DeliverParam> deliverList;
    //退货单list
    private List<ISO151427SoReturnParam> returnList;
    //结算明细订单状态
    private String settlementStatus;
    //卖家id
    private String sellerId;
    //卖家编码
    private String sellerCode;
    //订单id
    private String orderId;
    //订单编码
    private String orderCode;

    public List<ISO151427DeliverParam> getDeliverList() {
        return deliverList;
    }

    public void setDeliverList(List<ISO151427DeliverParam> deliverList) {
        this.deliverList = deliverList;
    }

    public List<ISO151427SoReturnParam> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ISO151427SoReturnParam> returnList) {
        this.returnList = returnList;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
