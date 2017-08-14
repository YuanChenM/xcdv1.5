/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_plan_detail对应的SscDeliveryPlanDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryPlanDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 唯一ID */
    private java.lang.Long id;
    /** 产品ID */
    private java.lang.Long detailId;
    /** 计划生产日期 */
    private java.util.Date produceDate;
    /** 计划生产数量 */
    private java.lang.Integer planNum;
    /** 实际生产数量 */
    private java.lang.Integer realNum;
    /** 类型（1生产期管控 2运输期管控） */
    private java.lang.String type;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryPlanDetail() {

    }

    /**
     * <p>唯一ID。</p>
     *
     * @return the 唯一ID
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * <p>唯一ID。</p>
     *
     * @param id 唯一ID。
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     * <p>产品ID。</p>
     *
     * @return the 产品ID
     */
    public java.lang.Long getDetailId() {
        return detailId;
    }

    /**
     * <p>产品ID。</p>
     *
     * @param detailId 产品ID。
     */
    public void setDetailId(java.lang.Long detailId) {
        this.detailId = detailId;
    }

    /**
     * <p>计划生产日期。</p>
     *
     * @return the 计划生产日期
     */
    public java.util.Date getProduceDate() {
        return produceDate;
    }

    /**
     * <p>计划生产日期。</p>
     *
     * @param produceDate 计划生产日期。
     */
    public void setProduceDate(java.util.Date produceDate) {
        this.produceDate = produceDate;
    }

    /**
     * <p>计划生产数量。</p>
     *
     * @return the 计划生产数量
     */
    public java.lang.Integer getPlanNum() {
        return planNum;
    }

    /**
     * <p>计划生产数量。</p>
     *
     * @param planNum 计划生产数量。
     */
    public void setPlanNum(java.lang.Integer planNum) {
        this.planNum = planNum;
    }

    /**
     * <p>实际生产数量。</p>
     *
     * @return the 实际生产数量
     */
    public java.lang.Integer getRealNum() {
        return realNum;
    }

    /**
     * <p>实际生产数量。</p>
     *
     * @param realNum 实际生产数量。
     */
    public void setRealNum(java.lang.Integer realNum) {
        this.realNum = realNum;
    }

    /**
     * <p>类型（1生产期管控 2运输期管控）。</p>
     *
     * @return the 类型（1生产期管控 2运输期管控）
     */
    public java.lang.String getType() {
        return type;
    }

    /**
     * <p>类型（1生产期管控 2运输期管控）。</p>
     *
     * @param type 类型（1生产期管控 2运输期管控）。
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

}
