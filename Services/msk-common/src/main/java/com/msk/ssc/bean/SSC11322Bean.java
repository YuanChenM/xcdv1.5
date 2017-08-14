package com.msk.ssc.bean;

import com.msk.core.entity.SscVerificationForContractDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xia_xiaojie on 2016/8/9.
 */
@ApiModel(value = "SSC11322Bean", description = "SscVerificationForContractDetail实体的封装对象")
public class SSC11322Bean extends SscVerificationForContractDetail {
    @ApiModelProperty(value = "核销单明细")
    private List<SSC11322Bean> verificationDetails;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "发货订单ID")
    private Long deliveryId;

    @ApiModelProperty(value = "首付款比例")
    private BigDecimal firstPercent;

    @ApiModelProperty(value = "产品编号")
    private String productCode;

    @ApiModelProperty(value = "货值")
    private BigDecimal productValue;


    public List<SSC11322Bean> getVerificationDetails() {
        return verificationDetails;
    }

    public void setVerificationDetails(List<SSC11322Bean> verificationDetails) {
        this.verificationDetails = verificationDetails;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public BigDecimal getFirstPercent() {
        return firstPercent;
    }

    public void setFirstPercent(BigDecimal firstPercent) {
        this.firstPercent = firstPercent;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

}
