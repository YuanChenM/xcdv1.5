/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_return_order_detail_availability_status对应的SoReturnOrderDetailAvailabilityStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoReturnOrderDetailAvailabilityStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** RETURN_ID */
    private Long returnId;
    /** RETURN_CODE */
    private String returnCode;
    /** ORDER_ID */
    private Long orderId;
    /** ORDER_CODE */
    private String orderCode;
    /** RETURN_DETAIL_AVAILABILITY_ID */
    private Long returnDetailAvailabilityId;
    /** ID */
    private Long id;
    /** STATUS */
    private Integer status;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnOrderDetailAvailabilityStatus() {

    }

    /**
     * <p>RETURN_ID。</p>
     *
     * @return the RETURN_ID
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * <p>RETURN_ID。</p>
     *
     * @param returnId RETURN_ID。
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    /**
     * <p>RETURN_CODE。</p>
     *
     * @return the RETURN_CODE
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * <p>RETURN_CODE。</p>
     *
     * @param returnCode RETURN_CODE。
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * <p>ORDER_ID。</p>
     *
     * @return the ORDER_ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>ORDER_ID。</p>
     *
     * @param orderId ORDER_ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>ORDER_CODE。</p>
     *
     * @return the ORDER_CODE
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>ORDER_CODE。</p>
     *
     * @param orderCode ORDER_CODE。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>RETURN_DETAIL_AVAILABILITY_ID。</p>
     *
     * @return the RETURN_DETAIL_AVAILABILITY_ID
     */
    public Long getReturnDetailAvailabilityId() {
        return returnDetailAvailabilityId;
    }

    /**
     * <p>RETURN_DETAIL_AVAILABILITY_ID。</p>
     *
     * @param returnDetailAvailabilityId RETURN_DETAIL_AVAILABILITY_ID。
     */
    public void setReturnDetailAvailabilityId(Long returnDetailAvailabilityId) {
        this.returnDetailAvailabilityId = returnDetailAvailabilityId;
    }

    /**
     * <p>ID。</p>
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID。</p>
     *
     * @param id ID。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * <p>STATUS。</p>
     *
     * @return the STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>STATUS。</p>
     *
     * @param status STATUS。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}
