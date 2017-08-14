package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by whc on 2016/10/21.
 */
public class BS2102126Result extends RsPageResult {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 买家列表
    private List<BS2102126Bean> buyerList;

    public List<BS2102126Bean> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List<BS2102126Bean> buyerList) {
        this.buyerList = buyerList;
    }
}
