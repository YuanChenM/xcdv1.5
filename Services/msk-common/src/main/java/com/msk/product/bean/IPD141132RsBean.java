package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * IPD141132RsBean.饲养标准指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141132RsBean", description = "searchList")
public class IPD141132RsBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品类别编码")
    private String classesCode;
    @ApiModelProperty(value = "产品加工类别编码")
    private String machiningCode;
    @ApiModelProperty(value = "产品种类编码")
    private String breedCode;
    @ApiModelProperty(value = "产品特征编码")
    private String featureCode;
    @ApiModelProperty(value = "产品标准ID")
    private String standardId;
    @ApiModelProperty(value = "fedList")
    private List<IPD141132RsFedBean> fedList;


    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public String getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(String standardId) {
        this.standardId = standardId;
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
     * Getter method for property <tt>fedList</tt>.
     *
     * @return property value of fedList
     */
    public List<IPD141132RsFedBean> getFedList() {
        return fedList;
    }

    /**
     * Setter method for property <tt>fedList</tt>.
     *
     * @param fedList value to be assigned to property fedList
     */
    public void setFedList(List<IPD141132RsFedBean> fedList) {
        this.fedList = fedList;
    }
}