package com.msk.bs.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by whc on 2016/10/12.
 */
@ApiModel(value = "IBS2101134RsBean",
    description = "委托订单详情")
public class IBS2101134RsBean implements Serializable{

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "需求订单编码")
    private String orderCode;

    @ApiModelProperty(value = "订单来源")
    private String orderSource;

    @ApiModelProperty(value = "订单来源名称")
    private String orderSourceName;

    @ApiModelProperty(value = "订单创建时间")
    private Date orderTime;

    @ApiModelProperty(value = "买家编码")
    private String buyersCode;

    @ApiModelProperty(value = "买家名称")
    private String buyersName;

    @ApiModelProperty(value = "买家类型")
    private String buyerType;

    @ApiModelProperty(value = "卖家编码")
    private String sellerCode;

    @ApiModelProperty(value = "卖家名称")
    private String sellerName;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "删除标志")
    private Integer delFlg;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    @ApiModelProperty(value = "订单类型名称")
    private String orderTypeName;

    @ApiModelProperty(value = "订单区域Code")
    private String districtCode;

    @ApiModelProperty(value = "订单区域名称")
    private String districtName;

    @ApiModelProperty(value = "付款类型")
    private Integer paymentType;

    @ApiModelProperty(value = "付款类型名称")
    private String paymentTypeName;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "支付时间")
    private Date paidTime;

    @ApiModelProperty(value = "直销员")
    private String sellers;

    @ApiModelProperty(value = "发货时间")
    private String orderSendTime;

    @ApiModelProperty(value = "订单员")
    private String orderTaker;

    @ApiModelProperty(value = "是否已开发票")
    private Integer invoiceFlg;

    @ApiModelProperty(value = "评价时间")
    private Date commentTime;

    @ApiModelProperty(value = "收货信息")
    private IBS2101135RsBean receiveInfo;

    @ApiModelProperty(value = "配送信息")
    private IBS2101136RsBean deliveryReq;

    @ApiModelProperty(value = "订单明细")
    private List<IBS2101137RsBean> orderDetail;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
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

    public Integer getInvoiceFlg() {
        return invoiceFlg;
    }

    public void setInvoiceFlg(Integer invoiceFlg) {
        this.invoiceFlg = invoiceFlg;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public IBS2101135RsBean getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(IBS2101135RsBean receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public IBS2101136RsBean getDeliveryReq() {
        return deliveryReq;
    }

    public void setDeliveryReq(IBS2101136RsBean deliveryReq) {
        this.deliveryReq = deliveryReq;
    }

    public List<IBS2101137RsBean> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<IBS2101137RsBean> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public String getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(String orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getOrderSourceName() {
        return orderSourceName;
    }

    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
}
