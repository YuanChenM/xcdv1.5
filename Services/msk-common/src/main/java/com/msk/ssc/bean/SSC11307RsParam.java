package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;

/**
 * Created by peng_hao on 2016/8/4.
 */
public class SSC11307RsParam extends BasePageParam {

    private static final long serialVersionUID = 1L;

    /** 付款申请单ID */
    private Long paymentRequestId;

    /** 付款申请单编号 */
    private String paymentRequestCode;

    /** 付款申请单编号 */
    private String paymentRequestName;

    /** 发货订单Id */
    private Long deliveryId;

    /** 发货订单编码 */
    private String deliveryCode;

    /** 付款状态 */
    private int payedStatus;

    /** 生产商 */
    private String supplierName;

    /**合同Id*/
    private Long contractId;

    /**合同编号*/
    private String contractCode;

    /**合同名称*/
    private String contractName;

    /** 最近付款日期 yyyy/MM/dd HH:mm:ss格式 */
    private String remitTimeStr;



    /** 一组付款状态*/
    private String[] paymentStatusArr;

    /** 一组审批状态*/
    private String[] auditingStatusArr;

    /** 一组合同状态*/
    private String[] contractStatusArr;

    /** 一组付款类型*/
    private String[] paymentTypeArr;

    /**版本号 排他使用*/
    private Integer ver;


    public int getPayedStatus() {
        return payedStatus;
    }

    public void setPayedStatus(int payedStatus) {
        this.payedStatus = payedStatus;
    }

    public String getRemitTimeStr() {
        return remitTimeStr;
    }

    public void setRemitTimeStr(String remitTimeStr) {
        this.remitTimeStr = remitTimeStr;
    }

    public Long getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(Long paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getPaymentRequestCode() {
        return paymentRequestCode;
    }

    public void setPaymentRequestCode(String paymentRequestCode) {
        this.paymentRequestCode = paymentRequestCode;
    }

    public String getPaymentRequestName() {
        return paymentRequestName;
    }

    public void setPaymentRequestName(String paymentRequestName) {
        this.paymentRequestName = paymentRequestName;
    }

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



    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String[] getPaymentStatusArr() {
        return paymentStatusArr;
    }

    public void setPaymentStatusArr(String[] paymentStatusArr) {
        this.paymentStatusArr = paymentStatusArr;
    }

    public String[] getAuditingStatusArr() {
        return auditingStatusArr;
    }

    public void setAuditingStatusArr(String[] auditingStatusArr) {
        this.auditingStatusArr = auditingStatusArr;
    }

    public String[] getContractStatusArr() {
        return contractStatusArr;
    }

    public void setContractStatusArr(String[] contractStatusArr) {
        this.contractStatusArr = contractStatusArr;
    }

    public String[] getPaymentTypeArr() {
        return paymentTypeArr;
    }

    public void setPaymentTypeArr(String[] paymentTypeArr) {
        this.paymentTypeArr = paymentTypeArr;
    }

    @Override
    public Integer getVer() {
        return ver;
    }

    @Override
    public void setVer(Integer ver) {
        this.ver = ver;
    }
}
