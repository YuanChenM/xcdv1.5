package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141131RsOrgBean.原种种源指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141131RsOrgBean", description = "orgList")
public class IPD141131RsOrgBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String orgStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String orgStdItemName;
    @ApiModelProperty(value = "优良值")
    private String goodVal;
    @ApiModelProperty(value = "一般值")
    private String normalVal;
    @ApiModelProperty(value = "差值")
    private String badVal;
    @ApiModelProperty(value = "产品标准ID")
    private String standardId;

    /**
     * Getter method for property <tt>orgStdItemId</tt>.
     *
     * @return property value of orgStdItemId
     */
    public String getOrgStdItemId() {
        return orgStdItemId;
    }

    /**
     * Setter method for property <tt>orgStdItemId</tt>.
     *
     * @param orgStdItemId value to be assigned to property orgStdItemId
     */
    public void setOrgStdItemId(String orgStdItemId) {
        this.orgStdItemId = orgStdItemId;
    }

    /**
     * Getter method for property <tt>orgStdItemName</tt>.
     *
     * @return property value of orgStdItemName
     */
    public String getOrgStdItemName() {
        return orgStdItemName;
    }

    /**
     * Setter method for property <tt>orgStdItemName</tt>.
     *
     * @param orgStdItemName value to be assigned to property orgStdItemName
     */
    public void setOrgStdItemName(String orgStdItemName) {
        this.orgStdItemName = orgStdItemName;
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
}