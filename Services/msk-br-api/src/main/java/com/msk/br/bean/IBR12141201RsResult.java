package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by zhang_jian4 on 2016/10/18.
 */
public class IBR12141201RsResult  extends RsPageResult {
    //买手买家列表
    private List<IBR12141201RsBean> slBuyerList;

    public List<IBR12141201RsBean> getSlBuyerList() {
        return slBuyerList;
    }

    public void setSlBuyerList(List<IBR12141201RsBean> slBuyerList) {
        this.slBuyerList = slBuyerList;
    }
}
