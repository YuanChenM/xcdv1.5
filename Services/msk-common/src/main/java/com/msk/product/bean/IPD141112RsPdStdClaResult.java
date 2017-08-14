package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 封装参数
 * IPD141112RsPdTncParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141112RsPdStdClaResult", description = "result")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141112RsPdStdClaResult extends BaseBean {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "分类质量标准指标id")
    private String qltStdClaId;
    @ApiModelProperty(value = "分类质量标准指标名称")
    private String qltStdClaName;
    @ApiModelProperty(value = "具体质量指标列表")
    private List<IPD141112RsPdStdSubResult> qltStdSubList;

    /**
     * 获得qltStdClaId
     */
    public String getQltStdClaId() {
        return qltStdClaId;
    }

    /**
     * 设置qltStdClaId
     */
    public void setQltStdClaId(String qltStdClaId) {
        this.qltStdClaId = qltStdClaId;
    }

    /**
     * 获得qltStdClaName
     */
    public String getQltStdClaName() {
        return qltStdClaName;
    }

    /**
     * 设置qltStdClaName
     */
    public void setQltStdClaName(String qltStdClaName) {
        this.qltStdClaName = qltStdClaName;
    }

    /**
     * 获得qltStdSubList
     */
    public List<IPD141112RsPdStdSubResult> getQltStdSubList() {
        return qltStdSubList;
    }

    /**
     * 设置qltStdSubList
     */
    public void setQltStdSubList(List<IPD141112RsPdStdSubResult> qltStdSubList) {
        this.qltStdSubList = qltStdSubList;
    }
}