package com.msk.order.bean.result;

import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * ISO151427_结算详情查询接口
 * Created by wang_shuai on 2016/8/23.
 */
public class ISO151427RestResult extends RestPageResult {
    private List<ISO151427SettlementDetailResult> resultList;

    public List<ISO151427SettlementDetailResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ISO151427SettlementDetailResult> resultList) {
        this.resultList = resultList;
    }
}
