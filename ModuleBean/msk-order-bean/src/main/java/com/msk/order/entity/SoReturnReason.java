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
 * <p>表so_return_reason对应的SoReturnReason。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoReturnReason extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 退货原因ID */
    @Id
    private Integer reasonId;
    /** 退货原因名称 */
    private String reasonName;
    /** 以逗号分隔的退货类型 */
    private String returnType;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnReason() {

    }

    /**
     * <p>退货原因ID。</p>
     *
     * @return the 退货原因ID
     */
    public Integer getReasonId() {
        return reasonId;
    }

    /**
     * <p>退货原因ID。</p>
     *
     * @param reasonId 退货原因ID。
     */
    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    /**
     * <p>退货原因名称。</p>
     *
     * @return the 退货原因名称
     */
    public String getReasonName() {
        return reasonName;
    }

    /**
     * <p>退货原因名称。</p>
     *
     * @param reasonName 退货原因名称。
     */
    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    /**
     * <p>以逗号分隔的退货类型。</p>
     *
     * @return the 以逗号分隔的退货类型
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * <p>以逗号分隔的退货类型。</p>
     *
     * @param returnType 以逗号分隔的退货类型。
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

}
