package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by air on 2016/3/11.
 */
@ApiModel(value = "IPD141126Bean", description = "具体储存运输指标列表")
public class IPD141126Bean extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类指标ID")
    private String tspStdItemId;
    @ApiModelProperty(value = "分类指标")
    private String tspStdItemName;
    @ApiModelProperty(value = "合格值")
    private String okVal;
    @ApiModelProperty(value = "不合格值")
    private String ngVal;


    public String getTspStdItemId() {
        return tspStdItemId;
    }

    public void setTspStdItemId(String tspStdItemId) {
        this.tspStdItemId = tspStdItemId;
    }

    public String getTspStdItemName() {
        return tspStdItemName;
    }

    public void setTspStdItemName(String tspStdItemName) {
        this.tspStdItemName = tspStdItemName;
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
