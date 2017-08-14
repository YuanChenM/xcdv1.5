/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_payment_request对应的SscPaymentRequest。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscPaymentRequest extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 付款申请单ID */
    private java.lang.Long paymentRequestId;
    /** 付款申请单号 */
    private java.lang.String paymentRequestCode;
    /** 付款申请单名称 */
    private java.lang.String paymentRequestName;
    /** 请款人 */
    private java.lang.String applicant;
    /** 发货订单ID */
    private java.lang.Long deliveryId;
    /** 发货订单编号 */
    private java.lang.String deliveryCode;
    /** 合同核销单ID */
    private java.lang.Long verificationId;
    /** 合同核销单号 */
    private java.lang.String verificationCode;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 发货批次 */
    private java.lang.String deliveryBatch;
    /** 生产商ID */
    private java.lang.Long supplierId;
    /** 生产商 */
    private java.lang.String supplierName;
    /** 生产商编码 */
    private java.lang.String supplierCode;
    /** 生产商开户银行 */
    private java.lang.String supplierBank;
    /** 生产商银行帐号 */
    private java.lang.String supplierAccount;
    /** 采购商ID */
    private java.lang.Long purchaserId;
    /** 采购商 */
    private java.lang.String purchaserName;
    /** 采购商编码 */
    private java.lang.String purchaserCode;
    /** 付款类型 */
    private java.lang.Integer paymentType;
    /** 付款条件 */
    private java.lang.String paymentTerm;
    /** 付款方式 */
    private java.lang.String paymentMethod;
    /** 账款日期 */
    private java.util.Date accountingDate;
    /** 付款截止日 */
    private java.util.Date paymentDeadline;
    /** 金额 */
    private java.math.BigDecimal amount;
    /** 备注 */
    private java.lang.String remark;
    /** 审批标识 */
    private java.lang.String approvalFlag;
    /** 审批备注 */
    private java.lang.String approvalRemark;
    /** 审批人 */
    private java.lang.String approvalPerson;
    /** 审批时间 */
    private java.util.Date approvalDate;
    /** 审核标识 */
    private java.lang.String auditingFlag;
    /** 审核备注 */
    private java.lang.String auditingRemark;
    /** 审核状态 */
    private java.lang.Integer auditingStatus;
    /** 审核人 */
    private java.lang.String auditingPerson;
    /** 审核时间 */
    private java.util.Date auditingDate;
    /** 付款状态 */
    private java.lang.Integer payedStatus;
    /** 合同总金额 */
    private java.math.BigDecimal contractTotalAmount;
    /** 合同首付款金额 */
    private java.math.BigDecimal contractFirstAmount;
    /** 发货订单总金额 */
    private java.math.BigDecimal deliverTotalAmount;
    /** 本次运输实际发生额 */
    private java.math.BigDecimal transportAmount;
    /** 包材使用费实际发生额 */
    private java.math.BigDecimal packageAmount;
    /** 已付金额 */
    private java.math.BigDecimal paidAmount;
    /** 首付款按比例已支付 */
    private java.math.BigDecimal paidDownPaymentPercentage;
    /** 核销金额 */
    private java.math.BigDecimal verificationAmount;
    /**
     * <p>默认构造函数。</p>
     */
    public SscPaymentRequest() {

    }

    /**
     * <p>付款申请单ID。</p>
     *
     * @return the 付款申请单ID
     */
    public java.lang.Long getPaymentRequestId() {
        return paymentRequestId;
    }

    /**
     * <p>付款申请单ID。</p>
     *
     * @param paymentRequestId 付款申请单ID。
     */
    public void setPaymentRequestId(java.lang.Long paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    /**
     * <p>付款申请单号。</p>
     *
     * @return the 付款申请单号
     */
    public java.lang.String getPaymentRequestCode() {
        return paymentRequestCode;
    }

    /**
     * <p>付款申请单号。</p>
     *
     * @param paymentRequestCode 付款申请单号。
     */
    public void setPaymentRequestCode(java.lang.String paymentRequestCode) {
        this.paymentRequestCode = paymentRequestCode;
    }

    /**
     * <p>付款申请单名称。</p>
     *
     * @return the 付款申请单名称
     */
    public java.lang.String getPaymentRequestName() {
        return paymentRequestName;
    }

    /**
     * <p>付款申请单名称。</p>
     *
     * @param paymentRequestName 付款申请单名称。
     */
    public void setPaymentRequestName(java.lang.String paymentRequestName) {
        this.paymentRequestName = paymentRequestName;
    }

    /**
     * <p>请款人。</p>
     *
     * @return the 请款人
     */
    public java.lang.String getApplicant() {
        return applicant;
    }

    /**
     * <p>请款人。</p>
     *
     * @param applicant 请款人。
     */
    public void setApplicant(java.lang.String applicant) {
        this.applicant = applicant;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @return the 发货订单ID
     */
    public java.lang.Long getDeliveryId() {
        return deliveryId;
    }

    /**
     * <p>发货订单ID。</p>
     *
     * @param deliveryId 发货订单ID。
     */
    public void setDeliveryId(java.lang.Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @return the 发货订单编号
     */
    public java.lang.String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * <p>发货订单编号。</p>
     *
     * @param deliveryCode 发货订单编号。
     */
    public void setDeliveryCode(java.lang.String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * <p>合同核销单ID。</p>
     *
     * @return the 合同核销单ID
     */
    public java.lang.Long getVerificationId() {
        return verificationId;
    }

    /**
     * <p>合同核销单ID。</p>
     *
     * @param verificationId 合同核销单ID。
     */
    public void setVerificationId(java.lang.Long verificationId) {
        this.verificationId = verificationId;
    }

    /**
     * <p>合同核销单号。</p>
     *
     * @return the 合同核销单号
     */
    public java.lang.String getVerificationCode() {
        return verificationCode;
    }

    /**
     * <p>合同核销单号。</p>
     *
     * @param verificationCode 合同核销单号。
     */
    public void setVerificationCode(java.lang.String verificationCode) {
        this.verificationCode = verificationCode;
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
     * <p>发货批次。</p>
     *
     * @return the 发货批次
     */
    public java.lang.String getDeliveryBatch() {
        return deliveryBatch;
    }

    /**
     * <p>发货批次。</p>
     *
     * @param deliveryBatch 发货批次。
     */
    public void setDeliveryBatch(java.lang.String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
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
     * <p>生产商编码。</p>
     *
     * @return the 生产商编码
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>生产商编码。</p>
     *
     * @param supplierCode 生产商编码。
     */
    public void setSupplierCode(java.lang.String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>生产商开户银行。</p>
     *
     * @return the 生产商开户银行
     */
    public java.lang.String getSupplierBank() {
        return supplierBank;
    }

    /**
     * <p>生产商开户银行。</p>
     *
     * @param supplierBank 生产商开户银行。
     */
    public void setSupplierBank(java.lang.String supplierBank) {
        this.supplierBank = supplierBank;
    }

    /**
     * <p>生产商银行帐号。</p>
     *
     * @return the 生产商银行帐号
     */
    public java.lang.String getSupplierAccount() {
        return supplierAccount;
    }

    /**
     * <p>生产商银行帐号。</p>
     *
     * @param supplierAccount 生产商银行帐号。
     */
    public void setSupplierAccount(java.lang.String supplierAccount) {
        this.supplierAccount = supplierAccount;
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
     * <p>付款类型。</p>
     *
     * @return the 付款类型
     */
    public java.lang.Integer getPaymentType() {
        return paymentType;
    }

    /**
     * <p>付款类型。</p>
     *
     * @param paymentType 付款类型。
     */
    public void setPaymentType(java.lang.Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * <p>付款条件。</p>
     *
     * @return the 付款条件
     */
    public java.lang.String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * <p>付款条件。</p>
     *
     * @param paymentTerm 付款条件。
     */
    public void setPaymentTerm(java.lang.String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * <p>付款方式。</p>
     *
     * @return the 付款方式
     */
    public java.lang.String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * <p>付款方式。</p>
     *
     * @param paymentMethod 付款方式。
     */
    public void setPaymentMethod(java.lang.String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * <p>账款日期。</p>
     *
     * @return the 账款日期
     */
    public java.util.Date getAccountingDate() {
        return accountingDate;
    }

    /**
     * <p>账款日期。</p>
     *
     * @param accountingDate 账款日期。
     */
    public void setAccountingDate(java.util.Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    /**
     * <p>付款截止日。</p>
     *
     * @return the 付款截止日
     */
    public java.util.Date getPaymentDeadline() {
        return paymentDeadline;
    }

    /**
     * <p>付款截止日。</p>
     *
     * @param paymentDeadline 付款截止日。
     */
    public void setPaymentDeadline(java.util.Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    /**
     * <p>金额。</p>
     *
     * @return the 金额
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    /**
     * <p>金额。</p>
     *
     * @param amount 金额。
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
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
     * <p>审批标识。</p>
     *
     * @return the 审批标识
     */
    public java.lang.String getApprovalFlag() {
        return approvalFlag;
    }

    /**
     * <p>审批标识。</p>
     *
     * @param approvalFlag 审批标识。
     */
    public void setApprovalFlag(java.lang.String approvalFlag) {
        this.approvalFlag = approvalFlag;
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
     * <p>审核标识。</p>
     *
     * @return the 审核标识
     */
    public java.lang.String getAuditingFlag() {
        return auditingFlag;
    }

    /**
     * <p>审核标识。</p>
     *
     * @param auditingFlag 审核标识。
     */
    public void setAuditingFlag(java.lang.String auditingFlag) {
        this.auditingFlag = auditingFlag;
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
     * <p>审核状态。</p>
     *
     * @return the 审核状态
     */
    public java.lang.Integer getAuditingStatus() {
        return auditingStatus;
    }

    /**
     * <p>审核状态。</p>
     *
     * @param auditingStatus 审核状态。
     */
    public void setAuditingStatus(java.lang.Integer auditingStatus) {
        this.auditingStatus = auditingStatus;
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
     * <p>付款状态。</p>
     *
     * @return the 付款状态
     */
    public java.lang.Integer getPayedStatus() {
        return payedStatus;
    }

    /**
     * <p>付款状态。</p>
     *
     * @param payedStatus 付款状态。
     */
    public void setPayedStatus(java.lang.Integer payedStatus) {
        this.payedStatus = payedStatus;
    }

    /**
     * <p>合同总金额。</p>
     *
     * @return the 合同总金额
     */
    public java.math.BigDecimal getContractTotalAmount() {
        return contractTotalAmount;
    }

    /**
     * <p>合同总金额。</p>
     *
     * @param contractTotalAmount 合同总金额。
     */
    public void setContractTotalAmount(java.math.BigDecimal contractTotalAmount) {
        this.contractTotalAmount = contractTotalAmount;
    }

    /**
     * <p>合同首付款金额。</p>
     *
     * @return the 合同首付款金额
     */
    public java.math.BigDecimal getContractFirstAmount() {
        return contractFirstAmount;
    }

    /**
     * <p>合同首付款金额。</p>
     *
     * @param contractFirstAmount 合同首付款金额。
     */
    public void setContractFirstAmount(java.math.BigDecimal contractFirstAmount) {
        this.contractFirstAmount = contractFirstAmount;
    }

    /**
     * <p>发货订单总金额。</p>
     *
     * @return the 发货订单总金额
     */
    public java.math.BigDecimal getDeliverTotalAmount() {
        return deliverTotalAmount;
    }

    /**
     * <p>发货订单总金额。</p>
     *
     * @param deliverTotalAmount 发货订单总金额。
     */
    public void setDeliverTotalAmount(java.math.BigDecimal deliverTotalAmount) {
        this.deliverTotalAmount = deliverTotalAmount;
    }

    /**
     * <p>本次运输实际发生额。</p>
     *
     * @return the 本次运输实际发生额
     */
    public java.math.BigDecimal getTransportAmount() {
        return transportAmount;
    }

    /**
     * <p>本次运输实际发生额。</p>
     *
     * @param transportAmount 本次运输实际发生额。
     */
    public void setTransportAmount(java.math.BigDecimal transportAmount) {
        this.transportAmount = transportAmount;
    }

    /**
     * <p>包材使用费实际发生额。</p>
     *
     * @return the 包材使用费实际发生额
     */
    public java.math.BigDecimal getPackageAmount() {
        return packageAmount;
    }

    /**
     * <p>包材使用费实际发生额。</p>
     *
     * @param packageAmount 包材使用费实际发生额。
     */
    public void setPackageAmount(java.math.BigDecimal packageAmount) {
        this.packageAmount = packageAmount;
    }

    /**
     * <p>已付金额。</p>
     *
     * @return the 已付金额
     */
    public java.math.BigDecimal getPaidAmount() {
        return paidAmount;
    }

    /**
     * <p>已付金额。</p>
     *
     * @param paidAmount 已付金额。
     */
    public void setPaidAmount(java.math.BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * <p>首付款按比例已支付。</p>
     *
     * @return the 首付款按比例已支付
     */
    public java.math.BigDecimal getPaidDownPaymentPercentage() {
        return paidDownPaymentPercentage;
    }

    /**
     * <p>首付款按比例已支付。</p>
     *
     * @param paidDownPaymentPercentage 首付款按比例已支付。
     */
    public void setPaidDownPaymentPercentage(java.math.BigDecimal paidDownPaymentPercentage) {
        this.paidDownPaymentPercentage = paidDownPaymentPercentage;
    }

    /**
     * <p>核销金额。</p>
     *
     * @return the 核销金额
     */
    public java.math.BigDecimal getVerificationAmount() {
        return verificationAmount;
    }

    /**
     * <p>核销金额。</p>
     *
     * @param verificationAmount 核销金额。
     */
    public void setVerificationAmount(java.math.BigDecimal verificationAmount) {
        this.verificationAmount = verificationAmount;
    }

}
