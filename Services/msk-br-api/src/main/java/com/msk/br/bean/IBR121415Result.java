package com.msk.br.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/10/24.
 */
public class IBR121415Result extends RsPageResult {
    List<IBR121415RsParam> buyerIdList;

    public List<IBR121415RsParam> getBuyerIdList() {
        return buyerIdList;
    }

    public void setBuyerIdList(List<IBR121415RsParam> buyerIdList) {
        this.buyerIdList = buyerIdList;
    }
}
