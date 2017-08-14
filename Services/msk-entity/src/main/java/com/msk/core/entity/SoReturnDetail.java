/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_return_detail对应的SoReturnDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoReturnDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 退货明细ID */
    private Long detailId;
    /** 退货单ID */
    private Long returnId;
    /** 订单ID */
    private Long orderId;
    /** 订单编码 */
    private String orderCode;
    /** 分批订单ID */
    private String subOrderId;
    /** 分批订单编码 */
    private String subOrderCode;
    /** 配送单ID */
    private Long deliverId;
    /** 配送单编码 */
    private String deliverCode;
    /** 供应商编码 */
    private String supplierCode;
    /** 供应商名称 */
    private String supplierName;
    /** 生产商编码 */
    private String manufactureCode;
    /** 生产商名称 */
    private String manufactureName;
    /** 产品类别编码，一级分类 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 产品品种编码 */
    private String breedCode;
    /** 产品品种名称 */
    private String breedName;
    /** 产品特征编码 */
    private String featureCode;
    /** 产品特征名称 */
    private String featureName;
    /** 产品包装编码 */
    private String normsCode;
    /** 产品包装名称 */
    private String normsName;
    /** 产品编号 */
    private String pdCode;
    /** 产品名称 */
    private String pdName;
    /** 产品等级 */
    private String pdLevel;
    /** 产品等级编码 */
    private String pdGradeCode;
    /** 产品等级名称 */
    private String pdGradeName;
    /** SKU编码 */
    private String skuCode;
    /** 产品单位 */
    private String unit;
    /** 单箱体积 */
    private java.math.BigDecimal packingVolume;
    /** 重量 */
    private java.math.BigDecimal weight;
    /** 体积 */
    private java.math.BigDecimal volume;
    /** 退货数量 */
    private java.math.BigDecimal returnQty;
    /** 退货原因 */
    private Integer returnReason;
    /** 明细状态,CodeMaster */
    private Integer detailStatus;
    /**
     * <p>默认构造函数。</p>
     */
    public SoReturnDetail() {

    }

    /**
     * <p>退货明细ID。</p>
     *
     * @return the 退货明细ID
     */
    public Long getDetailId() {
        return detailId;
    }

    /**
     * <p>退货明细ID。</p>
     *
     * @param detailId 退货明细ID。
     */
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
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
     * <p>订单ID。</p>
     *
     * @return the 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * <p>订单ID。</p>
     *
     * @param orderId 订单ID。
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * <p>订单编码。</p>
     *
     * @return the 订单编码
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * <p>订单编码。</p>
     *
     * @param orderCode 订单编码。
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @return the 分批订单ID
     */
    public String getSubOrderId() {
        return subOrderId;
    }

    /**
     * <p>分批订单ID。</p>
     *
     * @param subOrderId 分批订单ID。
     */
    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @return the 分批订单编码
     */
    public String getSubOrderCode() {
        return subOrderCode;
    }

    /**
     * <p>分批订单编码。</p>
     *
     * @param subOrderCode 分批订单编码。
     */
    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }

    /**
     * <p>配送单ID。</p>
     *
     * @return the 配送单ID
     */
    public Long getDeliverId() {
        return deliverId;
    }

    /**
     * <p>配送单ID。</p>
     *
     * @param deliverId 配送单ID。
     */
    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    /**
     * <p>配送单编码。</p>
     *
     * @return the 配送单编码
     */
    public String getDeliverCode() {
        return deliverCode;
    }

    /**
     * <p>配送单编码。</p>
     *
     * @param deliverCode 配送单编码。
     */
    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @return the 供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>供应商名称。</p>
     *
     * @param supplierName 供应商名称。
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @return the 生产商编码
     */
    public String getManufactureCode() {
        return manufactureCode;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @param manufactureCode 生产商编码。
     */
    public void setManufactureCode(String manufactureCode) {
        this.manufactureCode = manufactureCode;
    }

    /**
     * <p>生产商名称。</p>
     *
     * @return the 生产商名称
     */
    public String getManufactureName() {
        return manufactureName;
    }

    /**
     * <p>生产商名称。</p>
     *
     * @param manufactureName 生产商名称。
     */
    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    /**
     * <p>产品类别编码，一级分类。</p>
     *
     * @return the 产品类别编码，一级分类
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别编码，一级分类。</p>
     *
     * @param classesCode 产品类别编码，一级分类。
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @return the 产品类别名称
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @param classesName 产品类别名称。
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @return the 产品品种编码
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @param breedCode 产品品种编码。
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @return the 产品品种名称
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * <p>产品品种名称。</p>
     *
     * @param breedName 产品品种名称。
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @return the 产品包装编码
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>产品包装编码。</p>
     *
     * @param normsCode 产品包装编码。
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>产品包装名称。</p>
     *
     * @return the 产品包装名称
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * <p>产品包装名称。</p>
     *
     * @param normsName 产品包装名称。
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>产品编号。</p>
     *
     * @return the 产品编号
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编号。</p>
     *
     * @param pdCode 产品编号。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品名称。</p>
     *
     * @return the 产品名称
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * <p>产品名称。</p>
     *
     * @param pdName 产品名称。
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>产品等级。</p>
     *
     * @return the 产品等级
     */
    public String getPdLevel() {
        return pdLevel;
    }

    /**
     * <p>产品等级。</p>
     *
     * @param pdLevel 产品等级。
     */
    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public String getPdGradeCode() {
        return pdGradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param pdGradeCode 产品等级编码。
     */
    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @return the 产品等级名称
     */
    public String getPdGradeName() {
        return pdGradeName;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @param pdGradeName 产品等级名称。
     */
    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    /**
     * <p>SKU编码。</p>
     *
     * @return the SKU编码
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * <p>SKU编码。</p>
     *
     * @param skuCode SKU编码。
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * <p>产品单位。</p>
     *
     * @return the 产品单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * <p>产品单位。</p>
     *
     * @param unit 产品单位。
     */
    public void setUnit(String unit) {
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
     * <p>退货原因。</p>
     *
     * @return the 退货原因
     */
    public Integer getReturnReason() {
        return returnReason;
    }

    /**
     * <p>退货原因。</p>
     *
     * @param returnReason 退货原因。
     */
    public void setReturnReason(Integer returnReason) {
        this.returnReason = returnReason;
    }

    /**
     * <p>明细状态,CodeMaster。</p>
     *
     * @return the 明细状态,CodeMaster
     */
    public Integer getDetailStatus() {
        return detailStatus;
    }

    /**
     * <p>明细状态,CodeMaster。</p>
     *
     * @param detailStatus 明细状态,CodeMaster。
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

}
