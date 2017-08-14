package com.msk.bs.bean;

import com.msk.common.bean.RsPageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by ni_shaotang on 2016/7/25
 */
@ApiModel(value = "ShopingCarParam", description = "param")
public class ShopingCarParam extends RsPageParam {
    //购物车清单
    @ApiModelProperty(value = "车牌")
    private Long carId;
    @ApiModelProperty(value = "买家ID")
    private String buyersId;
    @ApiModelProperty(value = "买家类型(1:买家 2：管家 3：买手)")
    private int buyersType;
    @ApiModelProperty(value = "卖家编号")
    private String sellerCode;
    @ApiModelProperty(value = "卖家名称")
    private String sellerName;
    @ApiModelProperty(value = "订单来源")
    private int orderSource;
    @ApiModelProperty(value = "买家手机号")
    private String slTel;
    @ApiModelProperty(value = "明细INDEX")
    private Long carDetailId;
    @ApiModelProperty(value = "购物状态0：加入购物车 1：选中 9：失效")
    private int status;
    @ApiModelProperty(value = "CAR_SOURCE")
    private String carSource;
    @ApiModelProperty(value = "移除原因")
    private int removEeason;

    @ApiModelProperty(value = "产品编码")
    private String pdCode;
    @ApiModelProperty(value = "产品名称")
    private String pdName;
    @ApiModelProperty(value = "数量")
    private Integer pdNum;
    @ApiModelProperty(value = "加入购物车时的价格")
    private BigDecimal oldPrice;
    @ApiModelProperty(value = "breedName")
    private String breedName;
    @ApiModelProperty(value = "价盘通道")
    private IBA2141106RsBean[] priceWay;

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public Integer getPdNum() {
        return pdNum;
    }

    public void setPdNum(Integer pdNum) {
        this.pdNum = pdNum;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(String buyersId) {
        this.buyersId = buyersId;
    }

    public int getBuyersType() {
        return buyersType;
    }

    public void setBuyersType(int buyersType) {
        this.buyersType = buyersType;
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

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public Long getCarDetailId() {
        return carDetailId;
    }

    public void setCarDetailId(Long carDetailId) {
        this.carDetailId = carDetailId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCarSource() {
        return carSource;
    }

    public void setCarSource(String carSource) {
        this.carSource = carSource;
    }

    public int getRemovEeason() {
        return removEeason;
    }

    public void setRemovEeason(int removEeason) {
        this.removEeason = removEeason;
    }

    public IBA2141106RsBean[] getPriceWay() {
        return priceWay;
    }

    public void setPriceWay(IBA2141106RsBean[] priceWay) {
        this.priceWay = priceWay;
    }

    public String getSlTel() {
        return slTel;
    }

    public void setSlTel(String slTel) {
        this.slTel = slTel;
    }

}
