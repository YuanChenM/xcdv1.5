package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 封装参数
 * IPD141115PdInfoRsParam
 *
 * @author xhy
 */
@ApiModel(value = "IPD141115PdInfoRsResult", description = "产品信息列表")
@JsonIgnoreProperties(value = { "delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId" })
public class IPD141115PdInfoRsResult extends BaseBean {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品编号")
    private String productCode;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "产品分类编码")
    private String classesCode;
    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;
    @ApiModelProperty(value = "产品品种编码")
    private String breedCode;
    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;
    @ApiModelProperty(value = "产品净重编码")
    private String weightCode;
    @ApiModelProperty(value = "包装规格")
    private String pkgSpec;
    @ApiModelProperty(value = "包装编码")
    private String pkgCode;
    @ApiModelProperty(value = "产品规格")
    private String productSpec;
    @ApiModelProperty(value = "净重")
    private BigDecimal netWeight;

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
     * 获得pkgCode
     */
    public String getPkgCode() {
        return pkgCode;
    }

    /**
     * 设置pkgCode
     */
    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
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
     * 获得pkgSpec
     */
    public String getPkgSpec() {
        return pkgSpec;
    }

    /**
     * 设置pkgSpec
     */
    public void setPkgSpec(String pkgSpec) {
        this.pkgSpec = pkgSpec;
    }

    /**
     * 获得productSpec
     */
    public String getProductSpec() {
        return productSpec;
    }

    /**
     * 设置productSpec
     */
    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    /**
     * 获得netWeight
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }

    /**
     * 设置netWeight
     */
    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }
}