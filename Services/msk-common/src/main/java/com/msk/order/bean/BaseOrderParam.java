package com.msk.order.bean;


import com.hoperun.core.bean.BaseParam;

/**
 * BaseOrderBean
 *
 * @author jiang_nan
 * @version 1.0
 **/
public class BaseOrderParam extends BaseParam {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private Long orderId;
    private String orderCode;
    public BaseOrderParam() {
    }

    public BaseOrderParam(Long orderId, String orderCode) {
        this.orderId = orderId;
        this.orderCode = orderCode;
    }

    /**
     * Getter method for property <tt>orderId</tt>.
     *
     * @return property value of orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Setter method for property <tt>orderId</tt>.
     *
     * @param orderId value to be assigned to property orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Getter method for property <tt>orderCode</tt>.
     *
     * @return property value of orderCode
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * Setter method for property <tt>orderCode</tt>.
     *
     * @param orderCode value to be assigned to property orderCode
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
