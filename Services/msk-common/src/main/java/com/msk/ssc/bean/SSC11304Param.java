package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by sun_jiaju on 2016/06/30.
 */
@ApiModel(value = "SSC11304Param", description = "合同模块的查询参数")
public class SSC11304Param extends BasePageParam {
    @ApiModelProperty(value = "合同编号")
    private String contractCode;

    @ApiModelProperty(value = "采购商编号")
    private String purchaserCode;

    @ApiModelProperty(value = "产品编号")
    private String pdCode;

    @ApiModelProperty(value = "发货订单ID")
    private Long deliveryId;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "核销单ID")
    private Long verificationId;

    @ApiModelProperty(value = "付款记录ID(资金池一览跳转用)")
    private Long paymentId;

    @ApiModelProperty(value = "付款记录ID(资金池详细跳转用)")
    private Long paymentDetailId;

    @ApiModelProperty(value = "差异单页面跳转ID")
    private Long differId;

    @ApiModelProperty(value = "付款清单ID(用于付款一览跳转)")
    private Long  paymentRequestId;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "合同产品ID")
    private Long productId;

    @ApiModelProperty(value = "中标书ID")
    private Long bidId;

    @ApiModelProperty(value = "付款申请详情页类型")
    private String type;


    public Long getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(Long paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public Long getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(Long paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    private List<SSC11304DeliveryPlanBean> deliveryPlans;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getPurchaserCode() {
        return purchaserCode;
    }

    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public List<SSC11304DeliveryPlanBean> getDeliveryPlans() {
        return deliveryPlans;
    }

    public void setDeliveryPlans(List<SSC11304DeliveryPlanBean> deliveryPlans) {
        this.deliveryPlans = deliveryPlans;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }
    public Long getDifferId() {
        return differId;
    }

    public void setDifferId(Long differId) {
        this.differId = differId;
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}