package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 封装参数
 * IPD141121RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141121RsParam", description = "param")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141121RsParam extends RsPageParam {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类别编码")
    private String classesCode;
    @ApiModelProperty(value = "产品加工程度编码")
    private String machiningCode;

    @ApiModelProperty(value = "品种编码")
    private String breedCode;

    @ApiModelProperty(value = "特征编码")
    private String featureCode;
    @ApiModelProperty(value = "等级ID")
    private String levelId;
    @ApiModelProperty(value = "产品标准ID")
    private String standardId;
    @ApiModelProperty(value = "stdItemId")
    private String stdItemId;


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

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStdItemId() {
        return stdItemId;
    }

    public void setStdItemId(String stdItemId) {
        this.stdItemId = stdItemId;
    }
}