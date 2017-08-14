package com.msk.order.bean.param;

import com.msk.common.bean.param.BaseParam;

/**
 * ISO151434_获取上半月分销量接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151434RestParam extends BaseParam {
    private int orderSource;
    private String thisMonth;

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public String getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(String thisMonth) {
        this.thisMonth = thisMonth;
    }
}
