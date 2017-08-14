package com.msk.order.bean.result;

import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * ISO151428_购买记录查询接口
 * Created by wang_shuai on 2016/8/24.
 */
public class ISO151428RestResult extends RestPageResult {
    private List<ISO151428BuyRecordResult> buyRecordList;

    public List<ISO151428BuyRecordResult> getBuyRecordList() {
        return buyRecordList;
    }

    public void setBuyRecordList(List<ISO151428BuyRecordResult> buyRecordList) {
        this.buyRecordList = buyRecordList;
    }
}
