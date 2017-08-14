/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_transport_detail对应的SscTransportDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscTransportDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ID */
    private java.lang.Long id;
    /** 发货单类型0表示发货订单1表示发货确认单 */
    private java.lang.String deliveryType;
    /** 发货单编号 */
    private java.lang.String deliveryCode;
    /** 车次ID */
    private java.lang.Long transportTrips;
    /** 预计发货时间 */
    private java.util.Date expectDeliveryDate;
    /** 预计到达时间 */
    private java.util.Date expectArriveDate;
    /** 产品属性码 */
    private java.lang.String productAttrCode;
    /** 计划发货重量 */
    private java.lang.Integer planDeliveryQua;
    /** 计划发货箱数 */
    private java.lang.Integer planDeliveryBox;
    /**
     * <p>默认构造函数。</p>
     */
    public SscTransportDetail() {

    }

    /**
     * <p>ID。</p>
     *
     * @return the ID
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * <p>ID。</p>
     *
     * @param id ID。
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }

    /**
     * <p>发货单类型0表示发货订单1表示发货确认单。</p>
     *
     * @return the 发货单类型0表示发货订单1表示发货确认单
     */
    public java.lang.String getDeliveryType() {
        return deliveryType;
    }

    /**
     * <p>发货单类型0表示发货订单1表示发货确认单。</p>
     *
     * @param deliveryType 发货单类型0表示发货订单1表示发货确认单。
     */
    public void setDeliveryType(java.lang.String deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * <p>发货单编号。</p>
     *
     * @return the 发货单编号
     */
    public java.lang.String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货单编号。</p>
     *
     * @param deliveryCode 发货单编号。
     */
    public void setDeliveryCode(java.lang.String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>车次ID。</p>
     *
     * @return the 车次ID
     */
    public java.lang.Long getTransportTrips() {
        return transportTrips;
    }

    /**
     * <p>车次ID。</p>
     *
     * @param transportTrips 车次ID。
     */
    public void setTransportTrips(java.lang.Long transportTrips) {
        this.transportTrips = transportTrips;
    }

    /**
     * <p>预计发货时间。</p>
     *
     * @return the 预计发货时间
     */
    public java.util.Date getExpectDeliveryDate() {
        return expectDeliveryDate;
    }

    /**
     * <p>预计发货时间。</p>
     *
     * @param expectDeliveryDate 预计发货时间。
     */
    public void setExpectDeliveryDate(java.util.Date expectDeliveryDate) {
        this.expectDeliveryDate = expectDeliveryDate;
    }

    /**
     * <p>预计到达时间。</p>
     *
     * @return the 预计到达时间
     */
    public java.util.Date getExpectArriveDate() {
        return expectArriveDate;
    }

    /**
     * <p>预计到达时间。</p>
     *
     * @param expectArriveDate 预计到达时间。
     */
    public void setExpectArriveDate(java.util.Date expectArriveDate) {
        this.expectArriveDate = expectArriveDate;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @return the 产品属性码
     */
    public java.lang.String getProductAttrCode() {
        return productAttrCode;
    }

    /**
     * <p>产品属性码。</p>
     *
     * @param productAttrCode 产品属性码。
     */
    public void setProductAttrCode(java.lang.String productAttrCode) {
        this.productAttrCode = productAttrCode;
    }

    /**
     * <p>计划发货重量。</p>
     *
     * @return the 计划发货重量
     */
    public java.lang.Integer getPlanDeliveryQua() {
        return planDeliveryQua;
    }

    /**
     * <p>计划发货重量。</p>
     *
     * @param planDeliveryQua 计划发货重量。
     */
    public void setPlanDeliveryQua(java.lang.Integer planDeliveryQua) {
        this.planDeliveryQua = planDeliveryQua;
    }

    /**
     * <p>计划发货箱数。</p>
     *
     * @return the 计划发货箱数
     */
    public java.lang.Integer getPlanDeliveryBox() {
        return planDeliveryBox;
    }

    /**
     * <p>计划发货箱数。</p>
     *
     * @param planDeliveryBox 计划发货箱数。
     */
    public void setPlanDeliveryBox(java.lang.Integer planDeliveryBox) {
        this.planDeliveryBox = planDeliveryBox;
    }

}
