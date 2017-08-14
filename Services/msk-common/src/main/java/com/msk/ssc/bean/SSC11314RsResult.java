package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryConfirmBasic;

/**
 * Created by wang_shuai on 2016/7/4.
 */
public class SSC11314RsResult extends SscDeliveryConfirmBasic {
    //合同名称
    private String contractName;
    //发货单编号
    private String deliveryCode;
    //物流区名称
    private String lgcsAreaName;

    public String getLgcsAreaName() {
        return lgcsAreaName;
    }

    public void setLgcsAreaName(String lgcsAreaName) {
        this.lgcsAreaName = lgcsAreaName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }
}
