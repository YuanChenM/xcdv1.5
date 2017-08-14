package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrFileBuyerPool;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/8/8.
 */
public class IBR121408RsPageResult extends RsPageResult {

    private List<IBR121408RsBean> houseList;

    public List<IBR121408RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBR121408RsBean> houseList) {
        this.houseList = houseList;
    }
}
