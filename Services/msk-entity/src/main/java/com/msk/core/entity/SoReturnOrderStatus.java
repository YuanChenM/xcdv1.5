/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_return_order_status对应的SoReturnOrderStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoReturnOrderStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 退货单ID */
    private Long returnId;
    /** 编号 */
    private Long id;
    /** 状态 */
    private Integer status;
    /** RETURN_CODE */
    private String returnCode;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnOrderStatus() {

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
     * <p>编号。</p>
     *
     * @return the 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>编号。</p>
     *
     * @param id 编号。
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

}
