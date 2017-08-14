package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liu_tao2 on 2016/8/22.
 */
public class BSO151402OrderResult extends BaseEntity {

    /** 订单ID */
    private Long orderId;

    /** 分批订单Id */
    private Long subOrderId;

    /** 订单明细Id */
    private Long orderDetailId;

    /** 分批订单明细ID */
    private Long subOrderDetailId;

    /** 区域编码 */
    private String districtCode;

    /** 订单明细等级 */
    private Integer orderDetailLevel;

    /** 产品编码 */
    private String pdCode;

    /** 订单数量 */
    private BigDecimal orderQty;

    /** 卖家编码 */
    private String sellerCode;

    /** 分批订单明细状态 */
    private Integer subOrderDetailStatus;

    /** 销售平台 */
    private String salePlatform;

    private String pdName;

    private Date orderTime;

    private String orderCode;

    private String lgcsCode;

    public String getLgcsCode() {
        return lgcsCode;
    }

    public void setLgcsCode(String lgcsCode) {
        this.lgcsCode = lgcsCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public Integer getSubOrderDetailStatus() {
        return subOrderDetailStatus;
    }

    public void setSubOrderDetailStatus(Integer subOrderDetailStatus) {
        this.subOrderDetailStatus = subOrderDetailStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getSubOrderDetailId() {
        return subOrderDetailId;
    }

    public void setSubOrderDetailId(Long subOrderDetailId) {
        this.subOrderDetailId = subOrderDetailId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getOrderDetailLevel() {
        return orderDetailLevel;
    }

    public void setOrderDetailLevel(Integer orderDetailLevel) {
        this.orderDetailLevel = orderDetailLevel;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }
}
