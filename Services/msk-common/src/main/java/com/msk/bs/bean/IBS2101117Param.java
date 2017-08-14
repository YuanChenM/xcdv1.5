package com.msk.bs.bean;

import com.hoperun.core.bean.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by ni_shaotang on 2016/8/10.
 */
@ApiModel(value = "IBS2101117Param",description = "param")
public class IBS2101117Param extends BaseParam{

    @ApiModelProperty(value = "买家id")
    private String buyerId;

    @ApiModelProperty(value = "认证状态")
    private String applyStatus;

    @ApiModelProperty(value = "订单创建时间")
    private Date orderCrtTime;

    @ApiModelProperty(value = "有效期天数")
    private Integer days;

    @ApiModelProperty(value = "结束时候")
    private Date endTime;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getOrderCrtTime() {
        return orderCrtTime;
    }

    public void setOrderCrtTime(Date orderCrtTime) {
        this.orderCrtTime = orderCrtTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
