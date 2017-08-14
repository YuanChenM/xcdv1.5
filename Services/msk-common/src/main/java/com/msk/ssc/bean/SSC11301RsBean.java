package com.msk.ssc.bean;

import com.msk.core.entity.SscBidBasicInfo;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/6/28.
 */
public class SSC11301RsBean extends SscBidBasicInfo {

    List<SSC11301RsBean> sscBidBasicInfoList;

    /** 卖家编码 */
    private java.lang.String slCode;
    /**
     * 开标日期
     */
    private String openDate;
    /**
     * 开标开始日期
     */
    private String startDate;
    /**
     * 开标结束日期
     */
    private String endDate;

    private int resultLine;

    private String type;

    private String bidCode;

    /** 合同ID */
    private Long contractId;

    public List<SSC11301RsBean> getSscBidBasicInfoList() {
        return sscBidBasicInfoList;
    }

    public void setSscBidBasicInfoList(List<SSC11301RsBean> sscBidBasicInfoList) {
        this.sscBidBasicInfoList = sscBidBasicInfoList;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSlCode() {
        return slCode;
    }

    public void setSlCode(String slCode) {
        this.slCode = slCode;
    }

    public int getResultLine() {
        return resultLine;
    }

    public void setResultLine(int resultLine) {
        this.resultLine = resultLine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBidCode() {
        return bidCode;
    }

    public void setBidCode(String bidCode) {
        this.bidCode = bidCode;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

}
