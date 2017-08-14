package com.msk.stock.bean;


import com.msk.common.bean.RsPageParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author zhang_qiang1
 */
public class ISO151433RsParam  {
     private List<StockRsParam> pdList=new ArrayList<StockRsParam>();


    public List<StockRsParam> getPdList() {
        return pdList;
    }

    public void setPdList(List<StockRsParam> pdList) {
        this.pdList = pdList;
    }
}
