package com.msk.order.bean.result;


import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * 卖家，买手，管家快捷信息查询接口返回结果
 * Created by wang_jianzhou on 2016/8/22.
 */
public class ISO151423RestResult extends BaseResult{

    private List<ISO151423OrdersRestResult> orders;

    public List<ISO151423OrdersRestResult> getOrders() {
        return orders;
    }

    public void setOrders(List<ISO151423OrdersRestResult> orders) {
        this.orders = orders;
    }
}
