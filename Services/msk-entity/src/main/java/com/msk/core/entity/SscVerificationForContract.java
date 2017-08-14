/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_verification_for_contract对应的SscVerificationForContract。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscVerificationForContract extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 合同核销单ID */
    private Long verificationId;
    /** 合同核销单号 */
    private String verificationCode;
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 生产商ID */
    private Long supplierId;
    /** 生产商 */
    private String supplierName;
    /** 供应商编码 */
    private String supplierCode;
    /** 采购商ID */
    private Long purchaserId;
    /** 采购商 */
    private String purchaserName;
    /** 采购商编码 */
    private String purchaserCode;
    /** 核销金额 */
    private java.math.BigDecimal verificationAmount;
    /** 核销方法 */
    private Integer verificationMethord;
    /** 核销日期 */
    private java.util.Date verificationDate;
    /** 核销状态 */
    private Integer status;
    /** 备注 */
    private String remark;
    /** 核销处理办法 */
    private String verificationType;
    /** 核销人姓名 */
    private String chargerName;
    /** 核销人ID */
    private String chargerId;
    /** 审核状态 */
    private Integer auditStatus;
    /** 采购商确认ID */
    private String purchaserConfirmId;
    /** 采购商确认名称  */
    private String purchaserConfirmName;
    /** 采购商确认时间 */
    private java.util.Date purchaserConfirmTime;
    /** 采购商确认状态 */
    private Integer purchaserConfirmStatus;
    /** 采购商确认备注 */
    private String purchaserConfirmRemark;
    /** 生产商确认ID */
    private String supplierConfirmId;
    /** 生产商确认名称 */
    private String supplierConfirmName;
    /** 生产商确认时间 */
    private java.util.Date supplierConfirmTime;
    /** 供应商确认状态 */
    private Integer supplierConfirmStatus;
    /** 供应商确认备注 */
    private String supplierConfirmRemark;
    /** 核销人备注 */
    private String verificationRemark;
    /**
     * <p>默认构造函数。</p>
     */
    public SscVerificationForContract() {

    }

    /**
     * <p>合同核销单ID。</p>
     *
     * @return the 合同核销单ID
     */
    public Long getVerificationId() {
        return verificationId;
    }

    /**
     * <p>合同核销单ID。</p>
     *
     * @param verificationId 合同核销单ID。
     */
    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    /**
     * <p>合同核销单号。</p>
     *
     * @return the 合同核销单号
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * <p>合同核销单号。</p>
     *
     * @param verificationCode 合同核销单号。
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * <p>合同ID。</p>
     *
     * @return the 合同ID
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * <p>合同ID。</p>
     *
     * @param contractId 合同ID。
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * <p>合同编号。</p>
     *
     * @return the 合同编号
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * <p>合同编号。</p>
     *
     * @param contractCode 合同编号。
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * <p>合同名称。</p>
     *
     * @return the 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * <p>合同名称。</p>
     *
     * @param contractName 合同名称。
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * <p>生产商ID。</p>
     *
     * @return the 生产商ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>生产商ID。</p>
     *
     * @param supplierId 生产商ID。
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>生产商。</p>
     *
     * @return the 生产商
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>生产商。</p>
     *
     * @param supplierName 生产商。
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @return the 供应商编码
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>供应商编码。</p>
     *
     * @param supplierCode 供应商编码。
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * <p>采购商ID。</p>
     *
     * @return the 采购商ID
     */
    public Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * <p>采购商ID。</p>
     *
     * @param purchaserId 采购商ID。
     */
    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * <p>采购商。</p>
     *
     * @return the 采购商
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * <p>采购商。</p>
     *
     * @param purchaserName 采购商。
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @return the 采购商编码
     */
    public String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * <p>采购商编码。</p>
     *
     * @param purchaserCode 采购商编码。
     */
    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode;
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

    /**
     * <p>核销方法。</p>
     *
     * @return the 核销方法
     */
    public Integer getVerificationMethord() {
        return verificationMethord;
    }

    /**
     * <p>核销方法。</p>
     *
     * @param verificationMethord 核销方法。
     */
    public void setVerificationMethord(Integer verificationMethord) {
        this.verificationMethord = verificationMethord;
    }

    /**
     * <p>核销日期。</p>
     *
     * @return the 核销日期
     */
    public java.util.Date getVerificationDate() {
        return verificationDate;
    }

    /**
     * <p>核销日期。</p>
     *
     * @param verificationDate 核销日期。
     */
    public void setVerificationDate(java.util.Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    /**
     * <p>核销状态。</p>
     *
     * @return the 核销状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <p>核销状态。</p>
     *
     * @param status 核销状态。
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <p>备注。</p>
     *
     * @return the 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>备注。</p>
     *
     * @param remark 备注。
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>核销处理办法。</p>
     *
     * @return the 核销处理办法
     */
    public String getVerificationType() {
        return verificationType;
    }

    /**
     * <p>核销处理办法。</p>
     *
     * @param verificationType 核销处理办法。
     */
    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }

    /**
     * <p>核销人姓名。</p>
     *
     * @return the 核销人姓名
     */
    public String getChargerName() {
        return chargerName;
    }

    /**
     * <p>核销人姓名。</p>
     *
     * @param chargerName 核销人姓名。
     */
    public void setChargerName(String chargerName) {
        this.chargerName = chargerName;
    }

    /**
     * <p>核销人ID。</p>
     *
     * @return the 核销人ID
     */
    public String getChargerId() {
        return chargerId;
    }

    /**
     * <p>核销人ID。</p>
     *
     * @param chargerId 核销人ID。
     */
    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }

    /**
     * <p>审核状态。</p>
     *
     * @return the 审核状态
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * <p>审核状态。</p>
     *
     * @param auditStatus 审核状态。
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * <p>采购商确认ID。</p>
     *
     * @return the 采购商确认ID
     */
    public String getPurchaserConfirmId() {
        return purchaserConfirmId;
    }

    /**
     * <p>采购商确认ID。</p>
     *
     * @param purchaserConfirmId 采购商确认ID。
     */
    public void setPurchaserConfirmId(String purchaserConfirmId) {
        this.purchaserConfirmId = purchaserConfirmId;
    }

    /**
     * <p>采购商确认名称 。</p>
     *
     * @return the 采购商确认名称 
     */
    public String getPurchaserConfirmName() {
        return purchaserConfirmName;
    }

    /**
     * <p>采购商确认名称 。</p>
     *
     * @param purchaserConfirmName 采购商确认名称 。
     */
    public void setPurchaserConfirmName(String purchaserConfirmName) {
        this.purchaserConfirmName = purchaserConfirmName;
    }

    /**
     * <p>采购商确认时间。</p>
     *
     * @return the 采购商确认时间
     */
    public java.util.Date getPurchaserConfirmTime() {
        return purchaserConfirmTime;
    }

    /**
     * <p>采购商确认时间。</p>
     *
     * @param purchaserConfirmTime 采购商确认时间。
     */
    public void setPurchaserConfirmTime(java.util.Date purchaserConfirmTime) {
        this.purchaserConfirmTime = purchaserConfirmTime;
    }

    /**
     * <p>采购商确认状态。</p>
     *
     * @return the 采购商确认状态
     */
    public Integer getPurchaserConfirmStatus() {
        return purchaserConfirmStatus;
    }

    /**
     * <p>采购商确认状态。</p>
     *
     * @param purchaserConfirmStatus 采购商确认状态。
     */
    public void setPurchaserConfirmStatus(Integer purchaserConfirmStatus) {
        this.purchaserConfirmStatus = purchaserConfirmStatus;
    }

    /**
     * <p>采购商确认备注。</p>
     *
     * @return the 采购商确认备注
     */
    public String getPurchaserConfirmRemark() {
        return purchaserConfirmRemark;
    }

    /**
     * <p>采购商确认备注。</p>
     *
     * @param purchaserConfirmRemark 采购商确认备注。
     */
    public void setPurchaserConfirmRemark(String purchaserConfirmRemark) {
        this.purchaserConfirmRemark = purchaserConfirmRemark;
    }

    /**
     * <p>生产商确认ID。</p>
     *
     * @return the 生产商确认ID
     */
    public String getSupplierConfirmId() {
        return supplierConfirmId;
    }

    /**
     * <p>生产商确认ID。</p>
     *
     * @param supplierConfirmId 生产商确认ID。
     */
    public void setSupplierConfirmId(String supplierConfirmId) {
        this.supplierConfirmId = supplierConfirmId;
    }

    /**
     * <p>生产商确认名称。</p>
     *
     * @return the 生产商确认名称
     */
    public String getSupplierConfirmName() {
        return supplierConfirmName;
    }

    /**
     * <p>生产商确认名称。</p>
     *
     * @param supplierConfirmName 生产商确认名称。
     */
    public void setSupplierConfirmName(String supplierConfirmName) {
        this.supplierConfirmName = supplierConfirmName;
    }

    /**
     * <p>生产商确认时间。</p>
     *
     * @return the 生产商确认时间
     */
    public java.util.Date getSupplierConfirmTime() {
        return supplierConfirmTime;
    }

    /**
     * <p>生产商确认时间。</p>
     *
     * @param supplierConfirmTime 生产商确认时间。
     */
    public void setSupplierConfirmTime(java.util.Date supplierConfirmTime) {
        this.supplierConfirmTime = supplierConfirmTime;
    }

    /**
     * <p>供应商确认状态。</p>
     *
     * @return the 供应商确认状态
     */
    public Integer getSupplierConfirmStatus() {
        return supplierConfirmStatus;
    }

    /**
     * <p>供应商确认状态。</p>
     *
     * @param supplierConfirmStatus 供应商确认状态。
     */
    public void setSupplierConfirmStatus(Integer supplierConfirmStatus) {
        this.supplierConfirmStatus = supplierConfirmStatus;
    }

    /**
     * <p>供应商确认备注。</p>
     *
     * @return the 供应商确认备注
     */
    public String getSupplierConfirmRemark() {
        return supplierConfirmRemark;
    }

    /**
     * <p>供应商确认备注。</p>
     *
     * @param supplierConfirmRemark 供应商确认备注。
     */
    public void setSupplierConfirmRemark(String supplierConfirmRemark) {
        this.supplierConfirmRemark = supplierConfirmRemark;
    }

    /**
     * <p>核销人备注。</p>
     *
     * @return the 核销人备注
     */
    public String getVerificationRemark() {
        return verificationRemark;
    }

    /**
     * <p>核销人备注。</p>
     *
     * @param verificationRemark 核销人备注。
     */
    public void setVerificationRemark(String verificationRemark) {
        this.verificationRemark = verificationRemark;
    }

}
