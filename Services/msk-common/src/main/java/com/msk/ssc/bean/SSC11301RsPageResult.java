package com.msk.ssc.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/12.
 */
public class SSC11301RsPageResult extends RsPageResult {

    private List<SSC11301RsBean> SSC11301RsBean;

    private int line;

    private Long bidId;

    private String bidProjectNo;

    public List<SSC11301RsBean> getSSC11301RsBean() {
        return SSC11301RsBean;
    }

    public void setSSC11301RsBean(List<SSC11301RsBean> SSC11301RsBean) {
        this.SSC11301RsBean = SSC11301RsBean;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

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
}
