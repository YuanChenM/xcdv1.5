package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fjm on 2016/3/9.
 */
@ApiModel(value = "IPD141122RsResult", description = "result")
public class IPD141122RsResult extends BaseEntity {
    @ApiModelProperty(value = "分类指标ID")
    private String scientificName;
    @ApiModelProperty(value = "俗名")
    private String localName;
    @ApiModelProperty(value = "销售名")
    private String salesName;
    @ApiModelProperty(value = "原料原产地")
    private String placeOrigin;
    @ApiModelProperty(value = "现产地")
    private String placeCurrent;
    @ApiModelProperty(value = "原料种源")
    private String source;
    @ApiModelProperty(value = "雏类")
    private String childType;
    @ApiModelProperty(value = "饲养方式")
    private String feedType;
    @ApiModelProperty(value = "饲养周期")
    private String feedPeriod;

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public String getPlaceCurrent() {
        return placeCurrent;
    }

    public void setPlaceCurrent(String placeCurrent) {
        this.placeCurrent = placeCurrent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedPeriod() {
        return feedPeriod;
    }

    public void setFeedPeriod(String feedPeriod) {
        this.feedPeriod = feedPeriod;
    }

    /*private List<IPD141122RsParam> list = new ArrayList<IPD141122RsParam>();


    public List<IPD141122RsParam> getList() {
        return list;
    }

    public void setList(List<IPD141122RsParam> list) {
        this.list = list;
    }*/
}
