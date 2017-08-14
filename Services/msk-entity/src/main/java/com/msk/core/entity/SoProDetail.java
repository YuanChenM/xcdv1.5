/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表so_pro_detail对应的SoProDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SoProDetail extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** PRO_ID */
    private Long proId;
    /** PRO_CODE */
    private String proCode;
    /** PRO_DETAIL_ID */
    private Long proDetailId;
    /** 产品类别编码，一级分类 */
    private String classesCode;
    /** 产品类别名称 */
    private String classesName;
    /** 产品品种 */
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
    /** PD_CODE */
    private String pdCode;
    /** PD_NAME */
    private String pdName;
    /** PD_LEVEL */
    private String pdLevel;
    /** PD_GRADE_CODE */
    private String pdGradeCode;
    /** PD_GRADE_NAME */
    private String pdGradeName;
    /** PD_UNIT */
    private String pdUnit;
    /** PACKING_VOLUME */
    private java.math.BigDecimal packingVolume;
    /** PD_WEIGHT */
    private java.math.BigDecimal pdWeight;
    /** PD_VOLUME */
    private java.math.BigDecimal pdVolume;
    /** ORDER_PRICE */
    private java.math.BigDecimal orderPrice;
    /** PRICE_CYCLE */
    private String priceCycle;
    /** ORDER_QTY */
    private java.math.BigDecimal orderQty;
    /**
     * <p>默认构造函数。</p>
     */
    public SoProDetail() {

    }

    /**
     * <p>PRO_ID。</p>
     *
     * @return the PRO_ID
     */
    public Long getProId() {
        return proId;
    }

    /**
     * <p>PRO_ID。</p>
     *
     * @param proId PRO_ID。
     */
    public void setProId(Long proId) {
        this.proId = proId;
    }

    /**
     * <p>PRO_CODE。</p>
     *
     * @return the PRO_CODE
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p>PRO_CODE。</p>
     *
     * @param proCode PRO_CODE。
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p>PRO_DETAIL_ID。</p>
     *
     * @return the PRO_DETAIL_ID
     */
    public Long getProDetailId() {
        return proDetailId;
    }

    /**
     * <p>PRO_DETAIL_ID。</p>
     *
     * @param proDetailId PRO_DETAIL_ID。
     */
    public void setProDetailId(Long proDetailId) {
        this.proDetailId = proDetailId;
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
     * <p>产品品种。</p>
     *
     * @return the 产品品种
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * <p>产品品种。</p>
     *
     * @param breedCode 产品品种。
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
     * <p>PD_CODE。</p>
     *
     * @return the PD_CODE
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * <p>PD_CODE。</p>
     *
     * @param pdCode PD_CODE。
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * <p>PD_NAME。</p>
     *
     * @return the PD_NAME
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * <p>PD_NAME。</p>
     *
     * @param pdName PD_NAME。
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    /**
     * <p>PD_LEVEL。</p>
     *
     * @return the PD_LEVEL
     */
    public String getPdLevel() {
        return pdLevel;
    }

    /**
     * <p>PD_LEVEL。</p>
     *
     * @param pdLevel PD_LEVEL。
     */
    public void setPdLevel(String pdLevel) {
        this.pdLevel = pdLevel;
    }

    /**
     * <p>PD_GRADE_CODE。</p>
     *
     * @return the PD_GRADE_CODE
     */
    public String getPdGradeCode() {
        return pdGradeCode;
    }

    /**
     * <p>PD_GRADE_CODE。</p>
     *
     * @param pdGradeCode PD_GRADE_CODE。
     */
    public void setPdGradeCode(String pdGradeCode) {
        this.pdGradeCode = pdGradeCode;
    }

    /**
     * <p>PD_GRADE_NAME。</p>
     *
     * @return the PD_GRADE_NAME
     */
    public String getPdGradeName() {
        return pdGradeName;
    }

    /**
     * <p>PD_GRADE_NAME。</p>
     *
     * @param pdGradeName PD_GRADE_NAME。
     */
    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    /**
     * <p>PD_UNIT。</p>
     *
     * @return the PD_UNIT
     */
    public String getPdUnit() {
        return pdUnit;
    }

    /**
     * <p>PD_UNIT。</p>
     *
     * @param pdUnit PD_UNIT。
     */
    public void setPdUnit(String pdUnit) {
        this.pdUnit = pdUnit;
    }

    /**
     * <p>PACKING_VOLUME。</p>
     *
     * @return the PACKING_VOLUME
     */
    public java.math.BigDecimal getPackingVolume() {
        return packingVolume;
    }

    /**
     * <p>PACKING_VOLUME。</p>
     *
     * @param packingVolume PACKING_VOLUME。
     */
    public void setPackingVolume(java.math.BigDecimal packingVolume) {
        this.packingVolume = packingVolume;
    }

    /**
     * <p>PD_WEIGHT。</p>
     *
     * @return the PD_WEIGHT
     */
    public java.math.BigDecimal getPdWeight() {
        return pdWeight;
    }

    /**
     * <p>PD_WEIGHT。</p>
     *
     * @param pdWeight PD_WEIGHT。
     */
    public void setPdWeight(java.math.BigDecimal pdWeight) {
        this.pdWeight = pdWeight;
    }

    /**
     * <p>PD_VOLUME。</p>
     *
     * @return the PD_VOLUME
     */
    public java.math.BigDecimal getPdVolume() {
        return pdVolume;
    }

    /**
     * <p>PD_VOLUME。</p>
     *
     * @param pdVolume PD_VOLUME。
     */
    public void setPdVolume(java.math.BigDecimal pdVolume) {
        this.pdVolume = pdVolume;
    }

    /**
     * <p>ORDER_PRICE。</p>
     *
     * @return the ORDER_PRICE
     */
    public java.math.BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * <p>ORDER_PRICE。</p>
     *
     * @param orderPrice ORDER_PRICE。
     */
    public void setOrderPrice(java.math.BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * <p>PRICE_CYCLE。</p>
     *
     * @return the PRICE_CYCLE
     */
    public String getPriceCycle() {
        return priceCycle;
    }

    /**
     * <p>PRICE_CYCLE。</p>
     *
     * @param priceCycle PRICE_CYCLE。
     */
    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    /**
     * <p>ORDER_QTY。</p>
     *
     * @return the ORDER_QTY
     */
    public java.math.BigDecimal getOrderQty() {
        return orderQty;
    }

    /**
     * <p>ORDER_QTY。</p>
     *
     * @param orderQty ORDER_QTY。
     */
    public void setOrderQty(java.math.BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

}
