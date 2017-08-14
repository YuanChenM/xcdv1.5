/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_return_status对应的SoReturnStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoReturnStatus extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** RETURN_ID */
    private Long returnId;
    /** RETURN_CODE */
    private String returnCode;
    /** 退货状态ID */
    private Long statusId;
    /** 退货状态 */
    private Integer returnStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnStatus() {

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
     * <p>退货状态ID。</p>
     *
     * @return the 退货状态ID
     */
    public Long getStatusId() {
        return statusId;
    }

    /**
     * <p>退货状态ID。</p>
     *
     * @param statusId 退货状态ID。
     */
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    /**
     * <p>退货状态。</p>
     *
     * @return the 退货状态
     */
    public Integer getReturnStatus() {
        return returnStatus;
    }

    /**
     * <p>退货状态。</p>
     *
     * @param returnStatus 退货状态。
     */
    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

}
