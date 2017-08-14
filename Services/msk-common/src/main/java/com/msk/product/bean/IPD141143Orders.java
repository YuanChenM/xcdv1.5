package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 
 * IPD141143RsResult.神农客价盘通道同步接口
 *
 * @author    xhy 2016-4-8
 */
@ApiModel(value = "IPD141143Orders", description = "searchList")
public class IPD141143Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "商品ID（一级分类2位+二级分类1位+品种2位+特征2位+净重2位+产品等级1位）")
    private String productId;
    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;
    @ApiModelProperty(value = "物流区编码")
    private String logiAreaCode;
    @ApiModelProperty(value = "价格列表")
    private List<IPD141143OrderLevel> waylist;
    @ApiModelProperty(value = "产品类别编码")
    private String classesCode;
    @ApiModelProperty(value = "产品加工类别编码")
    private String machiningCode;
    @ApiModelProperty(value = "产品种类编码")
    private String breedCode;
    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;
    @ApiModelProperty(value = "净重")
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
     * Getter method for property <tt>productId</tt>.
     *
     * @return property value of productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Setter method for property <tt>productId</tt>.
     *
     * @param productId value to be assigned to property productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
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

    /**
     * Getter method for property <tt>logiAreaCode</tt>.
     *
     * @return property value of logiAreaCode
     */
    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    /**
     * Setter method for property <tt>logiAreaCode</tt>.
     *
     * @param logiAreaCode value to be assigned to property logiAreaCode
     */
    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    /**
     * Getter method for property <tt>waylist</tt>.
     *
     * @return property value of waylist
     */
    public List<IPD141143OrderLevel> getWaylist() {
        return waylist;
    }

    /**
     * Setter method for property <tt>waylist</tt>.
     *
     * @param waylist value to be assigned to property waylist
     */
    public void setWaylist(List<IPD141143OrderLevel> waylist) {
        this.waylist = waylist;
    }
}