package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by FjM on 2016/3/11.
 */
@ApiModel(value = "IPD141125RsResult", description = "result")
public class IPD141125RsResult extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类通用质量准指标ID")
    private String gnqStdClaId;
    @ApiModelProperty(value = "分类通用质量标准指标")
    private String gnqStdClaName;
    @ApiModelProperty(value = "具体通用质量指标列表")
    private List<IPD141125Bean> gnqStdSublist ;

    public String getGnqStdClaId() {
        return gnqStdClaId;
    }

    public void setGnqStdClaId(String gnqStdClaId) {
        this.gnqStdClaId = gnqStdClaId;
    }

    public String getGnqStdClaName() {
        return gnqStdClaName;
    }

    public void setGnqStdClaName(String gnqStdClaName) {
        this.gnqStdClaName = gnqStdClaName;
    }

    public List<IPD141125Bean> getGnqStdSublist() {
        return gnqStdSublist;
    }

    public void setGnqStdSublist(List<IPD141125Bean> gnqStdSublist) {
        this.gnqStdSublist = gnqStdSublist;
    }
}
