package com.msk.bs.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by whc on 2016/10/12.
 */
@ApiModel(value = "IBS2101137RsBean",
    description = "委托订单详情（订单明细）")
public class IBS2101137RsBean {

    @ApiModelProperty(value = "产品编码")
    private Integer pdCode;
    @ApiModelProperty(value = "产品名称")
    private String pdName;
    @ApiModelProperty(value = "产品价格")
    private BigDecimal pdPrice;
    @ApiModelProperty(value = "产品规格")
    private String normsName;
    @ApiModelProperty(value = "产品净重")
    private String weightName;
    @ApiModelProperty(value = "产品等级")
    private String pdGradeName;
    @ApiModelProperty(value = "订购数量")
    private BigDecimal orderQty;
    @ApiModelProperty(value = "产品总金额=产品单价*产品数量")
    private BigDecimal pdTotalPrice;
    @ApiModelProperty(value = "发货数量")
    private BigDecimal sendQty;
    @ApiModelProperty(value = "取消数量")
    private BigDecimal cancelQty;
    @ApiModelProperty(value = "退货数量")
    private BigDecimal returnQty;
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal rejectionQty;


    public Integer getPdCode() {
        return pdCode;
    }

    public void setPdCode(Integer pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public BigDecimal getPdPrice() {
        return pdPrice;
    }

    public void setPdPrice(BigDecimal pdPrice) {
        this.pdPrice = pdPrice;
    }

    public String getNormsName() {
        return normsName;
    }

    public void setNormsName(String normsName) {
        this.normsName = normsName;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public String getPdGradeName() {
        return pdGradeName;
    }

    public void setPdGradeName(String pdGradeName) {
        this.pdGradeName = pdGradeName;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getPdTotalPrice() {
        return pdTotalPrice;
    }

    public void setPdTotalPrice(BigDecimal pdTotalPrice) {
        this.pdTotalPrice = pdTotalPrice;
    }

    public BigDecimal getSendQty() {
        return sendQty;
    }

    public void setSendQty(BigDecimal sendQty) {
        this.sendQty = sendQty;
    }

    public BigDecimal getCancelQty() {
        return cancelQty;
    }

    public void setCancelQty(BigDecimal cancelQty) {
        this.cancelQty = cancelQty;
    }

    public BigDecimal getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(BigDecimal returnQty) {
        this.returnQty = returnQty;
    }

    public BigDecimal getRejectionQty() {
        return rejectionQty;
    }

    public void setRejectionQty(BigDecimal rejectionQty) {
        this.rejectionQty = rejectionQty;
    }
}
