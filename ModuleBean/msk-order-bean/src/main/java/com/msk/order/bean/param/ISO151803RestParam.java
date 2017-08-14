package com.msk.order.bean.param;

import com.msk.common.annotation.valid.Required;
import com.msk.common.bean.param.BaseParam;

/**
 * ISO151803_订单发货明细信息查询
 * Created by sun_jiaju on 2016/8/29.
 */
public class ISO151803RestParam extends BaseParam {
    // 买家ID
    @Required(required = true, message = "买家ID不能为空")
    private String buyerId;
    // 买家电话
    private String buyerTel;
    // 订单ID
    @Required(required = true, message = "订单ID不能为空")
    private Long orderId;
    // 订单编码
    private String orderCode;
    // 发货单ID
    private Long shipId;
    // 发货单编号
    private String shipCode;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

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

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }
}
