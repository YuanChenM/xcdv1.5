package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by air on 2016/3/11.
 */
@ApiModel(value = "IPD141126RsResult", description = "result")
public class IPD141126RsResult extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类储存运输标准指标ID")
    private String tspStdClaId;
    @ApiModelProperty(value = "分类储存运输标准指标")
    private String tspStdClaName;
    @ApiModelProperty(value = "具体储存运输指标列表")
    private List<IPD141126Bean> tspStdSublist ;

    public String getTspStdClaId() {
        return tspStdClaId;
    }

    public void setTspStdClaId(String tspStdClaId) {
        this.tspStdClaId = tspStdClaId;
    }

    public String getTspStdClaName() {
        return tspStdClaName;
    }

    public void setTspStdClaName(String tspStdClaName) {
        this.tspStdClaName = tspStdClaName;
    }

    public List<IPD141126Bean> getTspStdSublist() {
        return tspStdSublist;
    }

    public void setTspStdSublist(List<IPD141126Bean> tspStdSublist) {
        this.tspStdSublist = tspStdSublist;
    }
}
