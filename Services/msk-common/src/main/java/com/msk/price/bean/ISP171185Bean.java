package com.msk.price.bean;


import com.msk.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by ni_shaotang on 2016/8/26.
 */
@ApiModel(value = "ISP171185Bean", description = "产品信息列表")
public class ISP171185Bean extends BaseEntity {

    @ApiModelProperty(value = "产品编码(10位)")
    private String pdCode;

    @ApiModelProperty(value = "产品等级编码")
    private String gradeCode;

    @ApiModelProperty(value = "物流区编码")
    private String logiAreaCode;

    @ApiModelProperty(value = "价盘周期编号(共5位: 年(2位) + 月(2位) + 半旬号(1位))")
    private String priceCycle;

    @ApiModelProperty(value = "订单等级（10级）")
    private String orderLevel;

    //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang Start
    @ApiModelProperty(value = "订单等级（4级）")
    private String sellWayCode;

    @ApiModelProperty(value = "订单等级名称（4级）")
    private String sellWayName;

    //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang End
    @ApiModelProperty(value = "商品单价（箱价）")
    private BigDecimal pdBoxPrice;

    @ApiModelProperty(value = "商品单价（公斤价）")
    private BigDecimal pdKgPrice;

    //Modif for 参数类型变更 at 2016/09/10 by ni_shaotang Start
    @ApiModelProperty(value = "产品数量(箱数)")
    private Long orderQty;

    @ApiModelProperty(value = "价盘阶梯开始数量")
    private Long startQty;

    @ApiModelProperty(value = "价盘阶梯结束数量")
    private Long endQty;

    //Modif for 参数类型变更 at 2016/09/10 by ni_shaotang End
    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
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

    public String getPriceCycle() {
        return priceCycle;
    }

    public void setPriceCycle(String priceCycle) {
        this.priceCycle = priceCycle;
    }

    public String getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(String orderLevel) {
        this.orderLevel = orderLevel;
    }

    public BigDecimal getPdBoxPrice() {
        return pdBoxPrice;
    }

    public void setPdBoxPrice(BigDecimal pdBoxPrice) {
        this.pdBoxPrice = pdBoxPrice;
    }

    public BigDecimal getPdKgPrice() {
        return pdKgPrice;
    }

    public void setPdKgPrice(BigDecimal pdKgPrice) {
        this.pdKgPrice = pdKgPrice;
    }

    //Modif for 参数类型变更 at 2016/09/10 by ni_shaotang Start
    public Long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }

    public Long getStartQty() {
        return startQty;
    }

    public void setStartQty(Long startQty) {
        this.startQty = startQty;
    }

    public Long getEndQty() {
        return endQty;
    }

    public void setEndQty(Long endQty) {
        this.endQty = endQty;
    }

    //Modif for 参数类型变更 at 2016/09/10 by ni_shaotang End
    //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang Start
    public String getSellWayCode() {
        return sellWayCode;
    }

    public void setSellWayCode(String sellWayCode) {
        this.sellWayCode = sellWayCode;
    }

    public String getSellWayName() {
        return sellWayName;
    }

    public void setSellWayName(String sellWayName) {
        this.sellWayName = sellWayName;
    }

    //Add for 产品价盘信息添加4级订单 at 2016/09/07 by ni_shaotang End
}
