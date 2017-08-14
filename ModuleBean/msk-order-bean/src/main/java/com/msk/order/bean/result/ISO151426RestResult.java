package com.msk.order.bean.result;

import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * ISO151426_管家订单查询接口
 * Created by wang_shuai on 2016/8/22.
 */
public class ISO151426RestResult extends RestPageResult {
    private List<ISO151426OrderRestResult> orders;

    public List<ISO151426OrderRestResult> getOrders() {
        return orders;
    }

    public void setOrders(List<ISO151426OrderRestResult> orders) {
        this.orders = orders;
    }
}
