package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 产品标准包装档案卡查询参数
 * IPD141108RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141109RsParam", description = "param")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId" })
public class IPD141109RsParam extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类别编码")
    private String classesCode;
    @ApiModelProperty(value = "品种编码")
    private String breedCode;
    @ApiModelProperty(value = "特征编码")
    private String featureCode;
    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;
    @ApiModelProperty(value = "产品标准id")
    private Long standardId;

    @ApiModelProperty(value = "包装规格编码")
    private String normsCode;

    @ApiModelProperty(value = "单个产品净重")
    private String normsSuttle;

    @ApiModelProperty(value = "单个产品规格净重误差范围")
    private String normsError;

    @ApiModelProperty(value = "内包装净重/个数")
    private String normsNumber;

    @ApiModelProperty(value = "内包装尺寸")
    private String normsSize;

    @ApiModelProperty(value = "内包装材质及技术标准")
    private String normsTexture;

    @ApiModelProperty(value = "外包装规格")
    private String normsOut;

    @ApiModelProperty(value = "外包装净重/毛重")
    private String normsKg;

    @ApiModelProperty(value = "外包装尺寸")
    private String normsOutSize;

    @ApiModelProperty(value = "外包装材质及技术标准")
    private String normsOutTexture;

    @ApiModelProperty(value = "其他包装信息")
    private String normsTen;

    @ApiModelProperty(value = "外包装长")
    private BigDecimal normsLength;

    @ApiModelProperty(value = "外包装宽")
    private BigDecimal normsWidth;

    @ApiModelProperty(value = "外包装高")
    private BigDecimal normsHeight;

    @ApiModelProperty(value = "外包装体积")
    private BigDecimal normsVolume;
    @ApiModelProperty(value = "内包装净重数值")
    private String netWeightInner;
    @ApiModelProperty(value = "外包装净重数值")
    private String netWeightOut;

    /**
     * Get the normsCode.
     * 
     * @return normsCode
     *
     * @author xhy
     */
    public String getNormsCode() {
        return this.normsCode;
    }

    /**
     * Set the normsCode.
     *
     * @param normsCode normsCode
     *
     * @author xhy
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Get the normsSuttle.
     *
     * @return normsSuttle
     *
     * @author xhy
     */
    public String getNormsSuttle() {
        return this.normsSuttle;
    }

    /**
     * Set the normsSuttle.
     *
     * @param normsSuttle normsSuttle
     *
     * @author xhy
     */
    public void setNormsSuttle(String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * Get the normsError.
     *
     * @return normsError
     *
     * @author xhy
     */
    public String getNormsError() {
        return this.normsError;
    }

    /**
     * Set the normsError.
     *
     * @param normsError normsError
     *
     * @author xhy
     */
    public void setNormsError(String normsError) {
        this.normsError = normsError;
    }

    /**
     * Get the normsNumber.
     *
     * @return normsNumber
     *
     * @author xhy
     */
    public String getNormsNumber() {
        return this.normsNumber;
    }

    /**
     * Set the normsNumber.
     *
     * @param normsNumber normsNumber
     *
     * @author xhy
     */
    public void setNormsNumber(String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * Get the normsSize.
     *
     * @return normsSize
     *
     * @author xhy
     */
    public String getNormsSize() {
        return this.normsSize;
    }

    /**
     * Set the normsSize.
     *
     * @param normsSize normsSize
     *
     * @author xhy
     */
    public void setNormsSize(String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * Get the normsTexture.
     *
     * @return normsTexture
     *
     * @author xhy
     */
    public String getNormsTexture() {
        return this.normsTexture;
    }

    /**
     * Set the normsTexture.
     *
     * @param normsTexture normsTexture
     *
     * @author xhy
     */
    public void setNormsTexture(String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * Get the normsOut.
     *
     * @return normsOut
     *
     * @author xhy
     */
    public String getNormsOut() {
        return this.normsOut;
    }

    /**
     * Set the normsOut.
     *
     * @param normsOut normsOut
     *
     * @author xhy
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * Get the normsKg.
     *
     * @return normsKg
     *
     * @author xhy
     */
    public String getNormsKg() {
        return this.normsKg;
    }

    /**
     * Set the normsKg.
     *
     * @param normsKg normsKg
     *
     * @author xhy
     */
    public void setNormsKg(String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * Get the normsOutSize.
     *
     * @return normsOutSize
     *
     * @author xhy
     */
    public String getNormsOutSize() {
        return this.normsOutSize;
    }

    /**
     * Set the normsOutSize.
     *
     * @param normsOutSize normsOutSize
     *
     * @author xhy
     */
    public void setNormsOutSize(String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * Get the normsOutTexture.
     *
     * @return normsOutTexture
     *
     * @author xhy
     */
    public String getNormsOutTexture() {
        return this.normsOutTexture;
    }

    /**
     * Set the normsOutTexture.
     *
     * @param normsOutTexture normsOutTexture
     *
     * @author xhy
     */
    public void setNormsOutTexture(String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
    }

    /**
     * Get the normsTen.
     *
     * @return normsTen
     *
     * @author xhy
     */
    public String getNormsTen() {
        return this.normsTen;
    }

    /**
     * Set the normsTen.
     *
     * @param normsTen normsTen
     *
     * @author xhy
     */
    public void setNormsTen(String normsTen) {
        this.normsTen = normsTen;
    }

    /**
     * Get the normsLength.
     *
     * @return normsLength
     *
     * @author xhy
     */
    public BigDecimal getNormsLength() {
        return this.normsLength;
    }

    /**
     * Set the normsLength.
     *
     * @param normsLength normsLength
     *
     * @author xhy
     */
    public void setNormsLength(BigDecimal normsLength) {
        this.normsLength = normsLength;
    }

    /**
     * Get the normsWidth.
     *
     * @return normsWidth
     *
     * @author xhy
     */
    public BigDecimal getNormsWidth() {
        return this.normsWidth;
    }

    /**
     * Set the normsWidth.
     *
     * @param normsWidth normsWidth
     *
     * @author xhy
     */
    public void setNormsWidth(BigDecimal normsWidth) {
        this.normsWidth = normsWidth;
    }

    /**
     * Get the normsHeight.
     *
     * @return normsHeight
     *
     * @author xhy
     */
    public BigDecimal getNormsHeight() {
        return this.normsHeight;
    }

    /**
     * Set the normsHeight.
     *
     * @param normsHeight normsHeight
     *
     * @author xhy
     */
    public void setNormsHeight(BigDecimal normsHeight) {
        this.normsHeight = normsHeight;
    }

    /**
     * Get the normsVolume.
     *
     * @return normsVolume
     *
     * @author xhy
     */
    public BigDecimal getNormsVolume() {
        return this.normsVolume;
    }

    /**
     * Set the normsVolume.
     *
     * @param normsVolume normsVolume
     *
     * @author xhy
     */
    public void setNormsVolume(BigDecimal normsVolume) {
        this.normsVolume = normsVolume;
    }

    /**
     * Get the classesCode.
     *
     * @return classesCode
     *
     * @author xhy
     */
    public String getClassesCode() {
        return this.classesCode;
    }

    /**
     * Set the classesCode.
     *
     * @param classesCode classesCode
     *
     * @author xhy
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Get the breedCode.
     *
     * @return breedCode
     *
     * @author xhy
     */
    public String getBreedCode() {
        return this.breedCode;
    }

    /**
     * Set the breedCode.
     *
     * @param breedCode breedCode
     *
     * @author xhy
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Get the featureCode.
     *
     * @return featureCode
     *
     * @author xhy
     */
    public String getFeatureCode() {
        return this.featureCode;
    }

    /**
     * Set the featureCode.
     *
     * @param featureCode featureCode
     *
     * @author xhy
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Get the gradeCode.
     *
     * @return gradeCode
     *
     * @author xhy
     */
    public String getGradeCode() {
        return this.gradeCode;
    }

    /**
     * Set the gradeCode.
     *
     * @param gradeCode gradeCode
     *
     * @author xhy
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    /**
     * Get the netWeightInner.
     *
     * @return netWeightInner
     *
     * @author xhy
     */
    public String getNetWeightInner() {
        return this.netWeightInner;
    }

    /**
     * Set the netWeightInner.
     *
     * @param netWeightInner netWeightInner
     *
     * @author xhy
     */
    public void setNetWeightInner(String netWeightInner) {
        this.netWeightInner = netWeightInner;
    }

    /**
     * Get the netWeightOut.
     *
     * @return netWeightOut
     *
     * @author xhy
     */
    public String getNetWeightOut() {
        return this.netWeightOut;
    }

    /**
     * Set the netWeightOut.
     *
     * @param netWeightOut netWeightOut
     *
     * @author xhy
     */
    public void setNetWeightOut(String netWeightOut) {
        this.netWeightOut = netWeightOut;
    }

    /**
     * Get the standardId.
     *
     * @return standardId
     *
     * @author xhy
     */
    /**
     * 获得standardId
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * 设置standardId
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }
}