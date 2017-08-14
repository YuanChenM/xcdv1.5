package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141140RsBean.原料种源信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141140RsBean", description = "searchList")
public class IPD141140RsBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品类别编码")
    private String classesCode;
    @ApiModelProperty(value = "产品加工类别编码")
    private String machiningCode;
    @ApiModelProperty(value = "产品种类编码")
    private String breedCode;
    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;
    @ApiModelProperty(value = "学名")
    private String scientificName;
    @ApiModelProperty(value = "俗名")
    private String localName;
    @ApiModelProperty(value = "销售名")
    private String salesName;
    @ApiModelProperty(value = "原料原产地")
    private String placeOrigin;
    @ApiModelProperty(value = "现产地")
    private String placeCurrent;
    @ApiModelProperty(value = "原料种源")
    private String source;
    @ApiModelProperty(value = "雏类")
    private String childType;
    @ApiModelProperty(value = "饲养方式")
    private String feedType;
    @ApiModelProperty(value = "饲养周期")
    private String feedPeriod;
    @ApiModelProperty(value = "classestreeCode")
    private String classestreeCode;


    /**
     * Getter method for property <tt>classestreeCode</tt>.
     *
     * @return property value of classestreeCode
     */
    public String getClassestreeCode() {
        return classestreeCode;
    }

    /**
     * Setter method for property <tt>classestreeCode</tt>.
     *
     * @param classestreeCode value to be assigned to property classestreeCode
     */
    public void setClassestreeCode(String classestreeCode) {
        this.classestreeCode = classestreeCode;
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
     * Getter method for property <tt>scientificName</tt>.
     *
     * @return property value of scientificName
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Setter method for property <tt>scientificName</tt>.
     *
     * @param scientificName value to be assigned to property scientificName
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Getter method for property <tt>localName</tt>.
     *
     * @return property value of localName
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Setter method for property <tt>localName</tt>.
     *
     * @param localName value to be assigned to property localName
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Getter method for property <tt>salesName</tt>.
     *
     * @return property value of salesName
     */
    public String getSalesName() {
        return salesName;
    }

    /**
     * Setter method for property <tt>salesName</tt>.
     *
     * @param salesName value to be assigned to property salesName
     */
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    /**
     * Getter method for property <tt>placeOrigin</tt>.
     *
     * @return property value of placeOrigin
     */
    public String getPlaceOrigin() {
        return placeOrigin;
    }

    /**
     * Setter method for property <tt>placeOrigin</tt>.
     *
     * @param placeOrigin value to be assigned to property placeOrigin
     */
    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    /**
     * Getter method for property <tt>placeCurrent</tt>.
     *
     * @return property value of placeCurrent
     */
    public String getPlaceCurrent() {
        return placeCurrent;
    }

    /**
     * Setter method for property <tt>placeCurrent</tt>.
     *
     * @param placeCurrent value to be assigned to property placeCurrent
     */
    public void setPlaceCurrent(String placeCurrent) {
        this.placeCurrent = placeCurrent;
    }

    /**
     * Getter method for property <tt>source</tt>.
     *
     * @return property value of source
     */
    public String getSource() {
        return source;
    }

    /**
     * Setter method for property <tt>source</tt>.
     *
     * @param source value to be assigned to property source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Getter method for property <tt>childType</tt>.
     *
     * @return property value of childType
     */
    public String getChildType() {
        return childType;
    }

    /**
     * Setter method for property <tt>childType</tt>.
     *
     * @param childType value to be assigned to property childType
     */
    public void setChildType(String childType) {
        this.childType = childType;
    }

    /**
     * Getter method for property <tt>feedType</tt>.
     *
     * @return property value of feedType
     */
    public String getFeedType() {
        return feedType;
    }

    /**
     * Setter method for property <tt>feedType</tt>.
     *
     * @param feedType value to be assigned to property feedType
     */
    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    /**
     * Getter method for property <tt>feedPeriod</tt>.
     *
     * @return property value of feedPeriod
     */
    public String getFeedPeriod() {
        return feedPeriod;
    }

    /**
     * Setter method for property <tt>feedPeriod</tt>.
     *
     * @param feedPeriod value to be assigned to property feedPeriod
     */
    public void setFeedPeriod(String feedPeriod) {
        this.feedPeriod = feedPeriod;
    }
}