/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_pre_pd对应的SscDeliveryPrePd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryPrePd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品明细ID */
    private java.lang.Long detailId;
    /** 发货预入库单ID */
    private java.lang.Long deliveryPreIntoId;
    /** 发货预入库单编号 */
    private java.lang.Long deliveryPreIntoCode;
    /** 产品编码 */
    private java.lang.String pdCode;
    /** 产品描述 */
    private java.lang.String pdDesc;
    /** 产品类别编码 */
    private java.lang.String classesCode;
    /** 产品类别名称 */
    private java.lang.String classesName;
    /** 产品加工类型编码 */
    private java.lang.String machiningCode;
    /** 产品加工类型名称 */
    private java.lang.String machiningName;
    /** 产品品种编码 */
    private java.lang.String breedCode;
    /** 产品品种名称 */
    private java.lang.String breedName;
    /** 产品特征编码 */
    private java.lang.String featureCode;
    /** 产品特征名称 */
    private java.lang.String featureName;
    /** 产品净重编码 */
    private java.lang.String weightCode;
    /** 产品净重名称 */
    private java.lang.String weightName;
    /** 净重数值 */
    private java.math.BigDecimal weightVal;
    /** 产品包装规格编码 */
    private java.lang.String normsCode;
    /** 产品包装规格名称 */
    private java.lang.String normsName;
    /** 产品等级编码 */
    private java.lang.String gradeCode;
    /** 产品等级名称 */
    private java.lang.String gradeName;
    /** 计划发货重量 */
    private java.math.BigDecimal productPlanWeight;
    /** 实际发货重量 */
    private java.math.BigDecimal productRecvWeight;
    /** 计划发货箱数 */
    private java.lang.Integer productPlanBox;
    /** 实际收货箱数 */
    private java.lang.Integer productRecvBox;
    /** 本次结算标准价 */
    private java.math.BigDecimal settkementStandardPrice;
    /** 备注 */
    private java.lang.String remark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryPrePd() {

    }

    /**
     * <p>产品明细ID。</p>
     *
     * @return the 产品明细ID
     */
    public java.lang.Long getDetailId() {
        return detailId;
    }

    /**
     * <p>产品明细ID。</p>
     *
     * @param detailId 产品明细ID。
     */
    public void setDetailId(java.lang.Long detailId) {
        this.detailId = detailId;
    }

    /**
     * <p>发货预入库单ID。</p>
     *
     * @return the 发货预入库单ID
     */
    public java.lang.Long getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    /**
     * <p>发货预入库单ID。</p>
     *
     * @param deliveryPreIntoId 发货预入库单ID。
     */
    public void setDeliveryPreIntoId(java.lang.Long deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    /**
     * <p>发货预入库单编号。</p>
     *
     * @return the 发货预入库单编号
     */
    public java.lang.Long getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    /**
     * <p>发货预入库单编号。</p>
     *
     * @param deliveryPreIntoCode 发货预入库单编号。
     */
    public void setDeliveryPreIntoCode(java.lang.Long deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @return the 产品编码
     */
    public java.lang.String getPdCode() {
        return pdCode;
    }

    /**
     * <p>产品编码。</p>
     *
     * @param pdCode 产品编码。
     */
    public void setPdCode(java.lang.String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>产品描述。</p>
     *
     * @return the 产品描述
     */
    public java.lang.String getPdDesc() {
        return pdDesc;
    }

    /**
     * <p>产品描述。</p>
     *
     * @param pdDesc 产品描述。
     */
    public void setPdDesc(java.lang.String pdDesc) {
        this.pdDesc = pdDesc;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @return the 产品类别编码
     */
    public java.lang.String getClassesCode() {
        return classesCode;
    }

    /**
     * <p>产品类别编码。</p>
     *
     * @param classesCode 产品类别编码。
     */
    public void setClassesCode(java.lang.String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @return the 产品类别名称
     */
    public java.lang.String getClassesName() {
        return classesName;
    }

    /**
     * <p>产品类别名称。</p>
     *
     * @param classesName 产品类别名称。
     */
    public void setClassesName(java.lang.String classesName) {
        this.classesName = classesName;
    }

    /**
     * <p>产品加工类型编码。</p>
     *
     * @return the 产品加工类型编码
     */
    public java.lang.String getMachiningCode() {
        return machiningCode;
    }

    /**
     * <p>产品加工类型编码。</p>
     *
     * @param machiningCode 产品加工类型编码。
     */
    public void setMachiningCode(java.lang.String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * <p>产品加工类型名称。</p>
     *
     * @return the 产品加工类型名称
     */
    public java.lang.String getMachiningName() {
        return machiningName;
    }

    /**
     * <p>产品加工类型名称。</p>
     *
     * @param machiningName 产品加工类型名称。
     */
    public void setMachiningName(java.lang.String machiningName) {
        this.machiningName = machiningName;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @return the 产品品种编码
     */
    public java.lang.String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种编码。</p>
     *
     * @param breedCode 产品品种编码。
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
     * <p>产品特征编码。</p>
     *
     * @return the 产品特征编码
     */
    public java.lang.String getFeatureCode() {
        return featureCode;
    }

    /**
     * <p>产品特征编码。</p>
     *
     * @param featureCode 产品特征编码。
     */
    public void setFeatureCode(java.lang.String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @return the 产品特征名称
     */
    public java.lang.String getFeatureName() {
        return featureName;
    }

    /**
     * <p>产品特征名称。</p>
     *
     * @param featureName 产品特征名称。
     */
    public void setFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
    }

    /**
     * <p>产品净重编码。</p>
     *
     * @return the 产品净重编码
     */
    public java.lang.String getWeightCode() {
        return weightCode;
    }

    /**
     * <p>产品净重编码。</p>
     *
     * @param weightCode 产品净重编码。
     */
    public void setWeightCode(java.lang.String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * <p>产品净重名称。</p>
     *
     * @return the 产品净重名称
     */
    public java.lang.String getWeightName() {
        return weightName;
    }

    /**
     * <p>产品净重名称。</p>
     *
     * @param weightName 产品净重名称。
     */
    public void setWeightName(java.lang.String weightName) {
        this.weightName = weightName;
    }

    /**
     * <p>净重数值。</p>
     *
     * @return the 净重数值
     */
    public java.math.BigDecimal getWeightVal() {
        return weightVal;
    }

    /**
     * <p>净重数值。</p>
     *
     * @param weightVal 净重数值。
     */
    public void setWeightVal(java.math.BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    /**
     * <p>产品包装规格编码。</p>
     *
     * @return the 产品包装规格编码
     */
    public java.lang.String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>产品包装规格编码。</p>
     *
     * @param normsCode 产品包装规格编码。
     */
    public void setNormsCode(java.lang.String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>产品包装规格名称。</p>
     *
     * @return the 产品包装规格名称
     */
    public java.lang.String getNormsName() {
        return normsName;
    }

    /**
     * <p>产品包装规格名称。</p>
     *
     * @param normsName 产品包装规格名称。
     */
    public void setNormsName(java.lang.String normsName) {
        this.normsName = normsName;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @return the 产品等级编码
     */
    public java.lang.String getGradeCode() {
        return gradeCode;
    }

    /**
     * <p>产品等级编码。</p>
     *
     * @param gradeCode 产品等级编码。
     */
    public void setGradeCode(java.lang.String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @return the 产品等级名称
     */
    public java.lang.String getGradeName() {
        return gradeName;
    }

    /**
     * <p>产品等级名称。</p>
     *
     * @param gradeName 产品等级名称。
     */
    public void setGradeName(java.lang.String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * <p>计划发货重量。</p>
     *
     * @return the 计划发货重量
     */
    public java.math.BigDecimal getProductPlanWeight() {
        return productPlanWeight;
    }

    /**
     * <p>计划发货重量。</p>
     *
     * @param productPlanWeight 计划发货重量。
     */
    public void setProductPlanWeight(java.math.BigDecimal productPlanWeight) {
        this.productPlanWeight = productPlanWeight;
    }

    /**
     * <p>实际发货重量。</p>
     *
     * @return the 实际发货重量
     */
    public java.math.BigDecimal getProductRecvWeight() {
        return productRecvWeight;
    }

    /**
     * <p>实际发货重量。</p>
     *
     * @param productRecvWeight 实际发货重量。
     */
    public void setProductRecvWeight(java.math.BigDecimal productRecvWeight) {
        this.productRecvWeight = productRecvWeight;
    }

    /**
     * <p>计划发货箱数。</p>
     *
     * @return the 计划发货箱数
     */
    public java.lang.Integer getProductPlanBox() {
        return productPlanBox;
    }

    /**
     * <p>计划发货箱数。</p>
     *
     * @param productPlanBox 计划发货箱数。
     */
    public void setProductPlanBox(java.lang.Integer productPlanBox) {
        this.productPlanBox = productPlanBox;
    }

    /**
     * <p>实际收货箱数。</p>
     *
     * @return the 实际收货箱数
     */
    public java.lang.Integer getProductRecvBox() {
        return productRecvBox;
    }

    /**
     * <p>实际收货箱数。</p>
     *
     * @param productRecvBox 实际收货箱数。
     */
    public void setProductRecvBox(java.lang.Integer productRecvBox) {
        this.productRecvBox = productRecvBox;
    }

    /**
     * <p>本次结算标准价。</p>
     *
     * @return the 本次结算标准价
     */
    public java.math.BigDecimal getSettkementStandardPrice() {
        return settkementStandardPrice;
    }

    /**
     * <p>本次结算标准价。</p>
     *
     * @param settkementStandardPrice 本次结算标准价。
     */
    public void setSettkementStandardPrice(java.math.BigDecimal settkementStandardPrice) {
        this.settkementStandardPrice = settkementStandardPrice;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

}
