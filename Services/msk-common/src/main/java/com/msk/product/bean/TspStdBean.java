package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdTspStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@ApiModel(value = "TspStdBean", description = "分类储存运输标准指标")
public class TspStdBean extends PdTspStd {
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

    @ApiModelProperty(value = "0:是目录节点,1:不是目录节点")
    private String isCatalog;
    @ApiModelProperty(value = "分类储存运输标准指标")
    private List<TspStdBean> pdTspStds;

    @ApiModelProperty(value = "等级")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;


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

    public String getIsCatalog() {
        return isCatalog;
    }

    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    public List<TspStdBean> getPdTspStds() {
        return pdTspStds;
    }

    public void setPdTspStds(List<TspStdBean> pdTspStds) {
        this.pdTspStds = pdTspStds;
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
}
