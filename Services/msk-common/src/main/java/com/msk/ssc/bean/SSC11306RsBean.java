package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryOrderBasic;

import java.math.BigDecimal;

/**
 * Created by yang_yang
 */
public class SSC11306RsBean extends SscDeliveryOrderBasic {

    /** 合同名称 */
    private String contractName;
    /** 产品重量 */
    private BigDecimal weight;
    /** 干线运费 */
    private BigDecimal trunkFreight;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getTrunkFreight() {
        return trunkFreight;
    }

    public void setTrunkFreight(BigDecimal trunkFreight) {
        this.trunkFreight = trunkFreight;
    }
}
