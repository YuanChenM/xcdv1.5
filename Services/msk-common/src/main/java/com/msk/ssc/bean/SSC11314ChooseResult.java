package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by wang_shuai on 2016/7/4.
 */
public class SSC11314ChooseResult extends BaseEntity {
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 发货单编号 */
    private String deliveryCode;
    /** 合同flag*/
    private String contractFlag;
    /** 合同flag*/
    private String deliveryFlag;
    /** 合同*/
    private List<String> contractList;
    /** 发货单*/
    private List<String> deliveryList;

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

    public String getContractFlag() {
        return contractFlag;
    }

    public void setContractFlag(String contractFlag) {
        this.contractFlag = contractFlag;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public List<String> getContractList() {
        return contractList;
    }

    public void setContractList(List<String> contractList) {
        this.contractList = contractList;
    }

    public List<String> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<String> deliveryList) {
        this.deliveryList = deliveryList;
    }
}
