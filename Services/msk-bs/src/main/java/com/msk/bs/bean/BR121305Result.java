package com.msk.bs.bean;

import com.msk.common.bean.RsPageResult;
import com.msk.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by ren_qiang on 2016/8/4.
 */
public class BR121305Result extends RsPageResult { //物流区
    private List<BR121305Bean> ibr121305RsBeanList;

    public List<BR121305Bean> getIbr121305RsBeanList() {
        return ibr121305RsBeanList;
    }

    public void setIbr121305RsBeanList(List<BR121305Bean> ibr121305RsBeanList) {
        this.ibr121305RsBeanList = ibr121305RsBeanList;
    }
}
