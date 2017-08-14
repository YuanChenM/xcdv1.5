package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by sun_jiaju on 2016/6/30.
 */
@ApiModel(value = "SSC11304Result", description = "合同模块接口的返回值")
public class SSC11304Result extends RsPageResult {
    @ApiModelProperty(value = "合同产品ID")
    private Long productId;

    @ApiModelProperty(value = "合同产品")
    private List<SSC11304ProductBean> products;

    @ApiModelProperty(value = "合同包材ID")
    private Long packageId;

    @ApiModelProperty(value = "合同包材")
    private List<SSC11304PackageBean> packages;

    @ApiModelProperty(value = "交货计划ID")
    private Long deliveryPlanId;

    @ApiModelProperty(value = "交货计划")
    private List<SSC11304DeliveryPlanBean> deliveryPlans;

    @ApiModelProperty(value = "有效箱数")
    private Integer effectBoxNum;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同产品ID")
    private Long detailId;

    @ApiModelProperty(value = "到货日期")
    private String evaDate;

    @ApiModelProperty(value = "合同产品详细信息bean")
    private List<SSC11304ProductBean> pdPageResult;

    @ApiModelProperty(value = "合同包材信息列表")
    private List<SSC11304PackageBean> packingPageResult;

    @ApiModelProperty(value = "合同产品交货计划信息bean")
    private List<SSC11304DeliveryPlanBean> dpPageResult;


    public List<SSC11304ProductBean> getPdPageResult() {
        return pdPageResult;
    }

    public void setPdPageResult(List<SSC11304ProductBean> pdPageResult) {
        this.pdPageResult = pdPageResult;
    }

    public List<SSC11304PackageBean> getPackingPageResult() {
        return packingPageResult;
    }

    public void setPackingPageResult(List<SSC11304PackageBean> packingPageResult) {
        this.packingPageResult = packingPageResult;
    }

    public List<SSC11304DeliveryPlanBean> getDpPageResult() {
        return dpPageResult;
    }

    public void setDpPageResult(List<SSC11304DeliveryPlanBean> dpPageResult) {
        this.dpPageResult = dpPageResult;
    }

    public Integer getEffectBoxNum() {
        return effectBoxNum;
    }

    public void setEffectBoxNum(Integer effectBoxNum) {
        this.effectBoxNum = effectBoxNum;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getEvaDate() {
        return evaDate;
    }

    public void setEvaDate(String evaDate) {
        this.evaDate = evaDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<SSC11304ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<SSC11304ProductBean> products) {
        this.products = products;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public List<SSC11304PackageBean> getPackages() {
        return packages;
    }

    public void setPackages(List<SSC11304PackageBean> packages) {
        this.packages = packages;
    }

    public Long getDeliveryPlanId() {
        return deliveryPlanId;
    }

    public void setDeliveryPlanId(Long deliveryPlanId) {
        this.deliveryPlanId = deliveryPlanId;
    }

    public List<SSC11304DeliveryPlanBean> getDeliveryPlans() {
        return deliveryPlans;
    }

    public void setDeliveryPlans(List<SSC11304DeliveryPlanBean> deliveryPlans) {
        this.deliveryPlans = deliveryPlans;
    }

}