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
 * <p>表so_return_status对应的SoReturnStatus。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoReturnStatus extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 退货单ID */
    private Long returnId;
    /** 退货单编码 */
    private String returnCode;
    /** 退货状态ID */
    @Id
    private Long statusId;
    /** 退货状态 */
    private Integer returnStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnStatus() {

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
