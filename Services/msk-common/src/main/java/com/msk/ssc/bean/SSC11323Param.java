package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ding_guangjian on 2016/8/3.
 */
public class SSC11323Param extends BasePageParam {
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
    /** 发票申请人 */
    private java.lang.String requester;
    /** 开票日期 */
    private java.util.Date invoiceDate;
    /** 发票状态 */
    private java.lang.String status;
    /** 备注 */
    private java.lang.String remark;
    /** 文件ID */
    private java.lang.String uploadFileId;
    /** 文件名 */
    private java.lang.String uploadFileName;
    //时间String格式
    private String ScontractActDateStr;
    private String invoiceDateStr;
    private String requestTimeStr;
    //合同状态
    private String contractStatus;
    //检索条件arr
    private String [] invoiceTypeArr;
    private  String [] statusArr;

    public String[] getInvoiceTypeArr() {
        return invoiceTypeArr;
    }

    public void setInvoiceTypeArr(String[] invoiceTypeArr) {
        this.invoiceTypeArr = invoiceTypeArr;
    }

    public String[] getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(String[] statusArr) {
        this.statusArr = statusArr;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }


    public String getScontractActDateStr() {
        return ScontractActDateStr;
    }

    public void setScontractActDateStr(String scontractActDateStr) {
        ScontractActDateStr = scontractActDateStr;
    }

    public String getInvoiceDateStr() {
        return invoiceDateStr;
    }

    public void setInvoiceDateStr(String invoiceDateStr) {
        this.invoiceDateStr = invoiceDateStr;
    }

    public String getRequestTimeStr() {
        return requestTimeStr;
    }

    public void setRequestTimeStr(String requestTimeStr) {
        this.requestTimeStr = requestTimeStr;
    }


    /**
     * <p>默认构造函数。</p>
     */

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

    public void setContractActDate(Date contractActDate) {
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
