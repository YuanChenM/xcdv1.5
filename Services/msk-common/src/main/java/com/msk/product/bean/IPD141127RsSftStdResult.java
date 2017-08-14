package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 封装参数
 * IPD141112RsPdTncParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141127RsSftStdResult", description = "具体质量指标列表")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141127RsSftStdResult extends BaseBean {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类质量标准指标id")
    private String sftStdSubId;
    @ApiModelProperty(value = "分类质量标准指标名称")
    private String sftStdSubName;
    @ApiModelProperty(value = "具体质量指标分类ID")
    private String sftStdExcVal;
    @ApiModelProperty(value = "具体质量指标分类ID")
    private String sftStdSuitVal;
    @ApiModelProperty(value = "具体质量指标分类名称")
    private String sftStdUnqualVal;


    /**
     * Getter method for property <tt>sftStdSubId</tt>.
     *
     * @return property value of sftStdSubId
     */
    public String getSftStdSubId() {
        return sftStdSubId;
    }

    /**
     * Setter method for property <tt>sftStdSubId</tt>.
     *
     * @param sftStdSubId value to be assigned to property sftStdSubId
     */
    public void setSftStdSubId(String sftStdSubId) {
        this.sftStdSubId = sftStdSubId;
    }

    /**
     * Getter method for property <tt>sftStdSubName</tt>.
     *
     * @return property value of sftStdSubName
     */
    public String getSftStdSubName() {
        return sftStdSubName;
    }

    /**
     * Setter method for property <tt>sftStdSubName</tt>.
     *
     * @param sftStdSubName value to be assigned to property sftStdSubName
     */
    public void setSftStdSubName(String sftStdSubName) {
        this.sftStdSubName = sftStdSubName;
    }

    /**
     * Getter method for property <tt>sftStdExcVal</tt>.
     *
     * @return property value of sftStdExcVal
     */
    public String getSftStdExcVal() {
        return sftStdExcVal;
    }

    /**
     * Setter method for property <tt>sftStdExcVal</tt>.
     *
     * @param sftStdExcVal value to be assigned to property sftStdExcVal
     */
    public void setSftStdExcVal(String sftStdExcVal) {
        this.sftStdExcVal = sftStdExcVal;
    }

    /**
     * Getter method for property <tt>sftStdSuitVal</tt>.
     *
     * @return property value of sftStdSuitVal
     */
    public String getSftStdSuitVal() {
        return sftStdSuitVal;
    }

    /**
     * Setter method for property <tt>sftStdSuitVal</tt>.
     *
     * @param sftStdSuitVal value to be assigned to property sftStdSuitVal
     */
    public void setSftStdSuitVal(String sftStdSuitVal) {
        this.sftStdSuitVal = sftStdSuitVal;
    }

    /**
     * Getter method for property <tt>sftStdUnqualVal</tt>.
     *
     * @return property value of sftStdUnqualVal
     */
    public String getSftStdUnqualVal() {
        return sftStdUnqualVal;
    }

    /**
     * Setter method for property <tt>sftStdUnqualVal</tt>.
     *
     * @param sftStdUnqualVal value to be assigned to property sftStdUnqualVal
     */
    public void setSftStdUnqualVal(String sftStdUnqualVal) {
        this.sftStdUnqualVal = sftStdUnqualVal;
    }
}