package com.msk.so.bean;

import com.msk.common.bean.RsPageParam;

import java.util.Date;

/**
 * ISO153202Param
 * yang_yang
 */
public class ISO153104Param extends RsPageParam{

    private static final long serialVersionUID = 1L;

    private Date startTime;

    private Date endTime;

    private String businessMainId;

    private String settlementFlg;

    private String[] settlementFlgs;

    private String remark;

    /** 查询类型输入项  1 分页  2 详情 */
    private Integer queryFlag;

    /**  卖家计费单ID */
    private Integer sellerBillId;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBusinessMainId() {
        return businessMainId;
    }

    public void setBusinessMainId(String businessMainId) {
        this.businessMainId = businessMainId;
    }

    public String getSettlementFlg() {
        return settlementFlg;
    }

    public void setSettlementFlg(String settlementFlg) {
        this.settlementFlg = settlementFlg;
    }

    public String[] getSettlementFlgs() {
        return settlementFlgs;
    }

    public void setSettlementFlgs(String[] settlementFlgs) {
        this.settlementFlgs = settlementFlgs;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(Integer queryFlag) {
        this.queryFlag = queryFlag;
    }

    public Integer getSellerBillId() {
        return sellerBillId;
    }

    public void setSellerBillId(Integer sellerBillId) {
        this.sellerBillId = sellerBillId;
    }
}
