/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_sub_order_status对应的SoSubOrderStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoSubOrderStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 分批订单状态ID */
    private Long statusId;
    /** 分批订单ID */
    private Long subOrderId;
    /** 订单ID */
    private Long orderId;
    /** 分批订单状态 */
    private Integer subOrderStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoSubOrderStatus() {

    }

    /**
     * <p>分批订单状态ID。</p>
     *
     * @return the 分批订单状态ID
     */
    public Long getStatusId() {
        return statusId;
    }

    /**
     * <p>分批订单状态ID。</p>
     *
     * @param statusId 分批订单状态ID。
     */
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public Long getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
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
     * <p>分批订单状态。</p>
     *
     * @return the 分批订单状态
     */
    public Integer getSubOrderStatus() {
        return subOrderStatus;
    }

    /**
     * <p>分批订单状态。</p>
     *
     * @param subOrderStatus 分批订单状态。
     */
    public void setSubOrderStatus(Integer subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

}
