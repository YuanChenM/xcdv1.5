/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_delivery_order_pd对应的SscDeliveryOrderPd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscDeliveryOrderPd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品ID */
    private java.lang.Long detailId;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编号 */
    private java.lang.String deliveryCode;
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
    /** 产品质量标准 */
    private java.lang.String productQualityStandard;
    /** 产品重量 */
    private java.math.BigDecimal productQua;
    /** 产品箱数 */
    private java.lang.Integer productBox;
    /** 不含包装离岸价 */
    private java.math.BigDecimal fobFreePackage;
    /** 包材成本 */
    private java.math.BigDecimal packageCost;
    /** 含包装离岸价 */
    private java.math.BigDecimal fobIncludePackage;
    /** 干线运费 */
    private java.math.BigDecimal trunkFreight;
    /** 到岸价 */
    private java.math.BigDecimal cif;
    /** 本次结算标准价 */
    private java.math.BigDecimal settkementStandardPrice;
    /** 不含运费结算标准价 */
    private java.math.BigDecimal standardPrice;
    /** 货值 */
    private java.math.BigDecimal productValue;
    /** 备注 */
    private java.lang.String remark;
    /** 品牌所属企业ID */
    private java.lang.Long brandEpId;
    /** 品牌ID */
    private java.lang.Long brandId;
    /** 品牌名称 */
    private java.lang.String brandName;
    /**
     * <p>默认构造函数。</p>
     */
    public SscDeliveryOrderPd() {

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
     * <p>发货订单ID。</p>
     *
     * @return the 发货订单ID
     */
    public java.lang.Long getDeliveryId() {
        return deliveryId;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @param deliveryId 发货订单ID。
     */
    public void setDeliveryId(java.lang.Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @return the 发货订单编号
     */
    public java.lang.String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @param deliveryCode 发货订单编号。
     */
    public void setDeliveryCode(java.lang.String deliveryCode) {
        this.deliveryCode = deliveryCode;
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
     * <p>产品质量标准。</p>
     *
     * @return the 产品质量标准
     */
    public java.lang.String getProductQualityStandard() {
        return productQualityStandard;
    }

    /**
     * <p>产品质量标准。</p>
     *
     * @param productQualityStandard 产品质量标准。
     */
    public void setProductQualityStandard(java.lang.String productQualityStandard) {
        this.productQualityStandard = productQualityStandard;
    }

    /**
     * <p>产品重量。</p>
     *
     * @return the 产品重量
     */
    public java.math.BigDecimal getProductQua() {
        return productQua;
    }

    /**
     * <p>产品重量。</p>
     *
     * @param productQua 产品重量。
     */
    public void setProductQua(java.math.BigDecimal productQua) {
        this.productQua = productQua;
    }

    /**
     * <p>产品箱数。</p>
     *
     * @return the 产品箱数
     */
    public java.lang.Integer getProductBox() {
        return productBox;
    }

    /**
     * <p>产品箱数。</p>
     *
     * @param productBox 产品箱数。
     */
    public void setProductBox(java.lang.Integer productBox) {
        this.productBox = productBox;
    }

    /**
     * <p>不含包装离岸价。</p>
     *
     * @return the 不含包装离岸价
     */
    public java.math.BigDecimal getFobFreePackage() {
        return fobFreePackage;
    }

    /**
     * <p>不含包装离岸价。</p>
     *
     * @param fobFreePackage 不含包装离岸价。
     */
    public void setFobFreePackage(java.math.BigDecimal fobFreePackage) {
        this.fobFreePackage = fobFreePackage;
    }

    /**
     * <p>包材成本。</p>
     *
     * @return the 包材成本
     */
    public java.math.BigDecimal getPackageCost() {
        return packageCost;
    }

    /**
     * <p>包材成本。</p>
     *
     * @param packageCost 包材成本。
     */
    public void setPackageCost(java.math.BigDecimal packageCost) {
        this.packageCost = packageCost;
    }

    /**
     * <p>含包装离岸价。</p>
     *
     * @return the 含包装离岸价
     */
    public java.math.BigDecimal getFobIncludePackage() {
        return fobIncludePackage;
    }

    /**
     * <p>含包装离岸价。</p>
     *
     * @param fobIncludePackage 含包装离岸价。
     */
    public void setFobIncludePackage(java.math.BigDecimal fobIncludePackage) {
        this.fobIncludePackage = fobIncludePackage;
    }

    /**
     * <p>干线运费。</p>
     *
     * @return the 干线运费
     */
    public java.math.BigDecimal getTrunkFreight() {
        return trunkFreight;
    }

    /**
     * <p>干线运费。</p>
     *
     * @param trunkFreight 干线运费。
     */
    public void setTrunkFreight(java.math.BigDecimal trunkFreight) {
        this.trunkFreight = trunkFreight;
    }

    /**
     * <p>到岸价。</p>
     *
     * @return the 到岸价
     */
    public java.math.BigDecimal getCif() {
        return cif;
    }

    /**
     * <p>到岸价。</p>
     *
     * @param cif 到岸价。
     */
    public void setCif(java.math.BigDecimal cif) {
        this.cif = cif;
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
     * <p>不含运费结算标准价。</p>
     *
     * @return the 不含运费结算标准价
     */
    public java.math.BigDecimal getStandardPrice() {
        return standardPrice;
    }

    /**
     * <p>不含运费结算标准价。</p>
     *
     * @param standardPrice 不含运费结算标准价。
     */
    public void setStandardPrice(java.math.BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    /**
     * <p>货值。</p>
     *
     * @return the 货值
     */
    public java.math.BigDecimal getProductValue() {
        return productValue;
    }

    /**
     * <p>货值。</p>
     *
     * @param productValue 货值。
     */
    public void setProductValue(java.math.BigDecimal productValue) {
        this.productValue = productValue;
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

    /**
     * <p>品牌所属企业ID。</p>
     *
     * @return the 品牌所属企业ID
     */
    public java.lang.Long getBrandEpId() {
        return brandEpId;
    }

    /**
     * <p>品牌所属企业ID。</p>
     *
     * @param brandEpId 品牌所属企业ID。
     */
    public void setBrandEpId(java.lang.Long brandEpId) {
        this.brandEpId = brandEpId;
    }

    /**
     * <p>品牌ID。</p>
     *
     * @return the 品牌ID
     */
    public java.lang.Long getBrandId() {
        return brandId;
    }

    /**
     * <p>品牌ID。</p>
     *
     * @param brandId 品牌ID。
     */
    public void setBrandId(java.lang.Long brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>品牌名称。</p>
     *
     * @return the 品牌名称
     */
    public java.lang.String getBrandName() {
        return brandName;
    }

    /**
     * <p>品牌名称。</p>
     *
     * @param brandName 品牌名称。
     */
    public void setBrandName(java.lang.String brandName) {
        this.brandName = brandName;
    }

}
