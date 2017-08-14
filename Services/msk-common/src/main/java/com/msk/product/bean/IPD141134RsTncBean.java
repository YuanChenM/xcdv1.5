package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141134RsBean.加工质量标准指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141134RsTncBean", description = "searchList")
public class IPD141134RsTncBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String tncStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String tncStdItemName;
    @ApiModelProperty(value = "优良值")
    private String goodVal;
    @ApiModelProperty(value = "一般值")
    private String normalVal;
    @ApiModelProperty(value = "差值")
    private String badVal;


    /**
     * Getter method for property <tt>tncStdItemId</tt>.
     *
     * @return property value of tncStdItemId
     */
    public String getTncStdItemId() {
        return tncStdItemId;
    }

    /**
     * Setter method for property <tt>tncStdItemId</tt>.
     *
     * @param tncStdItemId value to be assigned to property tncStdItemId
     */
    public void setTncStdItemId(String tncStdItemId) {
        this.tncStdItemId = tncStdItemId;
    }

    /**
     * Getter method for property <tt>tncStdItemName</tt>.
     *
     * @return property value of tncStdItemName
     */
    public String getTncStdItemName() {
        return tncStdItemName;
    }

    /**
     * Setter method for property <tt>tncStdItemName</tt>.
     *
     * @param tncStdItemName value to be assigned to property tncStdItemName
     */
    public void setTncStdItemName(String tncStdItemName) {
        this.tncStdItemName = tncStdItemName;
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