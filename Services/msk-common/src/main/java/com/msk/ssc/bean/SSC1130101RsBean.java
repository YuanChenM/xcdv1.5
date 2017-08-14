package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;


/**
 * 参数传递
 */
public class SSC1130101RsBean extends BaseEntity {

    /**招标公司文本框Id*/
    private String purchaserNameId;

    /**中标公司文本框Id*/
    private String supplierNameId;

    /**中标编号文本框Id*/
    private String bidInputId;

    /**中标确认书状态传递,以","分隔*/
    private String bidStatusStatusStr;
    /**判断是否生成合同*/
    private String bidFlag;

    //招标项目编号
    private String bidId;
    //招标项目名称
    private String bidProjectName;

    //招标项目编号
    private String bidProjectNo;

    //中标厂商
    private String bidProducerName;
    //开标日期
    private String bidOpenDate;
    // 开标开始日期
    private String bidStartDate;
    // 开标结束日期
    private String bidEndDate;
    //中标确认书状态
    private String bidStatus;
    //合同id
    private Long contractId;


    public String getPurchaserNameId() {
        return purchaserNameId;
    }

    public void setPurchaserNameId(String purchaserNameId) {
        this.purchaserNameId = purchaserNameId;
    }

    public String getSupplierNameId() {
        return supplierNameId;
    }

    public void setSupplierNameId(String supplierNameId) {
        this.supplierNameId = supplierNameId;
    }

    public String getBidInputId() {
        return bidInputId;
    }

    public void setBidInputId(String bidInputId) {
        this.bidInputId = bidInputId;
    }

    public String getBidStatusStatusStr() {
        return bidStatusStatusStr;
    }

    public void setBidStatusStatusStr(String bidStatusStatusStr) {
        this.bidStatusStatusStr = bidStatusStatusStr;
    }

    public String getBidFlag() {
        return bidFlag;
    }

    public void setBidFlag(String bidFlag) {
        this.bidFlag = bidFlag;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public String getBidProjectName() {
        return bidProjectName;
    }

    public void setBidProjectName(String bidProjectName) {
        this.bidProjectName = bidProjectName;
    }

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }

    public String getBidProducerName() {
        return bidProducerName;
    }

    public void setBidProducerName(String bidProducerName) {
        this.bidProducerName = bidProducerName;
    }

    public String getBidOpenDate() {
        return bidOpenDate;
    }

    public void setBidOpenDate(String bidOpenDate) {
        this.bidOpenDate = bidOpenDate;
    }

    public String getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(String bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public String getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(String bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}
