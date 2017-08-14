package com.msk.batch.order.bean;


import com.msk.core.entity.BaseEntity;

import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/24.
 */
public class BSO151402OrderInfo extends BaseEntity {
    private Long subOrderId;

    private Integer orderStatus;

    private Long orderId;

    private String orderCode;

    private String salePlatform;

    private Date orderTime;

    private String slCode;

    private String districtCode;

    private Integer subOrderStatus;

    private Integer soOrderDetailStatus;

    private Integer subOrderDetailStatus;

    private Integer orderShipDetailStatus;

    private Long statusId;

    private Integer orderType;

    public Integer getOrderShipDetailStatus() {
        return orderShipDetailStatus;
    }

    public void setOrderShipDetailStatus(Integer orderShipDetailStatus) {
        this.orderShipDetailStatus = orderShipDetailStatus;
    }

    public Integer getSoOrderDetailStatus() {
        return soOrderDetailStatus;
    }

    public void setSoOrderDetailStatus(Integer soOrderDetailStatus) {
        this.soOrderDetailStatus = soOrderDetailStatus;
    }

    public Integer getSubOrderDetailStatus() {
        return subOrderDetailStatus;
    }

    public void setSubOrderDetailStatus(Integer subOrderDetailStatus) {
        this.subOrderDetailStatus = subOrderDetailStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Integer getSubOrderStatus() {
        return subOrderStatus;
    }

    public void setSubOrderStatus(Integer subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }
}
