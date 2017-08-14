package com.msk.order.bean.result;

import com.msk.common.bean.result.RestPageResult;

import java.util.List;

/**
 * Created by liu_tao2 on 2016/8/8.
 */
public class ISO151416OrderSearchResult extends RestPageResult {
    private List<ISO151416SearchResult> orders;

    public List<ISO151416SearchResult> getOrders() {
        return orders;
    }

    public void setOrders(List<ISO151416SearchResult> orders) {
        this.orders = orders;
    }
}
