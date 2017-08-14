package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141137RsTspBean.储存运输指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141137RsTspBean", description = "tspList")
public class IPD141137RsTspBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String tspStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String tspStdItemName;
    @ApiModelProperty(value = "合格值")
    private String okVal;
    @ApiModelProperty(value = "不合格值")
    private String ngVal;

    /**
     * Getter method for property <tt>tspStdItemId</tt>.
     *
     * @return property value of tspStdItemId
     */
    public String getTspStdItemId() {
        return tspStdItemId;
    }

    /**
     * Setter method for property <tt>tspStdItemId</tt>.
     *
     * @param tspStdItemId value to be assigned to property tspStdItemId
     */
    public void setTspStdItemId(String tspStdItemId) {
        this.tspStdItemId = tspStdItemId;
    }

    /**
     * Getter method for property <tt>tspStdItemName</tt>.
     *
     * @return property value of tspStdItemName
     */
    public String getTspStdItemName() {
        return tspStdItemName;
    }

    /**
     * Setter method for property <tt>tspStdItemName</tt>.
     *
     * @param tspStdItemName value to be assigned to property tspStdItemName
     */
    public void setTspStdItemName(String tspStdItemName) {
        this.tspStdItemName = tspStdItemName;
    }

    /**
     * Getter method for property <tt>okVal</tt>.
     *
     * @return property value of okVal
     */
    public String getOkVal() {
        return okVal;
    }

    /**
     * Setter method for property <tt>okVal</tt>.
     *
     * @param okVal value to be assigned to property okVal
     */
    public void setOkVal(String okVal) {
        this.okVal = okVal;
    }

    /**
     * Getter method for property <tt>ngVal</tt>.
     *
     * @return property value of ngVal
     */
    public String getNgVal() {
        return ngVal;
    }

    /**
     * Setter method for property <tt>ngVal</tt>.
     *
     * @param ngVal value to be assigned to property ngVal
     */
    public void setNgVal(String ngVal) {
        this.ngVal = ngVal;
    }
}