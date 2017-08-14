/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表br_o_order_info对应的BrOOrderInfo</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class BrOOrderInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** ORDER_ID */
    private Long orderId;
    /** ORDER_CODE */
    private String orderCode;
    /** ORDER_DETAIL_ID */
    private Long orderDetailId;
    /** BUYER_ID */
    private String buyerId;
    /** BUYER_CODE */
    private String buyerCode;
    /** BUYER_NAME */
    private String buyerName;
    /** BUYER_TYPE */
    private String buyerType;
    /** PD_CODE */
    private String pdCode;
    /** CLASSES_CODE */
    private String classesCode;
    /** MACHINING_CODE */
    private String machiningCode;
    /** BREED_CODE */
    private String breedCode;
    /** FEATURE_CODE */
    private String featureCode;
    /** GRADE_CODE */
    private String gradeCode;
    /** PD_PRICE */
    private java.math.BigDecimal pdPrice;
    /** UNIT */
    private String unit;
    /** PACKING_VOLUME */
    private java.math.BigDecimal packingVolume;
    /** WEIGHT */
    private java.math.BigDecimal weight;
    /** VOLUME */
    private java.math.BigDecimal volume;
    /** ORDER_AMOUNT */
    private java.math.BigDecimal orderAmount;
    /** ORDER_QTY */
    private java.math.BigDecimal orderQty;
    /** RECEIVE_QTY */
    private java.math.BigDecimal receiveQty;
    /** RETURN_QTY */
    private java.math.BigDecimal returnQty;
    /** ORDER_TIME */
    private java.util.Date orderTime;
    /** RECEIVED_TIME */
    private java.util.Date receivedTime;
    /** ORDER_STATUS */
    private Integer orderStatus;
    /** DETAIL_STATUS */
    private Integer detailStatus;
    /**
     * <p>默认构造函数</p>
     */
    public BrOOrderInfo() {

    }

    /**
     * <p>ORDER_ID</p>
     *
     * @return the ORDER_ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>ORDER_ID</p>
     *
     * @param orderId ORDER_ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>ORDER_CODE</p>
     *
     * @return the ORDER_CODE
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>ORDER_CODE</p>
     *
     * @param orderCode ORDER_CODE
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>ORDER_DETAIL_ID</p>
     *
     * @return the ORDER_DETAIL_ID
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * <p>ORDER_DETAIL_ID</p>
     *
     * @param orderDetailId ORDER_DETAIL_ID
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @return the BUYER_ID
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * <p>BUYER_ID</p>
     *
     * @param buyerId BUYER_ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * <p>BUYER_CODE</p>
     *
     * @return the BUYER_CODE
     */
    public String getBuyerCode() {
        return buyerCode;
    }

    /**
     * <p>BUYER_CODE</p>
     *
     * @param buyerCode BUYER_CODE
     */
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    /**
     * <p>BUYER_NAME</p>
     *
     * @return the BUYER_NAME
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * <p>BUYER_NAME</p>
     *
     * @param buyerName BUYER_NAME
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * <p>BUYER_TYPE</p>
     *
     * @return the BUYER_TYPE
     */
    public String getBuyerType() {
        return buyerType;
    }

    /**
     * <p>BUYER_TYPE</p>
     *
     * @param buyerType BUYER_TYPE
     */
    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    /**
     * <p>PD_CODE</p>
     *
     * @return the PD_CODE
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>PD_CODE</p>
     *
     * @param pdCode PD_CODE
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>CLASSES_CODE</p>
     *
     * @return the CLASSES_CODE
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>CLASSES_CODE</p>
     *
     * @param classesCode CLASSES_CODE
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>MACHINING_CODE</p>
     *
     * @return the MACHINING_CODE
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>MACHINING_CODE</p>
     *
     * @param machiningCode MACHINING_CODE
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>BREED_CODE</p>
     *
     * @return the BREED_CODE
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>BREED_CODE</p>
     *
     * @param breedCode BREED_CODE
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>FEATURE_CODE</p>
     *
     * @return the FEATURE_CODE
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>FEATURE_CODE</p>
     *
     * @param featureCode FEATURE_CODE
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>GRADE_CODE</p>
     *
     * @return the GRADE_CODE
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>GRADE_CODE</p>
     *
     * @param gradeCode GRADE_CODE
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>PD_PRICE</p>
     *
     * @return the PD_PRICE
     */
    public java.math.BigDecimal getPdPrice() {
        return pdPrice;
    }

    /**
     * <p>PD_PRICE</p>
     *
     * @param pdPrice PD_PRICE
     */
    public void setPdPrice(java.math.BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    /**
     * <p>UNIT</p>
     *
     * @return the UNIT
     */
    public String getUnit() {
        return unit;
    }

    /**
     * <p>UNIT</p>
     *
     * @param unit UNIT
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * <p>PACKING_VOLUME</p>
     *
     * @return the PACKING_VOLUME
     */
    public java.math.BigDecimal getPackingVolume() {
        return packingVolume;
    }

    /**
     * <p>PACKING_VOLUME</p>
     *
     * @param packingVolume PACKING_VOLUME
     */
    public void setPackingVolume(java.math.BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    /**
     * <p>WEIGHT</p>
     *
     * @return the WEIGHT
     */
    public java.math.BigDecimal getWeight() {
        return weight;
    }

    /**
     * <p>WEIGHT</p>
     *
     * @param weight WEIGHT
     */
    public void setWeight(java.math.BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * <p>VOLUME</p>
     *
     * @return the VOLUME
     */
    public java.math.BigDecimal getVolume() {
        return volume;
    }

    /**
     * <p>VOLUME</p>
     *
     * @param volume VOLUME
     */
    public void setVolume(java.math.BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * <p>ORDER_AMOUNT</p>
     *
     * @return the ORDER_AMOUNT
     */
    public java.math.BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * <p>ORDER_AMOUNT</p>
     *
     * @param orderAmount ORDER_AMOUNT
     */
    public void setOrderAmount(java.math.BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * <p>ORDER_QTY</p>
     *
     * @return the ORDER_QTY
     */
    public java.math.BigDecimal getOrderQty() {
        return orderQty;
    }

    /**
     * <p>ORDER_QTY</p>
     *
     * @param orderQty ORDER_QTY
     */
    public void setOrderQty(java.math.BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    /**
     * <p>RECEIVE_QTY</p>
     *
     * @return the RECEIVE_QTY
     */
    public java.math.BigDecimal getReceiveQty() {
        return receiveQty;
    }

    /**
     * <p>RECEIVE_QTY</p>
     *
     * @param receiveQty RECEIVE_QTY
     */
    public void setReceiveQty(java.math.BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    /**
     * <p>RETURN_QTY</p>
     *
     * @return the RETURN_QTY
     */
    public java.math.BigDecimal getReturnQty() {
        return returnQty;
    }

    /**
     * <p>RETURN_QTY</p>
     *
     * @param returnQty RETURN_QTY
     */
    public void setReturnQty(java.math.BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    /**
     * <p>ORDER_TIME</p>
     *
     * @return the ORDER_TIME
     */
    public java.util.Date getOrderTime() {
        return orderTime;
    }

    /**
     * <p>ORDER_TIME</p>
     *
     * @param orderTime ORDER_TIME
     */
    public void setOrderTime(java.util.Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * <p>RECEIVED_TIME</p>
     *
     * @return the RECEIVED_TIME
     */
    public java.util.Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * <p>RECEIVED_TIME</p>
     *
     * @param receivedTime RECEIVED_TIME
     */
    public void setReceivedTime(java.util.Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * <p>ORDER_STATUS</p>
     *
     * @return the ORDER_STATUS
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * <p>ORDER_STATUS</p>
     *
     * @param orderStatus ORDER_STATUS
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * <p>DETAIL_STATUS</p>
     *
     * @return the DETAIL_STATUS
     */
    public Integer getDetailStatus() {
        return detailStatus;
    }

    /**
     * <p>DETAIL_STATUS</p>
     *
     * @param detailStatus DETAIL_STATUS
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

}
