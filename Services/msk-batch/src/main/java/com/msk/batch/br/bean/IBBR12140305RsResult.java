package com.msk.batch.br.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by yuan_zhifei on 2016/9/18.
 */
public class IBBR12140305RsResult extends RsPageResult {
    //管家列表
    private List<IBBR12140305RsBean> houseList;

    public List<IBBR12140305RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBBR12140305RsBean> houseList) {
        this.houseList = houseList;
    }
}
