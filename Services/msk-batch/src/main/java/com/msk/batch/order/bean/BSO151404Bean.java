package com.msk.batch.order.bean;


import com.msk.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wang_shuai on 2016/8/30.
 */
public class BSO151404Bean extends BaseEntity {
    /** ORDER_ID */
    private Long orderId;
    /** ORDER_CODE */
    private String orderCode;
    /** ORDER_DETAIL_ID */
    private Long orderDetailId;
    /** ORDER_DETAIL_AVAILABILITY_ID */
    private Long orderDetailAvailabilityId;
    /** SELLER_CODE */
    private String sellerCode;
    /** SELLER_NAME */
    private String sellerName;
    /** SOURCE_SELLER_CODE */
    private String sourceSellerCode;
    /** SOURCE_SELLER_NAME */
    private String sourceSellerName;
    /** ORDER_SOURCE 订单来源*/
    private Integer orderSource;
    /** DISTRICT_CODE 物流区域*/
    private String districtCode;
    /** SUPPLIER_CODE */
    private String supplierCode;
    /** SUPPLIER_NAME */
    private String supplierName;
    /** CLASSES_CODE */
    private String classesCode;
    /** CLASSES_NAME */
    private String classesName;
    /** BREED_CODE */
    private String breedCode;
    /** BREED_NAME */
    private String breedName;
    /** FEATURE_CODE */
    private String featureCode;
    /** FEATURE_NAME */
    private String featureName;
    /** NORMS_CODE */
    private String normsCode;
    /** NORMS_NAME */
    private String normsName;
    /** PD_CODE */
    private String pdCode;
    /** PD_NAME */
    private String pdName;
    /** PD_GRADE_CODE */
    private String pdGradeCode;
    /** PD_GRADE_NAME */
    private String pdGradeName;
    /** PD_LEVEL */
    private String pdLevel;
    /** UNIT */
    private String unit;
    /** PACKING_VOLUME */
    private String packingVolume;
    /** WEIGHT */
    private String weight;
    /** VOLUME */
    private String volume;
    /** SUPP_QTY */
    private java.math.BigDecimal suppQty;
    /** SEND_QTY */
    private java.math.BigDecimal sendQty;
    /** RECEIVE_QTY */
    private java.math.BigDecimal receiveQty;
    /** CANCEL_QTY */
    private java.math.BigDecimal cancelQty;
    /** RETURN_QTY */
    private java.math.BigDecimal returnQty;
    /** SEND_TIME */
    private java.util.Date sendTime;
    /** RECEIVED_TIME */
    private java.util.Date receivedTime;

    /**STATUS 订单状态*/
    private String status;
    //单价
    private BigDecimal pdPrice;
    //销售平台
    private String salePlatform;
    //分批订单id
    private Long subOrderId;


    /**
     * <p>默认构造函数。</p>
     */
    public BSO151404Bean() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderDetailAvailabilityId() {
        return orderDetailAvailabilityId;
    }

    public void setOrderDetailAvailabilityId(Long orderDetailAvailabilityId) {
        this.orderDetailAvailabilityId = orderDetailAvailabilityId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSourceSellerCode() {
        return sourceSellerCode;
    }

    public void setSourceSellerCode(String sourceSellerCode) {
        this.sourceSellerCode = sourceSellerCode;
    }

    public String getSourceSellerName() {
        return sourceSellerName;
    }

    public void setSourceSellerName(String sourceSellerName) {
        this.sourceSellerName = sourceSellerName;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getNormsCode() {
        return normsCode;
    }

    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdGradeCode() {
        return pdGradeCode;
    }

    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public String getPdLevel() {
        return pdLevel;
    }

    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPackingVolume() {
        return packingVolume;
    }

    public void setPackingVolume(String packingVolume) {
        this.packingVolume = packingVolume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public BigDecimal getSuppQty() {
        return suppQty;
    }

    public void setSuppQty(BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public BigDecimal getCancelQty() {
        return cancelQty;
    }

    public void setCancelQty(BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }
}
