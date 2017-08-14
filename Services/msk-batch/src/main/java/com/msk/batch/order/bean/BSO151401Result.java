package com.msk.batch.order.bean;

import com.msk.core.entity.BaseEntity;
import com.msk.core.entity.SoSalesRanking;

import java.util.List;

/**
 * Created by liutao on 2016/9/8.
 */
public class BSO151401Result extends BaseEntity {
    List<SoSalesRanking> sellers;

    public List<SoSalesRanking> getSellers() {
        return sellers;
    }

    public void setSellers(List<SoSalesRanking> sellers) {
        this.sellers = sellers;
    }
}
