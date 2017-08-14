package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@ApiModel(value = "IPD141146RsResult", description = "result")
public class IPD141146RsResult extends BaseEntity {
    @ApiModelProperty(value = "产品分类编码")
    private String classesCode;

    @ApiModelProperty(value = "产品分类名称")
    private String classesName;

    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;

    @ApiModelProperty(value = "产品加工程度名称")
    private String machiningName;

    @ApiModelProperty(value = "产品品种编码")
    private String breedCode;

    @ApiModelProperty(value = "产品品种名称")
    private String breedName;

    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;

    @ApiModelProperty(value = "产品特征名称")
    private String featureName;

    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;

    @ApiModelProperty(value = "产品净重名称")
    private String weightName;

    @ApiModelProperty(value = "净重数值")
    private BigDecimal weightVal;

    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "产品等级名称")
    private String gradeName;

    @ApiModelProperty(value = "产品国家编码")
    private String countryCode;

    @ApiModelProperty(value = "产品包装编码")
    private String normsCode;

    @ApiModelProperty(value = "产品包装规格名称")
    private String normsName;

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

    /**
     * Getter method for property <tt>normsCode</tt>.
     *
     * @return property value of normsCode
     */
    public String getNormsCode() {
        return normsCode;
    }

    /**
     * Setter method for property <tt>normsCode</tt>.
     *
     * @param normsCode value to be assigned to property normsCode
     */
    public void setNormsCode(String normsCode) {
        this.normsCode = normsCode;
    }

    /**
     * Getter method for property <tt>normsName</tt>.
     *
     * @return property value of normsName
     */
    public String getNormsName() {
        return normsName;
    }

    /**
     * Setter method for property <tt>normsName</tt>.
     *
     * @param normsName value to be assigned to property normsName
     */
    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    /**
     * Getter method for property <tt>normsSuttle</tt>.
     *
     * @return property value of normsSuttle
     */
    public String getNormsSuttle() {
        return normsSuttle;
    }

    /**
     * Setter method for property <tt>normsSuttle</tt>.
     *
     * @param normsSuttle value to be assigned to property normsSuttle
     */
    public void setNormsSuttle(String normsSuttle) {
        this.normsSuttle = normsSuttle;
    }

    /**
     * Getter method for property <tt>normsError</tt>.
     *
     * @return property value of normsError
     */
    public String getNormsError() {
        return normsError;
    }

    /**
     * Setter method for property <tt>normsError</tt>.
     *
     * @param normsError value to be assigned to property normsError
     */
    public void setNormsError(String normsError) {
        this.normsError = normsError;
    }

    /**
     * Getter method for property <tt>normsNumber</tt>.
     *
     * @return property value of normsNumber
     */
    public String getNormsNumber() {
        return normsNumber;
    }

    /**
     * Setter method for property <tt>normsNumber</tt>.
     *
     * @param normsNumber value to be assigned to property normsNumber
     */
    public void setNormsNumber(String normsNumber) {
        this.normsNumber = normsNumber;
    }

    /**
     * Getter method for property <tt>normsSize</tt>.
     *
     * @return property value of normsSize
     */
    public String getNormsSize() {
        return normsSize;
    }

    /**
     * Setter method for property <tt>normsSize</tt>.
     *
     * @param normsSize value to be assigned to property normsSize
     */
    public void setNormsSize(String normsSize) {
        this.normsSize = normsSize;
    }

    /**
     * Getter method for property <tt>normsTexture</tt>.
     *
     * @return property value of normsTexture
     */
    public String getNormsTexture() {
        return normsTexture;
    }

    /**
     * Setter method for property <tt>normsTexture</tt>.
     *
     * @param normsTexture value to be assigned to property normsTexture
     */
    public void setNormsTexture(String normsTexture) {
        this.normsTexture = normsTexture;
    }

    /**
     * Getter method for property <tt>normsOut</tt>.
     *
     * @return property value of normsOut
     */
    public String getNormsOut() {
        return normsOut;
    }

    /**
     * Setter method for property <tt>normsOut</tt>.
     *
     * @param normsOut value to be assigned to property normsOut
     */
    public void setNormsOut(String normsOut) {
        this.normsOut = normsOut;
    }

    /**
     * Getter method for property <tt>normsKg</tt>.
     *
     * @return property value of normsKg
     */
    public String getNormsKg() {
        return normsKg;
    }

    /**
     * Setter method for property <tt>normsKg</tt>.
     *
     * @param normsKg value to be assigned to property normsKg
     */
    public void setNormsKg(String normsKg) {
        this.normsKg = normsKg;
    }

    /**
     * Getter method for property <tt>normsOutSize</tt>.
     *
     * @return property value of normsOutSize
     */
    public String getNormsOutSize() {
        return normsOutSize;
    }

    /**
     * Setter method for property <tt>normsOutSize</tt>.
     *
     * @param normsOutSize value to be assigned to property normsOutSize
     */
    public void setNormsOutSize(String normsOutSize) {
        this.normsOutSize = normsOutSize;
    }

    /**
     * Getter method for property <tt>normsOutTexture</tt>.
     *
     * @return property value of normsOutTexture
     */
    public String getNormsOutTexture() {
        return normsOutTexture;
    }

    /**
     * Setter method for property <tt>normsOutTexture</tt>.
     *
     * @param normsOutTexture value to be assigned to property normsOutTexture
     */
    public void setNormsOutTexture(String normsOutTexture) {
        this.normsOutTexture = normsOutTexture;
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

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getMachiningName() {
        return machiningName;
    }

    public void setMachiningName(String machiningName) {
        this.machiningName = machiningName;
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

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public BigDecimal getWeightVal() {
        return weightVal;
    }

    public void setWeightVal(BigDecimal weightVal) {
        this.weightVal = weightVal;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
