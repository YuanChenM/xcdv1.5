/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_return_order_detail_status对应的SoReturnOrderDetailStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoReturnOrderDetailStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 退货单ID */
    private Long returnId;
    /** 退货单编码 */
    private String returnCode;
    /** 退货单明细ID */
    private Long returnDetailId;
    /** 退货状态ID */
    private Long id;
    /** 状态 */
    private Integer status;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnOrderDetailStatus() {

    }

    /**
     * <p>退货单ID。</p>
     *
     * @return the 退货单ID
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * <p>退货单ID。</p>
     *
     * @param returnId 退货单ID。
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    /**
     * <p>退货单编码。</p>
     *
     * @return the 退货单编码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * <p>退货单编码。</p>
     *
     * @param returnCode 退货单编码。
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * <p>退货单明细ID。</p>
     *
     * @return the 退货单明细ID
     */
    public Long getReturnDetailId() {
        return returnDetailId;
    }

    /**
     * <p>退货单明细ID。</p>
     *
     * @param returnDetailId 退货单明细ID。
     */
    public void setReturnDetailId(Long returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    /**
     * <p>退货状态ID。</p>
     *
     * @return the 退货状态ID
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>退货状态ID。</p>
     *
     * @param id 退货状态ID。
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
