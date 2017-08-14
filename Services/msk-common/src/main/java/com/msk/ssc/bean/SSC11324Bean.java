package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SscInvoiceRequest;

import java.math.BigDecimal;

/**
 * Created by ding_guangjian on 2016/8/3.
 */
public class SSC11324Bean extends SscInvoiceRequest {

    //时间String格式
    private String invoiceDateStr;
    private String contractActDateStr;
    private String requestTimeStr;
    private  String receiveDateStr;
    private String approvalDateStr;
    private String auditingDateStr;
    //文件名称URL字符备份
    private String uploadFileNameStr;
    //已开票数
    private int invoiceRequestCount;
    //合同已开票总金额
    private BigDecimal contractInvoiceAmount;
    //合同申请开票总金额
    private BigDecimal contractInvoiceRequestAmount;
    //剩余开票金额
    private  BigDecimal remainContractInvoiceAmount;
    //已付款金额
    private  BigDecimal amount;
    //状态控制
    private  String statusCtr;
    //付款状态
    private  int paymentType;
    //付款正负值
    private  BigDecimal verificationAmount;

    public String getUploadFileNameStr() {
        return uploadFileNameStr;
    }
    public void setUploadFileNameStr(String uploadFileNameStr) {
        this.uploadFileNameStr = uploadFileNameStr;
    }
    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getVerificationAmount() {
        return verificationAmount;
    }

    public void setVerificationAmount(BigDecimal verificationAmount) {
        this.verificationAmount = verificationAmount;
    }

    public String getApprovalDateStr() {
        return approvalDateStr;
    }

    public void setApprovalDateStr(String approvalDateStr) {
        this.approvalDateStr = approvalDateStr;
    }

    public String getAuditingDateStr() {
        return auditingDateStr;
    }

    public void setAuditingDateStr(String auditingDateStr) {
        this.auditingDateStr = auditingDateStr;
    }

    public String getStatusCtr() {
        return statusCtr;
    }

    public void setStatusCtr(String statusCtr) {
        this.statusCtr = statusCtr;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRemainContractInvoiceAmount() {
        return remainContractInvoiceAmount;
    }

    public void setRemainContractInvoiceAmount(BigDecimal remainContractInvoiceAmount) {
        this.remainContractInvoiceAmount = remainContractInvoiceAmount;
    }

    public BigDecimal getContractInvoiceAmount() {
        return contractInvoiceAmount;
    }

    public void setContractInvoiceAmount(BigDecimal contractInvoiceAmount) {
        this.contractInvoiceAmount = contractInvoiceAmount;
    }

    public String getReceiveDateStr() {
        return receiveDateStr;
    }

    public void setReceiveDateStr(String receiveDateStr) {
        this.receiveDateStr = receiveDateStr;
    }

    public int getInvoiceRequestCount() {
        return invoiceRequestCount;
    }

    public void setInvoiceRequestCount(int invoiceRequestCount) {
        this.invoiceRequestCount = invoiceRequestCount;
    }

    public BigDecimal getContractInvoiceRequestAmount() {
        return contractInvoiceRequestAmount;
    }

    public void setContractInvoiceRequestAmount(BigDecimal contractInvoiceRequestAmount) {
        this.contractInvoiceRequestAmount = contractInvoiceRequestAmount;
    }

    public String getInvoiceDateStr() {
        return invoiceDateStr;
    }

    public void setInvoiceDateStr(String invoiceDateStr) {
        this.invoiceDateStr = invoiceDateStr;
    }

    public String getContractActDateStr() {
        return contractActDateStr;
    }

    public void setContractActDateStr(String contractActDateStr) {
        this.contractActDateStr = contractActDateStr;
    }

    public String getRequestTimeStr() {
        return requestTimeStr;
    }

    public void setRequestTimeStr(String requestTimeStr) {
        this.requestTimeStr = requestTimeStr;
    }
}
