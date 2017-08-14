package com.msk.stock.bean;

import com.msk.common.bean.RsPageParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_qiang1 on 2016/5/17.
 */
public class StockRsParamList  extends RsPageParam {

    private List<StockRsParam> pdList=new ArrayList<StockRsParam>();


    public List<StockRsParam> getPdList() {
        return pdList;
    }

    public void setPdList(List<StockRsParam> pdList) {
        this.pdList = pdList;
    }
}
