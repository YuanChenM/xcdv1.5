package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * IPD141132RsBean.饲养标准指标信息同步接口
 *
 * @author xhy
 */
@ApiModel(value = "IPD141133RsMctBean", description = "mctList")
public class IPD141133RsMctBean extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String mctStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String mctStdItemName;
    @ApiModelProperty(value = "优良值")
    private String okVal;
    @ApiModelProperty(value = "一般值")
    private String ngVal;
    @ApiModelProperty(value = "等级ID")
    private String levelId;
    @ApiModelProperty(value = "父节点ID")
    private String parentId;
    @ApiModelProperty(value = "isCatalog")
    private String isCatalog;

    /**
     * Getter method for property <tt>mctStdItemId</tt>.
     *
     * @return property value of mctStdItemId
     */
    public String getMctStdItemId() {
        return mctStdItemId;
    }

    /**
     * Setter method for property <tt>mctStdItemId</tt>.
     *
     * @param mctStdItemId value to be assigned to property mctStdItemId
     */
    public void setMctStdItemId(String mctStdItemId) {
        this.mctStdItemId = mctStdItemId;
    }

    /**
     * Getter method for property <tt>mctStdItemName</tt>.
     *
     * @return property value of mctStdItemName
     */
    public String getMctStdItemName() {
        return mctStdItemName;
    }

    /**
     * Setter method for property <tt>mctStdItemName</tt>.
     *
     * @param mctStdItemName value to be assigned to property mctStdItemName
     */
    public void setMctStdItemName(String mctStdItemName) {
        this.mctStdItemName = mctStdItemName;
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


    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIsCatalog() {
        return isCatalog;
    }

    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }
}