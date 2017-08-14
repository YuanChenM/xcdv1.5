package com.msk.ssc.bean;

import com.msk.core.entity.SscContractPrDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by sun_jiaju on 2016/06/30.
 */
@ApiModel(value = "SSC11304ProductBean", description = "SscContractPrDetail实体的封装对象")
public class SSC11304ProductBean extends SscContractPrDetail {
    @ApiModelProperty(value = "有效箱数")
    private Integer effecBoxNum;

    @ApiModelProperty(value = "产品名称")
    private String pdName;

    @ApiModelProperty(value = "交货计划")
    private String deliveryPlan;

    @ApiModelProperty(value = "已分配箱数")
    private int plannedBoxes;

    @ApiModelProperty(value = "产品重量(吨)")
    private BigDecimal productTonnage;

    @ApiModelProperty(value = "总箱数")
    private int totalBoxes;

    @ApiModelProperty(value = "总重量")
    private BigDecimal totalWeights;

    @ApiModelProperty(value = "总吨数")
    private BigDecimal totalTonnages;

    @ApiModelProperty(value = "总货值")
    private BigDecimal totalValues;

    @ApiModelProperty(value = "首付款总额")
    private BigDecimal totalPayments;


    public Integer getEffecBoxNum() {
        return effecBoxNum;
    }

    public void setEffecBoxNum(Integer effecBoxNum) {
        this.effecBoxNum = effecBoxNum;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }

    public int getPlannedBoxes() {
        return plannedBoxes;
    }

    public void setPlannedBoxes(int plannedBoxes) {
        this.plannedBoxes = plannedBoxes;
    }

    public int getTotalBoxes() {
        return totalBoxes;
    }

    public void setTotalBoxes(int totalBoxes) {
        this.totalBoxes = totalBoxes;
    }

    public BigDecimal getTotalValues() {
        return totalValues;
    }

    public void setTotalValues(BigDecimal totalValues) {
        this.totalValues = totalValues;
    }

    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(BigDecimal totalPayments) {
        this.totalPayments = totalPayments;
    }

    public BigDecimal getProductTonnage() {
        return productTonnage;
    }

    public void setProductTonnage(BigDecimal productTonnage) {
        this.productTonnage = productTonnage;
    }

    public BigDecimal getTotalTonnages() {
        return totalTonnages;
    }

    public void setTotalTonnages(BigDecimal totalTonnages) {
        this.totalTonnages = totalTonnages;
    }

    public BigDecimal getTotalWeights() {
        return totalWeights;
    }

    public void setTotalWeights(BigDecimal totalWeights) {
        this.totalWeights = totalWeights;
    }

}