package com.msk.ssc.bean;

import com.hoperun.core.bean.BaseParam;

/**
 * Created by wang_shuai on 2016/7/5.
 */
public class SSC11314ChooseParam extends BaseParam {
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 发货单编号 */
    private String deliveryCode;

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

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }
}
