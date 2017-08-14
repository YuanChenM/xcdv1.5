package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by  on 2016/8/9
 */
public class SSC11324ChooseResult extends BaseEntity {
    /** 合同ID */
    private Long contractId;
    /** 合同编号 */
    private String contractCode;
    /** 合同名称 */
    private String contractName;
    /** 合同flag*/
    private String contractFlag;
    /** 合同*/
    private List<String> contractList;

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

    public String getContractFlag() {
        return contractFlag;
    }

    public void setContractFlag(String contractFlag) {
        this.contractFlag = contractFlag;
    }

    public List<String> getContractList() {
        return contractList;
    }

    public void setContractList(List<String> contractList) {
        this.contractList = contractList;
    }

}
