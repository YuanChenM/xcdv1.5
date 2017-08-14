package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151434_获取上半月分销量接口
 * Created by chu_jian on 2016/8/3.
 */
public class ISO151434RestResult extends BaseResult {
    /*产品List*/
    private List<ISO151434SalesRestResult> salesList;


    public List<ISO151434SalesRestResult> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<ISO151434SalesRestResult> salesList) {
        this.salesList = salesList;
    }
}
