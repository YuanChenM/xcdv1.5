/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_order_detail_status对应的SoOrderDetailStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoOrderDetailStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 订单明细ID */
    private Long orderDetailId;
    /** 订单明细状态ID */
    private Long id;
    /** 状态 */
    private Integer status;
    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderDetailStatus() {

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
     * <p>订单明细ID。</p>
     *
     * @return the 订单明细ID
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @param orderDetailId 订单明细ID。
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * <p>订单明细状态ID。</p>
     *
     * @return the 订单明细状态ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>订单明细状态ID。</p>
     *
     * @param id 订单明细状态ID。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>状态。</p>
     *
     * @return the 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>状态。</p>
     *
     * @param status 状态。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}
