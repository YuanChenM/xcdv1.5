package com.msk.buyers.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by zhu_kai1 on 2016/6/20.
 */
public class IBY121224Result extends BaseEntity {
   private List<IBY121223Result> buyerShopList;

    public List<IBY121223Result> getBuyerShopList() {
        return buyerShopList;
    }

    public void setBuyerShopList(List<IBY121223Result> buyerShopList) {
        this.buyerShopList = buyerShopList;
    }
}
