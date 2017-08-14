package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/12.
 */
@ApiModel(value = "IBS2101138RsBean",
    description = "买家信息")
public class IBS2101138RsBean {

    @ApiModelProperty(value = "买家ID")
    private String buyerId;
    @ApiModelProperty(value = "开始日时")
    private String startTime;
    @ApiModelProperty(value = "结束日时")
    private String endTime;
    @ApiModelProperty(value = "认证方式（A：冻品管家发展专属会员买家 B：买家选择专属冻品管家）")
    private String applySide;
    @ApiModelProperty(value = " 申请状态（1：申请中 2：专属会员）")
    private String applyStatus;
    @ApiModelProperty(value = "申请日时")
    private String applyTime;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getApplySide() {
        return applySide;
    }

    public void setApplySide(String applySide) {
        this.applySide = applySide;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}
