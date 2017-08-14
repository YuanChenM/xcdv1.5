package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询产品主码
 * IPD141105RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141105RsParam", description = "返回参数产品加工程度等级")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId", "actTime","breedName","featureName","gradeCode","gradeName" })
public class IPD141105RsParam extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "类别编码")
    private String classesCode;
    @ApiModelProperty(value = "编码等级")
    private Integer codeLevel;
    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;
    @ApiModelProperty(value = "品种编码")
    private String breedCode;
    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;
    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;

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
     * Getter method for property <tt>codeLevel</tt>.
     *
     * @return property value of codeLevel
     */
    public Integer getCodeLevel() {
        return codeLevel;
    }

    /**
     * Setter method for property <tt>codeLevel</tt>.
     *
     * @param codeLevel value to be assigned to property codeLevel
     */
    public void setCodeLevel(Integer codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getMachiningCode() {
        return machiningCode;
    }

    public void setMachiningCode(String machiningCode) {
        this.machiningCode = machiningCode;
    }

    public String getBreedCode() {
        return breedCode;
    }

    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getWeightCode() {
        return weightCode;
    }

    public void setWeightCode(String weightCode) {
        this.weightCode = weightCode;
    }
}