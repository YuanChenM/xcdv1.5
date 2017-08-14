/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_invoice_request对应的SscInvoiceRequest。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscInvoiceRequest extends BaseEntity{
    /**
     * 
     */
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
    /** 发票接收人 */
    private java.lang.String receiver;
    /** 发票接受时间 */
    private java.util.Date receiveDate;
    /** 购货方纳税人识别号 */
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
    /** 审批人ID */
    private java.lang.String approvalId;
    /** 审批人 */
    private java.lang.String approvalPerson;
    /** 审批时间 */
    private java.util.Date approvalDate;
    /** 审批备注 */
    private java.lang.String approvalRemark;
    /** 审核人ID */
    private java.lang.String auditingId;
    /** 审核人 */
    private java.lang.String auditingPerson;
    /** 审核时间 */
    private java.util.Date auditingDate;
    /** 审核备注 */
    private java.lang.String auditingRemark;
    /** 备注 */
    private java.lang.String remark;
    /** 文件ID */
    private java.lang.String uploadFileId;
    /** 文件名 */
    private java.lang.String uploadFileName;
    /**
     * <p>默认构造函数。</p>
     */
    public SscInvoiceRequest() {

    }

    /**
     * <p>发票申请ID。</p>
     *
     * @return the 发票申请ID
     */
    public java.lang.Long getInvoiceRequestId() {
        return invoiceRequestId;
    }

    /**
     * <p>发票申请ID。</p>
     *
     * @param invoiceRequestId 发票申请ID。
     */
    public void setInvoiceRequestId(java.lang.Long invoiceRequestId) {
        this.invoiceRequestId = invoiceRequestId;
    }

    /**
     * <p>发票申请编号。</p>
     *
     * @return the 发票申请编号
     */
    public java.lang.String getInvoiceRequestCode() {
        return invoiceRequestCode;
    }

    /**
     * <p>发票申请编号。</p>
     *
     * @param invoiceRequestCode 发票申请编号。
     */
    public void setInvoiceRequestCode(java.lang.String invoiceRequestCode) {
        this.invoiceRequestCode = invoiceRequestCode;
    }

    /**
     * <p>发票项目名称。</p>
     *
     * @return the 发票项目名称
     */
    public java.lang.String getInvoiceRequestDesc() {
        return invoiceRequestDesc;
    }

    /**
     * <p>发票项目名称。</p>
     *
     * @param invoiceRequestDesc 发票项目名称。
     */
    public void setInvoiceRequestDesc(java.lang.String invoiceRequestDesc) {
        this.invoiceRequestDesc = invoiceRequestDesc;
    }

    /**
     * <p>合同ID。</p>
     *
     * @return the 合同ID
     */
    public java.lang.Long getContractId() {
        return contractId;
    }

    /**
     * <p>合同ID。</p>
     *
     * @param contractId 合同ID。
     */
    public void setContractId(java.lang.Long contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public java.lang.String getContractCode() {
        return contractCode;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractCode 合同编号。
     */
    public void setContractCode(java.lang.String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * <p>合同名称。</p>
     *
     * @return the 合同名称
     */
    public java.lang.String getContractName() {
        return contractName;
    }

    /**
     * <p>合同名称。</p>
     *
     * @param contractName 合同名称。
     */
    public void setContractName(java.lang.String contractName) {
        this.contractName = contractName;
    }

    /**
     * <p>生产商ID。</p>
     *
     * @return the 生产商ID
     */
    public java.lang.Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>生产商ID。</p>
     *
     * @param supplierId 生产商ID。
     */
    public void setSupplierId(java.lang.Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>生产商。</p>
     *
     * @return the 生产商
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>生产商。</p>
     *
     * @param supplierName 生产商。
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>采购商ID。</p>
     *
     * @return the 采购商ID
     */
    public java.lang.Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * <p>采购商ID。</p>
     *
     * @param purchaserId 采购商ID。
     */
    public void setPurchaserId(java.lang.Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * <p>采购商。</p>
     *
     * @return the 采购商
     */
    public java.lang.String getPurchaserName() {
        return purchaserName;
    }

    /**
     * <p>采购商。</p>
     *
     * @param purchaserName 采购商。
     */
    public void setPurchaserName(java.lang.String purchaserName) {
        this.purchaserName = purchaserName;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @return the 采购商编码
     */
    public java.lang.String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @param purchaserCode 采购商编码。
     */
    public void setPurchaserCode(java.lang.String purchaserCode) {
        this.purchaserCode = purchaserCode;
    }

    /**
     * <p>合同生效日期。</p>
     *
     * @return the 合同生效日期
     */
    public java.util.Date getContractActDate() {
        return contractActDate;
    }

    /**
     * <p>合同生效日期。</p>
     *
     * @param contractActDate 合同生效日期。
     */
    public void setContractActDate(java.util.Date contractActDate) {
        this.contractActDate = contractActDate;
    }

    /**
     * <p>应付金额。</p>
     *
     * @return the 应付金额
     */
    public java.math.BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * <p>应付金额。</p>
     *
     * @param contractAmount 应付金额。
     */
    public void setContractAmount(java.math.BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * <p>收款单位。</p>
     *
     * @return the 收款单位
     */
    public java.lang.String getReceiving() {
        return receiving;
    }

    /**
     * <p>收款单位。</p>
     *
     * @param receiving 收款单位。
     */
    public void setReceiving(java.lang.String receiving) {
        this.receiving = receiving;
    }

    /**
     * <p>付款单位。</p>
     *
     * @return the 付款单位
     */
    public java.lang.String getPayer() {
        return payer;
    }

    /**
     * <p>付款单位。</p>
     *
     * @param payer 付款单位。
     */
    public void setPayer(java.lang.String payer) {
        this.payer = payer;
    }

    /**
     * <p>发票金额。</p>
     *
     * @return the 发票金额
     */
    public java.math.BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     * <p>发票金额。</p>
     *
     * @param invoiceAmount 发票金额。
     */
    public void setInvoiceAmount(java.math.BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    /**
     * <p>发票类型。</p>
     *
     * @return the 发票类型
     */
    public java.lang.String getInvoiceType() {
        return invoiceType;
    }

    /**
     * <p>发票类型。</p>
     *
     * @param invoiceType 发票类型。
     */
    public void setInvoiceType(java.lang.String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * <p>申请时间。</p>
     *
     * @return the 申请时间
     */
    public java.util.Date getRequestTime() {
        return requestTime;
    }

    /**
     * <p>申请时间。</p>
     *
     * @param requestTime 申请时间。
     */
    public void setRequestTime(java.util.Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * <p>发票申请人。</p>
     *
     * @return the 发票申请人
     */
    public java.lang.String getRequester() {
        return requester;
    }

    /**
     * <p>发票申请人。</p>
     *
     * @param requester 发票申请人。
     */
    public void setRequester(java.lang.String requester) {
        this.requester = requester;
    }

    /**
     * <p>开票日期。</p>
     *
     * @return the 开票日期
     */
    public java.util.Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * <p>开票日期。</p>
     *
     * @param invoiceDate 开票日期。
     */
    public void setInvoiceDate(java.util.Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * <p>发票状态。</p>
     *
     * @return the 发票状态
     */
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * <p>发票状态。</p>
     *
     * @param status 发票状态。
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    /**
     * <p>发票接收人。</p>
     *
     * @return the 发票接收人
     */
    public java.lang.String getReceiver() {
        return receiver;
    }

    /**
     * <p>发票接收人。</p>
     *
     * @param receiver 发票接收人。
     */
    public void setReceiver(java.lang.String receiver) {
        this.receiver = receiver;
    }

    /**
     * <p>发票接受时间。</p>
     *
     * @return the 发票接受时间
     */
    public java.util.Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * <p>发票接受时间。</p>
     *
     * @param receiveDate 发票接受时间。
     */
    public void setReceiveDate(java.util.Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * <p>购货方纳税人识别号。</p>
     *
     * @return the 购货方纳税人识别号
     */
    public java.lang.String getBuyerTaxpayerCode() {
        return buyerTaxpayerCode;
    }

    /**
     * <p>购货方纳税人识别号。</p>
     *
     * @param buyerTaxpayerCode 购货方纳税人识别号。
     */
    public void setBuyerTaxpayerCode(java.lang.String buyerTaxpayerCode) {
        this.buyerTaxpayerCode = buyerTaxpayerCode;
    }

    /**
     * <p>收货方纳税人识别号。</p>
     *
     * @return the 收货方纳税人识别号
     */
    public java.lang.String getReceiverTaxpayerCode() {
        return receiverTaxpayerCode;
    }

    /**
     * <p>收货方纳税人识别号。</p>
     *
     * @param receiverTaxpayerCode 收货方纳税人识别号。
     */
    public void setReceiverTaxpayerCode(java.lang.String receiverTaxpayerCode) {
        this.receiverTaxpayerCode = receiverTaxpayerCode;
    }

    /**
     * <p>购货方地址。</p>
     *
     * @return the 购货方地址
     */
    public java.lang.String getBuyerAddr() {
        return buyerAddr;
    }

    /**
     * <p>购货方地址。</p>
     *
     * @param buyerAddr 购货方地址。
     */
    public void setBuyerAddr(java.lang.String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    /**
     * <p>销售方地址。</p>
     *
     * @return the 销售方地址
     */
    public java.lang.String getSellerAddr() {
        return sellerAddr;
    }

    /**
     * <p>销售方地址。</p>
     *
     * @param sellerAddr 销售方地址。
     */
    public void setSellerAddr(java.lang.String sellerAddr) {
        this.sellerAddr = sellerAddr;
    }

    /**
     * <p>购货方电话。</p>
     *
     * @return the 购货方电话
     */
    public java.lang.String getBuyerTel() {
        return buyerTel;
    }

    /**
     * <p>购货方电话。</p>
     *
     * @param buyerTel 购货方电话。
     */
    public void setBuyerTel(java.lang.String buyerTel) {
        this.buyerTel = buyerTel;
    }

    /**
     * <p>销售方电话。</p>
     *
     * @return the 销售方电话
     */
    public java.lang.String getSellerTel() {
        return sellerTel;
    }

    /**
     * <p>销售方电话。</p>
     *
     * @param sellerTel 销售方电话。
     */
    public void setSellerTel(java.lang.String sellerTel) {
        this.sellerTel = sellerTel;
    }

    /**
     * <p>购货方开户银行。</p>
     *
     * @return the 购货方开户银行
     */
    public java.lang.String getBuyerBank() {
        return buyerBank;
    }

    /**
     * <p>购货方开户银行。</p>
     *
     * @param buyerBank 购货方开户银行。
     */
    public void setBuyerBank(java.lang.String buyerBank) {
        this.buyerBank = buyerBank;
    }

    /**
     * <p>销售方开户银行。</p>
     *
     * @return the 销售方开户银行
     */
    public java.lang.String getSellerBank() {
        return sellerBank;
    }

    /**
     * <p>销售方开户银行。</p>
     *
     * @param sellerBank 销售方开户银行。
     */
    public void setSellerBank(java.lang.String sellerBank) {
        this.sellerBank = sellerBank;
    }

    /**
     * <p>购货方账号。</p>
     *
     * @return the 购货方账号
     */
    public java.lang.String getBuyerAccount() {
        return buyerAccount;
    }

    /**
     * <p>购货方账号。</p>
     *
     * @param buyerAccount 购货方账号。
     */
    public void setBuyerAccount(java.lang.String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    /**
     * <p>销售方账号。</p>
     *
     * @return the 销售方账号
     */
    public java.lang.String getSellerAccount() {
        return sellerAccount;
    }

    /**
     * <p>销售方账号。</p>
     *
     * @param sellerAccount 销售方账号。
     */
    public void setSellerAccount(java.lang.String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    /**
     * <p>审批人ID。</p>
     *
     * @return the 审批人ID
     */
    public java.lang.String getApprovalId() {
        return approvalId;
    }

    /**
     * <p>审批人ID。</p>
     *
     * @param approvalId 审批人ID。
     */
    public void setApprovalId(java.lang.String approvalId) {
        this.approvalId = approvalId;
    }

    /**
     * <p>审批人。</p>
     *
     * @return the 审批人
     */
    public java.lang.String getApprovalPerson() {
        return approvalPerson;
    }

    /**
     * <p>审批人。</p>
     *
     * @param approvalPerson 审批人。
     */
    public void setApprovalPerson(java.lang.String approvalPerson) {
        this.approvalPerson = approvalPerson;
    }

    /**
     * <p>审批时间。</p>
     *
     * @return the 审批时间
     */
    public java.util.Date getApprovalDate() {
        return approvalDate;
    }

    /**
     * <p>审批时间。</p>
     *
     * @param approvalDate 审批时间。
     */
    public void setApprovalDate(java.util.Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * <p>审批备注。</p>
     *
     * @return the 审批备注
     */
    public java.lang.String getApprovalRemark() {
        return approvalRemark;
    }

    /**
     * <p>审批备注。</p>
     *
     * @param approvalRemark 审批备注。
     */
    public void setApprovalRemark(java.lang.String approvalRemark) {
        this.approvalRemark = approvalRemark;
    }

    /**
     * <p>审核人ID。</p>
     *
     * @return the 审核人ID
     */
    public java.lang.String getAuditingId() {
        return auditingId;
    }

    /**
     * <p>审核人ID。</p>
     *
     * @param auditingId 审核人ID。
     */
    public void setAuditingId(java.lang.String auditingId) {
        this.auditingId = auditingId;
    }

    /**
     * <p>审核人。</p>
     *
     * @return the 审核人
     */
    public java.lang.String getAuditingPerson() {
        return auditingPerson;
    }

    /**
     * <p>审核人。</p>
     *
     * @param auditingPerson 审核人。
     */
    public void setAuditingPerson(java.lang.String auditingPerson) {
        this.auditingPerson = auditingPerson;
    }

    /**
     * <p>审核时间。</p>
     *
     * @return the 审核时间
     */
    public java.util.Date getAuditingDate() {
        return auditingDate;
    }

    /**
     * <p>审核时间。</p>
     *
     * @param auditingDate 审核时间。
     */
    public void setAuditingDate(java.util.Date auditingDate) {
        this.auditingDate = auditingDate;
    }

    /**
     * <p>审核备注。</p>
     *
     * @return the 审核备注
     */
    public java.lang.String getAuditingRemark() {
        return auditingRemark;
    }

    /**
     * <p>审核备注。</p>
     *
     * @param auditingRemark 审核备注。
     */
    public void setAuditingRemark(java.lang.String auditingRemark) {
        this.auditingRemark = auditingRemark;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * <p>文件ID。</p>
     *
     * @return the 文件ID
     */
    public java.lang.String getUploadFileId() {
        return uploadFileId;
    }

    /**
     * <p>文件ID。</p>
     *
     * @param uploadFileId 文件ID。
     */
    public void setUploadFileId(java.lang.String uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    /**
     * <p>文件名。</p>
     *
     * @return the 文件名
     */
    public java.lang.String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * <p>文件名。</p>
     *
     * @param uploadFileName 文件名。
     */
    public void setUploadFileName(java.lang.String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

}
