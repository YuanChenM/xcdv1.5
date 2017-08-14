/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>表so_order_status对应的SoOrderStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoOrderStatus extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 订单状态编号 */
    @Id
    private Long statusId;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 订单状态 */
    private Integer orderStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderStatus() {

    }

    /**
     * <p>订单状态编号。</p>
     *
     * @return the 订单状态编号
     */
    public Long getStatusId() {
        return statusId;
    }

    /**
     * <p>订单状态编号。</p>
     *
     * @param statusId 订单状态编号。
     */
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>订单状态。</p>
     *
     * @return the 订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * <p>订单状态。</p>
     *
     * @param orderStatus 订单状态。
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

}
