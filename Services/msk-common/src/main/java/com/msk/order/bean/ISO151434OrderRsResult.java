package com.msk.order.bean;

import com.msk.common.bean.RsPageResult;

import java.util.List;

/**
 * Created by jiang_tengfei on 2016/5/23.
 */
public class ISO151434OrderRsResult extends RsPageResult{

    //查询结果集合
    private List<ISO151434RsResult> iso151434RsResultList;

    public List<ISO151434RsResult> getIso151434RsResultList() { return iso151434RsResultList; }

    public void setIso151434RsResultList(List<ISO151434RsResult> iso151434RsResultList) { this.iso151434RsResultList = iso151434RsResultList; }
}
