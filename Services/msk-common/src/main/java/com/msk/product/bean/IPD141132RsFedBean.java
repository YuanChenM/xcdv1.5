package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141132RsBean.饲养标准指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141132RsFedBean", description = "fedList")
public class IPD141132RsFedBean extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "饲养标准项目ID")
    private String fedStdItemId;

    @ApiModelProperty(value = "饲养标准项目名称")
    private String fedStdItemName;

    @ApiModelProperty(value = "优良")
    private String goodVal;

    @ApiModelProperty(value = "一般")
    private String normalVal;

    @ApiModelProperty(value = "差")
    private String badVal;


    /**
     * Getter method for property <tt>fedStdItemId</tt>.
     *
     * @return property value of fedStdItemId
     */
    public String getFedStdItemId() {
        return fedStdItemId;
    }

    /**
     * Setter method for property <tt>fedStdItemId</tt>.
     *
     * @param fedStdItemId value to be assigned to property fedStdItemId
     */
    public void setFedStdItemId(String fedStdItemId) {
        this.fedStdItemId = fedStdItemId;
    }

    /**
     * Getter method for property <tt>fedStdItemName</tt>.
     *
     * @return property value of fedStdItemName
     */
    public String getFedStdItemName() {
        return fedStdItemName;
    }

    /**
     * Setter method for property <tt>fedStdItemName</tt>.
     *
     * @param fedStdItemName value to be assigned to property fedStdItemName
     */
    public void setFedStdItemName(String fedStdItemName) {
        this.fedStdItemName = fedStdItemName;
    }

    /**
     * Getter method for property <tt>goodVal</tt>.
     *
     * @return property value of goodVal
     */
    public String getGoodVal() {
        return goodVal;
    }

    /**
     * Setter method for property <tt>goodVal</tt>.
     *
     * @param goodVal value to be assigned to property goodVal
     */
    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    /**
     * Getter method for property <tt>normalVal</tt>.
     *
     * @return property value of normalVal
     */
    public String getNormalVal() {
        return normalVal;
    }

    /**
     * Setter method for property <tt>normalVal</tt>.
     *
     * @param normalVal value to be assigned to property normalVal
     */
    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    /**
     * Getter method for property <tt>badVal</tt>.
     *
     * @return property value of badVal
     */
    public String getBadVal() {
        return badVal;
    }

    /**
     * Setter method for property <tt>badVal</tt>.
     *
     * @param badVal value to be assigned to property badVal
     */
    public void setBadVal(String badVal) {
        this.badVal = badVal;
    }
}