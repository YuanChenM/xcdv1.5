package com.msk.stock.bean;

import com.msk.common.bean.RsPageResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author zhang_qiang1
 */
public class ISO151433RsResult  {

    private Integer totalCount;
    List<Stock>pdStockList=new ArrayList<>();

    public List<Stock> getPdStockList() {
        return pdStockList;
    }

    public void setPdStockList(List<Stock> pdStockList) {
        this.pdStockList = pdStockList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
