package com.msk.product.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yang_chunyan on 2016/7/5.
 */
@ApiModel(value = "NormsParams", description = "包装参数")
public class NormsParams {

    @ApiModelProperty(value = "一级分类编码")
    private String classesCode;

    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;

    @ApiModelProperty(value = "品种编码")
    private String breedCode;

    @ApiModelProperty(value = "特征编码")
    private String featureCode;

    @ApiModelProperty(value = "净重编码")
    private String weightCode;

    @ApiModelProperty(value = "包装编码")
    private String NormsCode;

    public String getClassesCode() {
        return classesCode;
    }

    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
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

    public String getNormsCode() {
        return NormsCode;
    }

    public void setNormsCode(String normsCode) {
        NormsCode = normsCode;
    }
}
