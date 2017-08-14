package com.msk.inventory.bean;

import com.msk.comm.bean.result.RestPageResult;

import java.util.List;

/**
 * Created by wang_fan2 on 2016/8/23.
 */
public class ISO152413ResultBean extends RestPageResult{

    /** 产品库存集合 */
    private List<ISO152413PdStockResultBean> pdStockList;

    public List<ISO152413PdStockResultBean> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<ISO152413PdStockResultBean> pdStockList) {
        this.pdStockList = pdStockList;
    }
}
