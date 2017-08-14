package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SscPaymentRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wu_honglei on 2016/8/17.
 */
public class SSC1130802RsBean extends BaseEntity {
    private Long deliveryId;

    private String  deliveryCode;

    private Long contractId;

    private String contractCode;

    /**发货箱数*/
    private BigDecimal deliveryBox;

    private String  pdCode;

    /*合同订单箱数**/
    private BigDecimal contractBox;

    /**首付款比例*/
    private BigDecimal downPayment;

    /**合同货值*/
    private BigDecimal contractProductValue;

    /**发货货值*/
    private BigDecimal deliveryProductValue;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public BigDecimal getDeliveryBox() {
        return deliveryBox;
    }

    public void setDeliveryBox(BigDecimal deliveryBox) {
        this.deliveryBox = deliveryBox;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public BigDecimal getContractBox() {
        return contractBox;
    }

    public void setContractBox(BigDecimal contractBox) {
        this.contractBox = contractBox;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getContractProductValue() {
        return contractProductValue;
    }

    public void setContractProductValue(BigDecimal contractProductValue) {
        this.contractProductValue = contractProductValue;
    }

    public BigDecimal getDeliveryProductValue() {
        return deliveryProductValue;
    }

    public void setDeliveryProductValue(BigDecimal deliveryProductValue) {
        this.deliveryProductValue = deliveryProductValue;
    }
}
