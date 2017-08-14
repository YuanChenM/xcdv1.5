package com.msk.product.bean;


import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/5/4.
 */
@ApiModel(value = "IPD141147RsParam", description = "param")
public class IPD141147RsParam extends BaseParam {

    @ApiModelProperty(value = "产品一级分类Code")
    private String classesCode;

    @ApiModelProperty(value = "产品二级分类Code")
    private String machiningCode;

    @ApiModelProperty(value = "产品品种CODE")
    private String breedCode;

    @ApiModelProperty(value = "产品特征CODE")
    private String featureCode;

    @ApiModelProperty(value = "产品净重CODE")
    private String weightCode;

    @ApiModelProperty(value = "等级CODE")
    private String gradeCode;

    @ApiModelProperty(value = "等级CODE")
    private String platform;

    /**
     * Getter method for property <tt>platform</tt>.
     *
     * @return property value of platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Setter method for property <tt>platform</tt>.
     *
     * @param platform value to be assigned to property platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * Getter method for property <tt>classesCode</tt>.
     *
     * @return property value of classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * Setter method for property <tt>classesCode</tt>.
     *
     * @param classesCode value to be assigned to property classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * Getter method for property <tt>machiningCode</tt>.
     *
     * @return property value of machiningCode
     */
    public String getMachiningCode() {
        return machiningCode;
    }

    /**
     * Setter method for property <tt>machiningCode</tt>.
     *
     * @param machiningCode value to be assigned to property machiningCode
     */
    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    /**
     * Getter method for property <tt>breedCode</tt>.
     *
     * @return property value of breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * Setter method for property <tt>breedCode</tt>.
     *
     * @param breedCode value to be assigned to property breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * Getter method for property <tt>featureCode</tt>.
     *
     * @return property value of featureCode
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * Setter method for property <tt>featureCode</tt>.
     *
     * @param featureCode value to be assigned to property featureCode
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * Getter method for property <tt>weightCode</tt>.
     *
     * @return property value of weightCode
     */
    public String getWeightCode() {
        return weightCode;
    }

    /**
     * Setter method for property <tt>weightCode</tt>.
     *
     * @param weightCode value to be assigned to property weightCode
     */
    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }

    /**
     * Getter method for property <tt>gradeCode</tt>.
     *
     * @return property value of gradeCode
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * Setter method for property <tt>gradeCode</tt>.
     *
     * @param gradeCode value to be assigned to property gradeCode
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }
}
