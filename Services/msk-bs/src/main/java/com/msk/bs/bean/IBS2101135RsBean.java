package com.msk.bs.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.msk.core.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/12.
 */
@ApiModel(value = "IBS2101135RsBean",
    description = "委托订单详情（收货信息）")
public class IBS2101135RsBean {

    @ApiModelProperty(value = "收货人名称")
    private String receiverName;
    @ApiModelProperty(value = "收货人QQ号")
    private String receiverQQ;
    @ApiModelProperty(value = "收货人微信号")
    private String receiverWeixin;
    @ApiModelProperty(value = "收货人电话")
    private String receiverTel;
    @ApiModelProperty(value = "收货地址省份")
    private String receiverProvince;
    @ApiModelProperty(value = "收货地址市")
    private String receiverCity;
    @ApiModelProperty(value = "收货地址区")
    private String receiverDistrict;
    @ApiModelProperty(value = "收货人详细地址")
    private String receiverAddr;
    @ApiModelProperty(value = "习惯正常收货时间段")
    private String receiveTime;
    @ApiModelProperty(value = "习惯收货最早时间要求")
    private String receiveEarliest;
    @ApiModelProperty(value = "习惯收货最晚时间要求")
    private String receiveLast;
    @ApiModelProperty(value = "距离门店最近停车距离(米)")
    private BigDecimal parkingDistance;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "备注2")
    private String remark2;
    @ApiModelProperty(value = "备注3")
    private String remark3;
    @ApiModelProperty(value = "备注4")
    private String remark4;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverQQ() {
        return receiverQQ;
    }

    public void setReceiverQQ(String receiverQQ) {
        this.receiverQQ = receiverQQ;
    }

    public String getReceiverWeixin() {
        return receiverWeixin;
    }

    public void setReceiverWeixin(String receiverWeixin) {
        this.receiverWeixin = receiverWeixin;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveEarliest() {
        return receiveEarliest;
    }

    public void setReceiveEarliest(String receiveEarliest) {
        this.receiveEarliest = receiveEarliest;
    }

    public String getReceiveLast() {
        return receiveLast;
    }

    public void setReceiveLast(String receiveLast) {
        this.receiveLast = receiveLast;
    }

    public BigDecimal getParkingDistance() {
        return parkingDistance;
    }

    public void setParkingDistance(BigDecimal parkingDistance) {
        this.parkingDistance = parkingDistance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }
}
