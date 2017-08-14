package com.msk.ssc.bean;

import com.msk.core.entity.SscProducePdControl;

import java.math.BigDecimal;

/**
 * Created by liu_yan2 on 2016/8/8.
 */
public class SSC11326RsBean  extends SscProducePdControl{

    /** 合同编号 */
    private Long contractId;

    /** 合同编码 */
    private String contractCode;

    /** 合同名称 */
    private String contractName;

    /** 合同生效日期 */
    private String contractActDate;

    /** 最迟交货日期 */
    private String lastDeliveryDate;

    /** 实际生产开始日期（可以早于合同生效日期） */
    private String realProduceStartDate;

    /** 实际生产结束日期（可以晚于最迟交货日期） */
    private String realProduceEndDate;

    /** 计划生产日期 */
    private String produceDateStr;

    /** 产品名称 */
    private String pdName;

    /** 计划日生产量合计 */
    private BigDecimal totalPlanNum;

    /** 实际日生产量合计 */
    private BigDecimal totalRealNum;

    /** 是否可以修改 */
    private boolean readonly;

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

    public String getContractActDate() {
        return contractActDate;
    }

    public void setContractActDate(String contractActDate) {
        this.contractActDate = contractActDate;
    }

    public String getLastDeliveryDate() {
        return lastDeliveryDate;
    }

    public void setLastDeliveryDate(String lastDeliveryDate) {
        this.lastDeliveryDate = lastDeliveryDate;
    }

    public String getRealProduceStartDate() {
        return realProduceStartDate;
    }

    public void setRealProduceStartDate(String realProduceStartDate) {
        this.realProduceStartDate = realProduceStartDate;
    }

    public String getRealProduceEndDate() {
        return realProduceEndDate;
    }

    public void setRealProduceEndDate(String realProduceEndDate) {
        this.realProduceEndDate = realProduceEndDate;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getProduceDateStr() {
        return produceDateStr;
    }

    public void setProduceDateStr(String produceDateStr) {
        this.produceDateStr = produceDateStr;
    }

    public BigDecimal getTotalPlanNum() {
        return totalPlanNum;
    }

    public void setTotalPlanNum(BigDecimal totalPlanNum) {
        this.totalPlanNum = totalPlanNum;
    }

    public BigDecimal getTotalRealNum() {
        return totalRealNum;
    }

    public void setTotalRealNum(BigDecimal totalRealNum) {
        this.totalRealNum = totalRealNum;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}
