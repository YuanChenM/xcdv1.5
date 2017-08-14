package com.msk.product.bean;


import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141123RsResult.
 *
 * @author FjM
 */
@ApiModel(value = "IPD141123RsResult", description = "result")
public class IPD141123RsResult extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品标准ID")
    private Long standardId;

    @ApiModelProperty(value = "饲养标准项目ID")
    private String orgStdItemId;

    @ApiModelProperty(value = "饲养标准项目名称")
    private String orgStdItemName;

    @ApiModelProperty(value = "优良")
    private String orgGoodVal;

    @ApiModelProperty(value = "一般")
    private String orgNormalVal;

    @ApiModelProperty(value = "差")
    private String orgBadVal;

    /**
     * Getter method for property <tt>standardId</tt>.
     *
     * @return property value of standardId
     */
    public Long getStandardId() {
        return standardId;
    }

    /**
     * Setter method for property <tt>standardId</tt>.
     *
     * @param standardId value to be assigned to property standardId
     */
    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

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
     * Getter method for property <tt>orgGoodVal</tt>.
     *
     * @return property value of orgGoodVal
     */
    public String getOrgGoodVal() {
        return orgGoodVal;
    }

    /**
     * Setter method for property <tt>orgGoodVal</tt>.
     *
     * @param orgGoodVal value to be assigned to property orgGoodVal
     */
    public void setOrgGoodVal(String orgGoodVal) {
        this.orgGoodVal = orgGoodVal;
    }

    /**
     * Getter method for property <tt>orgNormalVal</tt>.
     *
     * @return property value of orgNormalVal
     */
    public String getOrgNormalVal() {
        return orgNormalVal;
    }

    /**
     * Setter method for property <tt>orgNormalVal</tt>.
     *
     * @param orgNormalVal value to be assigned to property orgNormalVal
     */
    public void setOrgNormalVal(String orgNormalVal) {
        this.orgNormalVal = orgNormalVal;
    }

    /**
     * Getter method for property <tt>orgBadVal</tt>.
     *
     * @return property value of orgBadVal
     */
    public String getOrgBadVal() {
        return orgBadVal;
    }


    private String goodVal;

    private String normalVal;

    private String badVal;

    /**
     * Setter method for property <tt>orgBadVal</tt>.
     *
     * @param orgBadVal value to be assigned to property orgBadVal
     */
    public void setOrgBadVal(String orgBadVal) {
        this.orgBadVal = orgBadVal;
    }

    public String getGoodVal() {
        return goodVal;
    }

    public void setGoodVal(String goodVal) {
        this.goodVal = goodVal;
    }

    public String getNormalVal() {
        return normalVal;
    }

    public void setNormalVal(String normalVal) {
        this.normalVal = normalVal;
    }

    public String getBadVal() {
        return badVal;
    }

    public void setBadVal(String badVal) {
        this.badVal = badVal;
    }
}
