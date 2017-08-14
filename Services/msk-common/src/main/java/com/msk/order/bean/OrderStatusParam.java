package com.msk.order.bean;

/**
 * OrderStatus
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class OrderStatusParam extends BaseOrderParam {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**本次订单状态*/
    private int oldStatus;
    /**新的订单状态*/
    private int newStatus;

    /**
     * 默认构造函数
     */
    public OrderStatusParam() {
    }

    public OrderStatusParam(Long orderId, String orderCode, int oldStatus, int newStatus) {
        super(orderId, orderCode);
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    /**
     * Getter method for property <tt>oldStatus</tt>.
     *
     * @return property value of oldStatus
     */
    public int getOldStatus() {
        return oldStatus;
    }

    /**
     * Setter method for property <tt>oldStatus</tt>.
     *
     * @param oldStatus value to be assigned to property oldStatus
     */
    public void setOldStatus(int oldStatus) {
        this.oldStatus = oldStatus;
    }

    /**
     * Getter method for property <tt>newStatus</tt>.
     *
     * @return property value of newStatus
     */
    public int getNewStatus() {
        return newStatus;
    }

    /**
     * Setter method for property <tt>newStatus</tt>.
     *
     * @param newStatus value to be assigned to property newStatus
     */
    public void setNewStatus(int newStatus) {
        this.newStatus = newStatus;
    }
}
