package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ding_guangjian on 2016/8/3.
 */
public class SSC11324Param extends BasePageParam {
    private static final long serialVersionUID = 1L;
    /** 发票申请ID */
    private java.lang.Long invoiceRequestId;
    /** 发票申请编号 */
    private java.lang.String invoiceRequestCode;
    /** 发票项目名称 */
    private java.lang.String invoiceRequestDesc;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 合同名称 */
    private java.lang.String contractName;
    /** 合同类型 */
    private Integer contractStatus;
    /** 一组合同类型 */
    private String[] contractStatusArr;
    /** 生产商ID */
    private java.lang.Long supplierId;
    /** 生产商 */
    private java.lang.String supplierName;
    /** 供应商编码 */
    private java.lang.String supplierCode;
    /** 采购商ID */
    private java.lang.Long purchaserId;
    /** 采购商 */
    private java.lang.String purchaserName;
    /** 采购商编码 */
    private java.lang.String purchaserCode;
    /** 合同生效日期 */
    private java.util.Date contractActDate;
    private String contractActDateStr;
    /** 应付金额 */
    private java.math.BigDecimal contractAmount;
    /** 收款单位 */
    private java.lang.String receiving;
    /** 付款单位 */
    private java.lang.String payer;
    /** 发票金额 */
    private java.math.BigDecimal invoiceAmount;
    /** 发票类型 */
    private java.lang.String invoiceType;
    /** 申请时间 */
    private java.util.Date requestTime;
    private String requestTimeStr;
    /** 发票申请人 */
    private java.lang.String requester;
    /** 开票日期 */
    private java.util.Date invoiceDate;
    private String  invoiceDateStr;
    /** 发票状态 */
    private java.lang.String status;
    /** 发票接收人 */
    private java.lang.String receiver;
    /** 发票接受时间 */
    private java.util.Date receiveDate;
    //发票申请时间Str
    private  String receiveDateStr;
    private java.lang.String invoiceStatus;
    /** 备注 */
    private java.lang.String remark;
    /** 文件ID */
    private java.lang.String uploadFileId;
    /** 文件名 */
    private java.lang.String uploadFileName;
    private java.lang.String buyerTaxpayerCode;
    /** 收货方纳税人识别号 */
    private java.lang.String receiverTaxpayerCode;
    /** 购货方地址 */
    private java.lang.String buyerAddr;
    /** 销售方地址 */
    private java.lang.String sellerAddr;
    /** 购货方电话 */
    private java.lang.String buyerTel;
    /** 销售方电话 */
    private java.lang.String sellerTel;
    /** 购货方开户银行 */
    private java.lang.String buyerBank;
    /** 销售方开户银行 */
    private java.lang.String sellerBank;
    /** 购货方账号 */
    private java.lang.String buyerAccount;
    /** 销售方账号 */
    private java.lang.String sellerAccount;
    /** 审批人 */
    private java.lang.String approvalPerson;
    /** 审批时间 */
    private java.util.Date approvalDate;
    /** 审核人 */
    private java.lang.String auditingPerson;
    /** 审核时间 */
    private java.util.Date auditingDate;
    /** 审批备注 */
    private String approvalRemark;
    /** 审核备注 */
    private String auditingRemark;
    /** 审批ID */
    private  String approvalId;
    /** 审核ID */
    private  String auditingId;

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getAuditingId() {
        return auditingId;
    }

    public void setAuditingId(String auditingId) {
        this.auditingId = auditingId;
    }
    public String getApprovalRemark() {
        return approvalRemark;
    }

    public void setApprovalRemark(String approvalRemark) {
        this.approvalRemark = approvalRemark;
    }

    public String getAuditingRemark() {
        return auditingRemark;
    }

    public void setAuditingRemark(String auditingRemark) {
        this.auditingRemark = auditingRemark;
    }

    public String getApprovalPerson() {
        return approvalPerson;
    }

    public void setApprovalPerson(String approvalPerson) {
        this.approvalPerson = approvalPerson;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getAuditingPerson() {
        return auditingPerson;
    }

    public void setAuditingPerson(String auditingPerson) {
        this.auditingPerson = auditingPerson;
    }

    public Date getAuditingDate() {
        return auditingDate;
    }

    public void setAuditingDate(Date auditingDate) {
        this.auditingDate = auditingDate;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public String getBuyerTaxpayerCode() {
        return buyerTaxpayerCode;
    }

    public void setBuyerTaxpayerCode(String buyerTaxpayerCode) {
        this.buyerTaxpayerCode = buyerTaxpayerCode;
    }

    public String getReceiverTaxpayerCode() {
        return receiverTaxpayerCode;
    }

    public void setReceiverTaxpayerCode(String receiverTaxpayerCode) {
        this.receiverTaxpayerCode = receiverTaxpayerCode;
    }

    public String getSellerAddr() {
        return sellerAddr;
    }

    public void setSellerAddr(String sellerAddr) {
        this.sellerAddr = sellerAddr;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getSellerTel() {
        return sellerTel;
    }

    public void setSellerTel(String sellerTel) {
        this.sellerTel = sellerTel;
    }

    public String getBuyerBank() {
        return buyerBank;
    }

    public void setBuyerBank(String buyerBank) {
        this.buyerBank = buyerBank;
    }

    public String getSellerBank() {
        return sellerBank;
    }

    public void setSellerBank(String sellerBank) {
        this.sellerBank = sellerBank;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
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

    public String getInvoiceDateStr() {
        return invoiceDateStr;
    }

    public void setInvoiceDateStr(String invoiceDateStr) {
        this.invoiceDateStr = invoiceDateStr;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveDateStr() {
        return receiveDateStr;
    }

    public void setReceiveDateStr(String receiveDateStr) {
        this.receiveDateStr = receiveDateStr;
    }

    /**
     * <p>默认构造函数。</p>
     */

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInvoiceRequestId() {
        return invoiceRequestId;
    }

    public void setInvoiceRequestId(Long invoiceRequestId) {
        this.invoiceRequestId = invoiceRequestId;
    }

    public String getInvoiceRequestCode() {
        return invoiceRequestCode;
    }

    public void setInvoiceRequestCode(String invoiceRequestCode) {
        this.invoiceRequestCode = invoiceRequestCode;
    }

    public String getInvoiceRequestDesc() {
        return invoiceRequestDesc;
    }

    public void setInvoiceRequestDesc(String invoiceRequestDesc) {
        this.invoiceRequestDesc = invoiceRequestDesc;
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

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String[] getContractStatusArr() {
        return contractStatusArr;
    }

    public void setContractStatusArr(String[] contractStatusArr) {
        this.contractStatusArr = contractStatusArr;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Long getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPurchaserCode() {
        return purchaserCode;
    }

    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    public Date getContractActDate() {
        return contractActDate;
    }

    public void setContractActDate(java.util.Date contractActDate) {
        this.contractActDate = contractActDate;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getReceiving() {
        return receiving;
    }

    public void setReceiving(String receiving) {
        this.receiving = receiving;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadFileId() {
        return uploadFileId;
    }

    public void setUploadFileId(String uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }




}

