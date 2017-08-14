package com.msk.ssc.bean;

import com.msk.core.entity.SscDeliveryOrderBasic;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by peng_hao on 2016/7/05.
 */
public class SSC11305RsBean extends SscDeliveryOrderBasic{

    /** 合同名称 */
    private String contractName;

    /**到货日期 yyyy/MM/dd 格式*/
    private String etaStr;

    private List<SSC11305RsBean> ssc11305RsBeanList;

    private BigDecimal weights;


    public List<SSC11305RsBean> getSsc11305RsBeanList() {
        return ssc11305RsBeanList;
    }

    public void setSsc11305RsBeanList(List<SSC11305RsBean> ssc11305RsBeanList) {
        this.ssc11305RsBeanList = ssc11305RsBeanList;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getEtaStr() {
        return etaStr;
    }

    public void setEtaStr(String etaStr) {
        this.etaStr = etaStr;
    }

    public BigDecimal getWeights() {
        return weights;
    }

    public void setWeights(BigDecimal weights) {
        this.weights = weights;
    }
}
