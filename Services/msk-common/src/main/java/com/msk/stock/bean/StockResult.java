package com.msk.stock.bean;

import com.msk.common.bean.RsPageResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/5/11.
 */
public class StockResult extends RsPageResult {
    private Integer effectResultCount;// 修改有效返回值

    List<Stock>pdStockList=new ArrayList<>();



    public List<Stock> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<Stock> pdStockList) {
        this.pdStockList = pdStockList;
    }


    public Integer getEffectResultCount() {
        return effectResultCount;
    }

    public void setEffectResultCount(Integer effectResultCount) {
        this.effectResultCount = effectResultCount;
    }

}
