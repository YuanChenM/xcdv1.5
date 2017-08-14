package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 买手销售订单
 * Created by ni_shaotang on 2016/10/11.
 */
@ApiModel(value = "IBA2141122Bean", description = "bean")
public class IBA2141122Bean extends BaseEntity {

    @ApiModelProperty(value = "订单ID")
    private String orderId;
    @ApiModelProperty(value = "订单编码")
    private String orderCode;
    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderAmount;
    @ApiModelProperty(value = "订单创建时间")
    private Date orderTime;
    @ApiModelProperty(value = "订单员")
    private String orderTaker;
    @ApiModelProperty(value = "管家名称")
    private String houseName;
    @ApiModelProperty(value = "买家名称")
    private String buyersName;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
