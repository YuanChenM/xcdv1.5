/*
 * 2014/09/23 自动生成 新規作成
 *
 * (c) 江苏润和.
 */
package com.msk.core.entity;
/**
 * <p>表ssc_bid_basic_info对应的SscBidBasicInfo。</p>
 *
 * @author 自动生成
 * @version 1.00
 */
public class SscBidBasicInfo extends BaseEntity{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /** 中标确认书ID */
    private java.lang.Long bidId;
    /** 招标项目编号 */
    private java.lang.String bidProjectNo;
    /** 招标项目名称 */
    private java.lang.String bidProjectName;
    /** 中标厂商企业ID */
    private java.lang.Long supplierId;
    /** 中标厂商名称 */
    private java.lang.String supplierName;
    /** 中标厂商编码 */
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
    /** 开标日期 */
    private java.util.Date bidOpenDate;
    /** 中标确认书状态 */
    private java.lang.String bidStatus;
    /** 开标开始日期 */
    private java.util.Date bidStartDate;
    /** 开标结束日期 */
    private java.util.Date bidEndDate;
    /**
     * <p>默认构造函数。</p>
     */
    public SscBidBasicInfo() {

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
     * <p>招标项目名称。</p>
     *
     * @return the 招标项目名称
     */
    public java.lang.String getBidProjectName() {
        return bidProjectName;
    }

    /**
     * <p>招标项目名称。</p>
     *
     * @param bidProjectName 招标项目名称。
     */
    public void setBidProjectName(java.lang.String bidProjectName) {
        this.bidProjectName = bidProjectName;
    }

    /**
     * <p>中标厂商企业ID。</p>
     *
     * @return the 中标厂商企业ID
     */
    public java.lang.Long getSupplierId() {
        return supplierId;
    }

    /**
     * <p>中标厂商企业ID。</p>
     *
     * @param supplierId 中标厂商企业ID。
     */
    public void setSupplierId(java.lang.Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * <p>中标厂商名称。</p>
     *
     * @return the 中标厂商名称
     */
    public java.lang.String getSupplierName() {
        return supplierName;
    }

    /**
     * <p>中标厂商名称。</p>
     *
     * @param supplierName 中标厂商名称。
     */
    public void setSupplierName(java.lang.String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * <p>中标厂商编码。</p>
     *
     * @return the 中标厂商编码
     */
    public java.lang.String getSupplierCode() {
        return supplierCode;
    }

    /**
     * <p>中标厂商编码。</p>
     *
     * @param supplierCode 中标厂商编码。
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
     * <p>开标日期。</p>
     *
     * @return the 开标日期
     */
    public java.util.Date getBidOpenDate() {
        return bidOpenDate;
    }

    /**
     * <p>开标日期。</p>
     *
     * @param bidOpenDate 开标日期。
     */
    public void setBidOpenDate(java.util.Date bidOpenDate) {
        this.bidOpenDate = bidOpenDate;
    }

    /**
     * <p>中标确认书状态。</p>
     *
     * @return the 中标确认书状态
     */
    public java.lang.String getBidStatus() {
        return bidStatus;
    }

    /**
     * <p>中标确认书状态。</p>
     *
     * @param bidStatus 中标确认书状态。
     */
    public void setBidStatus(java.lang.String bidStatus) {
        this.bidStatus = bidStatus;
    }

    /**
     * <p>开标开始日期。</p>
     *
     * @return the 开标开始日期
     */
    public java.util.Date getBidStartDate() {
        return bidStartDate;
    }

    /**
     * <p>开标开始日期。</p>
     *
     * @param bidStartDate 开标开始日期。
     */
    public void setBidStartDate(java.util.Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    /**
     * <p>开标结束日期。</p>
     *
     * @return the 开标结束日期
     */
    public java.util.Date getBidEndDate() {
        return bidEndDate;
    }

    /**
     * <p>开标结束日期。</p>
     *
     * @param bidEndDate 开标结束日期。
     */
    public void setBidEndDate(java.util.Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

}
