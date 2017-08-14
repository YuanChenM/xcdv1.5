package com.msk.buyers.bean;

import com.hoperun.core.bean.BasePageParam;
import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by tao_zhifa on 2016/7/5.
 */
public class BY121309Param extends BasePageParam{
    private String buyerId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
