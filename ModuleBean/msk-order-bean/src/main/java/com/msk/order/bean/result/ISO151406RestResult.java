package com.msk.order.bean.result;

import com.msk.common.bean.result.BaseResult;

import java.util.List;

/**
 * ISO151406RestResult接口返回结果信息.
 * Created by sun_jiaju on 2016/10/10.
 */
public class ISO151406RestResult extends BaseResult {
    private List<ISO151406HouseAccountSalesResult> salesList;

    public List<ISO151406HouseAccountSalesResult> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<ISO151406HouseAccountSalesResult> salesList) {
        this.salesList = salesList;
    }
}
