package com.msk.order.bean;


import com.msk.core.entity.SoOrderReceiveDemand;

/**
 * OrderDeliveryBean
 * 配送物流信息
 * 
 * @author jiang_nan
 * @version 1.0
 * update gyh
 * updateTime 2015.1.4
 **/
public class OrderReceiveDemandBean extends SoOrderReceiveDemand {

    private static final long serialVersionUID = 1L;

    //买家ID
    private String orderBuyersId;
    //买家编码
    private String buyerCode;
    //买家名称
    private String buyerName;
    //买家类型
    private String buyerType;
    //买家类型名称
    private String buyerTypeName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOrderBuyersId() {
        return orderBuyersId;
    }

    public void setOrderBuyersId(String orderBuyersId) {
        this.orderBuyersId = orderBuyersId;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }
}
