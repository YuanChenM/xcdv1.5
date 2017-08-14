package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by FjM on 2016/3/11.
 */
@ApiModel(value = "IPD141125Bean", description = "具体通用质量指标列表")
public class IPD141125Bean extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String gnqStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String gnqStdItemName;
    @ApiModelProperty(value = "合格值")
    private String okVal;
    @ApiModelProperty(value = "不合格值")
    private String ngVal;


    public String getGnqStdItemId() {
        return gnqStdItemId;
    }

    public void setGnqStdItemId(String gnqStdItemId) {
        this.gnqStdItemId = gnqStdItemId;
    }

    public String getGnqStdItemName() {
        return gnqStdItemName;
    }

    public void setGnqStdItemName(String gnqStdItemName) {
        this.gnqStdItemName = gnqStdItemName;
    }

    public String getOkVal() {
        return okVal;
    }

    public void setOkVal(String okVal) {
        this.okVal = okVal;
    }

    public String getNgVal() {
        return ngVal;
    }

    public void setNgVal(String ngVal) {
        this.ngVal = ngVal;
    }
}
