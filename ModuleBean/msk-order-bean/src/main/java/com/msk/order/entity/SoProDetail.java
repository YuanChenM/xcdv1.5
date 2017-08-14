/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.order.entity;

import com.msk.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>表so_pro_detail对应的SoProDetail。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
@Entity
public class SoProDetail extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 意愿ID */
    @Id
    private Long proId;
    /** 意愿编码 */
    private String proCode;
    /** 意愿明细ID */
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
    /** 产品单位 */
    private String pdUnit;
    /** 单箱体积 */
    private java.math.BigDecimal packingVolume;
    /** 重量 */
    private java.math.BigDecimal pdWeight;
    /** 体积 */
    private java.math.BigDecimal pdVolume;
    /** 订单价格 */
    private java.math.BigDecimal orderPrice;
    /** 价盘周期 */
    private String priceCycle;
    /** 订单数量 */
    private java.math.BigDecimal orderQty;
    /**
     * <p>默认构造函数。</p>
     */
    public SoProDetail() {

    }

    /**
     * <p>意愿ID。</p>
     *
     * @return the 意愿ID
     */
    public Long getProId() {
        return proId;
    }

    /**
     * <p>意愿ID。</p>
     *
     * @param proId 意愿ID。
     */
    public void setProId(Long proId) {
        this.proId = proId;
    }

    /**
     * <p>意愿编码。</p>
     *
     * @return the 意愿编码
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p>意愿编码。</p>
     *
     * @param proCode 意愿编码。
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p>意愿明细ID。</p>
     *
     * @return the 意愿明细ID
     */
    public Long getProDetailId() {
        return proDetailId;
    }

    /**
     * <p>意愿明细ID。</p>
     *
     * @param proDetailId 意愿明细ID。
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
     * <p>产品单位。</p>
     *
     * @return the 产品单位
     */
    public String getPdUnit() {
        return pdUnit;
    }

    /**
     * <p>产品单位。</p>
     *
     * @param pdUnit 产品单位。
     */
    public void setPdUnit(String pdUnit) {
        this.pdUnit = pdUnit;
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
    public java.math.BigDecimal getPdWeight() {
        return pdWeight;
    }

    /**
     * <p>重量。</p>
     *
     * @param pdWeight 重量。
     */
    public void setPdWeight(java.math.BigDecimal pdWeight) {
        this.pdWeight = pdWeight;
    }

    /**
     * <p>体积。</p>
     *
     * @return the 体积
     */
    public java.math.BigDecimal getPdVolume() {
        return pdVolume;
    }

    /**
     * <p>体积。</p>
     *
     * @param pdVolume 体积。
     */
    public void setPdVolume(java.math.BigDecimal pdVolume) {
        this.pdVolume = pdVolume;
    }

    /**
     * <p>订单价格。</p>
     *
     * @return the 订单价格
     */
    public java.math.BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * <p>订单价格。</p>
     *
     * @param orderPrice 订单价格。
     */
    public void setOrderPrice(java.math.BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @return the 价盘周期
     */
    public String getPriceCycle() {
        return priceCycle;
    }

    /**
     * <p>价盘周期。</p>
     *
     * @param priceCycle 价盘周期。
     */
    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    /**
     * <p>订单数量。</p>
     *
     * @return the 订单数量
     */
    public java.math.BigDecimal getOrderQty() {
        return orderQty;
    }

    /**
     * <p>订单数量。</p>
     *
     * @param orderQty 订单数量。
     */
    public void setOrderQty(java.math.BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

}
