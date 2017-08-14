package com.msk.ssc.bean;

import com.msk.core.entity.SscPaymentRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wu_honglei on 2016/8/4.
 */
public class SSC11308RsBean extends SscPaymentRequest{

    /**合同列表*/
    private List<SSC11303RsBean> contractList;

    /**类型*/
    private Integer type;

    /**审核标志 0:审批/1:审核*/
    private Integer flag;

    /**发货列表*/
    private  List<SSC11305RsBean> deliverList;

    /**账期日期*/
    private String accountingDateStr;

    private String contractName;

    /**核销列表*/
    private List<SSC11321RsBean> verificationList;

    /**关联合同*/
    private String isRelate;

    /**合计*/
    private BigDecimal totalApplyAmount;

    /**可申请金额*/
    private BigDecimal allowApplyAmount;

    /**核销金额:true:正数.false:负数*/
    private Boolean moneyFlag;

    public List<SSC11303RsBean> getContractList() {
        return contractList;
    }

    public void setContractList(List<SSC11303RsBean> contractList) {
        this.contractList = contractList;
    }

    public List<SSC11305RsBean> getDeliverList() {
        return deliverList;
    }

    public void setDeliverList(List<SSC11305RsBean> deliverList) {
        this.deliverList = deliverList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccountingDateStr() {
        return accountingDateStr;
    }

    public void setAccountingDateStr(String accountingDateStr) {
        this.accountingDateStr = accountingDateStr;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public List<SSC11321RsBean> getVerificationList() {
        return verificationList;
    }

    public void setVerificationList(List<SSC11321RsBean> verificationList) {
        this.verificationList = verificationList;
    }

    public String getIsRelate() {
        return isRelate;
    }

    public void setIsRelate(String isRelate) {
        this.isRelate = isRelate;
    }

    public BigDecimal getTotalApplyAmount() {
        return totalApplyAmount;
    }

    public void setTotalApplyAmount(BigDecimal totalApplyAmount) {
        this.totalApplyAmount = totalApplyAmount;
    }

    public BigDecimal getAllowApplyAmount() {
        return allowApplyAmount;
    }

    public void setAllowApplyAmount(BigDecimal allowApplyAmount) {
        this.allowApplyAmount = allowApplyAmount;
    }

    public Boolean getMoneyFlag() {
        return moneyFlag;
    }

    public void setMoneyFlag(Boolean moneyFlag) {
        this.moneyFlag = moneyFlag;
    }
}
