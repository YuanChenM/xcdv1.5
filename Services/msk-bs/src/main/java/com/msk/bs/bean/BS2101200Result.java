package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrBuyerPool;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhu_kai1 on 2016/8/24.
 */
public class BS2101200Result extends RsPageResult implements Serializable {

 private  List<BS2101200RsBean> brBuyerPoolList;

    public List<BS2101200RsBean> getBrBuyerPoolList() {
        return brBuyerPoolList;
    }

    public void setBrBuyerPoolList(List<BS2101200RsBean> brBuyerPoolList) {
        this.brBuyerPoolList = brBuyerPoolList;
    }
}
