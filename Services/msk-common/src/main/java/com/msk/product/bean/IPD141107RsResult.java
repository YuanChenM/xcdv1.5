package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品标准包装档案卡查询返回值
 * IPD141108RsResult
 *
 * @author xhy
 */
@ApiModel(value = "IPD141107RsResult", description = "产品标准包装档案卡查询返回值")
public class IPD141107RsResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类指标ID")
    private String tncStdItemId;

    @ApiModelProperty(value = "分类指标")
    private String tncStdItemName;

    @ApiModelProperty(value = "技术标准项目值1")
    private String tncStdVal1;

    @ApiModelProperty(value = "技术标准项目值2")
    private String tncStdVal2;

    @ApiModelProperty(value = "技术标准项目值3")
    private String tncStdVal3;

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
     * Getter method for property <tt>tncStdVal1</tt>.
     *
     * @return property value of tncStdVal1
     */
    public String getTncStdVal1() {
        return tncStdVal1;
    }

    /**
     * Setter method for property <tt>tncStdVal1</tt>.
     *
     * @param tncStdVal1 value to be assigned to property tncStdVal1
     */
    public void setTncStdVal1(String tncStdVal1) {
        this.tncStdVal1 = tncStdVal1;
    }

    /**
     * Getter method for property <tt>tncStdVal2</tt>.
     *
     * @return property value of tncStdVal2
     */
    public String getTncStdVal2() {
        return tncStdVal2;
    }

    /**
     * Setter method for property <tt>tncStdVal2</tt>.
     *
     * @param tncStdVal2 value to be assigned to property tncStdVal2
     */
    public void setTncStdVal2(String tncStdVal2) {
        this.tncStdVal2 = tncStdVal2;
    }

    /**
     * Getter method for property <tt>tncStdVal3</tt>.
     *
     * @return property value of tncStdVal3
     */
    public String getTncStdVal3() {
        return tncStdVal3;
    }

    /**
     * Setter method for property <tt>tncStdVal3</tt>.
     *
     * @param tncStdVal3 value to be assigned to property tncStdVal3
     */
    public void setTncStdVal3(String tncStdVal3) {
        this.tncStdVal3 = tncStdVal3;
    }
}