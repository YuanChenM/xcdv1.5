package com.msk.price.bean;

import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by yang_yang on 2016/6/8
 */
@ApiModel(value = "ISP171183Bean", description = "价格列表")
public class ISP171183Bean extends BaseEntity{

    @ApiModelProperty(value = "商品ID（一级分类2位+二级分类1位+品种2位+特征2位+净重2位+产品等级1位）")
    private String productId;

    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "物流区编码")
    private String logiAreaCode;

    @ApiModelProperty(value = "价盘周期编码")
    private String pricePeriod;

    @ApiModelProperty(value = "产品二级分类编码")
    private String orderLevel;

    @ApiModelProperty(value = "商品价格（公斤价）")
    private String priceOfKg;

    @ApiModelProperty(value = "商品价格（箱价）")
    private String priceOfBox;

    @ApiModelProperty(value = "价格列表")
    private List<ISP171183Bean> pricelist;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getLogiAreaCode() {
        return logiAreaCode;
    }

    public void setLogiAreaCode(String logiAreaCode) {
        this.logiAreaCode = logiAreaCode;
    }

    public String getPricePeriod() {
        return pricePeriod;
    }

    public void setPricePeriod(String pricePeriod) {
        this.pricePeriod = pricePeriod;
    }

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public String getPriceOfKg() {
        return priceOfKg;
    }

    public void setPriceOfKg(String priceOfKg) {
        this.priceOfKg = priceOfKg;
    }

    public String getPriceOfBox() {
        return priceOfBox;
    }

    public void setPriceOfBox(String priceOfBox) {
        this.priceOfBox = priceOfBox;
    }

    public List<ISP171183Bean> getPricelist() {
        return pricelist;
    }

    public void setPricelist(List<ISP171183Bean> pricelist) {
        this.pricelist = pricelist;
    }

}
