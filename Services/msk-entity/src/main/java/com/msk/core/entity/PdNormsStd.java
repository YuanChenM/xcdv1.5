/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;

import java.math.BigDecimal;

/**
 * <p>表pd_norms_std对应的PdNormsStd。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class PdNormsStd extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 产品标准ID */
    private java.lang.Long standardId;
    /** 包装规格编码 */
    private java.lang.String normsCode;
    /** 单个产品净重 */
    private java.lang.String normsSuttle;
    /** 单个产品规格净重误差范围 */
    private java.lang.String normsError;
    /** 内包装净重/个数 */
    private java.lang.String normsNumber;
    /** 内包装尺寸 */
    private java.lang.String normsSize;
    /** 内包装材质及技术标准 */
    private java.lang.String normsTexture;
    /** 外包装规格 */
    private java.lang.String normsOut;
    /** 外包装净重/毛重 */
    private java.lang.String normsKg;
    /** 外包装尺寸 */
    private java.lang.String normsOutSize;
    /** 外包装材质及技术标准 */
    private java.lang.String normsOutTexture;
    /** 第十种包装信息 */
    private java.lang.String normsTen;
    /** 外包装长 */
    private java.math.BigDecimal normsLength;
    /** 外包装宽 */
    private java.math.BigDecimal normsWidth;
    /** 外包装高 */
    private java.math.BigDecimal normsHeight;
    /** 外包装体积 */
    private java.math.BigDecimal normsVolume;
    /** 内包装净重数值 */
    private java.math.BigDecimal netweightInner;
    /** 外包装净重数值 */
    private java.math.BigDecimal netweightOut;
    /** 产品分类CODE */
    private java.lang.String classestreeCode;

    private java.math.BigDecimal grossweightOut;

    /**
     * Getter method for property <tt>grossweightOut</tt>.
     *
     * @return property value of grossweightOut
     */
    public BigDecimal getGrossweightOut() {
        return grossweightOut;
    }

    /**
     * Setter method for property <tt>grossweightOut</tt>.
     *
     * @param grossweightOut value to be assigned to property grossweightOut
     */
    public void setGrossweightOut(BigDecimal grossweightOut) {
        this.grossweightOut = grossweightOut;
    }

    /**
     * <p>默认构造函数。</p>
     */
    public PdNormsStd() {

    }

    /**
     * <p>产品标准ID。</p>
     *
     * @return the 产品标准ID
     */
    public java.lang.Long getStandardId() {
        return standardId;
    }

    /**
     * <p>产品标准ID。</p>
     *
     * @param standardId 产品标准ID。
     */
    public void setStandardId(java.lang.Long standardId) {
        this.standardId = standardId;
    }

    /**
     * <p>包装规格编码。</p>
     *
     * @return the 包装规格编码
     */
    public java.lang.String getNormsCode() {
        return normsCode;
    }

    /**
     * <p>包装规格编码。</p>
     *
     * @param normsCode 包装规格编码。
     */
    public void setNormsCode(java.lang.String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * <p>单个产品净重。</p>
     *
     * @return the 单个产品净重
     */
    public java.lang.String getNormsSuttle() {
        return normsSuttle;
    }

    /**
     * <p>单个产品净重。</p>
     *
     * @param normsSuttle 单个产品净重。
     */
    public void setNormsSuttle(java.lang.String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * <p>单个产品规格净重误差范围。</p>
     *
     * @return the 单个产品规格净重误差范围
     */
    public java.lang.String getNormsError() {
        return normsError;
    }

    /**
     * <p>单个产品规格净重误差范围。</p>
     *
     * @param normsError 单个产品规格净重误差范围。
     */
    public void setNormsError(java.lang.String normsError) {
        this.normsError = normsError;
    }

    /**
     * <p>内包装净重/个数。</p>
     *
     * @return the 内包装净重/个数
     */
    public java.lang.String getNormsNumber() {
        return normsNumber;
    }

    /**
     * <p>内包装净重/个数。</p>
     *
     * @param normsNumber 内包装净重/个数。
     */
    public void setNormsNumber(java.lang.String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * <p>内包装尺寸。</p>
     *
     * @return the 内包装尺寸
     */
    public java.lang.String getNormsSize() {
        return normsSize;
    }

    /**
     * <p>内包装尺寸。</p>
     *
     * @param normsSize 内包装尺寸。
     */
    public void setNormsSize(java.lang.String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * <p>内包装材质及技术标准。</p>
     *
     * @return the 内包装材质及技术标准
     */
    public java.lang.String getNormsTexture() {
        return normsTexture;
    }

    /**
     * <p>内包装材质及技术标准。</p>
     *
     * @param normsTexture 内包装材质及技术标准。
     */
    public void setNormsTexture(java.lang.String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * <p>外包装规格。</p>
     *
     * @return the 外包装规格
     */
    public java.lang.String getNormsOut() {
        return normsOut;
    }

    /**
     * <p>外包装规格。</p>
     *
     * @param normsOut 外包装规格。
     */
    public void setNormsOut(java.lang.String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * <p>外包装净重/毛重。</p>
     *
     * @return the 外包装净重/毛重
     */
    public java.lang.String getNormsKg() {
        return normsKg;
    }

    /**
     * <p>外包装净重/毛重。</p>
     *
     * @param normsKg 外包装净重/毛重。
     */
    public void setNormsKg(java.lang.String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * <p>外包装尺寸。</p>
     *
     * @return the 外包装尺寸
     */
    public java.lang.String getNormsOutSize() {
        return normsOutSize;
    }

    /**
     * <p>外包装尺寸。</p>
     *
     * @param normsOutSize 外包装尺寸。
     */
    public void setNormsOutSize(java.lang.String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * <p>外包装材质及技术标准。</p>
     *
     * @return the 外包装材质及技术标准
     */
    public java.lang.String getNormsOutTexture() {
        return normsOutTexture;
    }

    /**
     * <p>外包装材质及技术标准。</p>
     *
     * @param normsOutTexture 外包装材质及技术标准。
     */
    public void setNormsOutTexture(java.lang.String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
    }

    /**
     * <p>第十种包装信息。</p>
     *
     * @return the 第十种包装信息
     */
    public java.lang.String getNormsTen() {
        return normsTen;
    }

    /**
     * <p>第十种包装信息。</p>
     *
     * @param normsTen 第十种包装信息。
     */
    public void setNormsTen(java.lang.String normsTen) {
        this.normsTen = normsTen;
    }

    /**
     * <p>外包装长。</p>
     *
     * @return the 外包装长
     */
    public java.math.BigDecimal getNormsLength() {
        return normsLength;
    }

    /**
     * <p>外包装长。</p>
     *
     * @param normsLength 外包装长。
     */
    public void setNormsLength(java.math.BigDecimal normsLength) {
        this.normsLength = normsLength;
    }

    /**
     * <p>外包装宽。</p>
     *
     * @return the 外包装宽
     */
    public java.math.BigDecimal getNormsWidth() {
        return normsWidth;
    }

    /**
     * <p>外包装宽。</p>
     *
     * @param normsWidth 外包装宽。
     */
    public void setNormsWidth(java.math.BigDecimal normsWidth) {
        this.normsWidth = normsWidth;
    }

    /**
     * <p>外包装高。</p>
     *
     * @return the 外包装高
     */
    public java.math.BigDecimal getNormsHeight() {
        return normsHeight;
    }

    /**
     * <p>外包装高。</p>
     *
     * @param normsHeight 外包装高。
     */
    public void setNormsHeight(java.math.BigDecimal normsHeight) {
        this.normsHeight = normsHeight;
    }

    /**
     * <p>外包装体积。</p>
     *
     * @return the 外包装体积
     */
    public java.math.BigDecimal getNormsVolume() {
        return normsVolume;
    }

    /**
     * <p>外包装体积。</p>
     *
     * @param normsVolume 外包装体积。
     */
    public void setNormsVolume(java.math.BigDecimal normsVolume) {
        this.normsVolume = normsVolume;
    }

    /**
     * <p>内包装净重数值。</p>
     *
     * @return the 内包装净重数值
     */
    public java.math.BigDecimal getNetweightInner() {
        return netweightInner;
    }

    /**
     * <p>内包装净重数值。</p>
     *
     * @param netweightInner 内包装净重数值。
     */
    public void setNetweightInner(java.math.BigDecimal netweightInner) {
        this.netweightInner = netweightInner;
    }

    /**
     * <p>外包装净重数值。</p>
     *
     * @return the 外包装净重数值
     */
    public java.math.BigDecimal getNetweightOut() {
        return netweightOut;
    }

    /**
     * <p>外包装净重数值。</p>
     *
     * @param netweightOut 外包装净重数值。
     */
    public void setNetweightOut(java.math.BigDecimal netweightOut) {
        this.netweightOut = netweightOut;
    }

    /**
     * <p>产品分类CODE。</p>
     *
     * @return the 产品分类CODE
     */
    public java.lang.String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * <p>产品分类CODE。</p>
     *
     * @param classestreeCode 产品分类CODE。
     */
    public void setClassestreeCode(java.lang.String classestreeCode) {
        this.classestreeCode = classestreeCode;
    }

}
