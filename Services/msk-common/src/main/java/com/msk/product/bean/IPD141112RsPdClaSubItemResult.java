package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 封装参数
 * IPD141112RsParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141112RsPdClaSubItemResult", description = "result")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141112RsPdClaSubItemResult extends BaseBean {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @ApiModelProperty(value = "产品标准id")
    private Integer standardId;
    @ApiModelProperty(value = "产品编号")
    private String productCode;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "产品分类编码")
    private String classesCode;
    @ApiModelProperty(value = "产品品种编码")
    private String breedCode;
    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;
    @ApiModelProperty(value = "有无质量标准")
    private String hasQtySpec;
    @ApiModelProperty(value = "有无技术标准")
    private String hasTncSpec;
    @JsonIgnore
    @ApiModelProperty(value = "classesName")
    private String classesName;
    @JsonIgnore
    @ApiModelProperty(value = "产品品种名称")
    private String breedName;
    @JsonIgnore
    @ApiModelProperty(value = "产品特征名称")
    private String featureName;
    @ApiModelProperty(value = "质量标准列表")
    private List<IPD141112RsPdStdClaResult> qtySpecList;
    @ApiModelProperty(value = "技术标准列表")
    private List<IPD141112RsPdTncResult> tncSpecList;

    /**
     * 获得standardId
     */
    public Integer getStandardId() {
        return standardId;
    }

    /**
     * 设置standardId
     */
    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    /**
     * 获得productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获得productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获得classesCode
     */
    public String getClassesCode() {
        return classesCode;
    }

    /**
     * 设置classesCode
     */
    public void setClassesCode(String classesCode) {
        this.classesCode = classesCode;
    }

    /**
     * 获得breedCode
     */
    public String getBreedCode() {
        return breedCode;
    }

    /**
     * 设置breedCode
     */
    public void setBreedCode(String breedCode) {
        this.breedCode = breedCode;
    }

    /**
     * 获得featureCode
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * 设置featureCode
     */
    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    /**
     * 获得hasQtySpec
     */
    public String getHasQtySpec() {
        return hasQtySpec;
    }

    /**
     * 设置hasQtySpec
     */
    public void setHasQtySpec(String hasQtySpec) {
        this.hasQtySpec = hasQtySpec;
    }

    /**
     * 获得hasTncSpec
     */
    public String getHasTncSpec() {
        return hasTncSpec;
    }

    /**
     * 设置hasTncSpec
     */
    public void setHasTncSpec(String hasTncSpec) {
        this.hasTncSpec = hasTncSpec;
    }

    /**
     * 获得classesName
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * 设置classesName
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

    /**
     * 获得breedName
     */
    public String getBreedName() {
        return breedName;
    }

    /**
     * 设置breedName
     */
    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    /**
     * 获得featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * 设置featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /**
     * 获得qtySpecList
     */
    public List<IPD141112RsPdStdClaResult> getQtySpecList() {
        return qtySpecList;
    }

    /**
     * 设置qtySpecList
     */
    public void setQtySpecList(List<IPD141112RsPdStdClaResult> qtySpecList) {
        this.qtySpecList = qtySpecList;
    }

    /**
     * 获得tncSpecList
     */
    public List<IPD141112RsPdTncResult> getTncSpecList() {
        return tncSpecList;
    }

    /**
     * 设置tncSpecList
     */
    public void setTncSpecList(List<IPD141112RsPdTncResult> tncSpecList) {
        this.tncSpecList = tncSpecList;
    }
}