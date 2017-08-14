package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/4.
 */
public class SSC11314RsParam extends BasePageParam {
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 发货确认单编号 */
    private String deliveryConfirmCode;
    /** 发货单编号*/
    private String deliveryCode;
    /** 状态*/
    private String deliveryConfirmStatus;
    /** 采购方 */
    private String purchaserName;
    /** 供应商 */
    private String supplierName;
    /** 目标仓库 */
    private String arriveWarehouse;
    /** 目标仓库地址 */
    private String arriveWarehouseAddr;
    /** 一组状态*/
    private String[] delConfirmStatuses;
    /** 发货订单状态*/
    private Integer deliveryStatus;
    /** 到货车次*/
    private String deliveryBatch;
    /** 发货确认单ID */
    private Long deliveryConfirmId;
    /** 合同状态 */
    private List<Integer> contractStatusList;
    //物流区名称
    private String lgcsAreaName;

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
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

    public String getDeliveryConfirmCode() {
        return deliveryConfirmCode;
    }

    public void setDeliveryConfirmCode(String deliveryConfirmCode) {
        this.deliveryConfirmCode = deliveryConfirmCode;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String[] getDelConfirmStatuses() {
        return delConfirmStatuses;
    }

    public void setDelConfirmStatuses(String[] delConfirmStatuses) {
        this.delConfirmStatuses = delConfirmStatuses;
    }

    public String getDeliveryConfirmStatus() {
        return deliveryConfirmStatus;
    }

    public void setDeliveryConfirmStatus(String deliveryConfirmStatus) {
        this.deliveryConfirmStatus = deliveryConfirmStatus;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getArriveWarehouseAddr() {
        return arriveWarehouseAddr;
    }

    public void setArriveWarehouseAddr(String arriveWarehouseAddr) {
        this.arriveWarehouseAddr = arriveWarehouseAddr;
    }

    public String getArriveWarehouse() {
        return arriveWarehouse;
    }

    public void setArriveWarehouse(String arriveWarehouse) {
        this.arriveWarehouse = arriveWarehouse;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

    public List<Integer> getContractStatusList() {
        return contractStatusList;
    }

    public void setContractStatusList(List<Integer> contractStatusList) {
        this.contractStatusList = contractStatusList;
    }

    public Long getDeliveryConfirmId() {
        return deliveryConfirmId;
    }

    public void setDeliveryConfirmId(Long deliveryConfirmId) {
        this.deliveryConfirmId = deliveryConfirmId;
    }
}
