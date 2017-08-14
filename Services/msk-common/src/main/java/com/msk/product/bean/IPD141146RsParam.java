package com.msk.product.bean;


import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "IPD141146RsParam", description = "param")
public class IPD141146RsParam extends RsPageParam {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "code")
    private String code;
    @ApiModelProperty(value = "产品编码")
    private String pdCode;

    /*
     *档案卡类型
     * 原种种源标准指标 -- 1
     * 原种种源标准指标 -- 2
     * 原种饲养标准指标 -- 3
     * 产品加工技术标准指标 -- 4
     * 产品加工质量标准指标 -- 5
     * 产品通用质量标准指标 -- 6
     * 产品安全标准指标 -- 7
     * 储存运输标准指标 -- 8
     */
    /**
     * 产品类型
     * classes——1、
     * machining——2、
     * breed——3、
     * feature——4、
     * weight——5、
     * grade——6
     */
    @ApiModelProperty(value = "档案卡类型")
    private Integer type;
    @ApiModelProperty(value = "类别编码")
    private String classesCode;
    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;
    @ApiModelProperty(value = "品种编码")
    private String breedCode;
    @ApiModelProperty(value = "特征编码")
    private String featureCode;
    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;
    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    List<IPD141146RsParam> classesCodeList = new ArrayList<>();

    List<IPD141146RsParam> machiningCodeList = new ArrayList<>();

    List<IPD141146RsParam> breedCodeList = new ArrayList<>();

    List<IPD141146RsParam> featureCodeList = new ArrayList<>();

    List<IPD141146RsParam> weightCodeList = new ArrayList<>();

    List<IPD141146RsParam> gradeCodeList = new ArrayList<>();

    public List<IPD141146RsParam> getClassesCodeList() {
        return classesCodeList;
    }

    public void setClassesCodeList(List<IPD141146RsParam> classesCodeList) {
        this.classesCodeList = classesCodeList;
    }

    public List<IPD141146RsParam> getMachiningCodeList() {
        return machiningCodeList;
    }

    public void setMachiningCodeList(List<IPD141146RsParam> machiningCodeList) {
        this.machiningCodeList = machiningCodeList;
    }

    public List<IPD141146RsParam> getBreedCodeList() {
        return breedCodeList;
    }

    public void setBreedCodeList(List<IPD141146RsParam> breedCodeList) {
        this.breedCodeList = breedCodeList;
    }

    public List<IPD141146RsParam> getFeatureCodeList() {
        return featureCodeList;
    }

    public void setFeatureCodeList(List<IPD141146RsParam> featureCodeList) {
        this.featureCodeList = featureCodeList;
    }

    public List<IPD141146RsParam> getWeightCodeList() {
        return weightCodeList;
    }

    public void setWeightCodeList(List<IPD141146RsParam> weightCodeList) {
        this.weightCodeList = weightCodeList;
    }

    public List<IPD141146RsParam> getGradeCodeList() {
        return gradeCodeList;
    }

    public void setGradeCodeList(List<IPD141146RsParam> gradeCodeList) {
        this.gradeCodeList = gradeCodeList;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

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

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}