package com.msk.bs.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SoOrderDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by ni_shaotang on 2016/10/13.
 */
@ApiModel(value = "IBA2141123Bean", description = "bean")
public class IBA2141123Bean extends BaseEntity {

    @ApiModelProperty(value = "物流区")
    private String districtCode;
    @ApiModelProperty(value = "订单来源")
    private String orderSource;
    @ApiModelProperty(value = "订单类型")
    private String orderType;
    @ApiModelProperty(value = "付款类型")
    private String paymentType;
    @ApiModelProperty(value = "订单总金额")
    private String orderAmount;
    @ApiModelProperty(value = "是否开发票")
    private String invoiceFlg;
    @ApiModelProperty(value = "冻品管家")
    private String sellers;
    @ApiModelProperty(value = "订单员")
    private String orderTaker;
    @ApiModelProperty(value = "订单创建时间")
    private Date orderTime;
    @ApiModelProperty(value = "状态")
    private String orderStatus;
    @ApiModelProperty(value = "买家编号")
    private String buyersCode;
    @ApiModelProperty(value = "买家名称")
    private String buyersName;
    @ApiModelProperty(value = "买家类型")
    private String buyersType;
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    @ApiModelProperty(value = "收货人电话")
    private String receiverTel;
    @ApiModelProperty(value = "收货人省份")
    private String receiverProvince;
    @ApiModelProperty(value = "收货人城市")
    private String receiverCity;
    @ApiModelProperty(value = "收货人街道")
    private String receiverDistrict;
    @ApiModelProperty(value = "详细地址")
    private String receiverAddr;
    @ApiModelProperty(value = "订单详情")
    private List<SoOrderDetail> soOrderDetails;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getInvoiceFlg() {
        return invoiceFlg;
    }

    public void setInvoiceFlg(String invoiceFlg) {
        this.invoiceFlg = invoiceFlg;
    }

    public String getSellers() {
        return sellers;
    }

    public void setSellers(String sellers) {
        this.sellers = sellers;
    }

    public String getOrderTaker() {
        return orderTaker;
    }

    public void setOrderTaker(String orderTaker) {
        this.orderTaker = orderTaker;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBuyersCode() {
        return buyersCode;
    }

    public void setBuyersCode(String buyersCode) {
        this.buyersCode = buyersCode;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(String buyersType) {
        this.buyersType = buyersType;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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

    public List<SoOrderDetail> getSoOrderDetails() {
        return soOrderDetails;
    }

    public void setSoOrderDetails(List<SoOrderDetail> soOrderDetails) {
        this.soOrderDetails = soOrderDetails;
    }
}
