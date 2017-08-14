package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by yang_chunyan on 2016/8/4.
 */
public class IBS121306RsPageResult extends RsPageResult {

    List<IBS121306RsBean> ibr121306RsBeanList;

    public List<IBS121306RsBean> getIbr121306RsBeanList() {
        return ibr121306RsBeanList;
    }

    public void setIbr121306RsBeanList(List<IBS121306RsBean> ibr121306RsBeanList) {
        this.ibr121306RsBeanList = ibr121306RsBeanList;
    }
}
