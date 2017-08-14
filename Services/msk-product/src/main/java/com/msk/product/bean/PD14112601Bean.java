package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/6/1.
 */
@ApiModel(value = "PD14112601Bean", description = "产品特征分类")
public class PD14112601Bean extends BaseEntity {

    @ApiModelProperty(value = "产品属性码")
    private String attributeCode;

    @ApiModelProperty(value = "净重编码")
    private String featureCode;

    @ApiModelProperty(value = "净重名称")
    private String featureName;

    @ApiModelProperty(value = "包装规格编码")
    private String normsCode;

    @ApiModelProperty(value = "包装规格")
    private String normsOut;

    @ApiModelProperty(value = "产品包装码")
    private String pdNormCode;

    /**
     * Getter method for property <tt>attributeCode</tt>.
     *
     * @return property value of attributeCode
     */
    public String getAttributeCode() {
        return attributeCode;
    }

    /**
     * Setter method for property <tt>attributeCode</tt>.
     *
     * @param attributeCode value to be assigned to property attributeCode
     */
    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
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
     * Getter method for property <tt>featureName</tt>.
     *
     * @return property value of featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Setter method for property <tt>featureName</tt>.
     *
     * @param featureName value to be assigned to property featureName
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

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
     * Getter method for property <tt>pdNormCode</tt>.
     *
     * @return property value of pdNormCode
     */
    public String getPdNormCode() {
        return pdNormCode;
    }

    /**
     * Setter method for property <tt>pdNormCode</tt>.
     *
     * @param pdNormCode value to be assigned to property pdNormCode
     */
    public void setPdNormCode(String pdNormCode) {
        this.pdNormCode = pdNormCode;
    }
}
