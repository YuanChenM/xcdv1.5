package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.SscIntoBasic;
import com.msk.core.entity.SscIntoDetail;

import java.util.Date;
import java.util.List;

/**
 * Created by liu_yan2 on 2016/7/4.
 */
public class SSC11309RsParam extends BasePageParam {

    /** 入库单编号 */
    private String intoCode;

    /** 合同ID */
    private String contractId;

    /** 合同编号 */
    private String contractCode;

    /** 合同名称 */
    private String contractName;

    /** 生产商 */
    private String supplierName;

    /** 采购商 */
    private String purchaserName;

    /** 入库方式 */
    private String intoType;

    /** 一组入库方式*/
    private String[] intoTypes;

    /** 发货订单编码 */
    private String deliveryCode;

    /** 入库单产品列表 */
    private List<SSC11309Bean> sscIntoBasicList;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String[] getIntoTypes() {
        return intoTypes;
    }

    public void setIntoTypes(String[] intoTypes) {
        this.intoTypes = intoTypes;
    }

    public String getIntoCode() {
        return intoCode;
    }

    public void setIntoCode(String intoCode) {
        this.intoCode = intoCode;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getIntoType() {
        return intoType;
    }

    public void setIntoType(String intoType) {
        this.intoType = intoType;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public List<SSC11309Bean> getSscIntoBasicList() {
        return sscIntoBasicList;
    }

    public void setSscIntoBasicList(List<SSC11309Bean> sscIntoBasicList) {
        this.sscIntoBasicList = sscIntoBasicList;
    }
}
