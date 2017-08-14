package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by wu_honglei on 2016/7/4.
 */
@ApiModel(value = "SSC11308RsParam",description = "付款申请入参")
public class SSC11308RsParam extends BasePageParam {

    /**付款申请单号*/
    @ApiModelProperty(value = "付款申请单号")
    private String paymentRequestId;

    /**合同id*/
    @ApiModelProperty(value = "合同id")
    private String contractId;

    /**合同编号*/
    @ApiModelProperty(value = "合同编号")
    private String contractCode;


    /** 发货订单ID */
    @ApiModelProperty(value = "发货订单id")
    private String deliveryId;

    /** 发货订单编号 */
    @ApiModelProperty(value = "发货订单编号")
    private String deliveryCode;

    /** 核销单ID */
    @ApiModelProperty(value = "核销单id")
    private Long verificationId;

    /** 支付类型 */
    @ApiModelProperty(value = "支付类型:0:预付款 1:进度款 2:余款")
    private Integer paymentType;

    @ApiModelProperty(value = "发货订单id集合")
    private List<Long> deliveryIds;

    @ApiModelProperty(value = "合同id集合")
    private List<Long> contractIds;

    @ApiModelProperty(value = "核销id集合")
    private List<Long> verificationIds;

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public List<Long> getDeliveryIds() {
        return deliveryIds;
    }

    public void setDeliveryIds(List<Long> deliveryIds) {
        this.deliveryIds = deliveryIds;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public List<Long> getContractIds() {
        return contractIds;
    }

    public void setContractIds(List<Long> contractIds) {
        this.contractIds = contractIds;
    }

    public List<Long> getVerificationIds() {
        return verificationIds;
    }

    public void setVerificationIds(List<Long> verificationIds) {
        this.verificationIds = verificationIds;
    }
}
