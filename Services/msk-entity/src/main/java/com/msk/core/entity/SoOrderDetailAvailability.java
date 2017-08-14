/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_order_detail_availability对应的SoOrderDetailAvailability。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoOrderDetailAvailability extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 订单ID */
    private java.lang.Long orderId;
    /** 订单编码 */
    private java.lang.String orderCode;
    /** 订单明细ID */
    private java.lang.Long orderDetailId;
    /** 订单明细供货ID */
    private java.lang.Long orderDetailAvailabilityId;
    /** 供应商编码 */
    private java.lang.String supplierCode;
    /** 供应商名称 */
    private java.lang.String supplierName;
    /** 产品类型 */
    private java.lang.String classesCode;
    /** 产品类型名称 */
    private java.lang.String classesName;
    /** 产品品种 */
    private java.lang.String breedCode;
    /** 产品品种名称 */
    private java.lang.String breedName;
    /** 特征编码 */
    private java.lang.String featureCode;
    /** 特征名称 */
    private java.lang.String featureName;
    /** 产品包装编码 */
    private java.lang.String normsCode;
    /** 产品包装名称 */
    private java.lang.String normsName;
    /** 产品编号 */
    private java.lang.String pdCode;
    /** 产品名称 */
    private java.lang.String pdName;
    /** 产品等级 */
    private java.lang.String pdLevel;
    /** 产品等级编码 */
    private java.lang.String pdGradeCode;
    /** 产品等级名称 */
    private java.lang.String pdGradeName;
    /** 产品单位 */
    private java.lang.String unit;
    /** 单箱体积 */
    private java.math.BigDecimal packingVolume;
    /** 重量 */
    private java.math.BigDecimal weight;
    /** 体积 */
    private java.math.BigDecimal volume;
    /** 供应数量 */
    private java.math.BigDecimal suppQty;
    /** 发货数量 */
    private java.math.BigDecimal sendQty;
    /** 收货数量 */
    private java.math.BigDecimal receiveQty;
    /** 取消数量 */
    private java.math.BigDecimal cancelQty;
    /** 退货数量 */
    private java.math.BigDecimal returnQty;
    /** 拒收数量 */
    private java.math.BigDecimal rejectionQty;
    /** 发货时间 */
    private java.util.Date sendTime;
    /** 收货时间 */
    private java.util.Date receivedTime;
    /** 状态 */
    private java.lang.Integer status;
    /**
     * <p>默认构造函数。</p>
     */
    public SoOrderDetailAvailability() {

    }

    /**
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public java.lang.Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(java.lang.Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public java.lang.String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(java.lang.String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @return the 订单明细ID
     */
    public java.lang.Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * <p>订单明细ID。</p>
     *
     * @param orderDetailId 订单明细ID。
     */
    public void setOrderDetailId(java.lang.Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * <p>订单明细供货ID。</p>
     *
     * @return the 订单明细供货ID
     */
    public java.lang.Long getOrderDetailAvailabilityId() {
        return orderDetailAvailabilityId;
    }

    /**
     * <p>订单明细供货ID。</p>
     *
     * @param orderDetailAvailabilityId 订单明细供货ID。
     */
    public void setOrderDetailAvailabilityId(java.lang.Long orderDetailAvailabilityId) {
        this.orderDetailAvailabilityId = orderDetailAvailabilityId;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @return the 供应商名称
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @param supplierName 供应商名称。
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>产品类型。</p>
     *
     * @return the 产品类型
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类型。</p>
     *
     * @param classesCode 产品类型。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品类型名称。</p>
     *
     * @return the 产品类型名称
     */
    public java.lang.String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品类型名称。</p>
     *
     * @param classesName 产品类型名称。
     */
    public void setClassesName(java.lang.String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品品种。</p>
     *
     * @return the 产品品种
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @param breedCode 产品品种。
     */
    public void setBreedCode(java.lang.String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @return the 产品品种名称
     */
    public java.lang.String getBreedName() {
        return breedName;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @param breedName 产品品种名称。
     */
    public void setBreedName(java.lang.String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>特征编码。</p>
     *
     * @return the 特征编码
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>特征编码。</p>
     *
     * @param featureCode 特征编码。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>特征名称。</p>
     *
     * @return the 特征名称
     */
    public java.lang.String getFeatureName() {
        return featureName;
    }

    /**
     * <p>特征名称。</p>
     *
     * @param featureName 特征名称。
     */
    public void setFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @return the 产品包装编码
     */
    public java.lang.String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @param normsCode 产品包装编码。
     */
    public void setNormsCode(java.lang.String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>产品包装名称。</p>
     *
     * @return the 产品包装名称
     */
    public java.lang.String getNormsName() {
        return normsName;
    }

    /**
     * <p>产品包装名称。</p>
     *
     * @param normsName 产品包装名称。
     */
    public void setNormsName(java.lang.String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>产品编号。</p>
     *
     * @return the 产品编号
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编号。</p>
     *
     * @param pdCode 产品编号。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public java.lang.String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(java.lang.String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>产品等级。</p>
     *
     * @return the 产品等级
     */
    public java.lang.String getPdLevel() {
        return pdLevel;
    }

    /**
     * <p>产品等级。</p>
     *
     * @param pdLevel 产品等级。
     */
    public void setPdLevel(java.lang.String pdLevel) {
        this.pdLevel = pdLevel;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public java.lang.String getPdGradeCode() {
        return pdGradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param pdGradeCode 产品等级编码。
     */
    public void setPdGradeCode(java.lang.String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @return the 产品等级名称
     */
    public java.lang.String getPdGradeName() {
        return pdGradeName;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @param pdGradeName 产品等级名称。
     */
    public void setPdGradeName(java.lang.String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    /**
     * <p>产品单位。</p>
     *
     * @return the 产品单位
     */
    public java.lang.String getUnit() {
        return unit;
    }

    /**
     * <p>产品单位。</p>
     *
     * @param unit 产品单位。
     */
    public void setUnit(java.lang.String unit) {
        this.unit = unit;
    }

    /**
     * <p>单箱体积。</p>
     *
     * @return the 单箱体积
     */
    public java.math.BigDecimal getPackingVolume() {
        return packingVolume;
    }

    /**
     * <p>单箱体积。</p>
     *
     * @param packingVolume 单箱体积。
     */
    public void setPackingVolume(java.math.BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    /**
     * <p>重量。</p>
     *
     * @return the 重量
     */
    public java.math.BigDecimal getWeight() {
        return weight;
    }

    /**
     * <p>重量。</p>
     *
     * @param weight 重量。
     */
    public void setWeight(java.math.BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * <p>体积。</p>
     *
     * @return the 体积
     */
    public java.math.BigDecimal getVolume() {
        return volume;
    }

    /**
     * <p>体积。</p>
     *
     * @param volume 体积。
     */
    public void setVolume(java.math.BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * <p>供应数量。</p>
     *
     * @return the 供应数量
     */
    public java.math.BigDecimal getSuppQty() {
        return suppQty;
    }

    /**
     * <p>供应数量。</p>
     *
     * @param suppQty 供应数量。
     */
    public void setSuppQty(java.math.BigDecimal suppQty) {
        this.suppQty = suppQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @return the 发货数量
     */
    public java.math.BigDecimal getSendQty() {
        return sendQty;
    }

    /**
     * <p>发货数量。</p>
     *
     * @param sendQty 发货数量。
     */
    public void setSendQty(java.math.BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @return the 收货数量
     */
    public java.math.BigDecimal getReceiveQty() {
        return receiveQty;
    }

    /**
     * <p>收货数量。</p>
     *
     * @param receiveQty 收货数量。
     */
    public void setReceiveQty(java.math.BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @return the 取消数量
     */
    public java.math.BigDecimal getCancelQty() {
        return cancelQty;
    }

    /**
     * <p>取消数量。</p>
     *
     * @param cancelQty 取消数量。
     */
    public void setCancelQty(java.math.BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @return the 退货数量
     */
    public java.math.BigDecimal getReturnQty() {
        return returnQty;
    }

    /**
     * <p>退货数量。</p>
     *
     * @param returnQty 退货数量。
     */
    public void setReturnQty(java.math.BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @return the 拒收数量
     */
    public java.math.BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    /**
     * <p>拒收数量。</p>
     *
     * @param rejectionQty 拒收数量。
     */
    public void setRejectionQty(java.math.BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    /**
     * <p>发货时间。</p>
     *
     * @return the 发货时间
     */
    public java.util.Date getSendTime() {
        return sendTime;
    }

    /**
     * <p>发货时间。</p>
     *
     * @param sendTime 发货时间。
     */
    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @return the 收货时间
     */
    public java.util.Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * <p>收货时间。</p>
     *
     * @param receivedTime 收货时间。
     */
    public void setReceivedTime(java.util.Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * <p>状态。</p>
     *
     * @return the 状态
     */
    public java.lang.Integer getStatus() {
        return status;
    }

    /**
     * <p>状态。</p>
     *
     * @param status 状态。
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

}
