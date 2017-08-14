package com.msk.ssc.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;


public class SSC1130204RsBean extends BaseEntity {

    /**中标id*/
    private Long bidId ;
    /**中标编号*/
    private String bidProjectNo ;
    /**中标列表*/
    private List<String> bidList;
    /**flag*/
    private String bidFlag;




    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }

    public List<String> getBidList() {
        return bidList;
    }

    public void setBidList(List<String> bidList) {
        this.bidList = bidList;
    }

    public String getBidFlag() {
        return bidFlag;
    }

    public void setBidFlag(String bidFlag) {
        this.bidFlag = bidFlag;
    }
}
