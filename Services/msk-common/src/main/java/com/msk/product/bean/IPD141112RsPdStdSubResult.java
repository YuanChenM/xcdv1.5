package com.msk.product.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msk.common.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 封装参数
 * IPD141112RsPdStdSubParam.
 *
 * @author xhy
 */
@ApiModel(value = "IPD141112RsPdStdSubResult", description = "result")
@JsonIgnoreProperties(value = {"delFlg", "crtId", "crtTime", "updId", "updTime", "ver", "actId"})
public class IPD141112RsPdStdSubResult extends BaseBean {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "具体质量指标分类ID")
    private String qltStdClaId;
    @ApiModelProperty(value = "具体质量指标分类ID")
    private String qltStdSubId;
    @ApiModelProperty(value = "具体质量指标分类名称")
    private String qltStdSubName;
    @ApiModelProperty(value = "qltStdItemList")
    private List<IPD141112RsPdStdItemResult> qltStdItemList;


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
     * 获得qltStdSubId
     */
    public String getQltStdSubId() {
        return qltStdSubId;
    }

    /**
     * 设置qltStdSubId
     */
    public void setQltStdSubId(String qltStdSubId) {
        this.qltStdSubId = qltStdSubId;
    }

    /**
     * 获得qltStdSubName
     */
    public String getQltStdSubName() {
        return qltStdSubName;
    }

    /**
     * 获得qltStdItemList
     */
    public List<IPD141112RsPdStdItemResult> getQltStdItemList() {
        return qltStdItemList;
    }

    /**
     * 设置qltStdItemList
     */
    public void setQltStdItemList(List<IPD141112RsPdStdItemResult> qltStdItemList) {
        this.qltStdItemList = qltStdItemList;
    }

    /**
     * 设置qltStdSubName
     */
    public void setQltStdSubName(String qltStdSubName) {

        this.qltStdSubName = qltStdSubName;
    }


}