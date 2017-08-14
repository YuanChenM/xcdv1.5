package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * 查询公共买家池买家信息接口
 * Created by tao_zhifa on 2016/8/19.
 */
public class IBR121308RsPageResult extends RsPageResult {
    private List<IBR121308RsBean> buyerList;


    public List<IBR121308RsBean> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List<IBR121308RsBean> buyerList) {
        this.buyerList = buyerList;
    }
}
