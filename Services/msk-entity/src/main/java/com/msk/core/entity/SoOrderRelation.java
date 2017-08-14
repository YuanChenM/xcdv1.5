/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_order_relation对应的SoOrderRelation。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoOrderRelation extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 主订单编码 */
    private String mainOrderCode;
    /** 分单订单编码 */
    private String childOrderCode;
    /** 主订单ID */
    private Long mainOrderId;
    /** 分单订单ID */
    private Long childOrderId;
    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderRelation() {

    }

    /**
     * <p>主订单编码。</p>
     *
     * @return the 主订单编码
     */
    public String getMainOrderCode() {
        return mainOrderCode;
    }

    /**
     * <p>主订单编码。</p>
     *
     * @param mainOrderCode 主订单编码。
     */
    public void setMainOrderCode(String mainOrderCode) {
        this.mainOrderCode = mainOrderCode;
    }

    /**
     * <p>分单订单编码。</p>
     *
     * @return the 分单订单编码
     */
    public String getChildOrderCode() {
        return childOrderCode;
    }

    /**
     * <p>分单订单编码。</p>
     *
     * @param childOrderCode 分单订单编码。
     */
    public void setChildOrderCode(String childOrderCode) {
        this.childOrderCode = childOrderCode;
    }

    /**
     * <p>主订单ID。</p>
     *
     * @return the 主订单ID
     */
    public Long getMainOrderId() {
        return mainOrderId;
    }

    /**
     * <p>主订单ID。</p>
     *
     * @param mainOrderId 主订单ID。
     */
    public void setMainOrderId(Long mainOrderId) {
        this.mainOrderId = mainOrderId;
    }

    /**
     * <p>分单订单ID。</p>
     *
     * @return the 分单订单ID
     */
    public Long getChildOrderId() {
        return childOrderId;
    }

    /**
     * <p>分单订单ID。</p>
     *
     * @param childOrderId 分单订单ID。
     */
    public void setChildOrderId(Long childOrderId) {
        this.childOrderId = childOrderId;
    }

}
