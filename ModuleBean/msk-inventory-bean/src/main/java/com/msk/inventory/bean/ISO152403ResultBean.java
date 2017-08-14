package com.msk.inventory.bean;

import com.msk.comm.bean.result.RestPageResult;

import java.util.List;

/**
 * Created by zheng_xu on 2016/8/23.
 */
public class ISO152403ResultBean extends RestPageResult {
    private List<ISO152403StockResultBean> pdStockList;

    public List<ISO152403StockResultBean> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<ISO152403StockResultBean> pdStockList) {
        this.pdStockList = pdStockList;
    }

}
