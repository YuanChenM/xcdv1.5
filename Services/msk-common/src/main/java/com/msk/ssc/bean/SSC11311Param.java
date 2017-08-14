package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

/**
 * Created by xia_xiaojie on 2016/7/4.
 */
public class SSC11311Param extends BasePageParam {
    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 差异单ID */
    private java.lang.Long differId;
    /** 差异单编号 */
    private String differCode;
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 发货订单ID */
    private Long deliveryId;
    /** 发货订单号 */
    private String deliveryCode;
    /** 入库单ID */
    private String deliveryPreIntoId;
    private Long intoStoreId;
    /** 入库单号 */
    private String deliveryPreIntoCode;
    private Long intoStoreCode;
    /** 采购商 */
    private String purchaserName;
    /** 生产商 */
    private String supplierName;
    /** 发货日期 */
    private String etd;
    /** 预计到货日期 */
    private String eta;
    /** 预计实际发货日期 */
    private String deliveryDate;
    /** 实际到货日期 */
    private String arriveDate;
    /** 差异单状态 */
    private String confirmStatus;


    public Long getDifferId() {
        return differId;
    }

    public void setDifferId(Long differId) {
        this.differId = differId;
    }

    public String getDifferCode() {
        return differCode;
    }

    public void setDifferCode(String differCode) {
        this.differCode = differCode;
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

    public String getDeliveryPreIntoId() {
        return deliveryPreIntoId;
    }

    public void setDeliveryPreIntoId(String deliveryPreIntoId) {
        this.deliveryPreIntoId = deliveryPreIntoId;
    }

    public Long getIntoStoreId() {
        return intoStoreId;
    }

    public void setIntoStoreId(Long intoStoreId) {
        this.intoStoreId = intoStoreId;
    }

    public String getDeliveryPreIntoCode() {
        return deliveryPreIntoCode;
    }

    public void setDeliveryPreIntoCode(String deliveryPreIntoCode) {
        this.deliveryPreIntoCode = deliveryPreIntoCode;
    }

    public Long getIntoStoreCode() {
        return intoStoreCode;
    }

    public void setIntoStoreCode(Long intoStoreCode) {
        this.intoStoreCode = intoStoreCode;
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

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

}
