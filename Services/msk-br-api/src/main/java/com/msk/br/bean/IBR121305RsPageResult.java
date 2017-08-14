package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by zhao_chen on 2016/7/18.
 */
public class IBR121305RsPageResult extends RsPageResult {
    private List<IBR121305RsBean> ibr121305RsBeanList;

    public List<IBR121305RsBean> getIbr121305RsBeanList() {
        return ibr121305RsBeanList;
    }

    public void setIbr121305RsBeanList(List<IBR121305RsBean> ibr121305RsBeanList) {
        this.ibr121305RsBeanList = ibr121305RsBeanList;
    }
}
