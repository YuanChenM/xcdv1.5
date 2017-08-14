package com.msk.product.bean;


import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * LogiAreaPdBean.
 *
 * @author yuan_chen
 */
@ApiModel(value = "LogiAreaPdBean", description = "物流区产品信息列表")
public class LogiAreaPdBean extends BaseBean {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物流区编码")
    private String logiAreaCode;

    @ApiModelProperty(value = "物流区名称")
    private String logiAreaName;

    @ApiModelProperty(value = "产品编码")
    private String pdCode;

    @ApiModelProperty(value = "产品名称")
    private String pdName;

    @ApiModelProperty(value = "分类编码")
    private String classesCode;
    @ApiModelProperty(value = "二级分类编码")
    private String machiningCode;

    @ApiModelProperty(value = "品种编码")
    private String breedCode;

    @ApiModelProperty(value = "特征编码")
    private String featureCode;
    @ApiModelProperty(value = "净重编码")
    private String weightCode;

    @ApiModelProperty(value = "包装等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "包装编码")
    private String pkgCode;


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
     * 获得gradeCode
     */
    public String getGradeCode() {
        return gradeCode;
    }

    /**
     * 设置gradeCode
     */
    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
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
     * Getter method for property <tt>logiAreaName</tt>.
     *
     * @return property value of logiAreaName
     */
    public String getLogiAreaName() {
        return logiAreaName;
    }

    /**
     * Setter method for property <tt>logiAreaName</tt>.
     *
     * @param logiAreaName value to be assigned to property logiAreaName
     */
    public void setLogiAreaName(String logiAreaName) {
        this.logiAreaName = logiAreaName;
    }

    /**
     * Getter method for property <tt>pdCode</tt>.
     *
     * @return property value of pdCode
     */
    public String getPdCode() {
        return pdCode;
    }

    /**
     * Setter method for property <tt>pdCode</tt>.
     *
     * @param pdCode value to be assigned to property pdCode
     */
    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    /**
     * Getter method for property <tt>pdName</tt>.
     *
     * @return property value of pdName
     */
    public String getPdName() {
        return pdName;
    }

    /**
     * Setter method for property <tt>pdName</tt>.
     *
     * @param pdName value to be assigned to property pdName
     */
    public void setPdName(String pdName) {
        this.pdName = pdName;
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
}
