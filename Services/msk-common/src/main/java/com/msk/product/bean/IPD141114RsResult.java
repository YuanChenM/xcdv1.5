package com.msk.product.bean;

import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * 物流区接口
 * Created by Administrator on 2016/1/26.
 */
public class IPD141114RsResult extends BaseEntity {
    //返回了list集合
    private List<IPD14111401RsResult> logiAreaList;

    public List<IPD14111401RsResult> getLogiAreaList() {
        return logiAreaList;
    }

    public void setLogiAreaList(List<IPD14111401RsResult> logiAreaList) {
        this.logiAreaList = logiAreaList;
    }

}
