package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151407_买家平台下单数量统计
 * Created by sun_jiaju on 2016/8/24.
 */
public class ISO151407RestResult extends BaseResult {
    // 买家各下单方式订单数量合计
    private List<ISO151407RestOrderResult> orderCountInfoList;

    public List<ISO151407RestOrderResult> getOrderCountInfoList() {
        return orderCountInfoList;
    }

    public void setOrderCountInfoList(List<ISO151407RestOrderResult> orderCountInfoList) {
        this.orderCountInfoList = orderCountInfoList;
    }
}
