package com.msk.buyers.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/7/12.
 */
public class BY121308RsPageResult extends RsPageResult {

    private List<IBY121308RsBean> iby121308RsBeanList;

    public List<IBY121308RsBean> getIby121308RsBeanList() {
        return iby121308RsBeanList;
    }

    public void setIby121308RsBeanList(List<IBY121308RsBean> iby121308RsBeanList) {
        this.iby121308RsBeanList = iby121308RsBeanList;
    }
}
