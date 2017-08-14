/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_contract_basic对应的SscContractBasic。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscContractBasic extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 合同ID */
    private java.lang.Long contractId;
    /** 合同编号 */
    private java.lang.String contractCode;
    /** 中标确认书ID */
    private java.lang.Long bidId;
    /** 招标项目编号 */
    private java.lang.String bidProjectNo;
    /** 中标类型:0:正常  1:已关联  2:未关联 */
    private java.lang.String bidRelationType;
    /** 合同名称 */
    private java.lang.String contractName;
    /** 合同类型 */
    private java.lang.String contractType;
    /** 供应商企业ID */
    private java.lang.Long supplierId;
    /** 供应商 */
    private java.lang.String supplierName;
    /** 供应商编码 */
    private java.lang.String supplierCode;
    /** 采购商企业ID */
    private java.lang.Long purchaserId;
    /** 采购商 */
    private java.lang.String purchaserName;
    /** 采购商编码 */
    private java.lang.String purchaserCode;
    /** 采购商确认ID */
    private java.lang.String purchaserConfirmId;
    /** 采购商确认名称 */
    private java.lang.String purchaserConfirmName;
    /** 采购商确认时间 */
    private java.util.Date purchaserConfirmTime;
    /** 生产商确认ID */
    private java.lang.String supplierConfirmId;
    /** 生产商确认名称 */
    private java.lang.String supplierConfirmName;
    /** 生产商确认时间 */
    private java.util.Date supplierConfirmTime;
    /** 合同生效日期 */
    private java.util.Date contractActDate;
    /** 合同状态 */
    private java.lang.String contractStatus;
    /** 应付金额 */
    private java.math.BigDecimal contractAmount;
    /** 实际生产开始日期（可以早于合同生效日期） */
    private java.util.Date realProduceStartDate;
    /** 实际生产结束日期（可以晚于最迟交货日期） */
    private java.util.Date realProduceEndDate;
    /**
     * <p>默认构造函数。</p>
     */
    public SscContractBasic() {

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
     * <p>中标确认书ID。</p>
     *
     * @return the 中标确认书ID
     */
    public java.lang.Long getBidId() {
        return bidId;
    }

    /**
     * <p>中标确认书ID。</p>
     *
     * @param bidId 中标确认书ID。
     */
    public void setBidId(java.lang.Long bidId) {
        this.bidId = bidId;
    }

    /**
     * <p>招标项目编号。</p>
     *
     * @return the 招标项目编号
     */
    public java.lang.String getBidProjectNo() {
        return bidProjectNo;
    }

    /**
     * <p>招标项目编号。</p>
     *
     * @param bidProjectNo 招标项目编号。
     */
    public void setBidProjectNo(java.lang.String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }

    /**
     * <p>中标类型:0:正常  1:已关联  2:未关联。</p>
     *
     * @return the 中标类型:0:正常  1:已关联  2:未关联
     */
    public java.lang.String getBidRelationType() {
        return bidRelationType;
    }

    /**
     * <p>中标类型:0:正常  1:已关联  2:未关联。</p>
     *
     * @param bidRelationType 中标类型:0:正常  1:已关联  2:未关联。
     */
    public void setBidRelationType(java.lang.String bidRelationType) {
        this.bidRelationType = bidRelationType;
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
     * <p>合同类型。</p>
     *
     * @return the 合同类型
     */
    public java.lang.String getContractType() {
        return contractType;
    }

    /**
     * <p>合同类型。</p>
     *
     * @param contractType 合同类型。
     */
    public void setContractType(java.lang.String contractType) {
        this.contractType = contractType;
    }

    /**
     * <p>供应商企业ID。</p>
     *
     * @return the 供应商企业ID
     */
    public java.lang.Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>供应商企业ID。</p>
     *
     * @param supplierId 供应商企业ID。
     */
    public void setSupplierId(java.lang.Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>供应商。</p>
     *
     * @return the 供应商
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>供应商。</p>
     *
     * @param supplierName 供应商。
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
     * <p>采购商企业ID。</p>
     *
     * @return the 采购商企业ID
     */
    public java.lang.Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * <p>采购商企业ID。</p>
     *
     * @param purchaserId 采购商企业ID。
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
     * <p>采购商确认ID。</p>
     *
     * @return the 采购商确认ID
     */
    public java.lang.String getPurchaserConfirmId() {
        return purchaserConfirmId;
    }

    /**
     * <p>采购商确认ID。</p>
     *
     * @param purchaserConfirmId 采购商确认ID。
     */
    public void setPurchaserConfirmId(java.lang.String purchaserConfirmId) {
        this.purchaserConfirmId = purchaserConfirmId;
    }

    /**
     * <p>采购商确认名称。</p>
     *
     * @return the 采购商确认名称
     */
    public java.lang.String getPurchaserConfirmName() {
        return purchaserConfirmName;
    }

    /**
     * <p>采购商确认名称。</p>
     *
     * @param purchaserConfirmName 采购商确认名称。
     */
    public void setPurchaserConfirmName(java.lang.String purchaserConfirmName) {
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
     * <p>生产商确认ID。</p>
     *
     * @return the 生产商确认ID
     */
    public java.lang.String getSupplierConfirmId() {
        return supplierConfirmId;
    }

    /**
     * <p>生产商确认ID。</p>
     *
     * @param supplierConfirmId 生产商确认ID。
     */
    public void setSupplierConfirmId(java.lang.String supplierConfirmId) {
        this.supplierConfirmId = supplierConfirmId;
    }

    /**
     * <p>生产商确认名称。</p>
     *
     * @return the 生产商确认名称
     */
    public java.lang.String getSupplierConfirmName() {
        return supplierConfirmName;
    }

    /**
     * <p>生产商确认名称。</p>
     *
     * @param supplierConfirmName 生产商确认名称。
     */
    public void setSupplierConfirmName(java.lang.String supplierConfirmName) {
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
     * <p>合同状态。</p>
     *
     * @return the 合同状态
     */
    public java.lang.String getContractStatus() {
        return contractStatus;
    }

    /**
     * <p>合同状态。</p>
     *
     * @param contractStatus 合同状态。
     */
    public void setContractStatus(java.lang.String contractStatus) {
        this.contractStatus = contractStatus;
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
     * <p>实际生产开始日期（可以早于合同生效日期）。</p>
     *
     * @return the 实际生产开始日期（可以早于合同生效日期）
     */
    public java.util.Date getRealProduceStartDate() {
        return realProduceStartDate;
    }

    /**
     * <p>实际生产开始日期（可以早于合同生效日期）。</p>
     *
     * @param realProduceStartDate 实际生产开始日期（可以早于合同生效日期）。
     */
    public void setRealProduceStartDate(java.util.Date realProduceStartDate) {
        this.realProduceStartDate = realProduceStartDate;
    }

    /**
     * <p>实际生产结束日期（可以晚于最迟交货日期）。</p>
     *
     * @return the 实际生产结束日期（可以晚于最迟交货日期）
     */
    public java.util.Date getRealProduceEndDate() {
        return realProduceEndDate;
    }

    /**
     * <p>实际生产结束日期（可以晚于最迟交货日期）。</p>
     *
     * @param realProduceEndDate 实际生产结束日期（可以晚于最迟交货日期）。
     */
    public void setRealProduceEndDate(java.util.Date realProduceEndDate) {
        this.realProduceEndDate = realProduceEndDate;
    }

}
