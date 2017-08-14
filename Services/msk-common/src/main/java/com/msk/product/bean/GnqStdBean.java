package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.PdGnqStd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yang_chunyan on 2016/4/29.
 */
@ApiModel(value = "GnqStdBean", description = "具体通用质量指标列表")
public class GnqStdBean extends PdGnqStd {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通用质量标准项目ID")
    private String gnqStdItemId;

    @ApiModelProperty(value = "通用质量标准项目名称")
    private String gnqStdItemName;

    @ApiModelProperty(value = "保存优良值")
    private String okVal;

    @ApiModelProperty(value = "保存差值")
    private String ngVal;

    @ApiModelProperty(value = "0:是目录节点,1:不是目录节点")
    private String isCatalog;

    @ApiModelProperty(value = "pdGnqStds")
    private List<GnqStdBean> pdGnqStds;

    @ApiModelProperty(value = "等级")
    private String levelId;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;


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

    public String getIsCatalog() {
        return isCatalog;
    }

    public void setIsCatalog(String isCatalog) {
        this.isCatalog = isCatalog;
    }

    public List<GnqStdBean> getPdGnqStds() {
        return pdGnqStds;
    }

    public void setPdGnqStds(List<GnqStdBean> pdGnqStds) {
        this.pdGnqStds = pdGnqStds;
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
