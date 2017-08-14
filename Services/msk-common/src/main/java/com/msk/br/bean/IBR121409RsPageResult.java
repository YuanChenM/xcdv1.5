package com.msk.br.bean;


import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BrFileBuyerPool;

import java.util.List;

/**
 * 冻品管家分销分类买家池营销期公众买家分池买家注册管控
 * Created by tao_zhifa on 2016/8/8.
 */
public class IBR121409RsPageResult extends RsPageResult {


    private List<IBR121409RsBean> houseList;


    public List<IBR121409RsBean> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<IBR121409RsBean> houseList) {
        this.houseList = houseList;
    }
}
