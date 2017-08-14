package com.msk.ssc.bean;

import com.msk.core.entity.SscDifferBasic;

import java.math.BigDecimal;

/**
 * Created by xia_xiaojie on 2016/7/4.
 */
public class SSC11311Bean extends SscDifferBasic {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /*
     * ssc_contract_basic表字段
     */

    private String contractName;    //合同名称

    /*
     * ssc_delivery_order_basic表字段
     */

    private String purchaserName;   //采购商
    private String supplierName;    //生产商

    /*
     * ssc_delivery_pre_into表字段
     */

    private String etd;             //预计发货日期
    private String eta;             //预计到货日期
    private String deliveryDate;    //实际发货日期
    private String arriveDate;      //实际到货日期

    /*
     * 附加字段
     */

    private Long intoStoreId;       //入库单ID
    private Long intoStoreCode;     //入库单号

    private Long verificationId;
    private Long verificationDetailId;

    /** 企业盖章三证ID */
    private java.lang.String businessFileId;
    /** 企业盖章三证名称 */
    private java.lang.String businessFileName;
    /** 动物检疫合格证明ID */
    private java.lang.String quarantineFileId;
    /** 动物检疫合格证明名称 */
    private java.lang.String quarantineFileName;
    /** 出库清单ID */
    private java.lang.String inventoryFileId;
    /** 出库清单名称 */
    private java.lang.String inventoryFileName;
    /** 有效期官方检测报告ID */
    private java.lang.String reportFileId;
    /** 有效期官方检测报告名称 */
    private java.lang.String reportFileName;


    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Long getIntoStoreId() {
        return intoStoreId;
    }

    public void setIntoStoreId(Long intoStoreId) {
        this.intoStoreId = intoStoreId;
    }

    public Long getIntoStoreCode() {
        return intoStoreCode;
    }

    public void setIntoStoreCode(Long intoStoreCode) {
        this.intoStoreCode = intoStoreCode;
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public Long getVerificationDetailId() {
        return verificationDetailId;
    }

    public void setVerificationDetailId(Long verificationDetailId) {
        this.verificationDetailId = verificationDetailId;
    }

    public String getBusinessFileId() {
        return businessFileId;
    }

    public void setBusinessFileId(String businessFileId) {
        this.businessFileId = businessFileId;
    }

    public String getBusinessFileName() {
        return businessFileName;
    }

    public void setBusinessFileName(String businessFileName) {
        this.businessFileName = businessFileName;
    }

    public String getQuarantineFileId() {
        return quarantineFileId;
    }

    public void setQuarantineFileId(String quarantineFileId) {
        this.quarantineFileId = quarantineFileId;
    }

    public String getQuarantineFileName() {
        return quarantineFileName;
    }

    public void setQuarantineFileName(String quarantineFileName) {
        this.quarantineFileName = quarantineFileName;
    }

    public String getInventoryFileId() {
        return inventoryFileId;
    }

    public void setInventoryFileId(String inventoryFileId) {
        this.inventoryFileId = inventoryFileId;
    }

    public String getInventoryFileName() {
        return inventoryFileName;
    }

    public void setInventoryFileName(String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;
    }

    public String getReportFileId() {
        return reportFileId;
    }

    public void setReportFileId(String reportFileId) {
        this.reportFileId = reportFileId;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

}
