package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;
import com.msk.order.bean.result.OrderShipInfo;

import java.util.List;

/**
 * Created by wang_shuai on 2016/8/9.
 */
public class ISO151415OrderDeliverParam extends BaseParam {
    /** 订单ID */
    private Long orderId;

    /** 订单编码 */
    private String orderCode;

    /** 发货单ID */
    private Long shipId;

    /** 付款类型 */
    private Integer paymentType;

    /** 多次配送单信息 */
    private List<OrderShipInfo> shipList;

    /** 明细状态 */
    private Integer detailStatus;

    /** 主订单状态 */
    private Integer orderStatus;

    /** 子订单状态 */
    private Integer orderChildStatus;

    /**
     * 销售平台
     */
    private String salePlatform;

    /**
     * 销售区域编码
     */
    private String saleRegionCode;

    //分批订单code
    private String subOrderCode;


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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderShipInfo> getShipList() {
        return shipList;
    }

    public void setShipList(List<OrderShipInfo> shipList) {
        this.shipList = shipList;
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderChildStatus() {
        return orderChildStatus;
    }

    public void setOrderChildStatus(Integer orderChildStatus) {
        this.orderChildStatus = orderChildStatus;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getSaleRegionCode() {
        return saleRegionCode;
    }

    public void setSaleRegionCode(String saleRegionCode) {
        this.saleRegionCode = saleRegionCode;
    }

    public String getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }
}
