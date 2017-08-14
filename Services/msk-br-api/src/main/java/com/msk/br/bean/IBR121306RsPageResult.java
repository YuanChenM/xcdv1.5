package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrFileBuyerPool;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/18.
 */
public class IBR121306RsPageResult extends RsPageResult {

    List<IBR121306RsBean> ibr121306RsBeanList;

    public List<IBR121306RsBean> getIbr121306RsBeanList() {
        return ibr121306RsBeanList;
    }

    public void setIbr121306RsBeanList(List<IBR121306RsBean> ibr121306RsBeanList) {
        this.ibr121306RsBeanList = ibr121306RsBeanList;
    }
}
