package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * IPD141136RsBean.安全指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141136RsSftBean", description = "产品安全指标列表")
public class IPD141136RsSftBean extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String sftStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String sftStdItemName;
    @ApiModelProperty(value = "合格值")
    private String okVal;
    @ApiModelProperty(value = "不合格值")
    private String ngVal;

    /**
     * Getter method for property <tt>sftStdItemId</tt>.
     *
     * @return property value of sftStdItemId
     */
    public String getSftStdItemId() {
        return sftStdItemId;
    }

    /**
     * Setter method for property <tt>sftStdItemId</tt>.
     *
     * @param sftStdItemId value to be assigned to property sftStdItemId
     */
    public void setSftStdItemId(String sftStdItemId) {
        this.sftStdItemId = sftStdItemId;
    }

    /**
     * Getter method for property <tt>sftStdItemName</tt>.
     *
     * @return property value of sftStdItemName
     */
    public String getSftStdItemName() {
        return sftStdItemName;
    }

    /**
     * Setter method for property <tt>sftStdItemName</tt>.
     *
     * @param sftStdItemName value to be assigned to property sftStdItemName
     */
    public void setSftStdItemName(String sftStdItemName) {
        this.sftStdItemName = sftStdItemName;
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