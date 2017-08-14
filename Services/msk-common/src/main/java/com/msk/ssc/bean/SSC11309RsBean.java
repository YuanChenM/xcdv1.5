package com.msk.ssc.bean;

import com.msk.core.entity.SscIntoBasic;

import java.util.Date;

/**
 * Created by liu_yan2 on 2016/7/4.
 */
public class SSC11309RsBean extends SscIntoBasic {

    /** 合同名称 */
    private String contractName;

    /** 生产商 */
    private String supplierName;

    /** 采购商 */
    private String purchaserName;

    /** 计划 到货日期 yyyy-MM-dd 格式 */
    private String expectArriveDate;

    /** 实际 到货日期 yyyy-MM-dd 格式 */
    private String realArriveDate;

    /** 发货仓库*/
    private String deliveryWarehouse;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }


    public String getExpectArriveDate() {
        return expectArriveDate;
    }

    public void setExpectArriveDate(String expectArriveDate) {
        this.expectArriveDate = expectArriveDate;
    }

    public String getRealArriveDate() {
        return realArriveDate;
    }

    public void setRealArriveDate(String realArriveDate) {
        this.realArriveDate = realArriveDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }
}
