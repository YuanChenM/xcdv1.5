package com.msk.ssc.bean;

import com.hoperun.core.bean.BasePageParam;
import com.hoperun.core.bean.BaseParam;

/**
 *  param
 * <p/>
 * Created by zhao_chen on 2016/06/30.
 */
public class SSC11302Param extends BasePageParam {

    /** 中标确认书ID */
    private Long bidId;

    private Long bidCode;

    private String type;

    private Long detailId;

    private String pdCode;

    private String bidProjectNo;

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidCode() {
        return bidCode;
    }

    public void setBidCode(Long bidCode) {
        this.bidCode = bidCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getBidProjectNo() {
        return bidProjectNo;
    }

    public void setBidProjectNo(String bidProjectNo) {
        this.bidProjectNo = bidProjectNo;
    }
}
