package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 中标确认书画面param
 * <p/>
 * Created by yuan_zhifei on 2016/06/28.
 */
public class SSC11301Param extends BasePageParam {
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

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}
